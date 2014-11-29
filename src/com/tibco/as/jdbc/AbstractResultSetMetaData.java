// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.jdbc;

import java.sql.SQLException;

/**
 * This class contains all of the methods of java.sql.ResultSetMetaData which
 * are not implemented by ASResultSetMetaData.
 *
 */
public class AbstractResultSetMetaData
{

    public <T> T unwrap (Class<T> iface) throws SQLException
    {
        return null;
    }

    public boolean isWrapperFor (Class<?> iface) throws SQLException
    {
        return false;
    }

    public boolean isAutoIncrement (int column) throws SQLException
    {
        return false;
    }

    public boolean isCaseSensitive (int column) throws SQLException
    {
        return false;
    }

    public boolean isCurrency (int column) throws SQLException
    {
        return false;
    }

    public boolean isSigned (int column) throws SQLException
    {
        return false;
    }

    public String getSchemaName (int column) throws SQLException
    {
        return null;
    }

    public int getPrecision (int column) throws SQLException
    {
        return 0;
    }

    public int getScale (int column) throws SQLException
    {
        return 0;
    }

    public boolean isReadOnly (int column) throws SQLException
    {
        return false;
    }

    public boolean isWritable (int column) throws SQLException
    {
        return false;
    }

    public boolean isDefinitelyWritable (int column) throws SQLException
    {
        return false;
    }

    public String getColumnClassName (int column) throws SQLException
    {
        return null;
    }

}
