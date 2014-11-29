// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.sql;

import java.sql.SQLException;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.browser.Browser;

public class DeleteStatement implements ASSQLUpdateStatement
{
    String m_spaceName;
    String m_whereCondition;

    public DeleteStatement (String spaceName, String whereCondition)
    {
        m_spaceName = spaceName;
        m_whereCondition = whereCondition;
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
        int result = -1;

        // retrieve the information about the columns of the table for the
        // ResultSetMetaData
        int numRowsDeleted = 0;
        try
        {
            // TODO parse whereCondition to see if we can do a take instead of using a browser
            Browser browser = ASSQLUtils.getTakeBrowser(space, m_whereCondition);
            for (; browser.next() != null;)
            {
                numRowsDeleted++;
            }

            result = numRowsDeleted;
            browser.stop();
        }
        catch (ASException ex)
        {
            result = numRowsDeleted;
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
        if (m_whereCondition == null || m_whereCondition.isEmpty())
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
        String tfilter = m_whereCondition;
        String tablePrefix = m_spaceName + ".";
        if (tfilter.contains(tablePrefix))
            tfilter = tfilter.replace(tablePrefix, "");
        m_whereCondition = tfilter;
    }

}
