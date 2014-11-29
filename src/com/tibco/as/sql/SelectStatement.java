// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.tibco.as.jdbc.ASConnection;
import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;
import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.as.space.browser.BrowserDef.TimeScope;
import com.tibco.as.space.impl.data.ASSpaceResult;
import com.tibco.as.space.impl.data.ASSpaceResultList;
import com.tibco.as.space.impl.data.ASTuple;

public class SelectStatement implements ASSQLQueryStatement
{
    protected List<String>              m_spaceNames;
    protected List<Tuple>               m_columnInfo;
    protected HashMap<String, FieldDef> m_columnSpec;
    protected String                    m_filter;
    protected String                    m_quantifier;
    protected List<Tuple>               m_tableInfo;

    public SelectStatement (String quantifier, List<Tuple> columnInfo, List<Tuple> tableInfo, String filter)
    {
        m_quantifier = quantifier;
        m_columnInfo = columnInfo;
        m_tableInfo = tableInfo;
        m_filter = filter;
        // remove any table name prefixes from the filter
        // AS filters only work on a single space
        // if later we end up supporting queries on multiple spaces, this is the place
        // to parse the filters
        checkFilterForTableNames();
        // uncomment the following to help debugging filters
        //if (m_filter != null)
        //    System.out.println("filter: " + m_filter);
    }

    public ASSQLResult processQuery (java.sql.Statement jdbcStatement, Metaspace metaspace) throws SQLException
    {
        ASSQLResult result = null;
        try
        {
            populateSpaceNames();
            checkColumnsForTableCorrelationNames();

            // check to make sure we have space info for each column info entry
            checkSpaceInfo();

            // For count function, set * column name to $Asterisk as we want to
            // create a FieldDef for the column and * is not a valid character
            // for a field name. Also set the column alias appropriately.
            // Otherwise, if we have * for the columns, replace with all of the
            // columns in the space.
            m_columnInfo = checkColumnAsterisk(metaspace);

            // now initialize our map of column aliases and FieldDefs
            m_columnSpec = ASSQLUtils.getColumnSpec(metaspace, m_columnInfo);

            // handle count function first
            result = processCount(metaspace);

            int ssize = m_spaceNames.size();
            if (result == null && ssize == 1 && m_filter != null)
            {
                // if we have a single space and a filter, check to see if we can do a simple
                // get of the data in the space versus browsing the space
                ((ASConnection)jdbcStatement.getConnection()).getLogger().logTrace("SelectStatement.processQuery(): Trying to 'get' space data...");
                SpaceResultList tuples = getTupleList(metaspace, m_spaceNames.get(0), m_filter, m_columnSpec);
                if (tuples != null && !tuples.isEmpty())
                {
                    ((ASConnection)jdbcStatement.getConnection()).getLogger().logTrace("SelectStatement.processQuery(): Creating ASSQLResult...");
                    result = new ASSQLResult(m_spaceNames, tuples, m_columnInfo, m_columnSpec);
                    ((ASConnection)jdbcStatement.getConnection()).getLogger().logTrace("SelectStatement.processQuery(): Returning ASSQLResult...");
                }
            }
            if (result == null)
            {
                // for each space, get a browser for the space which retrieves the
                // appropriate rows from the space
                List<Browser> blist = new ArrayList<Browser>();
                // By default, use a SNAPSHOT browser with no query limit
                TimeScope tscope = TimeScope.SNAPSHOT;
                long qlimit = -1;  // no limit
                int max_rows = jdbcStatement.getMaxRows();
                if (max_rows > 0)
                {
                    qlimit = max_rows;
                }
                long fetchSize = 5000;  // use a default pre-fetch size of 5000 unless user specifies a fetch size
                int fsize = jdbcStatement.getFetchSize();
                // if fetch size is 0, the fetch size hint should be ignored
                if (fsize > 0)
                {
                    fetchSize = fsize;
                }
                else if (fsize == -1)
                {
                    // special fetch size setting used to indicate a CURRENT browser should be used
                    // a CURRENT browser doesn't prefetch any data
                    tscope = TimeScope.CURRENT;
                }
                for (int i = 0; i < ssize; i++)
                {
                    String spaceName = m_spaceNames.get(i);
                    //Space space = metaspace.getSpace(spaceName);
                    BrowserDef bdef = BrowserDef.create(BrowserDef.NO_WAIT, tscope, BrowserDef.DistributionScope.ALL);
                    // current browser ignores the query limit but we set it
                    // regardless of the browser type as it doesn't hurt
                    bdef.setQueryLimit(qlimit);
                    bdef.setPrefetch(fetchSize);

                    // if we have a filter, use for both spaces
                    // e.g. select * from table1, table2 where value > 500
                    // we don't support parsing filters at this time
                    // user must use separate queries to apply filters to different tables
                    String bmsg = String.format("SelectStatement.processQuery(): Retrieving browser...\tTimeScope: %s\tPrefetch: %d\tQuery Limit: %d\n", tscope, fetchSize, qlimit);
                    ((ASConnection)jdbcStatement.getConnection()).getLogger().logTrace(bmsg);
                    Browser browser = metaspace.browse(spaceName, BrowserType.GET, bdef, m_filter);
                    //Browser browser = space.browse(BrowserType.GET, bdef, m_filter);
                    blist.add(browser);
                }
                // now store the results
                ((ASConnection)jdbcStatement.getConnection()).getLogger().logTrace("SelectStatement.processQuery(): Creating ASSQLResult...");
                result = new ASSQLResult(m_spaceNames, blist, m_columnInfo, m_columnSpec);
                ((ASConnection)jdbcStatement.getConnection()).getLogger().logTrace("SelectStatement.processQuery(): Returning ASSQLResult...");
            }
        }
        catch (ASException ex)
        {
            throw new SQLException(ex);
        }

        return result;
    }

