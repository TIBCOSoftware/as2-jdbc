// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.sql;

public class ASSQLColumnInfo
{
    public final String columnName;
    public final String columnLabel;
    public String       columnSpaceName;

    public ASSQLColumnInfo (String columnName)
    {
        this(columnName, columnName, null);
    }

    public ASSQLColumnInfo (String columnName, String columnLabel)
    {
        this(columnName, columnLabel, null);
    }

    public ASSQLColumnInfo (String columnName, String columnLabel, String columnSpaceName)
    {
        this.columnName = columnName;
        this.columnLabel = columnLabel;
        this.columnSpaceName = columnSpaceName;
    }

}
