// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.sql;

public interface ASSQLStatement
{
    // all supported statements are derived from this basic interface
    // statements are then grouped as ASSQLQueryStatements or ASSQLUpdateStatements
    //
    // ASSQLQueryStatements return data resulting from the statement (e.g. SELECT)
    // ASSQLUpdateStatements return a count of items affected by the statement (e.g. INSERT)
}
