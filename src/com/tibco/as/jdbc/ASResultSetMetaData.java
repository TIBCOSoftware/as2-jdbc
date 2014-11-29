// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.jdbc;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;
import com.tibco.as.sql.ASSQLResult;
import com.tibco.as.sql.ASSQLUtils;
import com.tibco.as.sql.TypeUtil;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ASResultSetMetaData extends AbstractResultSetMetaData implements ResultSetMetaData
{

    protected Map<String, FieldDef> m_columnSpec;
    protected List<Tuple>           m_columnInfo;

    protected String[]              m_columnNames;
    protected Integer[]             m_columnTypes;

    public ASResultSetMetaData ()
    { }

    public ASResultSetMetaData (String[] inColumnNames, Integer[] inColumnTypes)
    {
        // This constructor is used mainly when DatabaseMetaData methods have
        // been invoked and is used to populate column name and column type information
        // which is needed even if there aren't any results for a method
        // (e.g. getColumns has no columns to display but the display header info
        // is still needed as if there were columns to display
        m_columnNames = inColumnNames;
        m_columnTypes = inColumnTypes;
    }

    public ASResultSetMetaData (ASSQLResult inResult)
    {
        // This constructor is used to populate the result set metadata based
        // upon the results of an SQL statement being executed
        m_columnSpec = inResult.getQueryColumnSpec();
        m_columnInfo = inResult.getQueryColumnInfo();
        populateColumnNames();
    }

    protected void populateColumnNames()
    {
        // Calling this method should only be necessary when
        // ASResultSetMetaData has been created to hold the execution
        // results of an SQL statement. The columnNames array will
        // act as an index into the columnInfo array list.
        if (m_columnInfo != null)
        {
            ArrayList<String> cnames = new ArrayList<String>();
            for (int i=0; i<m_columnInfo.size(); i++)
            {
                String name = m_columnInfo.get(i).getString(ASSQLUtils.COLUMN_NAME);
                cnames.add(name);
            }
            m_columnNames = new String[cnames.size()];
            cnames.toArray(m_columnNames);
        }
    }

    public void setColumnNames(String[] inColumnNames)
    {
        m_columnNames = inColumnNames;
    }

    public void setColumnTypes(Integer[] inColumnTypes)
    {
        m_columnTypes = inColumnTypes;
    }

    public void setColumnSpec(Map<String, FieldDef> inColumnSpec)
    {
        m_columnSpec = inColumnSpec;
    }

    public void setColumnInfo(List<Tuple> inColumnInfo)
    {
        m_columnInfo = inColumnInfo;
    }

    /**
     * Get the designated column's name.
     *
     * @param column
     *            the first column is 1, the second is 2, ...
     * @return column name
     * @exception SQLException
     *                if a database access error occurs
     */
    public String getColumnName (int column) throws SQLException
    {
        String result = null;
        checkIndex(column);
        if (m_columnNames != null)
        {
            result = m_columnNames[column-1];
        }
        else if (m_columnInfo != null)
        {
            Tuple cinfo = m_columnInfo.get(column - 1);
            result = cinfo.getString(ASSQLUtils.COLUMN_NAME);
        }
        return result;
    }

    /**
     * Gets the designated column's suggested title for use in printouts and
     * displays. The suggested title is usually specified by the SQL
     * <code>AS</code> clause. If a SQL <code>AS</code> is not specified, the
     * value returned from <code>getColumnLabel</code> will be the same as the
     * value returned by the <code>getColumnName</code> method.
     *
     * @param column
     *            the first column is 1, the second is 2, ...
     * @return the suggested column title
     * @exception SQLException
     *                if a database access error occurs
     */
    public String getColumnLabel (int column) throws SQLException
    {
        String result = null;
        checkIndex(column);
        if (m_columnNames != null)
        {
            result = getColumnName(column);
        }
        else
        {
            Tuple cinfo = m_columnInfo.get(column - 1);
            result = cinfo.getString(ASSQLUtils.COLUMN_ALIAS);
        }
        return result;
    }

    /**
     * Retrieves the designated column's SQL type.
     *
     * @param column
     *            the first column is 1, the second is 2, ...
     * @return SQL type from java.sql.Types
     * @exception SQLException
     *                if a database access error occurs
     */
    public int getColumnType (int column) throws SQLException
    {
        checkIndex(column);
        // always check for columnTypes first as we could have
        // both columnTypes and columnInfo
        if (m_columnTypes != null)
        {
            return m_columnTypes[column-1];
        }
        else
        {
            FieldDef fdef = getColumnFieldDef(column);
            return TypeUtil.getSQLType(fdef);
        }
    }

    /**
     * Returns the number of columns in this <code>ResultSet</code> object.
     */
    public int getColumnCount () throws SQLException
    {
        int count = 0;
        // always check for columnTypes first as we could have
        // both columnTypes and columnInfo
        if (m_columnTypes != null)
            count = m_columnTypes.length;
        else
            count = m_columnInfo.size();
        return count;
    }

    /**
     * Indicates the nullability of values in the designated column.
     *
     * @param column
     *            the first column is 1, the second is 2, ...
     * @return one of <code>columnNoNulls</code>, <code>columnNullable</code> or
     *         <code>columnNullableUnknown</code>
     * @exception SQLException
     *                if a database access error occurs
     */
    public int isNullable (int column) throws SQLException
    {
        int result = columnNullableUnknown;
        if (m_columnSpec != null)
        {
            checkIndex(column);
            FieldDef fdef = getColumnFieldDef(column);
            if (fdef != null)
            {
                if (fdef.isNullable())
                    result = columnNullable;
                else
                    result = columnNoNulls;
            }
        }
        // if we don't have a columnInfo object, we don't know
        // the nullability of the column
        return result;
    }

    /**
     * Indicates whether the designated column can be used in a where clause.
     *
     */
    public boolean isSearchable (int column) throws SQLException
    {
        checkIndex(column);
        return true;
    }

    /**
     * Retrieves the designated column's database-specific type name.
     *
     * @param column
     *            the first column is 1, the second is 2, ...
     * @return type name used by the database. If the column type is a
     *         user-defined type, then a fully-qualified type name is returned.
     * @exception SQLException
     *                if a database access error occurs
     */
    public String getColumnTypeName (int column) throws SQLException
    {
        String typeName = null;
        // always check for columnTypes first as we could have
        // both columnTypes and columnInfo
        if (m_columnTypes != null)
        {
            if (column > m_columnTypes.length)
            {
                return null;
            }
            int sqlType = m_columnTypes[column -1];
            typeName = TypeUtil.getASType(sqlType);
        }
        else if (m_columnInfo != null)
        {
            checkIndex(column);
            FieldDef fdef = getColumnFieldDef(column);
            if (fdef != null)
                typeName = fdef.getType().toString();
        }
        return typeName;
    }

    /**
     * Gets the designated column's table name.
     *
     * @param column
     *            the first column is 1, the second is 2, ...
     * @return table name or "" if not applicable
     * @exception SQLException
     *                if a database access error occurs
     */
    public String getTableName (int column) throws SQLException
    {
        String result = null;
        if (m_columnInfo != null)
        {
            checkIndex(column);
            Tuple cinfo = m_columnInfo.get(column - 1);
            result = cinfo.getString(ASSQLUtils.TABLE_NAME);
        }
        // if there is not an columnInfo object, this result set was
        // not associated with a table
        return result;
    }

    /**
     * Gets the designated column's table's catalog name.
     *
     * @param column
     *            the first column is 1, the second is 2, ...
     * @return the name of the catalog for the table in which the given column
     *         appears or "" if not applicable
     * @exception SQLException
     *                if a database access error occurs
     */
    public String getCatalogName (int column) throws SQLException
    {
        String result = null;
        if (m_columnInfo != null)
        {
            checkIndex(column);
            Tuple cinfo = m_columnInfo.get(column - 1);
            result = cinfo.getString(ASSQLUtils.METASPACE_NAME);
        }
        // if there is not a columnInfo object, this result set was
        // not associated with a table or column where we could set
        // the database name
        return result;
    }

    /**
     * Indicates the designated column's normal maximum width in characters.
     *
     * @param column the first column is 1, the second is 2, ...
     * @return the normal maximum number of characters allowed as the width
     *          of the designated column
     * @exception SQLException if a database access error occurs
     */
    public int getColumnDisplaySize (int column) throws SQLException
    {
        int result = 25;  // default
        FieldDef fdef = getColumnFieldDef(column);
        if (fdef != null)
        {
            int sqlType = TypeUtil.getSQLType(fdef);
            result = Utils.getSQLDisplaySize(sqlType);
        }
        return result;
    }

    /* -- internal support methods -- */
    private FieldDef getColumnFieldDef (int i) throws SQLException
    {
        FieldDef fdef = null;
        if (m_columnSpec != null)
        {
            // The map of columns and field defs uses the column alias
            // with >1 table, there could be duplicate column names and aliases are supposed to be unique
            String calias = getColumnLabel(i);
            fdef = m_columnSpec.get(calias);
        }
        return fdef;
    }

    private final void checkIndex (int index) throws SQLException
    {
        // first check for settings from DatabaseMetaData calls
        // always check for columnTypes first as we could have
        // both columnTypes and columnInfo
        if (m_columnTypes != null)
        {
            if (index < 1 || index > m_columnTypes.length)
                throw new SQLSyntaxErrorException(String.format(
                        "The index %s must be a number >= 1 and less than %d the number of columns in the result.",
                        String.valueOf(index))
                        + " " + m_columnTypes.length);

        }
        else if (m_columnInfo != null)
        {
            // check settings from SQL statement processing
            if (index < 1 || index > m_columnInfo.size())
                throw new SQLSyntaxErrorException(String.format(
                        "The index %s must be a number >= 1 and less than %d the number of columns in the result.",
                        String.valueOf(index), m_columnInfo.size()));
        }
    }
}
