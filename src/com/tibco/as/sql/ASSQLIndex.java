// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.sql;

import java.util.ArrayList;

/**
 * The ASSQLIndex class holds information that is used to build the indexes for a space.
 *
 */
public class ASSQLIndex
{
    private String            indexName;
    private String            indexType = "tree";      // default index type
    private ArrayList<String> indexColumns;

    public ASSQLIndex (String indexName, String indexType, ArrayList<String> indexColumns)
    {
        this.indexName = indexName;
        if (indexType != null)
            this.indexType = indexType;
        this.indexColumns = indexColumns;
    }

    public String getIndexName ()
    {
        return indexName;
    }

    public String getIndexType ()
    {
        return indexType;
    }

    public ArrayList<String> getIndexColumns ()
    {
        return indexColumns;
    }

}
