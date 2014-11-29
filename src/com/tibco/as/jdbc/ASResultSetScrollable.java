// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.jdbc;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLRecoverableException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;
import com.tibco.as.sql.TypeUtil;

/**
 * ASResultSetScrollable is a type of ResultSet in which the data is contained completely
 * in the ResultSet and therefore is easily scrollable. Currently this type of ResultSet
 * is only created by DatabaseMetaData method calls.
*/
public class ASResultSetScrollable extends ASResultSet
{

    protected List<Tuple[]>     m_resultTuples;    // results from non-queries


    public ASResultSetScrollable(String[] inm_columnNames, Integer[] inColumnTypes)
    {
        // This constructor is used when DatabaseMetaData methods have
        // been invoked. It populates column name and column type information
        // which is needed even if there aren't any results for a method
        // (e.g. getColumns has no columns to display but the display header info
        // is still needed as if there were columns to display
        m_resultMetaData = new ASResultSetMetaData(inm_columnNames, inColumnTypes);

        m_columnNames = new ArrayList<String>();
        for (int i=0; i<inm_columnNames.length; i++)
        {
            m_columnNames.add(inm_columnNames[i]);
        }
        m_nameToIndex = new TreeMap<String, Integer>(new Comparator<String>() {
            public int compare(String a, String b) { return a.toLowerCase().compareTo(b.toLowerCase()); }
        });
        for (int i=0; i < m_columnNames.size(); ++i)
        {
            m_nameToIndex.put(m_columnNames.get(i), i);
        }
    }

    public void populateResultSet (List<Tuple[]> columnTuples, List<Tuple> columnInfo)
    {
        // This method is used by DatabaseMetaData methods to complete the
        // population of the result set.
        m_resultTuples = columnTuples;
        m_resultMetaData.setColumnInfo(columnInfo);
        HashMap<String, FieldDef> columnSpec = Utils.getColumnSpec(columnTuples);
        m_resultMetaData.setColumnSpec(columnSpec);
        // we can move forward or backward since we contain all of the data in m_resultTuples
        m_rsType = TYPE_SCROLL_SENSITIVE;
    }

    public void close () throws SQLException
    {
        super.close();
        m_resultTuples = null;
    }

    public Statement getStatement () throws SQLException
    {
        // This result set wasn't generated using a Statement
        return null;
    }

    /**
     * Moves the cursor forward one row from its current position. A <code>ResultSet</code> cursor
     * is initially positioned before the first row; the first call to the method <code>next</code>
     * makes the first row the current row; the second call makes the second row the current row,
     * and so on.
     * <p>
     *
     * @return <code>true</code> if the new current row is valid; <code>false</code> if there are no
     *         more rows
     * @exception SQLException
     *                if a database access error occurs or this method is called on a closed result
     *                set
     */
    public boolean next () throws SQLException
    {
        boolean result = false;
        if (m_resultTuples != null)
        {
            if (m_rowCounter < m_resultTuples.size())
            {
                result = true;
                m_rowCounter++;
            }
        }
        return result;
    }

    public boolean isBeforeFirst () throws SQLException
    {
        boolean bresult = true;
        if (m_resultTuples != null)
        {
            // an initial m_rowCounter set to 0, is before the first row
            bresult = (m_rowCounter == 0 ? true : false);
        }
        return bresult;
    }

    public void beforeFirst () throws SQLException
    {
        if (m_resultTuples != null)
            m_rowCounter = 0;
        else
            throw new SQLFeatureNotSupportedException();
    }

    public boolean first () throws SQLException
    {
        boolean result = false;
        if (m_resultTuples != null)
        {
            m_rowCounter = 1;
            if (m_rowCounter <= m_resultTuples.size())
                result = true;
        }
        else
            throw new SQLFeatureNotSupportedException();
        return result;
    }

    // Did the last column that was read have a value of null
    public boolean wasNull () throws SQLException
    {
        boolean bresult = false;
        if (m_resultTuples != null)
            bresult = m_lastValueNull;
        return bresult;
    }

    public boolean isAfterLast () throws SQLException
    {
        boolean bresult = false;
        if (m_resultTuples != null)
        {
            bresult = (m_rowCounter > m_resultTuples.size() ? true : false);
        }
        return bresult;
    }

    public boolean isFirst () throws SQLException
    {
        boolean bresult = false;
        if (m_resultTuples != null)
            bresult = (m_rowCounter == 1 ? true : false);
        return bresult;
    }

    public Object getObject (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getObject(index);
    }

    public Object getObject (int columnIndex) throws SQLException
    {
        Object result = null;
        // do checks which should return exception first
        checkNotClosed();
        checkIndex(columnIndex);

        Tuple[] row = m_resultTuples.get(m_rowCounter-1);
        if (columnIndex > row.length)
        {
            throw new SQLException("Column index does not exist: " + columnIndex);
        }
        Tuple columnTuple = row[columnIndex-1];
        result = Utils.getJavaObject(columnTuple);
        m_lastValueNull = result == null? true : false;
        return result;
    }

    public String getString (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getString(index);
    }

