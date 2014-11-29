// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.sql;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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

public class UpdateStatement implements ASSQLUpdateStatement
{
    String                  m_spaceName;
    HashMap<String, String> m_columnValues;
    String                  m_filter;

    public UpdateStatement (String spaceName, HashMap<String, String> columnValues, String whereClause)
    {
        m_spaceName = spaceName;
        m_columnValues = columnValues;
        m_filter = whereClause;
        checkFilterForTableName();
    }

    public int processUpdate (Metaspace metaspace) throws SQLException
    {
        int result = -1;
        Space space = null;
        try
        {
            // Join the space. If the space is not already joined, it will be joined
            // as a leech. If it has already been joined, the role will not be changed.
            space = metaspace.getSpace(m_spaceName);
            result = processUpdate(metaspace, space);
        }
        catch (ASException ex)
        {
            throw new SQLException(ex);
        }
        finally
        {
            closeSpace(space);
        }
        return result;
    }

    public int processUpdate (Metaspace metaspace, Space space) throws SQLException
    {
        // we want to modify the data of an existing tuple in the space
        // first we need to retrieve the tuple from the space
        // then modify the values in the tuple with the ones we have been given
        // if we do not have a where clause, then all tuples in the space are
        // updated

        boolean getOptimized=true; // to know if we have successfully done the update using get

        SpaceDef spaceDef = null;
        try
        {
            spaceDef = space.getSpaceDef();
        }
        catch (ASException ex)
        {
            throw new SQLException(ex);
        }

        Browser browser = null;
        int numRowsUpdated = 0;
        try
        {

            // Try first to see if you can do gets followed by update rather than create a browser
            // TODO: This can be further optimized if all of the fields are being updated and to use a single put space call rather than a get and update calls
            Collection<String> keyfields = space.getSpaceDef().getKeyDef().getFieldNames();
            HashMap<String, String> keyMap = ASSQLUtils.getKeyValues(keyfields, m_filter);

            if (keyMap != null && !keyMap.isEmpty() && (keyMap.size() == keyfields.size()))
            {
                // first check if we have all the key fields
                for (String fieldName : keyfields )
                {
                    if (keyMap.containsKey(fieldName)!= true)
                        getOptimized = false;
                }

                if (getOptimized == true)
                {
                    Tuple tuple = null;
                    Iterator<Entry<String, String>> iter = keyMap.entrySet().iterator();

                    while (iter.hasNext())
                    {
                        Entry<String, String> entry = iter.next();
                        String fieldName = entry.getKey();
                        String fieldValue = entry.getValue();
                        FieldDef fieldDef = space.getSpaceDef().getFieldDef(fieldName);
                        // check string fields to make sure they are enclosed in quotes
                        // if not, the filter wasn't a clean assignment of a field to a string
                        if (fieldDef.getType() == FieldDef.FieldType.STRING)
                        {
                            if (!(fieldValue.startsWith("\"") && fieldValue.endsWith("\"")))
                            {
                                if (tuple != null)
                                {
                                    tuple = null;
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

                        if (tuple == null)
                            tuple = Tuple.create();

                        TupleUtil.setTupleField (tuple, fieldDef, fieldName, fieldValue);
                    }
                    if (tuple != null)
                    {
                        // now retrieve the tuple we were given key values for
                        Tuple oldTuple = space.get(tuple);
                        Tuple newTuple = TupleUtil.updateTuple(oldTuple, m_columnValues, spaceDef);

                        Tuple update = space.compareAndPut(oldTuple,newTuple);

                        if (update == newTuple)
                            numRowsUpdated++;

                    }
                    else
                        return(0);  // could not find the row with a get, nothing to update (unlike AS, you can't do 'update from Null' in a DB)
                }
                else getOptimized = false;
            }
            else
                getOptimized = false;

            // if the previous did not work now get a browser which will contain the entries to update instead
            if (getOptimized != true)
            {

                if (m_filter != null)
                {
                    browser = space.browse(BrowserType.GET, BrowserDef.create(0, TimeScope.SNAPSHOT), m_filter);
                }
                else
                    browser = space.browse(BrowserType.GET, BrowserDef.create(0, TimeScope.SNAPSHOT));

                // iterate thru the entries of the browser and update them
                Tuple oldTuple = null;
                while ((oldTuple = browser.next()) != null)
                {
                    Tuple newTuple = TupleUtil.updateTuple(oldTuple, m_columnValues, spaceDef);
                    if (newTuple != null)
                    {
                        Tuple storedTuple = space.compareAndPut(oldTuple, newTuple);
                        if (storedTuple == newTuple)
                            numRowsUpdated++;
                    }

                }
            }
        }
        catch (ASException ex)
        {
            throw new SQLException(ex);
        }
        catch (IllegalArgumentException ex)
        {
            // there was some kind of error accessing the database
            // user should close the connection and retry the query
            throw new SQLRecoverableException(ex);
        }
        finally
        {
            try
            {
                if (browser != null)
                    browser.stop();
            }
            catch (ASException ex)
            {
                // do nothing
            }
        }

        return numRowsUpdated;
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

    private void closeSpace(Space space)
    {
        try
        {
            if (space != null)
                space.close();
        }
        catch (ASException ex)
        {
            // do nothing
        }
    }

    protected void checkFilterForTableName()
    {
        if (m_filter == null || m_filter.isEmpty())
        {
            return;
        }
        if (m_spaceName == null || m_spaceName.isEmpty())
        {
            return;  // just return, error will be caught later
        }
        // Check the filter for any table names and remove them from the filter.
        // AS filters only work on a single space.
        // Could have done this in the grammar but more flexible this way if the driver ends
        // up applying filters to more than one space in the future.
        String tfilter = m_filter;
        String tablePrefix = m_spaceName + ".";
        if (tfilter.contains(tablePrefix))
            tfilter = tfilter.replace(tablePrefix, "");
        m_filter = tfilter;
    }

}
