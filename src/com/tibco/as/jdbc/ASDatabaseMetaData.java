// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.IndexDef;
import com.tibco.as.space.IndexDef.IndexType;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;

import com.tibco.as.sql.TypeUtil;
import com.tibco.as.sql.ASSQLUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class ASDatabaseMetaData extends AbstractDatabaseMetaData implements DatabaseMetaData
{
    private static final String PRODUCT_NAME        = "TIBCO ActiveSpaces(R)";
    private String              m_productVersion;
    private int                 m_productMajorVersion = -1;
    private int                 m_productMinorVersion = -1;
    private ASConnection        m_connection;

    private final static String TABLE_TYPE = "TABLE";

    public ASDatabaseMetaData (ASConnection connection)
    {
        m_connection = connection;
        m_productVersion = Metaspace.version();
        parseVersionInfo(m_productVersion);
    }

    public Connection getConnection () throws SQLException
    {
        return m_connection;
    }

    public String getURL () throws SQLException
    {
        return Utils.getURL(m_connection.getMetaspace());
    }

    public String getDatabaseProductName () throws SQLException
    {
        return PRODUCT_NAME;
    }

    public String getDatabaseProductVersion () throws SQLException
    {
        return m_productVersion;
    }

    public int getDatabaseMajorVersion () throws SQLException
    {
        return m_productMajorVersion;
    }

    public int getDatabaseMinorVersion () throws SQLException
    {
        return m_productMinorVersion;
    }

    public String getDriverName () throws SQLException
    {
        return ASDriver.JDBC_DRIVER_NAME;
    }

    public String getDriverVersion () throws SQLException
    {
        return String.format("%d.%d.%d", ASDriver.JDBC_DRIVER_MAJOR_VERSION, ASDriver.JDBC_DRIVER_MINOR_VERSION,
                ASDriver.JDBC_DRIVER_POINT_VERSION);
    }

    public int getDriverMajorVersion ()
    {
        return ASDriver.JDBC_DRIVER_MAJOR_VERSION;
    }

    public int getDriverMinorVersion ()
    {
        return ASDriver.JDBC_DRIVER_MINOR_VERSION;
    }

    public int getJDBCMajorVersion () throws SQLException
    {
        return 4;
    }

    public int getJDBCMinorVersion () throws SQLException
    {
        return 0;
    }

    public int getSQLStateType () throws SQLException
    {
        return sqlStateSQL;
    }

    public boolean allProceduresAreCallable () throws SQLException
    {
        return false;
    }

    public boolean allTablesAreSelectable () throws SQLException
    {
        // TODO: when security is integrated, this should return true
        // only if the user has access to all of the tables
        return true;
    }

    public boolean usesLocalFiles () throws SQLException
    {
        // AS stores all tables in memory
        return false;
    }

    public boolean usesLocalFilePerTable () throws SQLException
    {
        // AS stores all tables in memory
        return false;
    }

    public boolean isReadOnly () throws SQLException
    {
        // TODO: when security is integrated, this should return true
        // only if the user has read access to the database
        return false;
    }

    public String getSQLKeywords () throws SQLException
    {
        return "";
    }

    public String getNumericFunctions () throws SQLException
    {
        // AS doesn't support math functions
        return "";
    }

    public String getStringFunctions () throws SQLException
    {
        // AS doesn't support string functions
        return "";
    }

    public String getSystemFunctions () throws SQLException
    {
        // AS doesn't support system functions
        return "";
    }

    public String getTimeDateFunctions () throws SQLException
    {
        // AS doesn't support date and time functions
        return "";
    }

    public String getSchemaTerm () throws SQLException
    {
        // AS doesn't support schemas
        return "";
    }

    public String getProcedureTerm () throws SQLException
    {
        // AS doesn't support stored procedures
        return "";
    }

    public String getCatalogTerm () throws SQLException
    {
        return "metaspace";
    }

    /**
     * Retrieves whether a catalog appears at the start of a fully qualified
     * table name.  If not, the catalog appears at the end.
     *
     * @return <code>true</code> if the catalog name appears at the beginning
     *         of a fully qualified table name; <code>false</code> otherwise
     * @exception SQLException if a database access error occurs
     */
    public boolean isCatalogAtStart() throws SQLException
    {
        // AS doesn't support fully qualified tables names
        // TODO: the JDBC driver could handle this but each SQL command
        // would have to be parsed before being executed
        return false;
    }

    /**
     * Retrieves the <code>String</code> that this database uses as the
     * separator between a catalog and table name.
     *
     * @return the separator string
     * @exception SQLException if a database access error occurs
     */
    public String getCatalogSeparator() throws SQLException
    {
        // There is no separator since AS doesn't support fully qualified
        // table names.
        return "";
    }

    public boolean supportsSchemasInDataManipulation () throws SQLException
    {
        // AS doesn't support schemas
        return false;
    }

    public boolean supportsSchemasInProcedureCalls () throws SQLException
    {
        // AS doesn't support schemas or stored procedures
        return false;
    }

    public boolean supportsSchemasInTableDefinitions () throws SQLException
    {
        // AS doesn't support schemas
        return false;
    }

    public boolean supportsSchemasInIndexDefinitions () throws SQLException
    {
        // AS doesn't support schemas
        return false;
    }

    public boolean supportsSchemasInPrivilegeDefinitions () throws SQLException
    {
        // AS doesn't support schemas
        // TODO: When security is integrated, would this still be true?
        return false;
    }

    public boolean supportsCatalogsInDataManipulation() throws SQLException
    {
        return false;
    }

    public boolean supportsCatalogsInIndexDefinitions() throws SQLException
    {
        return false;
    }

    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException
    {
        return false;
    }

    public boolean supportsCatalogsInProcedureCalls() throws SQLException
    {
        return false;
    }

    public boolean supportsCatalogsInTableDefinitions() throws SQLException
    {
        return false;
    }

    public boolean supportsNonNullableColumns () throws SQLException
    {
        // AS only support specifying whether a column isNullable
        return false;
    }

    public boolean supportsTableCorrelationNames () throws SQLException
    {
        // Support for table correlation names is provided through the
        // example JDBC driver
        return true;
    }

    public boolean supportsDifferentTableCorrelationNames () throws SQLException
    {
        // No requirement for the correlation name to be different from the
        // actual table name
        return false;
    }

    public boolean supportsMixedCaseIdentifiers () throws SQLException
    {
        // AS uses identifiers in mixed case
        return true;
    }

    public boolean storesMixedCaseIdentifiers () throws SQLException
    {
        // AS treats mixed case unquoted SQL identifiers as case sensitive
        return false;
    }

    public boolean supportsMixedCaseQuotedIdentifiers () throws SQLException
    {
        // AS treats quoted mixed case SQL identifiers as case sensitive and stores them in mixed case
        return true;
    }

    public boolean storesMixedCaseQuotedIdentifiers () throws SQLException
    {
        // AS treats mixed case quoted SQL identifiers as case sensitive and stores them in mixed case
        return false;
    }

    public boolean storesUpperCaseIdentifiers () throws SQLException
    {
        // AS treats mixed case unquoted SQL identifiers as case sensitive
        return false;
    }

    public boolean storesLowerCaseIdentifiers () throws SQLException
    {
        // AS treats mixed case unquoted SQL identifiers as case sensitive
        return false;
    }

    public boolean storesUpperCaseQuotedIdentifiers () throws SQLException
    {
        // AS treats mixed case quoted SQL identifiers as case sensitive and stores them in mixed case
        return false;
    }

    public boolean storesLowerCaseQuotedIdentifiers () throws SQLException
    {
        // AS treats mixed case quoted SQL identifiers as case sensitive and stores them in mixed case
        return false;
    }


    public int getMaxBinaryLiteralLength () throws SQLException
    {
        return 0;
    }

    public int getMaxCharLiteralLength () throws SQLException
    {
        return 0;
    }

    public int getMaxColumnNameLength () throws SQLException
    {
        // AS uses Java string for column names which maps to VARCHAR
        return Utils.getSQLTypePrecision(java.sql.Types.VARCHAR);
    }

    public int getMaxColumnsInGroupBy () throws SQLException
    {
        return 0;
    }

    public int getMaxColumnsInIndex () throws SQLException
    {
        return 0;
    }

    public int getMaxColumnsInOrderBy () throws SQLException
    {
        return 0;
    }

    public int getMaxColumnsInSelect () throws SQLException
    {
        return 0;
    }

    public int getMaxColumnsInTable () throws SQLException
    {
        return 0;
    }

    public int getMaxConnections () throws SQLException
    {
        // For AS this is limited by what your hardware supports
        return 0;
    }

    public int getMaxCursorNameLength () throws SQLException
    {
        return 0;
    }

    public int getMaxIndexLength () throws SQLException
    {
        return 0;
    }

    public int getMaxSchemaNameLength () throws SQLException
    {
        return 0;
    }

    public int getMaxProcedureNameLength () throws SQLException
    {
        return 0;
    }

    public int getMaxCatalogNameLength () throws SQLException
    {
        return 0;
    }

    public int getMaxRowSize () throws SQLException
    {
        return 0;
    }

    public boolean doesMaxRowSizeIncludeBlobs () throws SQLException
    {
        return false;
    }

    public int getMaxStatementLength () throws SQLException
    {
        return 0;
    }

    public int getMaxStatements () throws SQLException
    {
        return 0;
    }

    public int getMaxTableNameLength () throws SQLException
    {
        return 0;
    }

    public int getMaxTablesInSelect () throws SQLException
    {
        return 0;
    }

    public int getMaxUserNameLength () throws SQLException
    {
        return 0;
    }

    public String getIdentifierQuoteString () throws SQLException
    {
        // The AS JDBC example driver does not use quoting for identifiers
        // so return a space
        return " ";
    }

    public static final String[] GET_CATALOGS_COLUMN_NAMES = {
        "TABLE_CAT"
    };
    public static final Integer[] GET_CATALOGS_COLUMN_TYPES = {
        Types.VARCHAR
    };
    public ResultSet getCatalogs() throws SQLException
    {
        // Assign the static name and type arrays to variables with shorter names so they
        // are easier to refer to when we use them in this method.
        String[] cnames = GET_CATALOGS_COLUMN_NAMES;
        Integer[] ctypes = GET_CATALOGS_COLUMN_TYPES;
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(cnames, ctypes);

        if (m_connection != null && m_connection.getMetaspace() != null)
        {
            // always provide as much column information in the result set metadata as possible
            // columnTuples holds the rows of column metadata
            List<Tuple[]> columnTuples = new ArrayList<Tuple[]>();
            // columnInfo holds metspace, space & field info for each row of column metadata
            List<Tuple> columnInfo = new ArrayList<Tuple>();

            String msName = m_connection.getMetaspace().getName();
            if (!msName.isEmpty())
            {
                columnTuples.add(new Tuple[] {
                        Utils.createColumnEntry(cnames[0], msName, ctypes[0])
                });
                // gather AS info for each row, this makes it easy to retrieve
                // the metaspace and space information for the field of the space
                // being described in each row of metadata
                {
                    Tuple columnTuple = Tuple.create();
                    columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
                    columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
                    columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
                    columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
                    columnInfo.add(columnTuple);
                }
                rs.populateResultSet(columnTuples, columnInfo);
            }
        }
        return rs;
    }

    public static final String[] GET_PROCEDURES_COLUMN_NAMES = {
        "PROCEDURE_CAT",
        "PROCEDURE_SCHEM",
        "PROCEDURE_NAME",
        "RESERVED1",
        "RESERVED2",
        "RESERVED3",
        "REMARKS",
        "PROCEDURE_TYPE",
        "SPECIFIC_NAME"
    };
    public static final Integer[] GET_PROCEDURES_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.OTHER,
        Types.OTHER,
        Types.OTHER,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.VARCHAR
    };
    public ResultSet getProcedures (String catalog, String schemaPattern, String procedureNamePattern)
            throws SQLException
    {
        // AS doesn't support procedures
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_PROCEDURES_COLUMN_NAMES, GET_PROCEDURES_COLUMN_TYPES);
        return rs;
    }

    public static final String[] GET_PROCEDURES_COLUMNS_COLUMN_NAMES = {
        "PROCEDURE_CAT",
        "PROCEDURE_SCHEM",
        "PROCEDURE_NAME",
        "COLUMN_NAME",
        "COLUMN_TYPE",
        "DATA_TYPE",
        "TYPE_NAME",
        "PRECISION",
        "LENGTH",
        "SCALE",
        "RADIX",
        "NULLABLE",
        "REMARKS",
        "COLUMN_DEF",
        "SQL_DATA_TYPE",
        "SQL_DATETIME_SUB",
        "CHAR_OCTET_LENGTH",
        "ORDINAL_POSITION",
        "IS_NULLABLE",
        "SPECIFIC_NAME"
    };
    public static final Integer[] GET_PROCEDURES_COLUMNS_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.INTEGER,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet getProcedureColumns (String catalog, String schemaPattern, String procedureNamePattern,
            String columnNamePattern) throws SQLException
    {
        // AS doesn't support procedures
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_PROCEDURES_COLUMNS_COLUMN_NAMES, GET_PROCEDURES_COLUMNS_COLUMN_TYPES);
        return rs;
    }

    public static final String[] GET_SCHEMAS_COLUMN_NAMES = {
        "TABLE_SCHEM",
        "TABLE_CATALOG"
    };
    public static final Integer[] GET_SCHEMAS_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet getSchemas () throws SQLException
    {
        // AS doesn't support schemas
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_SCHEMAS_COLUMN_NAMES, GET_SCHEMAS_COLUMN_TYPES);
        return rs;
    }

    public ResultSet getSchemas (String catalog, String schemaPattern) throws SQLException
    {
        // AS doesn't support schemas
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_SCHEMAS_COLUMN_NAMES, GET_SCHEMAS_COLUMN_TYPES);
        return rs;
    }

    public static final String[] GET_TABLE_TYPES_COLUMN_NAMES = {
        "TABLE_TYPE"
    };
    public static final Integer[] GET_TABLE_TYPES_COLUMN_TYPES = {
        Types.VARCHAR
    };
    public ResultSet getTableTypes() throws SQLException
    {
        // Assign the static name and type arrays to variables with shorter names so they
        // are easier to refer to when we use them in this method.
        String[] cnames = GET_TABLE_TYPES_COLUMN_NAMES;
        Integer[] ctypes = GET_TABLE_TYPES_COLUMN_TYPES;
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(cnames, ctypes);

        // always provide as much column information in the result set metadata as possible
        // columnTuples holds the rows of column metadata
        List<Tuple[]> columnTuples = new ArrayList<Tuple[]>();
        // columnInfo holds metspace, space & field info for each row of column metadata
        List<Tuple> columnInfo = new ArrayList<Tuple>();

        String msName = m_connection.getMetaspace().getName();
        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], TABLE_TYPE, ctypes[0])
        });
        // gather AS info for each row, this makes it easy to retrieve
        // the metaspace and space information for the field of the space
        // being described in each row of metadata
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }
        rs.populateResultSet(columnTuples, columnInfo);
        return rs;
    }

    public static final String[] GET_TABLES_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "TABLE_TYPE",
        "REMARKS",
        "TYPE_CAT",
        "TYPE_SCHEM",
        "TYPE_NAME",
        "SELF_REFERENCING_COL_NAME",
        "REF_GENERATION"
    };
    public static final Integer[] GET_TABLES_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet getTables(String catalog, String schemaPattern,
                        String tableNamePattern, String types[]) throws SQLException
    {
        // Assign the static name and type arrays to variables with shorter names so they
        // are easier to refer to when we use them in this method.
        String[] cnames = GET_TABLES_COLUMN_NAMES;
        Integer[] ctypes = GET_TABLES_COLUMN_TYPES;
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(cnames, ctypes);

        if (!checkCatalogName(catalog))
        {
            return rs;
        }
        if (!checkSchemaPattern(schemaPattern))
        {
            return rs;  // we don't support schemas
        }
        // check the given table types
        if (types != null)
        {
            boolean found = false;
            for (String t: types)
            {
                if (TABLE_TYPE.equals(t))
                {
                    found = true;
                    break;
                }
            }
            if (!found)
                return rs;
        }
        if (!checkConnection())
        {
            return rs;
        }

        try
        {
            String msName = m_connection.getMetaspace().getName();
            Collection<String> spaceNames = m_connection.getMetaspace().getUserSpaceNames();
            if (spaceNames != null && !spaceNames.isEmpty())
            {
                // always provide as much column information in the result set metadata as possible
                // columnTuples holds the rows of column metadata
                List<Tuple[]> columnTuples = new ArrayList<Tuple[]>();
                // columnInfo holds metspace, space & field info for each row of column metadata
                List<Tuple> columnInfo = new ArrayList<Tuple>();

                Iterator<String> spaceNameIter = spaceNames.iterator();
                while (spaceNameIter.hasNext())
                {
                    String spaceName = spaceNameIter.next();
                    if (Utils.matchPattern(tableNamePattern, spaceName))
                    {
                        // TODO: tables should be sorted by TABLE_NAME column
                        columnTuples.add(new Tuple[] {
                                Utils.createColumnEntry(cnames[0], msName, ctypes[0]),
                                Utils.createColumnEntry(cnames[1], null, ctypes[1]),
                                Utils.createColumnEntry(cnames[2], spaceName, ctypes[2]),
                                Utils.createColumnEntry(cnames[3], "TABLE", ctypes[3]),
                                Utils.createColumnEntry(cnames[4], "", ctypes[4]),
                                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                                Utils.createColumnEntry(cnames[6], null, ctypes[6]),
                                Utils.createColumnEntry(cnames[7], null, ctypes[7]),
                                Utils.createColumnEntry(cnames[8], null, ctypes[8]),
                                Utils.createColumnEntry(cnames[9], null, ctypes[9])
                        });
                        // gather AS info for each row, this makes it easy to retrieve
                        // the metaspace and space information for the field of the space
                        // being described in each row of metadata
                        {
                            Tuple columnTuple = Tuple.create();
                            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
                            columnTuple.put(ASSQLUtils.TABLE_NAME, spaceName);
                            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
                            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
                            columnInfo.add(columnTuple);
                        }
                    }
                    if (!columnTuples.isEmpty())
                        rs.populateResultSet(columnTuples, columnInfo);
                }
            }
        }
        catch(ASException e)
        {
            throw new SQLException(e);
        }
        return rs;
    }

    public static final String[] GET_TABLE_PRIVILEGES_COLUMN_NAMES = {
            "TABLE_CAT",
            "TABLE_SCHEM",
            "TABLE_NAME",
            "GRANTOR",
            "GRANTEE",
            "PRIVILEGE",
            "IS_GRANTABLE"
        };
        public static final Integer[] GET_TABLE_PRIVILEGES_COLUMN_TYPES = {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR
        };
    public ResultSet getTablePrivileges (String catalog, String schemaPattern, String tableNamePattern)
            throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_TABLE_PRIVILEGES_COLUMN_NAMES, GET_TABLE_PRIVILEGES_COLUMN_TYPES);
        return rs;
    }

    public static final String[] GET_BEST_ROW_IDENTIFIER_COLUMN_NAMES = {
        "SCOPE",
        "COLUMN_NAME",
        "DATA_TYPE",
        "TYPE_NAME",
        "COLUMN_SIZE",
        "BUFFER_LENGTH",
        "DECIMAL_DIGITS",
        "PSEUDO_COLUMN"
    };
    public static final Integer[] GET_BEST_ROW_IDENTIFIER_COLUMN_TYPES = {
        Types.SMALLINT,
        Types.VARCHAR,
        Types.INTEGER,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.SMALLINT,
        Types.SMALLINT
    };
    public ResultSet getBestRowIdentifier (String catalog, String schema, String table, int scope, boolean nullable)
            throws SQLException
    {
        // For AS, we only have the primary key to uniquely identify an entry in a table

        // Assign the static name and type arrays to variables with shorter names so they
        // are easier to refer to when we use them in this method.
        String[] cnames = GET_BEST_ROW_IDENTIFIER_COLUMN_NAMES;
        Integer[] ctypes = GET_BEST_ROW_IDENTIFIER_COLUMN_TYPES;
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(cnames, ctypes);

        if (!checkConnection())
            return rs;
        if (!checkCatalogName(catalog))
            return rs;
        if (schema != null && !schema.isEmpty())
            return rs;
        if (!checkTableName(table))
            return rs;
        // TODO: ignore checking scope for now
        //if (scope != bestRowSession)
        //  return rs; // only support bestRowSession?

        // find out the key fields for this table
        String msName = m_connection.getMetaspace().getName();
        Collection<String> keyFieldNames = null;
        SpaceDef spaceDef = null;
        try
        {
            spaceDef = m_connection.getMetaspace().getSpace(table).getSpaceDef();
            if (spaceDef != null)
            {
                KeyDef keyDef = spaceDef.getKeyDef();
                if (keyDef == null)
                    return rs;
                keyFieldNames = keyDef.getFieldNames();
            }
        }
        catch (ASException e)
        {
            throw new SQLException(e);
        }
        // if we don't have any key fields, we don't have a best row identifier
        if (keyFieldNames == null)
            return rs;

        // always provide as much column information in the result set metadata as possible
        // columnTuples holds the rows of column metadata
        List<Tuple[]> columnTuples = new ArrayList<Tuple[]>();
        // columnInfo holds metspace, space & field info for each row of column metadata
        List<Tuple> columnInfo = new ArrayList<Tuple>();

        Iterator<String> keyFieldNameIter = keyFieldNames.iterator();
        while (keyFieldNameIter.hasNext())
        {
            String keyFieldName = keyFieldNameIter.next();
            FieldDef fieldDef = spaceDef.getFieldDef(keyFieldName);
            if (!nullable && fieldDef.isNullable())
                continue;  // told not to include nullable fields, shouldn't hit as key fields can't be nullable

            int sqlType = TypeUtil.getSQLType(fieldDef);
            int columnSize = Utils.getSQLTypePrecision(sqlType);
            String ddStr = null;
            int dd = Utils.getSQLTypeDecimalDigits(sqlType);
            if (dd != -1)
                ddStr = "" + dd;

            columnTuples.add(new Tuple[] {
                    Utils.createColumnEntry(cnames[0], "" + bestRowSession, ctypes[0]),
                    Utils.createColumnEntry(cnames[1], keyFieldName, ctypes[1]),
                    Utils.createColumnEntry(cnames[2], table, ctypes[2]),
                    Utils.createColumnEntry(cnames[3], "" + sqlType, ctypes[3]),
                    Utils.createColumnEntry(cnames[4], "" + columnSize, ctypes[4]),
                    Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                    Utils.createColumnEntry(cnames[6], ddStr, ctypes[6]),
                    Utils.createColumnEntry(cnames[7], "" + bestRowNotPseudo, ctypes[7])
            });
            // gather AS info for each row, this makes it easy to retrieve
            // the metaspace and space information for the field of the space
            // being described in each row of metadata
            {
                Tuple columnTuple = Tuple.create();
                columnTuple.put(ASSQLUtils.COLUMN_NAME, keyFieldName);
                columnTuple.put(ASSQLUtils.TABLE_NAME, table);
                columnTuple.put(ASSQLUtils.COLUMN_ALIAS, keyFieldName);
                columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
                columnInfo.add(columnTuple);
            }
        }
        if (!columnTuples.isEmpty())
            rs.populateResultSet(columnTuples, columnInfo);
        return rs;
    }

    // metadata column and type information for the getColumns method
    public static final String[] GET_COLUMNS_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "COLUMN_NAME",
        "DATA_TYPE",
        "TYPE_NAME",
        "COLUMN_SIZE",
        "BUFFER_LENGTH",
        "DECIMAL_DIGITS",
        "NUM_PREC_RADIX",
        "NULLABLE",
        "REMARKS",
        "COLUMN_DEF",
        "SQL_DATA_TYPE",
        "SQL_DATETIME_SUB",
        "CHAR_OCTET_LENGTH",
        "ORDINAL_POSITION",
        "IS_NULLABLE",
        "SCOPE_CATALOG",
        "SCOPE_SCHEMA",
        "SCOPE_TABLE",
        "SOURCE_DATA_TYPE",
        "IS_AUTOINCREMENT",
        "IS_GENERATEDCOLUMN"
    };
    public static final Integer[] GET_COLUMNS_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet  getColumns (String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
            throws SQLException
    {
        // Assign the static name and type arrays to variables with shorter names so they
        // are easier to refer to when we use them in this method.
        String[] cnames = GET_COLUMNS_COLUMN_NAMES;
        Integer[] ctypes = GET_COLUMNS_COLUMN_TYPES;
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(cnames, ctypes);

        ResultSet tableRs = getTables(catalog, schemaPattern, tableNamePattern, null);
        if (!tableRs.next())
        {
            // there are no tables matching the catalog, schemaPattern, or tableNamePattern
            return rs;
        }
        else
        {
            // reset the ResultSet to before the beginning
            tableRs.beforeFirst();
        }

        // always provide as much column information in the result set metadata as possible
        // columnTuples holds the rows of column metadata
        List<Tuple[]> columnTuples = new ArrayList<Tuple[]>();
        // columnInfo holds metspace, space & field info for each row of column metadata
        List<Tuple> columnInfo = new ArrayList<Tuple>();

        // get column info for each table in the metaspace
        while (tableRs.next())
        {
            try
            {
                // retrieve the column info for each table
                String msName = m_connection.getMetaspace().getName();

                String spaceName = tableRs.getString(3);
                SpaceDef spaceDef = m_connection.getMetaspace().getSpace(spaceName).getSpaceDef();
                Collection<FieldDef> fieldDefs = spaceDef.getFieldDefs();

                // we need to keep track of the position of each column
                // the field defs are returned as an ArrayList so each column's index position
                // is the same as the order in which it is read
                int columnIndex = 0;
                Iterator<FieldDef> fieldDefIter = fieldDefs.iterator();
                while (fieldDefIter.hasNext())
                {
                    FieldDef fieldDef = fieldDefIter.next();
                    String fieldName = fieldDef.getName();
                    if (Utils.matchPattern(columnNamePattern, fieldName))
                    {
                        columnIndex++;
                        int sqlType = TypeUtil.getSQLType(fieldDef);
                        String sqlTypeName = Utils.getSQLTypeName(sqlType);
                        int columnSize = Utils.getSQLTypePrecision(sqlType);

                        // some column values have an integer type but could also be null
                        String ddStr = null;
                        int dd = Utils.getSQLTypeDecimalDigits(sqlType);
                        if (dd != -1)
                            ddStr = "" + dd;

                        String radixStr = null;
                        int radix = Utils.getSQLTypeRadix(sqlType);
                        if (radix != -1)
                            radixStr = "" + radix;

                        int nullable = fieldDef.isNullable() ? DatabaseMetaData.columnNullable : DatabaseMetaData.columnNoNulls;
                        String isNullable = fieldDef.isNullable() ? "YES" : "NO";
                        // each metadata column entry has a name, value and SQL type
                        columnTuples.add(new Tuple[] {
                                Utils.createColumnEntry(cnames[0], msName, ctypes[0]),
                                Utils.createColumnEntry(cnames[1], null, ctypes[1]),
                                Utils.createColumnEntry(cnames[2], spaceName, ctypes[2]),
                                Utils.createColumnEntry(cnames[3], fieldName, ctypes[3]),
                                Utils.createColumnEntry(cnames[4], "" + sqlType, ctypes[4]),
                                Utils.createColumnEntry(cnames[5], sqlTypeName, ctypes[5]),
                                Utils.createColumnEntry(cnames[6], "" + columnSize, ctypes[6]),
                                Utils.createColumnEntry(cnames[7], null, ctypes[7]), // not used
                                Utils.createColumnEntry(cnames[8], ddStr, ctypes[8]),
                                Utils.createColumnEntry(cnames[9], radixStr, ctypes[9]),
                                Utils.createColumnEntry(cnames[10], "" + nullable, ctypes[10]),
                                Utils.createColumnEntry(cnames[11], null, ctypes[11]),
                                Utils.createColumnEntry(cnames[12], null, ctypes[12]),
                                Utils.createColumnEntry(cnames[13], null, ctypes[13]), // not used
                                Utils.createColumnEntry(cnames[14], null, ctypes[14]), //not used
                                Utils.createColumnEntry(cnames[15], "0", ctypes[15]), // no limit
                                Utils.createColumnEntry(cnames[16], "" + columnIndex, ctypes[16]),
                                Utils.createColumnEntry(cnames[17], isNullable, ctypes[17]),
                                Utils.createColumnEntry(cnames[18], null, ctypes[18]),
                                Utils.createColumnEntry(cnames[19], null, ctypes[19]),
                                Utils.createColumnEntry(cnames[20], null, ctypes[20]),
                                Utils.createColumnEntry(cnames[21], null, ctypes[21]),
                                Utils.createColumnEntry(cnames[22], "NO", ctypes[22]),
                                Utils.createColumnEntry(cnames[23], "NO", ctypes[23])
                                });
                        // gather AS info for each row, this makes it easy to retrieve
                        // the metaspace and space information for the field of the space
                        // being described in each row of metadata
                        {
                            Tuple columnTuple = Tuple.create();
                            columnTuple.put(ASSQLUtils.COLUMN_NAME, fieldName);
                            columnTuple.put(ASSQLUtils.TABLE_NAME, spaceName);
                            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, fieldName);
                            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
                            columnInfo.add(columnTuple);
                        }
                    }
                }
            }
            catch(ASException e)
            {
                throw new SQLException(e);
            }
        }
        if (!columnTuples.isEmpty())
            rs.populateResultSet(columnTuples, columnInfo);
        return rs;
    }

    // metadata column and type information for the getColumnPrivileges method
    public static final String[] GET_COLUMN_PRIVILEGES_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "COLUMN_NAME",
        "GRANTOR",
        "GRANTEE",
        "PRIVILEGE",
        "IS_GRANTABLE"
    };
    public static final Integer[] GET_COLUMN_PRIVILEGES_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet getColumnPrivileges (String catalog, String schema, String table, String columnNamePattern)
            throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_COLUMN_PRIVILEGES_COLUMN_NAMES, GET_COLUMN_PRIVILEGES_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getVersionColumns method
    public static final String[] GET_VERSION_COLUMNS_COLUMN_NAMES = {
        "SCOPE",
        "COLUMN_NAME",
        "DATA_TYPE",
        "TYPE_NAME",
        "COLUMN_SIZE",
        "BUFFER_LENGTH",
        "DECIMAL_DIGITS",
        "PSEUDO_COLUMN"
    };
    public static final Integer[] GET_VERSION_COLUMNS_COLUMN_TYPES = {
        Types.SMALLINT,
        Types.VARCHAR,
        Types.INTEGER,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.SMALLINT,
        Types.SMALLINT
    };
    public ResultSet getVersionColumns (String catalog, String schema, String table) throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_VERSION_COLUMNS_COLUMN_NAMES, GET_VERSION_COLUMNS_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getPrimaryKeys method
    public static final String[] GET_PRIMARY_KEYS_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "COLUMN_NAME",
        "KEY_SEQ",
        "PK_NAME"
    };
    public static final Integer[] GET_PRIMARY_KEYS_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.VARCHAR
    };
    public ResultSet getPrimaryKeys (String catalog, String schema, String table) throws SQLException
    {
        // Assign the static name and type arrays to variables with shorter names so they
        // are easier to refer to when we use them in this method.
        String[] cnames = GET_PRIMARY_KEYS_COLUMN_NAMES;
        Integer[] ctypes = GET_PRIMARY_KEYS_COLUMN_TYPES;
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(cnames, ctypes);

        if (!checkConnection())
            return rs;
        if (!checkCatalogName(catalog))
            return rs;
        if (schema != null && !schema.isEmpty())
            return rs;
        if (!checkTableName(table))
            return rs;

        String msName = m_connection.getMetaspace().getName();
        Collection<String> keyFieldNames = null;
        try
        {
            SpaceDef spaceDef = m_connection.getMetaspace().getSpace(table).getSpaceDef();
            if (spaceDef != null)
            {
                KeyDef keyDef = spaceDef.getKeyDef();
                if (keyDef == null)
                    return rs;
                keyFieldNames = keyDef.getFieldNames();
            }
        }
        catch (ASException e)
        {
            throw new SQLException(e);
        }
        if (keyFieldNames == null)
            return rs;

        // always provide as much column information in the result set metadata as possible
        // columnTuples holds the rows of column metadata
        List<Tuple[]> columnTuples = new ArrayList<Tuple[]>();
        // columnInfo holds metspace, space & field info for each row of column metadata
        List<Tuple> columnInfo = new ArrayList<Tuple>();

        // we need to keep track of the position of each key field
        // the key field names are returned as an ArrayList so each key field's index position
        // is the same as the order in which it is read
        int columnIndex = 0;
        Iterator<String> keyFieldNameIter = keyFieldNames.iterator();
        while (keyFieldNameIter.hasNext())
        {
            String keyFieldName = keyFieldNameIter.next();
            columnIndex++;
            columnTuples.add(new Tuple[] {
                    Utils.createColumnEntry(cnames[0], msName, ctypes[0]),
                    Utils.createColumnEntry(cnames[1], null, ctypes[1]),
                    Utils.createColumnEntry(cnames[2], table, ctypes[2]),
                    Utils.createColumnEntry(cnames[3], keyFieldName, ctypes[3]),
                    Utils.createColumnEntry(cnames[4], "" + columnIndex, ctypes[4]),
                    Utils.createColumnEntry(cnames[5], null, ctypes[5])
            });
            // gather AS info for each row, this makes it easy to retrieve
            // the metaspace and space information for the field of the space
            // being described in each row of metadata
            {
                Tuple columnTuple = Tuple.create();
                columnTuple.put(ASSQLUtils.COLUMN_NAME, keyFieldName);
                columnTuple.put(ASSQLUtils.TABLE_NAME, table);
                columnTuple.put(ASSQLUtils.COLUMN_ALIAS, keyFieldName);
                columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
                columnInfo.add(columnTuple);
            }
        }
        if (!columnTuples.isEmpty())
            rs.populateResultSet(columnTuples, columnInfo);
        return rs;
    }

    // metadata column and type information for the getImportedKeys,
    // getExportedKeys, and getCrossReference methods
    public static final String[] GET_FOREIGN_KEY_COLUMN_NAMES = {
        "PKTABLE_CAT",
        "PKTABLE_SCHEM",
        "PKTABLE_NAME",
        "PKCOLUMN_NAME",
        "FKTABLE_CAT",
        "FKTABLE_SCHEM",
        "FKTABLE_NAME",
        "FKCOLUMN_NAME",
        "KEY_SEQ",
        "UPDATE_RULE",
        "DELETE_RULE",
        "FK_NAME",
        "PK_NAME",
        "DEFERRABILITY"
    };
    public static final Integer[] GET_FOREIGN_KEY_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT
    };
    public ResultSet getImportedKeys (String catalog, String schema, String table) throws SQLException
    {
        // TODO: add support for foreign keys when security is integrated
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_FOREIGN_KEY_COLUMN_NAMES, GET_FOREIGN_KEY_COLUMN_TYPES);
        return rs;
    }

    public ResultSet getExportedKeys (String catalog, String schema, String table) throws SQLException
    {
        // TODO: add support for foreign keys when security is integrated
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_FOREIGN_KEY_COLUMN_NAMES, GET_FOREIGN_KEY_COLUMN_TYPES);
        return rs;
    }

    public ResultSet getCrossReference (String parentCatalog, String parentSchema, String parentTable,
            String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException
    {
        // TODO: when security is integrated, can we implement this?
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_FOREIGN_KEY_COLUMN_NAMES, GET_FOREIGN_KEY_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getIndexInfo method
    public static final String[] GET_INDEX_INFO_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "NON_UNIQUE",
        "INDEX_QUALIFIER",
        "INDEX_NAME",
        "TYPE",
        "ORDINAL_POSITION",
        "COLUMN_NAME",
        "ASC_OR_DESC",
        "CARDINALITY",
        "PAGES",
        "FILTER_CONDITION"
    };
    public static final Integer[] GET_INDEX_INFO_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.BIT,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.VARCHAR
    };
    public ResultSet getIndexInfo (String catalog, String schema, String table, boolean unique, boolean approximate)
            throws SQLException
    {
        // Assign the static name and type arrays to variables with shorter names so they
        // are easier to refer to when we use them in this method.
        String[] cnames = GET_INDEX_INFO_COLUMN_NAMES;
        Integer[] ctypes = GET_INDEX_INFO_COLUMN_TYPES;
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(cnames, ctypes);

        if (!checkConnection())
            return rs;
        if (!checkCatalogName(catalog))
            return rs;
        if (schema != null && !schema.isEmpty())
            return rs;
        if (!checkTableName(table))
            return rs;

        String msName = m_connection.getMetaspace().getName();
        Collection<IndexDef> indexDefList = null;
        Collection<String> keyFieldNames = null;
        SpaceDef spaceDef = null;
        try
        {
            spaceDef = m_connection.getMetaspace().getSpace(table).getSpaceDef();
            if (spaceDef != null)
            {
                indexDefList = spaceDef.getIndexDefList();
                KeyDef keyDef = spaceDef.getKeyDef();
                if (keyDef != null)
                    keyFieldNames = keyDef.getFieldNames();
            }
        }
        catch (ASException e)
        {
            throw new SQLException(e);
        }
        if (indexDefList == null)
            return rs;

        // always provide as much column information in the result set metadata as possible
        // columnTuples holds the rows of column metadata
        List<Tuple[]> columnTuples = new ArrayList<Tuple[]>();
        // columnInfo holds metspace, space & field info for each row of column metadata
        List<Tuple> columnInfo = new ArrayList<Tuple>();

        // TODO: Order results by NON_UNIQUE, TYPE, INDEX_NAME and ORDINAL_POSITION
        Iterator<IndexDef> indexDefIter = indexDefList.iterator();
        while (indexDefIter.hasNext())
        {
            IndexDef indexDef = indexDefIter.next();
            String indexName = indexDef.getName();
            // AS supports index types of hash or tree
            short itype = DatabaseMetaData.tableIndexOther;
            if (indexDef.getIndexType().equals(IndexType.HASH))
                itype = DatabaseMetaData.tableIndexHashed;
            // If all of the fields in the index are key fields, then their values must be unique
            // so we would not have a non-unique index. That's about all we can check for regarding
            // non-uniqueness.
            Collection<String> fieldNames = indexDef.getFieldNames();
            String valuesNonUnique = checkFieldsAreKeys(fieldNames, keyFieldNames) ? "0" : "1";
            if (unique && valuesNonUnique.equals("0"))
                continue;

            // we need to keep track of the position of each field within the index
            // the index field names are returned as an ArrayList so each index field's position
            // is the same as the order in which it is read
            int columnPosition = 0;
            Iterator<String> fieldNamesIter = fieldNames.iterator();
            while (fieldNamesIter.hasNext())
            {
                String fieldName = fieldNamesIter.next();
                columnPosition++;
                columnTuples.add(new Tuple[] {
                        Utils.createColumnEntry(cnames[0], msName, ctypes[0]),
                        Utils.createColumnEntry(cnames[1], null, ctypes[1]),
                        Utils.createColumnEntry(cnames[2], table, ctypes[2]),
                        Utils.createColumnEntry(cnames[3], valuesNonUnique, ctypes[3]),
                        Utils.createColumnEntry(cnames[4], null, ctypes[4]),
                        Utils.createColumnEntry(cnames[5], indexName, ctypes[5]),
                        Utils.createColumnEntry(cnames[6], "" + itype, ctypes[6]),
                        Utils.createColumnEntry(cnames[7], "" + columnPosition, ctypes[7]),
                        Utils.createColumnEntry(cnames[8], fieldName, ctypes[8]),
                        Utils.createColumnEntry(cnames[9], null, ctypes[9]),
                        Utils.createColumnEntry(cnames[10], null, ctypes[10]), // AS doesn't sort
                        Utils.createColumnEntry(cnames[11], null, ctypes[11]), // AS doesn't use concept
                        Utils.createColumnEntry(cnames[12], indexName, ctypes[12])
                });
                // gather AS info for each row, this makes it easy to retrieve
                // the metaspace and space information for the field of the space
                // being described in each row of metadata
                {
                    Tuple columnTuple = Tuple.create();
                    columnTuple.put(ASSQLUtils.COLUMN_NAME, fieldName);
                    columnTuple.put(ASSQLUtils.TABLE_NAME, table);
                    columnTuple.put(ASSQLUtils.COLUMN_ALIAS, fieldName);
                    columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
                    columnInfo.add(columnTuple);
                }
            }
        }
        if (!columnTuples.isEmpty())
            rs.populateResultSet(columnTuples, columnInfo);
        return rs;
    }

    // metadata column and type information for the getTypeInfo metod
    public static final String[] GET_TYPE_INFO_COLUMN_NAMES = {
        "TYPE_NAME",
        "DATA_TYPE",
        "PRECISION",
        "LITERAL_PREFIX",
        "LITERAL_SUFFIX",
        "CREATE_PARAMS",
        "NULLABLE",
        "CASE_SENSITIVE",
        "SEARCHABLE",
        "UNSIGNED_ATTRIBUTE",
        "FIXED_PREC_SCALE",
        "AUTO_INCREMENT",
        "LOCAL_TYPE_NAME",
        "MINIMUM_SCALE",
        "MAXIMUM_SCALE",
        "SQL_DATA_TYPE",
        "SQL_DATETIME_SUB",
        "NUM_PREC_RADIX"
    };
    public static final Integer[] GET_TYPE_INFO_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.SMALLINT,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.BIT,
        Types.SMALLINT,
        Types.BIT,
        Types.BIT,
        Types.BIT,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.SMALLINT
    };
    public ResultSet getTypeInfo () throws SQLException
    {
        // Assign the static name and type arrays to variables with shorter names so they
        // are easier to refer to when we use them in this method.
        String[] cnames = GET_TYPE_INFO_COLUMN_NAMES;
        Integer[] ctypes = GET_TYPE_INFO_COLUMN_TYPES;
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(cnames, ctypes);

        // always provide as much column information in the result set metadata as possible
        // columnTuples holds the rows of column metadata
        List<Tuple[]> columnTuples = new ArrayList<Tuple[]>();
        // columnInfo holds metspace, space & field info for each row of column metadata
        List<Tuple> columnInfo = new ArrayList<Tuple>();

        String msName = m_connection.getMetaspace().getName();

        // order is by DATA_TYPE and then how closely AS data type matches
        String minScale = "" + 0;
        String maxScale = "" + 0;
        String trueStr = "1";
        String falseStr = "0";

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "BIT", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.BIT, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.BIT), ctypes[2]),
                Utils.createColumnEntry(cnames[3], null, ctypes[3]),
                Utils.createColumnEntry(cnames[4], null, ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typePredBasic, ctypes[8]),
                Utils.createColumnEntry(cnames[9], null, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], null, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.BIT), ctypes[12]),
                Utils.createColumnEntry(cnames[13], minScale, ctypes[13]),
                Utils.createColumnEntry(cnames[14], maxScale, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        // gather AS info for each row, this makes it easy to retrieve
        // the metaspace and space information for the field of the space
        // being described in each row of metadata
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "BIGINT", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.BIGINT, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.BIGINT), ctypes[2]),
                Utils.createColumnEntry(cnames[3], null, ctypes[3]),
                Utils.createColumnEntry(cnames[4], null, ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typePredBasic, ctypes[8]),
                Utils.createColumnEntry(cnames[9], falseStr, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], falseStr, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.BIGINT), ctypes[12]),
                Utils.createColumnEntry(cnames[13], minScale, ctypes[13]),
                Utils.createColumnEntry(cnames[14], maxScale, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "CHAR", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.CHAR, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.CHAR), ctypes[2]),
                Utils.createColumnEntry(cnames[3], "'", ctypes[3]),
                Utils.createColumnEntry(cnames[4], "'", ctypes[4]),
                Utils.createColumnEntry(cnames[5], "length", ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], trueStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typeSearchable, ctypes[8]),
                Utils.createColumnEntry(cnames[9], null, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], null, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.CHAR), ctypes[12]),
                Utils.createColumnEntry(cnames[13], null, ctypes[13]),
                Utils.createColumnEntry(cnames[14], null, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "INTEGER", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.INTEGER, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.INTEGER), ctypes[2]),
                Utils.createColumnEntry(cnames[3], null, ctypes[3]),
                Utils.createColumnEntry(cnames[4], null, ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typePredBasic, ctypes[8]),
                Utils.createColumnEntry(cnames[9], falseStr, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], falseStr, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.INTEGER), ctypes[12]),
                Utils.createColumnEntry(cnames[13], minScale, ctypes[13]),
                Utils.createColumnEntry(cnames[14], maxScale, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "SMALLINT", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.SMALLINT, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.SMALLINT), ctypes[2]),
                Utils.createColumnEntry(cnames[3], null, ctypes[3]),
                Utils.createColumnEntry(cnames[4], null, ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typePredBasic, ctypes[8]),
                Utils.createColumnEntry(cnames[9], falseStr, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], falseStr, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.SMALLINT), ctypes[12]),
                Utils.createColumnEntry(cnames[13], minScale, ctypes[13]),
                Utils.createColumnEntry(cnames[14], maxScale, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "REAL", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.REAL, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.REAL), ctypes[2]),
                Utils.createColumnEntry(cnames[3], null, ctypes[3]),
                Utils.createColumnEntry(cnames[4], null, ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typePredBasic, ctypes[8]),
                Utils.createColumnEntry(cnames[9], falseStr, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], falseStr, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.REAL), ctypes[12]),
                Utils.createColumnEntry(cnames[13], null, ctypes[13]),
                Utils.createColumnEntry(cnames[14], null, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], "" + 10, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "DOUBLE", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.DOUBLE, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.DOUBLE), ctypes[2]),
                Utils.createColumnEntry(cnames[3], null, ctypes[3]),
                Utils.createColumnEntry(cnames[4], null, ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typePredBasic, ctypes[8]),
                Utils.createColumnEntry(cnames[9], falseStr, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], falseStr, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.DOUBLE), ctypes[12]),
                Utils.createColumnEntry(cnames[13], null, ctypes[13]),
                Utils.createColumnEntry(cnames[14], null, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], "" + 10, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "VARCHAR", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.VARCHAR, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.VARCHAR), ctypes[2]),
                Utils.createColumnEntry(cnames[3], "'", ctypes[3]),
                Utils.createColumnEntry(cnames[4], "'", ctypes[4]),
                Utils.createColumnEntry(cnames[5], "max length", ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], trueStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typeSearchable, ctypes[8]),
                Utils.createColumnEntry(cnames[9], null, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], null, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.VARCHAR), ctypes[12]),
                Utils.createColumnEntry(cnames[13], null, ctypes[13]),
                Utils.createColumnEntry(cnames[14], null, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "DATE", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.DATE, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.DATE), ctypes[2]),
                Utils.createColumnEntry(cnames[3], "'", ctypes[3]),
                Utils.createColumnEntry(cnames[4], "'", ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typeSearchable, ctypes[8]),
                Utils.createColumnEntry(cnames[9], null, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], null, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.DATE), ctypes[12]),
                Utils.createColumnEntry(cnames[13], null, ctypes[13]),
                Utils.createColumnEntry(cnames[14], null, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "TIME", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.TIME, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.TIME), ctypes[2]),
                Utils.createColumnEntry(cnames[3], "'", ctypes[3]),
                Utils.createColumnEntry(cnames[4], "'", ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typeSearchable, ctypes[8]),
                Utils.createColumnEntry(cnames[9], null, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], null, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.TIME), ctypes[12]),
                Utils.createColumnEntry(cnames[13], null, ctypes[13]),
                Utils.createColumnEntry(cnames[14], null, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "TIMESTAMP", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.TIMESTAMP, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.TIMESTAMP), ctypes[2]),
                Utils.createColumnEntry(cnames[3], "'", ctypes[3]),
                Utils.createColumnEntry(cnames[4], "'", ctypes[4]),
                Utils.createColumnEntry(cnames[5], null, ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], falseStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typeSearchable, ctypes[8]),
                Utils.createColumnEntry(cnames[9], null, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], null, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.TIMESTAMP), ctypes[12]),
                Utils.createColumnEntry(cnames[13], null, ctypes[13]),
                Utils.createColumnEntry(cnames[14], null, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        columnTuples.add(new Tuple[] {
                Utils.createColumnEntry(cnames[0], "BLOB", ctypes[0]),
                Utils.createColumnEntry(cnames[1], "" + Types.BLOB, ctypes[1]),
                Utils.createColumnEntry(cnames[2], "" +Utils.getSQLTypePrecision(Types.BLOB), ctypes[2]),
                Utils.createColumnEntry(cnames[3], null, ctypes[3]),
                Utils.createColumnEntry(cnames[4], null, ctypes[4]),
                Utils.createColumnEntry(cnames[5], "max length", ctypes[5]),
                Utils.createColumnEntry(cnames[6], trueStr, ctypes[6]),
                Utils.createColumnEntry(cnames[7], trueStr, ctypes[7]),
                Utils.createColumnEntry(cnames[8], "" + typePredNone, ctypes[8]),
                Utils.createColumnEntry(cnames[9], null, ctypes[9]),
                Utils.createColumnEntry(cnames[10], falseStr, ctypes[10]),
                Utils.createColumnEntry(cnames[11], null, ctypes[11]),
                Utils.createColumnEntry(cnames[12], TypeUtil.getASType(Types.BLOB), ctypes[12]),
                Utils.createColumnEntry(cnames[13], null, ctypes[13]),
                Utils.createColumnEntry(cnames[14], null, ctypes[14]),
                Utils.createColumnEntry(cnames[15], null, ctypes[15]),
                Utils.createColumnEntry(cnames[16], null, ctypes[16]),
                Utils.createColumnEntry(cnames[17], null, ctypes[17])
        });
        {
            Tuple columnTuple = Tuple.create();
            columnTuple.put(ASSQLUtils.COLUMN_NAME, (String)null);
            columnTuple.put(ASSQLUtils.TABLE_NAME, (String)null);
            columnTuple.put(ASSQLUtils.COLUMN_ALIAS, (String)null);
            columnTuple.put(ASSQLUtils.METASPACE_NAME, msName);
            columnInfo.add(columnTuple);
        }

        rs.populateResultSet(columnTuples, columnInfo);
        return rs;
    }

    // metadata column and type information for the getUDTs method
    public static final String[] GET_UDTs_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "CLASS_NAME",
        "DATA_TYPE",
        "REMARKS",
        "BASE_TYPE"
    };
    public static final Integer[] GET_UDTs_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.VARCHAR,
        Types.SMALLINT
    };
    public ResultSet getUDTs (String catalog, String schemaPattern, String typeNamePattern, int[] types)
            throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_UDTs_COLUMN_NAMES, GET_UDTs_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getSuperTypes method
    public static final String[] GET_SUPER_TYPES_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "SUPERTYPE_CAT",
        "SUPERTYPE_SCHEM",
        "SUPERTYPE_NAME"
    };
    public static final Integer[] GET_SUPER_TYPES_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet getSuperTypes (String catalog, String schemaPattern, String typeNamePattern) throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_SUPER_TYPES_COLUMN_NAMES, GET_SUPER_TYPES_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getSuperTables method
    public static final String[] GET_SUPER_TABLES_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "SUPERTABLE_NAME"
    };
    public static final Integer[] GET_SUPER_TABLES_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet getSuperTables (String catalog, String schemaPattern, String tableNamePattern) throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_SUPER_TABLES_COLUMN_NAMES, GET_SUPER_TABLES_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getAttributes method
    public static final String[] GET_ATTRIBUTES_COLUMN_NAMES = {
        "TYPE_CAT",
        "TYPE_SCHEM",
        "TYPE_NAME",
        "ATTR_NAME",
        "DATA_TYPE",
        "ATTR_TYPE_NAME",
        "ATTR_SIZE",
        "DECIMAL_DIGITS",
        "NUM_PREC_RADIX",
        "NULLABLE",
        "REMARKS",
        "ATTR_DEF",
        "SQL_DATA_TYPE",
        "SQL_DATETIME_SUB",
        "CHAR_OCTET_LENGTH",
        "ORDINAL_POSITION",
        "IS_NULLABLE",
        "SCOPE_CATALOG",
        "SCOPE_SCHEMA",
        "SCOPE_TABLE",
        "SOURCE_DATA_TYPE"
    };
    public static final Integer[] GET_ATTRIBUTES_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT
    };
    public ResultSet getAttributes (String catalog, String schemaPattern, String typeNamePattern,
            String attributeNamePattern) throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_ATTRIBUTES_COLUMN_NAMES, GET_ATTRIBUTES_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getClientInfoProperties method
    public static final String[] GET_CLIENT_INFO_PROPERTIES_COLUMN_NAMES = {
        "NAME",
        "MAX_LEN",
        "DEFAULT_VALUE",
        "DESCRIPTION"
    };
    public static final Integer[] GET_CLIENT_INFO_PROPERTIES_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet getClientInfoProperties () throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_CLIENT_INFO_PROPERTIES_COLUMN_NAMES, GET_CLIENT_INFO_PROPERTIES_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getFunctions method
    public static final String[] GET_FUNCTIONS_COLUMN_NAMES = {
        "FUNCTION_CAT",
        "FUNCTION_SCHEM",
        "FUNCTION_NAME",
        "REMARKS",
        "FUNCTION_TYPE",
        "SPECIFIC_NAME"
    };
    public static final Integer[] GET_FUNCTIONS_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.VARCHAR
    };
    public ResultSet getFunctions (String catalog, String schemaPattern, String functionNamePattern)
            throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_FUNCTIONS_COLUMN_NAMES, GET_FUNCTIONS_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getFunctionColumns method
    public static final String[] GET_FUNCTION_COLUMNS_COLUMN_NAMES = {
        "FUNCTION_CAT",
        "FUNCTION_SCHEM",
        "FUNCTION_NAME",
        "COLUMN_NAME",
        "COLUMN_TYPE",
        "DATA_TYPE",
        "TYPE_NAME",
        "PRECISION",
        "LENGTH",
        "SCALE",
        "RADIX",
        "NULLABLE",
        "REMARKS",
        "CHAR_OCTET_LENGTH",
        "ORDINAL_POSITION",
        "IS_NULLABLE",
        "SPECIFIC_NAME"
    };
    public static final Integer[] GET_FUNCTION_COLUMNS_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.SMALLINT,
        Types.INTEGER,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.SMALLINT,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR
    };
    public ResultSet getFunctionColumns (String catalog, String schemaPattern, String functionNamePattern,
            String columnNamePattern) throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_FUNCTION_COLUMNS_COLUMN_NAMES, GET_FUNCTION_COLUMNS_COLUMN_TYPES);
        return rs;
    }

    // metadata column and type information for the getPseudoColumns method
    public static final String[] GET_PSEUDO_COLUMNS_COLUMN_NAMES = {
        "TABLE_CAT",
        "TABLE_SCHEM",
        "TABLE_NAME",
        "COLUMN_NAME",
        "DATA_TYPE",
        "COLUMN_SIZE",
        "DECIMAL_DIGITS",
        "NUM_PREC_RADIX",
        "COLUMN_USAGE",
        "REMARKS",
        "CHAR_OCTET_LENGTH",
        "IS_NULLABLE"
    };
    public static final Integer[] GET_PSEUDO_COLUMNS_COLUMN_TYPES = {
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.INTEGER,
        Types.VARCHAR,
        Types.VARCHAR,
        Types.INTEGER,
        Types.VARCHAR
    };
    public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern,
            String columnNamePattern) throws SQLException
    {
        // Populate the result set with the column name and type information.
        // This is needed by tools which display database metadata even if there are no rows of data to display
        ASResultSetScrollable rs = new ASResultSetScrollable(GET_PSEUDO_COLUMNS_COLUMN_NAMES, GET_PSEUDO_COLUMNS_COLUMN_TYPES);
        return rs;
    }


    // ---------- internal helper methods ---------- //
    private void parseVersionInfo (String versionInfo)
    {
        try
        {
            String delims = "[.]";
            String[] tokens = versionInfo.split(delims);
            String mjversion = tokens[0];
            String mnversion = tokens[1];
            if (mjversion != null)
                m_productMajorVersion = Integer.parseInt(mjversion);
            if (mnversion != null)
                m_productMinorVersion = Integer.parseInt(mnversion);
        }
        catch (Exception e)
        {
            // do nothing, keep default settings
        }
    }

    private boolean checkSchemaPattern(String schemaPattern)
    {
        boolean result = true;
        if (schemaPattern != null && !schemaPattern.isEmpty() && !"%".equals(schemaPattern))
        {
            result = false;  // we don't support schemas
        }
        return result;
    }

    private boolean checkConnection() throws SQLException
    {
        if (m_connection == null || m_connection.getMetaspace() == null)
        {
            // we don't have a metaspace connection, so throw an exception
            throw new SQLException(Utils.AS_NO_CONNECTION);
        }
        return true;
    }

    private boolean checkCatalogName(String catalogName)
    {
        boolean result = true;
        String msName = m_connection.getMetaspace().getName();
        if (catalogName != null && !msName.equals(catalogName))
        {
            result = false;
        }
        return result;
    }

    private boolean checkTableName(String tableName)
    {
        boolean result = true;
        try
        {
            if (m_connection.getMetaspace().getSpace(tableName) == null)
                result = false;
        }
        catch (ASException e)
        {
            result = false;
        }
        return result;
    }

    private boolean checkFieldsAreKeys(Collection<String> fieldNames, Collection<String> keyFieldNames)
    {
        boolean result = true;
        if (fieldNames == null || fieldNames.isEmpty())
            result = false;
        else if (keyFieldNames == null || keyFieldNames.isEmpty())
            result = false;
        else
        {
            Iterator<String> fieldNamesIter = fieldNames.iterator();
            while (fieldNamesIter.hasNext())
            {
                String fieldName = fieldNamesIter.next();
                if (!keyFieldNames.contains(fieldName))
                {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
