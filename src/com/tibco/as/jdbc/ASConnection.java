// -------------------------------------------------------------------
//  Copyright (c) 2012-$Date: 2016-06-15 11:18:48 -0700 (Wed, 15 Jun 2016) $ TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.jdbc;

import java.sql.*;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import com.tibco.as.space.ASCommon;
import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.LogLevel;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Tuple;

public class ASConnection extends AbstractConnection implements Connection
{

    protected Metaspace    m_metaspace;
    protected java.util.Map<String,Class<?>>  m_typeMap;
    protected SimpleLogger m_logger;
    protected int          m_prefetch = 0;
    private Set<Statement> m_statements  = new ConcurrentSkipListSet<Statement>();

    // can read from or write to spaces
    private boolean        m_readOnly   = false;
    // statements are executed one-by-one
    private boolean        m_autoCommit = true;

    // we currently don't support client info
    private Properties     m_clientInfo  = new Properties();

    public ASConnection () throws ASException
    {
        m_metaspace = Metaspace.connect();
        m_logger = new SimpleLogger(false);
    }

    public ASConnection (Metaspace metaspace)
    {
        m_metaspace = metaspace;
        m_logger = new SimpleLogger(false);
    }

    public ASConnection (java.util.Properties info) throws ASException
    {
        boolean debug = false;
        if (info != null && !info.isEmpty())
        {
            // set up AS console logging
            String logSetting = info.getProperty("console_log", "");
            if (logSetting != null && !logSetting.isEmpty())
            {
                if (logSetting.equals("DEBUG"))
                    logSetting = "FINE";
                LogLevel ll = LogLevel.valueOf(logSetting);
                ASCommon.setLogLevel(ll);
            }

            // read driver logging/tracing setting
            String debugSetting = info.getProperty("debug", "");
            if (debugSetting != null && !debugSetting.isEmpty())
            {
                if (debugSetting.toLowerCase().equals("true"))
                    debug = true;
                else if (debugSetting.toLowerCase().equals("false"))
                    debug = false;
                else
                    System.out.println("Ignoring debug setting. Only true or false allowed.");
            }

            // read prefetch size - special option for AS testing
            String prefetchSetting = info.getProperty("prefetch", "");
            if (prefetchSetting != null && !prefetchSetting.isEmpty())
            {
                try
                {
                    m_prefetch = Integer.parseInt(prefetchSetting);
                }
                catch (NumberFormatException ex)
                {
                    throw new ASException(ASStatus.INVALID_ARG, "Invalid prefetch size specified.");
                }
            }

            // create a metaspace connection based upon the given properties
            MemberDef memberDef = MemberDef.create();
            // check for a security token file first before setting
            // the discovery URL. If a security file is used, AS uses
            // the discovery URL in the security file and ignores the
            // discovery setting from the MemberDef object
            String security_token = info.getProperty("security_token", "");
            if (security_token != null && !security_token.isEmpty())
            	memberDef.setSecurityTokenFile(security_token);
            else
                memberDef.setDiscovery(info.getProperty("discovery", "tibpgm"));
            memberDef.setListen(info.getProperty("listen", "tcp"));
            memberDef.setRemoteListen(info.getProperty("remote_listen", ""));
            memberDef.setRemoteDiscovery(info.getProperty("remote_discovery", ""));
            memberDef.setMemberName(info.getProperty("member_name", ""));
            String metaspaceName = info.getProperty("metaspace", "ms");
            String rx_buffer_sizeStr = info.getProperty("rx_buffer_size", "");
            if (rx_buffer_sizeStr != null && !rx_buffer_sizeStr.isEmpty())
            {
                try
                {
                    long rxsize = Long.parseLong(rx_buffer_sizeStr);
                    memberDef.setRxBufferSize(rxsize);
                }
                catch (NumberFormatException ex)
                {
                    throw new ASException(ASStatus.INVALID_ARG, "Invalid rx_buffer_size specified.");
                }
            }
            String worker_thread_countStr = info.getProperty("worker_thread_count", "");
            if (worker_thread_countStr != null && !worker_thread_countStr.isEmpty())
            {
                try
                {
                    int threadCount = Integer.parseInt(worker_thread_countStr);
                    memberDef.setWorkerThreadCount(threadCount);
                }
                catch (NumberFormatException ex)
                {
                    throw new ASException(ASStatus.INVALID_ARG, "Invalid worker_thread_count specified.");
                }
            }

            Tuple context = Tuple.create();
            context.put("platform", "java");
            context.put("jointime", DateTime.create());
            memberDef.setContext(context);

            try
            {
                m_metaspace = Metaspace.connect(metaspaceName, memberDef);
            }
            catch (ASException asex)
            {
                String exmsg = asex.getMessage();
                if (exmsg.contains("metaspace_already_exists"))
                {
                    // a new JDBC Connection is used for each new set of queries but AS
                    // doesn't allow more than 1 metaspace connection in a program at a time
                    // since we got an error trying to create a connection to the metaspace
                    // check to see if there is an existing metaspace with the same name
                    m_metaspace = ASCommon.getMetaspace(metaspaceName);
                }
                if (m_metaspace == null)
                {
                    throw asex;  // rethrow the original exception
                }
            }
        }
        else
            m_metaspace = Metaspace.connect();
        m_logger = new SimpleLogger(debug);
    }

