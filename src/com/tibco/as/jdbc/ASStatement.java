// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import com.tibco.as.space.Metaspace;
import com.tibco.as.sql.ASSQLProcessor;
import com.tibco.as.sql.ASSQLResult;

public class ASStatement extends AbstractStatement implements Statement, Comparable<Object>
{

    protected ASConnection m_connection;
    protected int          m_maxFieldSize         = 0;
    protected int          m_maxRows              = 0;  // ResultSet returns all rows
    protected int          m_fetchSize            = 0;
    protected int          m_fetchDirection       = ResultSet.FETCH_FORWARD;
    protected int          m_resultSetType        = ResultSet.TYPE_FORWARD_ONLY;
    protected int          m_resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
    protected int          m_resultSetHoldability = ResultSet.HOLD_CURSORS_OVER_COMMIT;
    protected ResultSet    m_currentResultSet     = null;
    protected int          m_currentUpdateCount   = -1;

    protected ASStatement (Connection conn)
    {
        m_connection = (ASConnection) conn;
        // if prefetch setting specified in connection URL, initialize with that
        m_fetchSize = m_connection.getPrefetch();
    }

    protected ASStatement (Connection conn, int resultSetType, int resultSetConcurrency) throws SQLException
    {
        m_connection = (ASConnection) conn;
        checkResultSetType(resultSetType);
        // we only support TYPE_FORWARD_ONLY at this time, quietly swallow the given setting
        // this.resultSetType = resultSetType;
        checkResultSetConcurrency(resultSetConcurrency);
        // we only support CONCUR_READ_ONLY at this time, quietly swallow the given setting
        // this.resultSetConcurrency = resultSetConcurrency;

        // if prefetch setting specified in connection URL, initialize with that
        m_fetchSize = m_connection.getPrefetch();
    }