    protected void populateSpaceNames() throws ASException
    {
        if (m_tableInfo == null || m_tableInfo.isEmpty())
        {
            throw new ASException(ASStatus.NOT_FOUND, "Missing list of tables for SELECT statement.");
        }
        m_spaceNames = new ArrayList<String>();
        int numTables = m_tableInfo.size();
        for (int i = 0;i < numTables; i++)
        {
            // retrieve the table name and store it into the space name list
            String tname = m_tableInfo.get(i).getString(ASSQLUtils.TABLE_NAME);
            m_spaceNames.add(tname);
        }

    }

    protected void checkColumnsForTableCorrelationNames() throws ASException
    {
        if (m_tableInfo == null || m_tableInfo.isEmpty())
        {
            throw new ASException(ASStatus.NOT_FOUND, "Missing list of tables for SELECT statement.");
        }
        if (m_columnInfo == null || m_columnInfo.isEmpty())
        {
            throw new ASException(ASStatus.NOT_FOUND, "Missing list of columns for SELECT statement.");
        }
        // check the table names for each column and replace any correlation names
        // with their real table names
        int tsize = m_tableInfo.size();
        int csize = m_columnInfo.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple cinfo = m_columnInfo.get(i);
            String tname = cinfo.getString(ASSQLUtils.TABLE_NAME);
            if (tname != null && !tname.isEmpty())
            {
                for (int j = 0; j < tsize; j++)
                {
                    String correlationName = m_tableInfo.get(j).getString(ASSQLUtils.TABLE_CORRELATION_NAME);
                    if (correlationName != null && !correlationName.isEmpty() && tname.equals(correlationName))
                    {
                        String actualTableName = m_tableInfo.get(j).getString(ASSQLUtils.TABLE_NAME);
                        cinfo.put(ASSQLUtils.TABLE_NAME, actualTableName);
                        // uncomment the following to help with debugging table correlation names
                        //if (m_columnInfo.get(i).getString(ASSQLUtils.TABLE_NAME).equals(actualTableName))
                        //    System.out.println("Table correlation name updated.");
                    }
                }
            }
        }

    }

    protected void checkFilterForTableNames()
    {
        if (m_filter == null || m_filter.isEmpty())
        {
            return;
        }
        if (m_tableInfo == null || m_tableInfo.isEmpty())
        {
            return;  // just return, error will be caught later
        }
        // Check the filter for any table names or table correlation names
        // and remove them from the filter. AS filters only work on a single space.
        // Could have done this in the grammar but more flexible this way if the driver ends
        // up applying filters to more than one space in the future.
        String tfilter = m_filter;
        int tsize = m_tableInfo.size();
        for (int i = 0; i < tsize; i++)
        {
            String actualTableName = m_tableInfo.get(i).getString(ASSQLUtils.TABLE_NAME);
            if (actualTableName != null && !actualTableName.isEmpty())
            {
                String tablePrefix = actualTableName + ".";
                if (tfilter.contains(tablePrefix))
                    tfilter = tfilter.replace(tablePrefix, "");
            }
            String correlationName = m_tableInfo.get(i).getString(ASSQLUtils.TABLE_CORRELATION_NAME);
            if (correlationName != null && !correlationName.isEmpty())
            {
                String tablePrefix = correlationName + ".";
                if (tfilter.contains(tablePrefix))
                    tfilter = tfilter.replace(tablePrefix, "");
            }
        }
        m_filter = tfilter;
    }

    protected void checkSpaceInfo () throws ASException
    {
        // start building up the information needed for each column's metadata
        // for each column we need: database name, table name, column name,
        // column alias (optional), column field def
        // if we don't have a column alias, use the column name for the column
        // alias
        if (m_columnInfo == null || m_columnInfo.isEmpty())
        {
            throw new ASException(ASStatus.NOT_FOUND, "Missing list of columns for SELECT statement.");
        }

        // ensure each column already has an associated table name
        int csize = m_columnInfo.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple cinfo = m_columnInfo.get(i);
            String cname = cinfo.getString(ASSQLUtils.COLUMN_NAME);
            String tname = cinfo.getString(ASSQLUtils.TABLE_NAME);
            if (tname == null || tname.isEmpty())
            {
                if (m_spaceNames.isEmpty())
                    throw new ASException(ASStatus.NOT_FOUND, "Cannot find space name for column: "
                            + cinfo.getString(ASSQLUtils.COLUMN_NAME));
                else
                {
                    // for each column listed without a table name, we want to
                    // give it a table name from our table name list
                    // if more than one table is in the table name list, add
                    // entries to the column info list for each table after the first table
                    // for SELECT * FROM table1, table2, ... we want to end up
                    // with entries equivalent to table1.*, table2.*, ... in our column info
                    // for SELECT user FROM table1, table2, ... we do the same
                    // as for * but the result set will not be able to retrieve anything
                    // but the first user and table (we do what the user is asking even though it
                    // is not correct)
                    int ssize = m_spaceNames.size();
                    for (int j = 0; j < ssize; j++)
                    {
                        String sname = m_spaceNames.get(j);
                        // the first time thru, replace the existing tuple
                        if (j == 0)
                        {
                            cinfo.put(ASSQLUtils.TABLE_NAME, sname);
                            m_columnInfo.set(i, cinfo);
                        }
                        else
                        {
                            // for any subsequent times thru, add new tuples to the existing list
                            // so if we had * for the column name with FROM table1, table2, ...
                            // we end up with additional * entries for table2, ...
                            Tuple tuple = (Tuple) cinfo.clone();
                            tuple.put(ASSQLUtils.TABLE_NAME, sname);
                            m_columnInfo.add(tuple);
                        }
                    }
                }
            }
            else
            {
                // ensure table name is in the space name list
                if (!m_spaceNames.contains(tname))
                {
                    throw new ASException(ASStatus.NOT_FOUND, "Cannot find matching FROM table for column: " + tname
                            + "." + cname);
                }
            }
        }
    }

    protected List<Tuple> checkColumnAsterisk (Metaspace metaspace) throws ASException
    {
        List<Tuple> result = new ArrayList<Tuple>();
        String mname = metaspace.getName();
        // finish building up our metadata; if an * has been specified for the column name
        // retrieve the column names from the space and add those columns to the list
        // for all columns, add the metaspace name
        int csize = m_columnInfo.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple cinfo = m_columnInfo.get(i);
            // add the metaspace name to the column info
            cinfo.put(ASSQLUtils.METASPACE_NAME, mname);

            // now check the columns for *
            String cname = cinfo.getString(ASSQLUtils.COLUMN_NAME);
            String calias = cinfo.getString(ASSQLUtils.COLUMN_ALIAS);
            String fname = cinfo.getString(ASSQLUtils.COLUMN_FUNCTION);
            String tname = cinfo.getString(ASSQLUtils.TABLE_NAME);
            if (cname.equals("*") && (fname == null || fname.isEmpty()))
            {
                // we need to get the names of each of the fields from the space

                Space space = metaspace.getSpace(tname);
                Iterator<FieldDef> iter = space.getSpaceDef().getFieldDefs().iterator();
                while (iter.hasNext())
                {
                    cname = iter.next().getName();
                    Tuple tuple = Tuple.create();
                    tuple.put(ASSQLUtils.COLUMN_NAME, cname);
                    tuple.put(ASSQLUtils.METASPACE_NAME, mname);
                    tuple.put(ASSQLUtils.TABLE_NAME, tname);
                    // for * we won't have any aliases, so set the alias to the column name
                    // this allows us to always use the alias for retrieving columns without
                    // first checking for it to be null and then using the column name
                    tuple.put(ASSQLUtils.COLUMN_ALIAS, cname);
                    result.add(tuple);
                }
            }
            else
            {
                if (calias == null || calias.isEmpty())
                {
                    // if there isn't an alias and this is a function, set the alias
                    // based on the function name
                    // e.g. count(*) should return a field named COUNT_$Asterisk
                    // The $ tells us that what follows is a translation of a special character
                    if (fname != null && !fname.isEmpty())
                    {
                        String columnName = cname.equals("*") ? "$Asterisk" : cname;
                        calias = fname.toUpperCase() + "_" + tname + "_" + columnName;
                        cinfo.put(ASSQLUtils.COLUMN_NAME, columnName);
                        cinfo.put(ASSQLUtils.COLUMN_ALIAS, calias);
                    }
                    else
                    {
                        // if there isn't an alias, set the alias to the column name
                        // this allows us to use the alias without always checking for
                        // it to be null
                        calias = cname;
                        cinfo.put(ASSQLUtils.COLUMN_ALIAS, calias);
                    }

                }
                // add the tuple into our result list
                result.add(cinfo);
            }
        }
        return result;
    }

    protected void initColumnSpec (Metaspace metaspace) throws ASException
    {
        m_columnSpec = new HashMap<String, FieldDef>();
        int csize = m_columnInfo.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple cinfo = m_columnInfo.get(i);
            String cname = cinfo.getString(ASSQLUtils.COLUMN_NAME);
            String calias = cinfo.getString(ASSQLUtils.COLUMN_ALIAS);
            String tname = cinfo.getString(ASSQLUtils.TABLE_NAME);
            SpaceDef spaceDef = metaspace.getSpaceDef(tname);
            // store the FieldDef under the alias since columns from two
            // different tables could have the same names, but the alias
            // should uniquely identify them
            m_columnSpec.put(calias, spaceDef.getFieldDef(cname));
        }
    }

    protected SpaceResultList getTupleList (Metaspace metaspace, String spaceName, String filter,
            HashMap<String, FieldDef> columnSpec)
    {
        // we have a single space and a filter, check to see if we can do a simple
        // get of the data in the space versus browsing the space
        // if anything goes wrong, just return null so that the caller
        // will end up browsing the space instead
        SpaceResultList tuples = null;
        try
        {
            Space space = metaspace.getSpace(spaceName);
            Collection<String> keyfields = space.getSpaceDef().getKeyDef().getFieldNames();
            HashMap<String, String> keyMap = ASSQLUtils.getKeyValues(keyfields, filter);
            if (keyMap != null && !keyMap.isEmpty())
            {
                // set up to do a batch get
                ArrayList<Tuple> keytuples = new ArrayList<Tuple>();
                Iterator<Entry<String, String>> iter = keyMap.entrySet().iterator();
                while (iter.hasNext())
                {
                    Entry<String, String> entry = iter.next();
                    String fieldName = entry.getKey();
                    String fieldValue = entry.getValue();
                    FieldDef fieldDef = columnSpec.get(fieldName);
                    // check string fields to make sure they are enclosed in quotes
                    // if not, the filter wasn't a clean assignment of a field to a string
                    if (fieldDef.getType() == FieldDef.FieldType.STRING)
                    {
                        if (!(fieldValue.startsWith("\"") && fieldValue.endsWith("\"")))
                        {
                            if (keytuples != null)
                            {
                                keytuples.clear();
                                keytuples = null;
                            }
                            break;
                        }
                        else
                        {
                            // when selecting tuples from a space we need to strip the quotes
                            // from the key tuple value even if it contains a space or single quote
                            fieldValue = removeQuotesFromString(fieldValue);
                        }
                    }
                    Tuple tuple = Tuple.create();
                    TupleUtil.setTupleField (tuple, fieldDef, fieldName, fieldValue);
                    keytuples.add(tuple);
                }
                if (keytuples != null && !keytuples.isEmpty())
                {
                    // now retrieve the tuples we were given key values for
                    // TODO is doing a getAll more expensive than doing a get when we only have
                    //      one key?
                    SpaceResultList list = space.getAll(keytuples);
                    if (!list.hasError() && list.size() > 0)
                        tuples = list;  // only return the results if there was no error
                }
            }
        }
        catch (Exception ex)
        {
            // do nothing just return null so browser will be used
        }
        return tuples;
    }

    public String removeQuotesFromString (String sval)
    {
        String result = null;
        if (sval != null && !sval.isEmpty())
        {
            if (sval.startsWith("\"") && sval.endsWith("\""))
            {
                result = sval.substring(1, sval.length() - 1);
            }
        }
        return result;
    }

    protected ASSQLResult processCount(Metaspace metaspace) throws ASException
    {
        ASSQLResult result = null;
        ASSpaceResultList spResultList = new ASSpaceResultList();

        int csize = m_columnInfo.size();
        for (int i = 0; i < csize; i++)
        {
            // retrieve the column info
            Tuple cinfo = m_columnInfo.get(i);
            String cname = cinfo.getString(ASSQLUtils.COLUMN_NAME);
            String tname = cinfo.getString(ASSQLUtils.TABLE_NAME);
            String fname = cinfo.getString(ASSQLUtils.COLUMN_FUNCTION);
            if (cname.equals("$Asterisk") && fname != null && fname.toLowerCase().equals("count"))
            {
                // get the count of the space
                long count = 0;
                if (m_filter != null)
                {
                    // if there was a filter, apply it to all spaces
                    // e.g. select count(*) from table1, table2 where salary > 500
                    count = metaspace.getSpace(tname).size(m_filter);
                }
                else
                {
                    count = metaspace.getSpace(tname).size();
                }
                Tuple tuple = Tuple.create();
                tuple.put(cname, count);
                ASSpaceResult spResult = new ASSpaceResult((ASTuple) tuple, null);
                spResultList.add(spResult);
            }
        }
        if (!spResultList.isEmpty())
        {
            result = new ASSQLResult(m_spaceNames, spResultList, m_columnInfo, m_columnSpec);
        }
        return result;
    }

}