    public Metaspace getMetaspace ()
    {
        return m_metaspace;
    }

    public SimpleLogger getLogger()
    {
        return m_logger;
    }

    public int getPrefetch()
    {
        return m_prefetch;
    }

    public String getCatalog () throws SQLException
    {
        return m_metaspace.getName();
    }

    public void setCatalog (String catalog) throws SQLException
    {
        // AS doesn't allow arbitrarily setting the metaspace name
        // do nothing
    }

    public String getSchema() throws SQLException
    {
        // return null since schemas are not supported
        return null;
    }

    public void setSchema(String schema) throws SQLException
    {
        // do nothing since schemas are not supported in AS
    }

    public java.util.Map<String,Class<?>> getTypeMap () throws SQLException
    {
        // not supported but return a map anyway
        return m_typeMap;
    }

    public void setTypeMap(java.util.Map<String,Class<?>> map) throws SQLException
    {
        m_typeMap = map;
    }

    public void close () throws SQLException
    {
        getLogger().logTrace("Connection.close() called");
        // close all statements associated with this connection upon close
        for (Statement statement : m_statements)
            statement.close();
        m_statements.clear();

        try
        {
            if (m_metaspace != null)
            {
                // don't call closeAll() as there could be more than 1 JDBC Connection object using
                // the same Metaspace connection. Just release our metaspace reference. If we are
                // the last reference, then the equivalent of closeAll() will be done.
                //m_metaspace.closeAll();
                m_metaspace.close();
            }
            m_metaspace = null;
        }
        catch (ASException e)
        {
            throw new SQLException(e);
        }
    }

    public boolean isClosed () throws SQLException
    {
        return m_metaspace == null ? true : false;
    }

    public boolean isValid (int timeout) throws SQLException
    {
        checkForMetaspace();
        if (timeout < 0)
            throw new SQLTimeoutException("Timeout cannot be less than zero.");
        boolean result = true;
        try
        {
            // try to do something simple that will access the metaspace to see
            // if the metaspace is still active
            Collection<String> userSpaces = m_metaspace.getUserSpaceNames();
            userSpaces.clear();
        }
        catch (ASException ex)
        {
            // there was some problem accessing the metaspace
            result = false;
        }
        return result;
    }

    public Statement createStatement () throws SQLException
    {
        getLogger().logTrace("Connection.createStatement() called");
        checkForMetaspace();
        Statement statement = new ASStatement(this);
        m_statements.add(statement);
        return statement;
    }

    public Statement createStatement (int resultSetType, int resultSetConcurrency) throws SQLException
    {
        getLogger().logTrace("Connection.createStatement(int resultSetType, int resultSetConcurrency) called");
        checkForMetaspace();
        Statement statement = new ASStatement(this, resultSetType, resultSetConcurrency);
        m_statements.add(statement);
        return statement;
    }