    protected ASStatement (Connection conn, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException
    {
        m_connection = (ASConnection) conn;
        checkResultSetType(resultSetType);
        // we only support TYPE_FORWARD_ONLY at this time, quietly swallow the given setting
        // this.resultSetType = resultSetType;
        checkResultSetConcurrency(resultSetConcurrency);
        // we only support CONCUR_READ_ONLY at this time, quietly swallow the given setting
        // this.resultSetConcurrency = resultSetConcurrency;
        checkResultSetHoldability(resultSetHoldability);
        // we only support HOLD_CURSORS_OVER_COMMIT at this time, quietly swallow the given setting
        // this.resultSetHoldability = resultSetHoldability;

        // if prefetch setting specified in connection URL, initialize with that
        m_fetchSize = m_connection.getPrefetch();
    }

    public void close () throws SQLException
    {
        m_connection.removeStatement(this);
        m_connection = null;
    }

    public boolean isClosed () throws SQLException
    {
        return m_connection == null;
    }

    public Connection getConnection () throws SQLException
    {
        return m_connection;
    }

    /**
     * This method should only be invoked for those SQL statements which return data in a
     * <code>ResultSet</code> object. Typically this will be an SQL <code>SELECT</code> statement.
     *
     * @return a <code>ResultSet</code> object that contains the data produced by the given SQL
     *         statement. <code>null</code> will never be returned.
     *
     * @exception SQLException
     *                if a database access error occurs, this method is called on a closed
     *                <code>Statement</code> or the given SQL statement produces anything other than
     *                a single <code>ResultSet</code> object
     */
    public ResultSet executeQuery (String sql) throws SQLException
    {
        m_connection.getLogger().logTrace("Statement.executeQuery(\"" + sql + "\") called");
        checkForConnection();
        Metaspace metaspace = m_connection.getMetaspace();
        ASSQLResult asresult = ASSQLProcessor.executeQuery(this, metaspace, sql);
        m_connection.getLogger().logTrace("Statement.executeQuery(): Creating ASResultSet...");
        m_currentResultSet = new ASResultSet(asresult, this);
        m_currentUpdateCount = -1;
        return m_currentResultSet;
    }

    /**
     * This method should only be invoked for SQL statements which return a row count (e.g. INSERT,
     * UPDATE, or DELETE) or SQL statements that return nothing. It should not be invoked for SQL
     * statements which return data in their result.
     *
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
     *         for SQL statements that return nothing
     *
     * @exception SQLException
     *                if a database access error occurs, this method is called on a closed
     *                <code>Statement</code> or the given SQL statement produces a
     *                <code>ResultSet</code> object
     */
    public int executeUpdate (String sql) throws SQLException
    {
        m_connection.getLogger().logTrace("Statement.executeUpdate(\"" + sql + "\") called");
        checkForConnection();
        m_currentUpdateCount = -1;
        Metaspace metaspace = m_connection.getMetaspace();
        m_currentUpdateCount = ASSQLProcessor.executeUpdate(metaspace, sql);
        if (m_currentUpdateCount == -1)
            throw new SQLSyntaxErrorException("Invalid query string passed for processing.");
        m_currentResultSet = null;
        return m_currentUpdateCount;
    }

    // ----------------------- Multiple Results --------------------------

    /**
     * The <code>execute</code> method executes an SQL statement and indicates the form of the first
     * result. You must then use the methods <code>getResultSet</code> or
     * <code>getUpdateCount</code> to retrieve the result, and <code>getMoreResults</code> to move
     * to any subsequent result(s).
     *
     * @param sql
     *            any SQL statement
     * @return <code>true</code> if the first result is a <code>ResultSet</code> object;
     *         <code>false</code> if it is an update count or there are no results
     * @exception SQLException
     *                if a database access error occurs or this method is called on a closed
     *                <code>Statement</code>
     *
     */
    public boolean execute (String sql) throws SQLException
    {
        m_connection.getLogger().logTrace("Statement.execute(\"" + sql + "\") called");
        boolean result = true;
        // determine the type of query and invoke the appropriate execute method
        if (sql.toLowerCase().startsWith("create") || sql.toLowerCase().startsWith("insert")
                || sql.toLowerCase().startsWith("update") || sql.toLowerCase().startsWith("delete"))
        {
            result = false;  // we return an update count
            executeUpdate(sql);
        }
        else
        {
            executeQuery(sql);
        }
        return result;
    }

    /**
     * Retrieves the current result as an update count; if the result is a <code>ResultSet</code>
     * object or there are no more results, -1 is returned. This method should be called only once
     * per result.
     *
     */
    public int getUpdateCount () throws SQLException
    {
        checkForConnection();
        return m_currentUpdateCount;
    }

    /**
     * Retrieves the current ResultSet; if the result is an update count or there are not more
     * results, null is returned. This method should be called only once per result.
     */
    public ResultSet getResultSet () throws SQLException
    {
        checkForConnection();
        return m_currentResultSet;
    }

    /**
     * Moves to this <code>Statement</code> object's next result, returns <code>true</code> if it is
     * a <code>ResultSet</code> object, and implicitly closes any current <code>ResultSet</code>
     * object(s) obtained with the method <code>getResultSet</code>.
     */
    public boolean getMoreResults () throws SQLException
    {
        // we don't support multiple results at this time
        return false;
    }

    // ----------------------- End Multiple Results --------------------------

    public int getMaxFieldSize () throws SQLException
    {
        checkForConnection();
        return m_maxFieldSize;
    }

    public void setMaxFieldSize (int max) throws SQLException
    {
        checkForConnection();
        m_maxFieldSize = max;
    }

    public int getMaxRows () throws SQLException
    {
        checkForConnection();
        // 0 means there is no limit
        // by default, the AS JDBC driver will use a SNAPSHOT browser with no limit
        // so max rows will be 0
        return m_maxRows;
    }

    public void setMaxRows (int max) throws SQLException
    {
        checkForConnection();
        if (max < 0)
        {
            throw new SQLSyntaxErrorException("Max rows must be >= 0");
        }
        // if setMaxRows >0, the ResultSet will return the specified number of rows
        // if setMaxRows = 0 (default), the ResultSet will return all rows in the space
        m_maxRows = max;
    }

    public int getFetchSize () throws SQLException
    {
        checkForConnection();
        return m_fetchSize;
    }

    public void setFetchSize (int rows) throws SQLException
    {
        checkForConnection();
        // special setting for ActiveSpaces
        // allow a fetch size of -1 to be used to indicate a CURRENT browser should be used for
        // getting data from the space
        // cannot use 0 (zero) for this as zero already has a meaning for this call (fetch size
        // hint should be ignored)
        if (rows < -1)
            throw new SQLSyntaxErrorException("Fetch size cannot be less than zero.");
        m_fetchSize = rows;
    }

    public int getFetchDirection () throws SQLException
    {
        checkForConnection();
        return m_fetchDirection;
    }

    public void setFetchDirection (int direction) throws SQLException
    {
        checkForConnection();
        checkFetchDirection(direction);
        // we only support FETCH_FORWARD at this time, quietly ignore the given setting
        // fetchDirection = direction;
    }

    public int getResultSetType () throws SQLException
    {
        checkForConnection();
        return m_resultSetType;
    }

    protected final void checkForConnection () throws SQLException
    {
        if (m_connection == null)
            throw new SQLRecoverableException(Utils.AS_STATEMENT_CLOSED);
    }

    protected final void checkResultSetType (int rstype) throws SQLException
    {
        if (rstype != ResultSet.TYPE_FORWARD_ONLY && rstype != ResultSet.TYPE_SCROLL_INSENSITIVE
                && rstype != ResultSet.TYPE_SCROLL_SENSITIVE)
            throw new SQLSyntaxErrorException("Invalid Result Set Type");
    }

    protected final void checkResultSetConcurrency (int rsconcurrency) throws SQLException
    {
        if (rsconcurrency != ResultSet.CONCUR_READ_ONLY && rsconcurrency != ResultSet.CONCUR_UPDATABLE)
            throw new SQLSyntaxErrorException("Invalid Result Set Concurrency");
    }

    protected final void checkResultSetHoldability (int rsholdability) throws SQLException
    {
        if (rsholdability != ResultSet.HOLD_CURSORS_OVER_COMMIT && rsholdability != ResultSet.CLOSE_CURSORS_AT_COMMIT)
            throw new SQLSyntaxErrorException("Invalid Result Set Holdability");
    }

    protected final void checkFetchDirection (int direction) throws SQLException
    {
        if (direction == ResultSet.FETCH_FORWARD || direction == ResultSet.FETCH_REVERSE
                || direction == ResultSet.FETCH_UNKNOWN)
        {
            if ((getResultSetType() == ResultSet.TYPE_FORWARD_ONLY) && (direction != ResultSet.FETCH_FORWARD))
                throw new SQLSyntaxErrorException(String.format("Invalid fetch direction: %s", direction));
        }
        else
            throw new SQLSyntaxErrorException(String.format("Invalid fetch direction: %s", direction));
    }

    public int compareTo (Object target)
    {
        if (this.equals(target))
            return 0;
        if (this.hashCode() < target.hashCode())
            return -1;
        else
            return 1;
    }

}
