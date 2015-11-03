// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.sql;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;

public class ASSQLResult
{
    // the simplest result just contains the count of rows affected by an SQL query statement
    // the name of the table/space, and the columns in the row which were affected
    protected int                   m_queryUpdateCount = -1;
    protected List<String>          m_queryColumnNames;
    protected List<Tuple>           m_queryColumnInfo;

    protected Map<String, Integer>  m_nameToIndex;

    // results which return data contain the table/space information, the rows of data (contained
    // in the AS browser or an ArrayList for simple queries which can be performed as gets), the
    // name and type of each column
    protected List<String>          m_querySpaceNames;
    protected List<Browser>         m_queryBrowserList;
    protected SpaceResultList       m_queryTupleList;
    protected Map<String, FieldDef> m_queryColumnSpec;

    protected ASSQLTuple            m_currentTuple;
    protected boolean               m_queryBeforeFirst = true;
    protected boolean               m_queryAfterLast   = false;
    protected boolean               m_queryIsFirst     = false;

    public ASSQLResult (String spaceName, int updateCount, List<Tuple> cinfo, Map<String, FieldDef> cspec)
    {
        // constructor used when SQL query statements do not return data from the database
        m_querySpaceNames = new ArrayList<String>();
        m_querySpaceNames.add(spaceName);
        m_queryUpdateCount = updateCount;
        m_queryColumnInfo = cinfo;
        m_queryColumnNames = getColumnNameList(cinfo);
        m_nameToIndex = new TreeMap<String, Integer>(new Comparator<String>() {
            public int compare(String a, String b) { return a.toLowerCase().compareTo(b.toLowerCase()); }
        });
        for (int i=0; i < m_queryColumnNames.size(); ++i)
        {
            m_nameToIndex.put(m_queryColumnNames.get(i), i);
        }

        m_queryColumnSpec = cspec;
    }