    public Statement createStatement (int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException
    {
        getLogger().logTrace("Connection.createStatement(" + resultSetType + ", " + resultSetConcurrency + "," + resultSetHoldability + ") called");
        checkForMetaspace();
        Statement statement = new ASStatement(this, resultSetType, resultSetConcurrency, resultSetHoldability);
        m_statements.add(statement);
        return statement;
    }

    public PreparedStatement prepareStatement (String sql) throws SQLException
    {
        getLogger().logTrace("Connection.prepareStatement(\"" + sql + "\") called");
        checkForMetaspace();
        PreparedStatement statement = new ASPreparedStatement(this, sql);
        m_statements.add(statement);
        return statement;
    }

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException
    {
        return prepareStatement(sql, resultSetType, resultSetConcurrency, ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }

    public PreparedStatement prepareStatement (String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException
    {
        getLogger().logTrace("Connection.prepareStatement(\"" + sql + "\"," + resultSetType + "," + resultSetConcurrency + "," + resultSetHoldability + ") called");
        checkForMetaspace();
        PreparedStatement statement =
            new ASPreparedStatement(this, sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        m_statements.add(statement);
        return statement;
    }

    public DatabaseMetaData getMetaData () throws SQLException
    {
        checkForMetaspace();
        return new ASDatabaseMetaData(this);
    }

    public void setReadOnly (boolean readOnly) throws SQLException
    {
        checkForMetaspace();
        // ignore any setting as we only support read/write mode
    }

    public boolean isReadOnly () throws SQLException
    {
        checkForMetaspace();
        return m_readOnly;
    }

    public void setHoldability (int holdability) throws SQLException
    {
        checkForMetaspace();
        // ignore any setting as we only support HOLD_CURSORS_OVER_COMMIT
    }

    public int getHoldability () throws SQLException
    {
        checkForMetaspace();
        return ResultSet.HOLD_CURSORS_OVER_COMMIT;
    }

    public String nativeSQL (String sql) throws SQLException
    {
        checkForMetaspace();
        return sql;
    }

    public SQLWarning getWarnings () throws SQLException
    {
        checkForMetaspace();
        // we do not keep track of warnings
        return null;
    }

    public void clearWarnings () throws SQLException
    {
        checkForMetaspace();
        // we do not keep track of warnings
    }

    /* -- transaction related methods -- */
    public void setAutoCommit (boolean autoCommit) throws SQLException
    {
        boolean beginTransaction = false;
        getLogger().logTrace("Connection.setAutoCommit(" + Boolean.toString(autoCommit) + ") called");
        checkForMetaspace();
        if (m_autoCommit)
        {
            if (autoCommit != m_autoCommit)
            {
                beginTransaction = true;
            }
            m_autoCommit = autoCommit;
            if (beginTransaction)
            {
                // begin a transaction
                try
                {
                    m_metaspace.beginTransaction();
                }
                catch (ASException e)
                {
                    throw new SQLException(e);
                }
            }
        }
        else
        {
            if (autoCommit != m_autoCommit)
            {
                // we are changing from transactions to auto commit
                // rollback any uncommitted metaspace operations
                try
                {
                    m_metaspace.rollbackTransaction();
                    // Note: we don't call the Connection::rollback method
                    // as that starts a new transaction after the rollback
                }
                catch (ASException e)
                {
                    throw new SQLException(e);
                }
            }
            m_autoCommit = autoCommit;
        }
    }

    public boolean getAutoCommit () throws SQLException
    {
        checkForMetaspace();
        return m_autoCommit;
    }

    public int getTransactionIsolation () throws SQLException
    {
        // AS doesn't not prevent other members from reading entries while
        // a transaction which affects those entries is in progress on another
        // member
        return TRANSACTION_READ_UNCOMMITTED;
    }

    public void setTransactionIsolation (int level) throws SQLException
    {
        checkForMetaspace();
        // do nothing, AS doesn't support setting a transaction isolation level
    }

    public void commit () throws SQLException
    {
        getLogger().logTrace("Connection.commit() called");
        checkForMetaspace();
        if (m_autoCommit)
        {
            throw new SQLNonTransientConnectionException("Cannot invoke commit method when auto commit is true.");
        }
        try
        {
            // commit any existing uncommitted transactions
            // and begin a new transaction right away as we
            // don't stop using transactions until the auto
            // commit mode is changed
            m_metaspace.commitTransaction();
            m_metaspace.beginTransaction();
        }
        catch (ASException e)
        {
            throw new SQLException(e);
        }
    }

    public void rollback () throws SQLException
    {
        getLogger().logTrace("Connection.rollback() called");
        checkForMetaspace();
        if (m_autoCommit)
        {
            throw new SQLNonTransientConnectionException("Cannot invoke rollback method when auto commit is true.");
        }
        try
        {
            // rollback any existing uncommitted transactions
            // and begin a new transaction right away as we
            // don't stop using transactions until the auto
            // commit mode is changed
            m_metaspace.rollbackTransaction();
            m_metaspace.beginTransaction();
        }
        catch (ASException e)
        {
            throw new SQLException(e);
        }
    }

    // Client info properties are not used, so none of these methods relating to
    // client info actually do anything useful
    public void setClientInfo (Properties properties) throws SQLClientInfoException
    {
        if (properties != null)
            m_clientInfo = properties;
    }

    public Properties getClientInfo () throws SQLException
    {
        checkForMetaspace();
        return m_clientInfo;
    }

    public void setClientInfo (String name, String value) throws SQLClientInfoException
    {
        m_clientInfo.setProperty(name, value);
    }

    public String getClientInfo (String name) throws SQLException
    {
        checkForMetaspace();
        return m_clientInfo.getProperty(name);
    }

    /* -- internal support methods -- */

    protected final void checkForMetaspace () throws SQLException
    {
        if (m_metaspace == null)
            throw new SQLNonTransientConnectionException("Cannot invoke method when metaspace connection is closed.");
    }

    protected boolean removeStatement (Statement statement)
    {
        return m_statements.remove(statement);
    }

    public String toString ()
    {
        return getClass().getName() + " [" + m_metaspace.toString() + "]";
    }
}
