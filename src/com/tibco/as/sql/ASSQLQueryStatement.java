// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.sql;

import java.sql.SQLException;

import com.tibco.as.space.Metaspace;

public interface ASSQLQueryStatement extends ASSQLStatement
{
    public ASSQLResult processQuery (java.sql.Statement statement, Metaspace metaspace) throws SQLException;

}