    public ASSQLResult (List<String> snames, List<Browser> browsers, List<Tuple> cinfo, Map<String, FieldDef> cspec)
    {
        m_querySpaceNames = snames;
        m_queryBrowserList = browsers;
        m_queryColumnInfo = cinfo;
        m_queryColumnSpec = cspec;
        m_queryColumnNames = new ArrayList<String>();
        int csize = m_queryColumnInfo.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple tuple = m_queryColumnInfo.get(i);
            String calias = tuple.getString(ASSQLUtils.COLUMN_ALIAS);
            m_queryColumnNames.add(calias);
        }
        m_nameToIndex = new TreeMap<String, Integer>(new Comparator<String>() {
            public int compare(String a, String b) { return a.toLowerCase().compareTo(b.toLowerCase()); }
        });
        for (int i=0; i < m_queryColumnNames.size(); ++i)
        {
            m_nameToIndex.put(m_queryColumnNames.get(i), i);
        }
    }

    public ASSQLResult (List<String> snames, SpaceResultList tuples, List<Tuple> cinfo, Map<String, FieldDef> cspec)
    {
        m_querySpaceNames = snames;
        m_queryTupleList = tuples;
        m_queryColumnInfo = cinfo;
        m_queryColumnSpec = cspec;
        m_queryColumnNames = new ArrayList<String>();
        int csize = m_queryColumnInfo.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple tuple = m_queryColumnInfo.get(i);
            String calias = tuple.getString(ASSQLUtils.COLUMN_ALIAS);
            m_queryColumnNames.add(calias);
        }
        m_nameToIndex = new TreeMap<String, Integer>(new Comparator<String>() {
            public int compare(String a, String b) { return a.toLowerCase().compareTo(b.toLowerCase()); }
        });
        for (int i=0; i < m_queryColumnNames.size(); ++i)
        {
            m_nameToIndex.put(m_queryColumnNames.get(i), i);
        }
    }

    public void close ()
    {
        try
        {
            if (m_queryBrowserList != null && !m_queryBrowserList.isEmpty())
            {
                int bsize = m_queryBrowserList.size();
                for (int i = 0; i < bsize; i++)
                    m_queryBrowserList.get(i).stop();
            }
        }
        catch (ASException ex)
        {
            // do nothing
        }
    }

    public int getQueryUpdateCount ()
    {
        return m_queryUpdateCount;
    }

    public List<String> getQueryColumnNames ()
    {
        return m_queryColumnNames;
    }

    public List<Tuple> getQueryColumnInfo ()
    {
        return m_queryColumnInfo;
    }

    public Map<String, FieldDef> getQueryColumnSpec ()
    {
        return m_queryColumnSpec;
    }

    public boolean checkPosition ()
    {
        boolean result = false;
        if ((m_queryTupleList != null || (m_queryBrowserList != null && !m_queryBrowserList.isEmpty()))
                && m_currentTuple != null)
            result = true;
        return result;
    }

    public boolean wasLastValueNull ()
    {
        return m_currentTuple.wasLastValueNull();
    }

    /**
     * Returns whether the cursor is before the first tuple in the result set.
     */
    public boolean isBeforeFirst ()
    {
        return m_queryBeforeFirst;
    }

    /**
     * Returns whether the cursor is after the last tuple in the result set.
     */
    public boolean isAfterLast ()
    {
        return m_queryAfterLast;
    }

    /**
     * Returns whether the current tuple is the first tuple in the result set.
     */
    public boolean isFirst ()
    {
        return m_queryIsFirst;
    }

    /**
     * Retrieves the next row of data.
     * <p>
     *
     * @return <code>true</code> if there is a new current row; <code>false</code> if there are no
     *         more rows
     * @exception SQLException
     *                if a space access error occurs
     */
    public boolean next () throws SQLException
    {
        boolean result = false;
        // holds all of the tuples which should be returned as a single row of data
        // e.g. one tuple from each space
        List<Tuple> tupleList = new ArrayList<Tuple>();

        // queryTupleList will be non-null when we were able to do a get on a single
        // space instead of having to create browsers to browse for tuples
        if (m_queryTupleList != null && !m_queryTupleList.isEmpty())
        {
            int rsize = m_queryTupleList.size();
            for (int i=0; i<rsize; i++)
            {
                // get the first tuple in the results list and add it to our
                // tupleList, then remove it from the results list
                SpaceResult sresult = m_queryTupleList.get(0);
                Tuple tuple = sresult.getTuple();
                if (tuple != null)
                {
                    tupleList.add(tuple);
                    m_queryTupleList.remove(0);
                }
            }
        }
        else if (m_queryBrowserList != null && !m_queryBrowserList.isEmpty())
        {
            // we have a list of spaces and we have a list of corresponding browsers
            // now get the next tuple from each browser
            // we end up with a list of tuples which have a 1-to-1 correspondence to
            // the list of spaces
            int bsize = m_queryBrowserList.size();
            for (int i = 0; i < bsize; i++)
            {
                Browser browser = m_queryBrowserList.get(i);
                try
                {
                    Tuple tuple = browser.next();
                    if (tuple != null)
                        tupleList.add(tuple);
                    else
                    {
                        // add an empty tuple as a place holder
                        // we will fill the columns for this space with null
                        // when we see an empty tuple
                        tuple = Tuple.create();
                        tupleList.add(tuple);
                    }
                }
                catch (ASException ex)
                {
                    // there was some kind of error accessing the database
                    // user should close the connection and retry the query
                    throw new SQLRecoverableException(ex);
                }
            }
        }
        if (isTupleListEmpty(tupleList))
        {
            m_currentTuple = null;
            m_queryBeforeFirst = false;
            m_queryIsFirst = false;
            m_queryAfterLast = true;
            tupleList.clear();
        }
        else
        {
            try
            {
                m_currentTuple = new ASSQLTuple(m_querySpaceNames, tupleList, m_queryColumnInfo, m_queryColumnSpec);
                if (m_queryBeforeFirst)
                {
                    m_queryIsFirst = true;
                    m_queryBeforeFirst = false;
                }
                else
                {
                    m_queryIsFirst = false;
                }
                result = true;
            }
            catch (IllegalArgumentException ex)
            {
                throw new SQLException(ex);
            }
        }
        return result;
    }

    public Object getObject (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getObject(fieldName);
    }

    public String getString (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getString(fieldName);
    }

    public Boolean getBoolean (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getBoolean(fieldName);
    }

    public byte getByte (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getByte(fieldName);
    }

    public short getShort (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getShort(fieldName);
    }

    public int getInt (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getInt(fieldName);
    }

    public long getLong (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getLong(fieldName);
    }

    public float getFloat (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getFloat(fieldName);
    }

    public double getDouble (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getDouble(fieldName);
    }

    public byte[] getBytes (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getBytes(fieldName);
    }

    public Date getDate (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getDate(fieldName);
    }

    public Time getTime (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getTime(fieldName);
    }

    public Timestamp getTimestamp (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getTimestamp(fieldName);
    }

    public Blob getBlob (String fieldName) throws SQLException
    {
        checkFieldName(fieldName);
        checkCurrentTuple();
        return m_currentTuple.getBlob(fieldName);
    }

    /* -- Internal methods -- */

    private List<String> getColumnNameList (List<Tuple> cinfo)
    {
        List<String> result = new ArrayList<String>();
        int csize = cinfo.size();
        for (int i = 0; i < csize; i++)
        {
            Tuple tuple = cinfo.get(i);
            String calias = tuple.getString(ASSQLUtils.COLUMN_ALIAS);
            result.add(calias);
        }
        return result;
    }

    private final void checkFieldName (String fieldName) throws SQLException
    {
        if (fieldName == null)
            throw new SQLDataException("Missing field name parameter.");
        if (!m_nameToIndex.containsKey(fieldName))
            throw new SQLDataException("Invalid field name used: " + fieldName);
    }

    private final void checkCurrentTuple () throws SQLException
    {
        if (m_currentTuple == null)
            throw new SQLDataException("No data to read.");
    }

    private final boolean isTupleListEmpty (List<Tuple> tupleList)
    {
        boolean result = true;
        if (tupleList != null)
        {
            int lsize = tupleList.size();
            for (int i = 0; i < lsize; i++)
            {
                Tuple tuple = tupleList.get(i);
                if (tuple.size() != 0)
                    result = false;
            }
        }
        return result;
    }

}
