// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.jdbc;

import java.sql.Connection;
import java.sql.RowIdLifetime;
import java.sql.SQLException;

/**
 * This class contains all of the methods of java.sql.DatabaseMetaData which are
 * not implemented by ASDatabaseMetaData.
 *
 */
public class AbstractDatabaseMetaData
{

    public <T> T unwrap (Class<T> iface) throws SQLException
    {
        return null;
    }

    public boolean isWrapperFor (Class<?> iface) throws SQLException
    {
        return false;
    }

    public String getUserName () throws SQLException
    {
        return null;
    }

    public boolean nullsAreSortedHigh () throws SQLException
    {
        return false;
    }

    public boolean nullsAreSortedLow () throws SQLException
    {
        return false;
    }

    public boolean nullsAreSortedAtStart () throws SQLException
    {
        return false;
    }

    public boolean nullsAreSortedAtEnd () throws SQLException
    {
        return false;
    }

    public String getSearchStringEscape () throws SQLException
    {
        return null;
    }

    public String getExtraNameCharacters () throws SQLException
    {
        return null;
    }

    public boolean supportsAlterTableWithAddColumn () throws SQLException
    {
        return false;
    }

    public boolean supportsAlterTableWithDropColumn () throws SQLException
    {
        return false;
    }

    public boolean supportsColumnAliasing () throws SQLException
    {
        return false;
    }

    public boolean nullPlusNonNullIsNull () throws SQLException
    {
        return false;
    }

    public boolean supportsConvert () throws SQLException
    {
        return false;
    }

    public boolean supportsConvert (int fromType, int toType) throws SQLException
    {
        return false;
    }

    public boolean supportsExpressionsInOrderBy () throws SQLException
    {
        return false;
    }

    public boolean supportsOrderByUnrelated () throws SQLException
    {
        return false;
    }

    public boolean supportsGroupBy () throws SQLException
    {
        return false;
    }

    public boolean supportsGroupByUnrelated () throws SQLException
    {
        return false;
    }

    public boolean supportsGroupByBeyondSelect () throws SQLException
    {
        return false;
    }

    public boolean supportsLikeEscapeClause () throws SQLException
    {
        return false;
    }

    public boolean supportsMultipleResultSets () throws SQLException
    {
        return false;
    }

    public boolean supportsMultipleTransactions () throws SQLException
    {
        return false;
    }

    public boolean supportsMinimumSQLGrammar () throws SQLException
    {
        return false;
    }

    public boolean supportsCoreSQLGrammar () throws SQLException
    {
        return false;
    }

    public boolean supportsExtendedSQLGrammar () throws SQLException
    {
        return false;
    }

    public boolean supportsANSI92EntryLevelSQL () throws SQLException
    {
        return false;
    }

    public boolean supportsANSI92IntermediateSQL () throws SQLException
    {
        return false;
    }

    public boolean supportsANSI92FullSQL () throws SQLException
    {
        return false;
    }

    public boolean supportsIntegrityEnhancementFacility () throws SQLException
    {
        return false;
    }

    public boolean supportsOuterJoins () throws SQLException
    {
        return false;
    }

    public boolean supportsFullOuterJoins () throws SQLException
    {
        return false;
    }

    public boolean supportsLimitedOuterJoins () throws SQLException
    {
        return false;
    }

    public boolean supportsPositionedDelete () throws SQLException
    {
        return false;
    }

    public boolean supportsPositionedUpdate () throws SQLException
    {
        return false;
    }

    public boolean supportsSelectForUpdate () throws SQLException
    {
        return false;
    }

    public boolean supportsStoredProcedures () throws SQLException
    {
        return false;
    }

    public boolean supportsSubqueriesInComparisons () throws SQLException
    {
        return false;
    }

    public boolean supportsSubqueriesInExists () throws SQLException
    {
        return false;
    }

    public boolean supportsSubqueriesInIns () throws SQLException
    {
        return false;
    }

    public boolean supportsSubqueriesInQuantifieds () throws SQLException
    {
        return false;
    }

    public boolean supportsCorrelatedSubqueries () throws SQLException
    {
        return false;
    }

    public boolean supportsUnion () throws SQLException
    {
        return false;
    }

    public boolean supportsUnionAll () throws SQLException
    {
        return false;
    }

    public boolean supportsOpenCursorsAcrossCommit () throws SQLException
    {
        return false;
    }

    public boolean supportsOpenCursorsAcrossRollback () throws SQLException
    {
        return false;
    }

    public boolean supportsOpenStatementsAcrossCommit () throws SQLException
    {
        return false;
    }

    public boolean supportsOpenStatementsAcrossRollback () throws SQLException
    {
        return false;
    }


    public int getDefaultTransactionIsolation () throws SQLException
    {
        return Connection.TRANSACTION_NONE;
    }

    public boolean supportsTransactions () throws SQLException
    {
        return false;
    }

    public boolean supportsTransactionIsolationLevel (int level) throws SQLException
    {
        return false;
    }

    public boolean supportsDataDefinitionAndDataManipulationTransactions () throws SQLException
    {
        return false;
    }

    public boolean supportsDataManipulationTransactionsOnly () throws SQLException
    {
        return false;
    }

    public boolean dataDefinitionCausesTransactionCommit () throws SQLException
    {
        return false;
    }

    public boolean dataDefinitionIgnoredInTransactions () throws SQLException
    {
        return false;
    }

    public boolean supportsResultSetType (int type) throws SQLException
    {
        return false;
    }

    public boolean supportsResultSetConcurrency (int type, int concurrency) throws SQLException
    {
        return false;
    }

    public boolean ownUpdatesAreVisible (int type) throws SQLException
    {
        return false;
    }

    public boolean ownDeletesAreVisible (int type) throws SQLException
    {
        return false;
    }

    public boolean ownInsertsAreVisible (int type) throws SQLException
    {
        return false;
    }

    public boolean othersUpdatesAreVisible (int type) throws SQLException
    {
        return false;
    }

    public boolean othersDeletesAreVisible (int type) throws SQLException
    {
        return false;
    }

    public boolean othersInsertsAreVisible (int type) throws SQLException
    {
        return false;
    }

    public boolean updatesAreDetected (int type) throws SQLException
    {
        return false;
    }

    public boolean deletesAreDetected (int type) throws SQLException
    {
        return false;
    }

    public boolean insertsAreDetected (int type) throws SQLException
    {
        return false;
    }

    public boolean supportsBatchUpdates () throws SQLException
    {
        return false;
    }

    public boolean supportsSavepoints () throws SQLException
    {
        return false;
    }

    public boolean supportsNamedParameters () throws SQLException
    {
        return false;
    }

    public boolean supportsMultipleOpenResults () throws SQLException
    {
        return false;
    }

    public boolean supportsGetGeneratedKeys () throws SQLException
    {
        return false;
    }

    public boolean supportsResultSetHoldability (int holdability) throws SQLException
    {
        return false;
    }

    public int getResultSetHoldability () throws SQLException
    {
        return 0;
    }

    public boolean locatorsUpdateCopy () throws SQLException
    {
        return false;
    }

    public boolean supportsStatementPooling () throws SQLException
    {
        return false;
    }

    public RowIdLifetime getRowIdLifetime () throws SQLException
    {
        return null;
    }

    public boolean supportsStoredFunctionsUsingCallSyntax () throws SQLException
    {
        return false;
    }

    public boolean autoCommitFailureClosesAllResultSets () throws SQLException
    {
        return false;
    }

    public boolean generatedKeyAlwaysReturned() throws SQLException
    {
        return false;
    }

}
