// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.sql;

import java.sql.SQLException;
import java.util.List;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;

public class InsertStatement implements ASSQLUpdateStatement
{
    String       m_spaceName;
    List<String> m_columnNames;
    List<String> m_columnValues;

    public InsertStatement (String spaceName, List<String> columnNames, List<String> columnValues)
    {
        m_spaceName = spaceName;
        m_columnNames = columnNames;
        m_columnValues = columnValues;
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
        SpaceDef spaceDef = null;
        try
        {
            // get the space definition so we can get the field information
            spaceDef = space.getSpaceDef();
        }
        catch (ASException ex)
        {
            throw new SQLException(ex);
        }

        Tuple tuple = Tuple.create();
        for (int i = 0; i < m_columnNames.size(); i++)
        {
            String columnName = m_columnNames.get(i);
            String columnValue = m_columnValues.get(i);
            FieldDef fieldDef = spaceDef.getFieldDef(columnName);
            try
            {
                TupleUtil.setTupleField(tuple, fieldDef, columnName, columnValue);
            }
            catch (IllegalArgumentException ex)
            {
                throw new SQLException(ex);
            }
        }

        int numRowsInserted = 0;
        try
        {
            // for insert we only want to put the tuple into the space if an
            // entry does not already exist
            Tuple storedTuple = space.compareAndPut(null, tuple);
            if (storedTuple == tuple)
            {
                // number of rows processed is always 1, if successful
                numRowsInserted = 1;
            }
            result = numRowsInserted;
        }
        catch (ASException ex)
        {
            if (ex.getStatus().equals(ASStatus.ALREADY_EXISTS))
                result = numRowsInserted;
            else
                throw new SQLException(ex);
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

}
