// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.sql;

import java.sql.SQLException;
import java.sql.SQLNonTransientException;
import java.sql.SQLRecoverableException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import com.tibco.as.jdbc.ASConnection;
import com.tibco.as.jdbc.ASStatement;
import com.tibco.as.space.Metaspace;
import com.tibco.as.sql.grammar.ASSQLLexer;
import com.tibco.as.sql.grammar.ASSQLParser;

public class ASSQLProcessor
{

    public static ASSQLResult executeQuery (ASStatement jdbcStatement, Metaspace metaspace, String queryString) throws SQLException
    {
        ASSQLResult result = null;
        ((ASConnection)jdbcStatement.getConnection()).getLogger().logTrace("Perform ANTLR parsing of query string.");
        ASSQLStatement statement = getStatement(queryString);
        // Check to make sure the given SQL statement is supposed to return a ResultSet.
        // If not, throw an exception.
        // Queries return data in a ResultSet, whereas updates return the number of rows affected.
        if (statement instanceof ASSQLQueryStatement)
        {
            ((ASConnection)jdbcStatement.getConnection()).getLogger().logTrace("Invoking processQuery on query string.");
            result = ((ASSQLQueryStatement) statement).processQuery(jdbcStatement, metaspace);
        }
        else
        {
            throw new SQLNonTransientException("executeQuery invoked for wrong statement type.");
        }
        return result;
    }

    public static int executeUpdate (Metaspace metaspace, String queryString) throws SQLException
    {
        int result = -1;
        ASSQLStatement statement = getStatement(queryString);
        // Check to make sure the given SQL statement is not supposed to return
        // a ResultSet. If so, throw an exception. Updates return the number of
        // rows affected. Queries return data in a ResultSet.
        if (statement instanceof ASSQLUpdateStatement)
        {
            result = ((ASSQLUpdateStatement) statement).processUpdate(metaspace);
        }
        else
        {
            throw new SQLNonTransientException("executeUpdate invoked for wrong statement type.");
        }
        return result;
    }

    private static ASSQLStatement getStatement (String queryString) throws SQLException
    {
        CharStream charstream = new ANTLRStringStream(queryString);
        ASSQLLexer lexer = new ASSQLLexer(charstream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ASSQLParser parser = new ASSQLParser(tokens);

        ASSQLStatement statement = null;
        try
        {
            statement = parser.query();
        }
        catch (RecognitionException ex)
        {
            throw new SQLRecoverableException(ex);
        }

        // The lexer and parser queue up any errors they may have encountered
        // along the way, if necessary, we turn them into exceptions here.
        lexer.throwFirstRecognitionError();
        parser.throwFirstRecognitionError();

        return statement;
    }

}