    public String getString (int columnIndex) throws SQLException
    {
        String result = null;
        Object value = getObject(columnIndex);
        if (value != null)
            result = value.toString();
        return result;
    }

    public boolean getBoolean (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getBoolean(index);
    }

    public boolean getBoolean (int columnIndex) throws SQLException
    {
        boolean bresult = false;
        Object value = getObject(columnIndex);
        if (m_lastValueNull)
            bresult = false;
        else
            bresult = TypeUtil.getBoolean(value);
        return bresult;
    }

    public byte getByte (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getByte(index);
    }

    public byte getByte (int columnIndex) throws SQLException
    {
        byte bresult = 0;
        Object value = getObject(columnIndex);
        if (m_lastValueNull)
            bresult = 0;
        else
            bresult = TypeUtil.getByte(value);
        return bresult;
    }

    public short getShort (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getShort(index);
    }

    public short getShort (int columnIndex) throws SQLException
    {
        short sresult = (short)0;
        Object value = getObject(columnIndex);
        if (m_lastValueNull)
            sresult = 0;
        else
            sresult = TypeUtil.getShort(value);
        return sresult;
    }

    public int getInt (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getInt(index);
    }

    public int getInt (int columnIndex) throws SQLException
    {
        int iresult = 0;
        Object value = getObject(columnIndex);
        if (m_lastValueNull)
            iresult = 0;
        else
            iresult = TypeUtil.getInt(value);
        return iresult;
    }

    public long getLong (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getLong(index);
    }

    public long getLong (int columnIndex) throws SQLException
    {
        long lresult = (long)0;
        Object value = getObject(columnIndex);
        if (m_lastValueNull)
            lresult = 0;
        else
            lresult = TypeUtil.getLong(value);
        return lresult;
    }

    public float getFloat (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getFloat(index);
    }

    public float getFloat (int columnIndex) throws SQLException
    {
        float fresult = (float)0;
        Object value = getObject(columnIndex);
        if (m_lastValueNull)
            fresult = 0;
        else
            fresult = TypeUtil.getFloat(value);
        return fresult;
    }

    public double getDouble (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getDouble(index);
    }

    public double getDouble (int columnIndex) throws SQLException
    {
        double dresult = (double)0;
        Object value = getObject(columnIndex);
        if (m_lastValueNull)
            dresult = 0;
        else
            dresult = TypeUtil.getDouble(value);
        return dresult;
    }

    public byte[] getBytes (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getBytes(index);
    }

    public byte[] getBytes (int columnIndex) throws SQLException
    {
        byte[] bresult = null;
        Object value = getObject(columnIndex);
        if (m_lastValueNull)
            bresult = null;
        else
            bresult = TypeUtil.getBytes(value);
        return bresult;
    }

    public Date getDate (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getDate(index);
    }

    public Date getDate (int columnIndex) throws SQLException
    {
        Date date = null;
        Object value = getObject(columnIndex);
        if (!m_lastValueNull && value instanceof Date)
        {
            date = (Date)value;
        }
        else if (!m_lastValueNull)
        {
            throw new SQLSyntaxErrorException("Cannot convert " + value.getClass().getSimpleName() + " to Date");
        }
        return date;
    }

    public Time getTime (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getTime(index);
    }

    public Time getTime (int columnIndex) throws SQLException
    {
        Time time = null;
        Object value = getObject(columnIndex);
        if (!m_lastValueNull && value instanceof Time)
        {
            time = (Time)value;
        }
        else if (!m_lastValueNull)
        {
            throw new SQLSyntaxErrorException("Cannot convert " + value.getClass().getSimpleName() + " to Time");
        }
        return time;
    }

    public Timestamp getTimestamp (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getTimestamp(index);
    }

    public Timestamp getTimestamp (int columnIndex) throws SQLException
    {
        Timestamp ts = null;
        Object value = getObject(columnIndex);
        if (!m_lastValueNull && value instanceof Timestamp)
        {
            ts = (Timestamp)value;
        }
        else if (!m_lastValueNull)
        {
            throw new SQLSyntaxErrorException("Cannot convert " + value.getClass().getSimpleName() + " to Timestamp");
        }
        return ts;
    }

    public Blob getBlob (String columnLabel) throws SQLException
    {
        checkPosition();
        int index = findColumn(columnLabel);
        return getBlob(index);
    }

    public Blob getBlob (int columnIndex) throws SQLException
    {
        Blob blob = null;
        Object value = getObject(columnIndex);
        if (!m_lastValueNull && value instanceof Blob)
        {
            blob = (Blob)value;
        }
        else if (!m_lastValueNull)
        {
            throw new SQLSyntaxErrorException("Cannot convert " + value.getClass().getSimpleName() + " to Blob");
        }
        return blob;
    }

    /* -- internal supporting methods -- */

    protected void checkPosition () throws SQLException
    {
        if (m_resultTuples != null)
        {
            if (m_rowCounter > m_resultTuples.size())
            {
                throw new SQLException("ResultSet exhausted");
            }
        }
    }

    protected void checkNotClosed () throws SQLException
    {
        if (m_columnNames == null)
            throw new SQLRecoverableException("Method invoked on a closed ResultSet");
    }

}
