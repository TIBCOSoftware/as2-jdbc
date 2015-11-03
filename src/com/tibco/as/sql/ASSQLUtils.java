// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;
import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.as.space.browser.BrowserDef.TimeScope;

/**
 * ASSQLUtils contains utility methods for converting from SQL to ActiveSpaces
 *
 */
public class ASSQLUtils
{
    public static final String COLUMN_NAME     = "columnName";
    public static final String COLUMN_ALIAS    = "columnAlias";
    public static final String COLUMN_FUNCTION = "columnFunction";
    public static final String TABLE_NAME      = "tableName";
    public static final String METASPACE_NAME  = "metaspaceName";
    public static final String COLUMN_TYPE     = "columnType";
    public static final String COLUMN_VALUE    = "columnValue";
    public static final String COLUMN_NULLABLE = "columnNullable";
    public static final String NULL_CONSTRAINT = "nullConstraint";
    public static final String PKEY_CONSTRAINT = "pkeyConstraint";
    public static final String PKEY_TYPE       = "pkeyType";
    public static final String TABLE_CORRELATION_NAME = "tableCorrelationName";

    public static HashMap<String, FieldDef> getColumnSpec (Metaspace metaspace, List<Tuple> columnInfo)
            throws ASException
    {
        HashMap<String, FieldDef> columnSpec = new HashMap<String, FieldDef>();
        int csize = columnInfo.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple cinfo = columnInfo.get(i);
            String cname = cinfo.getString(ASSQLUtils.COLUMN_NAME);
            String calias = cinfo.getString(ASSQLUtils.COLUMN_ALIAS);
            String tname = cinfo.getString(ASSQLUtils.TABLE_NAME);
            String fname = cinfo.getString(ASSQLUtils.COLUMN_FUNCTION);
            if (fname == null || fname.isEmpty())
            {
                SpaceDef spaceDef = metaspace.getSpaceDef(tname);
                // store the FieldDef under the alias since columns from two
                // different tables could have the same names, but the alias
                // should uniquely identify them
                columnSpec.put(calias, spaceDef.getFieldDef(cname));
            }
            else
            {
                if (fname.toLowerCase().equals("count"))
                {
                    // we are returning the count of the field or space
                    // so create an appropriate FieldDef for returning a count
                    // we use the column alias for the field name as we will use
                    // the column alias to retrieve the field def
                    FieldDef fdef = FieldDef.create(calias, FieldDef.FieldType.LONG);
                    fdef.setNullable(true);
                    columnSpec.put(calias,  fdef);
                }
            }
        }
        return columnSpec;
    }

    public static List<Tuple> getColumnInfo (String metaspaceName, String spaceName, List<String> columnNames)
    {
        List<Tuple> result = new ArrayList<Tuple>();

        int csize = columnNames.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple tuple = Tuple.create();
            String columnName = stripDotPredicate(columnNames.get(i));
            tuple.put(ASSQLUtils.COLUMN_NAME, columnName);
            tuple.put(ASSQLUtils.TABLE_NAME, spaceName);
            tuple.put(ASSQLUtils.METASPACE_NAME, metaspaceName);
            tuple.put(ASSQLUtils.COLUMN_ALIAS, columnName);
            result.add(tuple);
        }
        return result;
    }

    protected static String stripDotPredicate (String columnName)
    {
        String result = columnName;
        int dot = columnName.indexOf(".");
        if (dot != -1)
        {
            result = columnName.substring(dot + 1);
        }
        return result;
    }

    public static Browser getTakeBrowser (Space space, String whereClause) throws ASException
    {
        // use timescope of ALL for take browsers
        return (whereClause == null) ? space.browse(BrowserType.TAKE, BrowserDef.create(500, TimeScope.ALL))
                : space.browse(BrowserType.TAKE, BrowserDef.create(500, TimeScope.ALL), whereClause.toString());
    }

    public static List<String> getColumnNames (Space space) throws ASException
    {
        List<String> columnNames = new ArrayList<String>();
        Iterator<FieldDef> iter = space.getSpaceDef().getFieldDefs().iterator();
        while (iter.hasNext())
        {
            columnNames.add(iter.next().getName());
        }
        return columnNames;
    }

    public static HashMap<String, String> getKeyValues(Collection<String> keyfields, String filter)
    {
        // Parse the given filter string looking for field assignments which match the
        // given key fields. If we find any mismatches, we want to return null as a mismatch
        // means we cannot do a get on the field since it is not a key field.
        // Note: we only handle filters with AND, filters with OR can be more complex
        //       so let a browser handle them for now (e.g. name = 'john' OR name = 'gary')
        HashMap<String, String> result = null;
        if (keyfields == null || keyfields.isEmpty() || filter == null || filter.isEmpty())
            return result;

        // we compare to uppercase AND here because AS filters expect uppercase
        // the grammar takes care of converting lowercase 'and' to uppercase if needed
        if (filter.contains(" AND ") && keyfields.size() == 1)
        {
            // we have possible multiple assignments but only one key field
            return result;
        }

        String newFilter = filter;
        boolean continueParsing = true;
        while (newFilter.contains(" AND "))
        {
            // we have possible multiple assignments
            // extract the assignment before the first occurrance of AND
            int endIndex = newFilter.indexOf(" AND ");
            String assignment = newFilter.substring(0, endIndex).trim();
            // set newFilter to the remaining substring after the and
            newFilter = newFilter.substring(endIndex + 5);
            // now parse the assignment string and see if it matches a key
            HashMap<String, String> fieldSetting = parseAssignment(assignment);
            if (fieldSetting == null || fieldSetting.isEmpty())
            {
                // we didn't have a field assignment, so cannot do a get on the field
                if (result != null)
                    result.clear();
                result = null;
                continueParsing = false;
                break;
            }

            // now see if the parsed field name matches a key field, if so we
            // return the field information as the key field info
            String fieldName = fieldSetting.keySet().iterator().next();
            String fieldValue = fieldSetting.get(fieldName);
            Iterator<String> keyFieldNames = keyfields.iterator();
            while (keyFieldNames.hasNext())
            {
                String keyField = keyFieldNames.next();
                if (keyField.equals(fieldName))
                {
                    if (result == null)
                        result = new HashMap<String, String>();
                    result.put(fieldName, fieldValue);
                    break;
                }
            }
        }
        // still have to parse the last substring

        // now we either have our original filter which possibly contained only
        // one assignment or we had a multiple assignment filter and we still
        // have to parse the last substring
        if (continueParsing)
        {
            // check to see if we have a single assignment in the filter
            // if so, parse out the field name and value
            HashMap<String, String> fieldSetting = parseAssignment(newFilter);
            if (fieldSetting != null && !fieldSetting.isEmpty())
            {
                String fieldName = fieldSetting.keySet().iterator().next();
                String fieldValue = fieldSetting.get(fieldName);
                // now see if the field name matches a key field, if so we
                // return the field information as the key field info
                Iterator<String> keyFieldNames = keyfields.iterator();
                while (keyFieldNames.hasNext())
                {
                    String keyField = keyFieldNames.next();
                    if (keyField.equals(fieldName))
                    {
                        if (result == null)
                            result = new HashMap<String, String>();
                        result.put(fieldName, fieldValue);
                        break;
                    }
                }
            }

        }
        return result;
    }

    public static HashMap<String, String> parseAssignment (String inStr)
    {
        // this method looks to see if we are given an assignment string
        // (e.g. fieldname = field value)
        // if so, we parse out the field name and its value and return it in the HashMap
        HashMap<String, String> result = null;
        int index = inStr.indexOf("=");
        if (index != -1)
        {
            // ensure we don't have a multiple assignment string
            int lastIndex = inStr.lastIndexOf("=");
            if (index == lastIndex)
            {
                String fieldName = inStr.substring(0, index).trim();
                String fieldValue = inStr.substring(index+1).trim();
                result = new HashMap<String, String>();
                result.put(fieldName, fieldValue);
            }
        }
        return result;
    }
}
