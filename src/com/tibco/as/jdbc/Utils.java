// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Tuple;
import com.tibco.as.sql.ASSQLUtils;
import com.tibco.as.sql.TypeUtil;

public class Utils
{
    public static final String URL                      = "jdbc:tibco:as";
    public static final String AS_FEATURE_NOT_SUPPORTED = "The ActiveSpaces JDBC Driver does not support this feature.";
    public static final String AS_STATEMENT_CLOSED      = "ActiveSpaces JDBC Driver method invoked on a closed statement.";
    public static final String AS_NO_CONNECTION         = "ActiveSpaces JDBC Driver method invoked without a metaspace connection.";
    public static final String SQL_TYPE_NOT_SUPPORTED   = "The ActiveSpaces JDBC Driver does not support this SQL data type.";
    public static final String OBJECT_NOT_SUPPORTED     = "The ActiveSpaces JDBC Driver does not support this Java object.";

    /**
     * parseURL - parses JDBC URLs of the form
     * jdbc:tibco:as[:<data-source-name>][;<propertyName>=<propertyValue>]* For AS, we can have
     * property values which contain semi-colons(;) and question marks(?). For example, we could
     * have several well-known members to use for discovery as in:
     * discovery=tcp://ip1:port1;ip2:port2 Or we could have a remote client discovery URL like:
     * discovery=tcp://ip:port?remote=true So we need to make sure we handle these special cases.
     * Other than that, the format is typical of what other JDBC drivers use for their URLs.
     */
    public static java.util.Properties parseURL (String url)
    {
        java.util.Properties settings = new java.util.Properties();
        if (url != null)
        {
            // first check for a metaspace name to be specified as the data-source-name
            String prefix = URL + ":";
            if (url.startsWith(prefix) && url.length() > prefix.length() && url.charAt(prefix.length()) != ';')
            {
                // we could have a data-source-name
                String name = url.substring(prefix.length());
                int semi = name.indexOf(';');
                if (semi != -1)
                {
                    name = name.substring(0, semi);
                }
                settings.put("metaspace", name);

            }
            // now check for properties to be specified
            if (url.indexOf(';') != -1)
            {
                boolean bEndOfParams = false;
                int paramNameStartIndex = 0;
                int paramNameEndIndex = 0;
                int paramValueStartIndex = 0;
                int paramValueEndIndex = 0;
                String paramName = null;
                String paramValue = null;

                int currentPosition = url.indexOf(";", 0);
                while (url.charAt(currentPosition + 1) == ';')
                    currentPosition++;

                while (!bEndOfParams)
                {
                    // find the parameter name
                    paramNameStartIndex = currentPosition;
                    if (paramNameStartIndex == -1)
                    {
                        bEndOfParams = true;
                        continue;
                    }
                    paramNameStartIndex++;
                    paramNameEndIndex = url.indexOf("=", paramNameStartIndex);
                    if (paramNameEndIndex == -1)
                    {
                        bEndOfParams = true;
                        continue;
                    }
                    paramName = url.substring(paramNameStartIndex, paramNameEndIndex);

                    // find the parameter value
                    boolean found = false;
                    paramValueStartIndex = paramNameEndIndex + 1;
                    if (paramName.equalsIgnoreCase("discovery"))
                    {
                        // check for ?remote=true to end the setting
                        int idx = -1;
                        if ((idx = url.toLowerCase().indexOf("?remote=true")) != -1)
                        {
                            found = true;
                            paramValueEndIndex = idx + 12;
                        }
                        else if ((idx = url.toLowerCase().indexOf("?remote=false")) != -1)
                        {
                            found = true;
                            paramValueEndIndex = idx + 13;
                        }
                        if (found)
                        {
                            paramValue = url.substring(paramValueStartIndex, paramValueEndIndex);
                            currentPosition = paramValueStartIndex + paramValue.length();
                            while (url.length() > currentPosition && url.charAt(currentPosition + 1) == ';')
                                currentPosition++;
                        }

                    }
                    if (!found)
                    {
                        paramValueEndIndex = url.indexOf("=", paramValueStartIndex);
                        if (paramValueEndIndex == -1)
                        {
                            // there is no next name/value parameter pair
                            // so we have the last parameter
                            paramValue = url.substring(paramValueStartIndex);
                            bEndOfParams = true;
                        }
                        else
                        {
                            // back up to the previous semi-colon
                            String tempVal = url.substring(paramValueStartIndex, paramValueEndIndex);
                            paramValueEndIndex = tempVal.lastIndexOf(";");
                            paramValue = tempVal.substring(0, paramValueEndIndex);
                            // now set the current position according to the original URL string
                            currentPosition = paramValueStartIndex + paramValue.length();
                            while (url.charAt(currentPosition + 1) == ';')
                                currentPosition++;
                        }
                    }
                    // store the parameter setting
                    settings.put(paramName.toLowerCase(), paramValue);
                }
            }
        }
        return settings;
    }

    /**
     * genURL - creates a JDBC URL from the given parameters of the form
     * jdbc:tibco:as[:<data-source-name>][;<propertyName>=<propertyValue>]*
     */
    public static String genURL (String name, String member_name, String discovery, String listen,
            String remote_discovery, String remote_listen, String rx_buffer_size, String worker_thread_count)
    {
        String url = URL;
        if (name != null && !name.isEmpty())
            url += ":" + name;
        if (member_name != null && !member_name.isEmpty())
            url += ";member_name=" + member_name;
        if (discovery != null && !discovery.isEmpty())
            url += ";discovery=" + discovery;
        if (listen != null && !listen.isEmpty())
            url += ";listen=" + listen;
        if (remote_discovery != null && !remote_discovery.isEmpty())
            url += ";remote_discovery=" + remote_discovery;
        if (remote_listen != null && !remote_listen.isEmpty())
            url += ";remote_listen=" + remote_listen;
        if (rx_buffer_size != null && !rx_buffer_size.isEmpty())
            url += ";rx_buffer_size=" + rx_buffer_size;
        if (worker_thread_count != null && !worker_thread_count.isEmpty())
            url += ";worker_thread_count=" + worker_thread_count;
        return url;
    }

    /**
     * getURL - returns the JDBC URL for the given metaspace
     */
    public static String getURL (Metaspace metaspace)
    {
        String url = URL;
        String name = null;
        String discovery = null;
        String listen = null;
        String member_name = null;
        String remote_discovery = null;
        String remote_listen = null;
        long rx_buffer_size = 0;
        int worker_thread_count = 0;

        if (metaspace != null)
        {
            name = metaspace.getName();
            MemberDef mdef = metaspace.getMemberDef();
            if (mdef != null)
            {
                member_name = mdef.getMemberName();
                discovery = mdef.getDiscovery();
                listen = mdef.getListen();
                remote_discovery = mdef.getRemoteDiscovery();
                remote_listen = mdef.getRemoteListen();
                rx_buffer_size = mdef.getRxBufferSize();
                worker_thread_count = mdef.getWorkerThreadCount();
            }
            url = genURL(name, member_name, discovery, listen, remote_discovery, remote_listen,
                    Long.toString(rx_buffer_size), Integer.toString(worker_thread_count));
        }
        return url;
    }

    /**
     * createURL - creates a JDBC URL from the given properties of the form
     * jdbc:tibco:as[:<data-source-name>][;<propertyName>=<propertyValue>]*
     */
    public static String createURL (java.util.Properties settings)
    {
        String url = URL;
        String name = null;
        String discovery = null;
        String listen = null;
        String member_name = null;
        String remote_discovery = null;
        String remote_listen = null;
        String rx_buffer_size = null;
        String worker_thread_count = null;

        if (settings != null && !settings.isEmpty())
        {
            Metaspace metaspace = (Metaspace) settings.get(Metaspace.class.getName());
            if (metaspace != null)
            {
                url = getURL(metaspace);
            }
            else
            {
                // check for metaspace name and properties to be specified
                name = settings.getProperty("metaspace", null);
                member_name = settings.getProperty("member_name", null);
                discovery = settings.getProperty("discovery", null);
                listen = settings.getProperty("listen", null);
                remote_discovery = settings.getProperty("remote_discovery", null);
                remote_listen = settings.getProperty("remote_listen", null);
                rx_buffer_size = settings.getProperty("rx_buffer_size", null);
                worker_thread_count = settings.getProperty("worker_thread_count", null);
                url = genURL(name, member_name, discovery, listen, remote_discovery, remote_listen, rx_buffer_size,
                        worker_thread_count);
            }
        }
        return url;
    }

    public static boolean matchPattern (String pattern, String svalue)
    {
        boolean result = false;
        if (pattern == null)
            result = true;
        else if ("%".equals(pattern))
            result = true;
        else if (pattern.equals(svalue))
            result = true;
        else if (pattern.contains("_") && pattern.length() == svalue.length())
        {
            int len = pattern.length();
            String newpattern = "";
            for (int i=0; i<len; i++)
            {
                if (pattern.charAt(i) == svalue.charAt(i))
                    newpattern += svalue.charAt(i);
                else if (pattern.charAt(i) == '_')
                    newpattern += svalue.charAt(i);
                else
                {
                    break;
                }

            }
            if (newpattern.equals(svalue))
                result = true;
        }
        return result;
    }

    public static String getSQLTypeName (int sqlType)
    {
        String resultType = null;
        switch (sqlType)
        {
            case java.sql.Types.BIT:
            {
                resultType = "BIT";
            }
            case java.sql.Types.BIGINT:
            {
                resultType = "BIGINT";
                break;
            }
            case java.sql.Types.CHAR:
            {
                resultType = "CHAR";
                break;
            }
            case java.sql.Types.INTEGER:
            {
                resultType = "INTEGER";
                break;
            }
            case java.sql.Types.SMALLINT:
            {
                resultType = "SMALLINT";
                break;
            }
            case java.sql.Types.VARCHAR:
            {
                resultType = "VARCHAR";
                break;
            }
            case java.sql.Types.REAL:
            {
                resultType = "REAL";
                break;
            }
            case java.sql.Types.DOUBLE:
            {
                resultType = "DOUBLE";
                break;
            }
            case java.sql.Types.DATE:
            {
                resultType = "DATE";
                break;
            }
            case java.sql.Types.TIME:
            {
                resultType = "TIME";
                break;
            }
            case java.sql.Types.TIMESTAMP:
            {
                resultType = "TIMESTAMP";
                break;
            }
            case java.sql.Types.BLOB:
            {
                resultType = "BLOB";
                break;
            }
            case java.sql.Types.TINYINT:
            {
                resultType = "TINYINT";
                break;
            }
            case java.sql.Types.FLOAT:
            {
                resultType = "FLOAT";
                break;
            }
            case java.sql.Types.NUMERIC:
            {
                resultType = "NUMERIC";
                break;
            }
            case java.sql.Types.DECIMAL:
            {
                resultType = "DECIMAL";
                break;
            }
            case java.sql.Types.LONGVARCHAR:
            {
                resultType = "LONGVARCHAR";
                break;
            }
            case java.sql.Types.BINARY:
            {
                resultType = "BINARY";
                break;
            }
            case java.sql.Types.VARBINARY:
            {
                resultType = "VARBINARY";
                break;
            }
            case java.sql.Types.LONGVARBINARY:
            {
                resultType = "LONGVARBINARY";
                break;
            }
            case java.sql.Types.NULL:
            {
                resultType = "NULL";
                break;
            }
            case java.sql.Types.OTHER:
            {
                resultType = "OTHER";
                break;
            }
            case java.sql.Types.JAVA_OBJECT:
            {
                resultType = "JAVA_OBJECT";
                break;
            }
            case java.sql.Types.DISTINCT:
            {
                resultType = "DISTINCT";
                break;
            }
            case java.sql.Types.STRUCT:
            {
                resultType = "STRUCT";
                break;
            }
            case java.sql.Types.ARRAY:
            {
                resultType = "ARRAY";
                break;
            }
            case java.sql.Types.CLOB:
            {
                resultType = "CLOB";
                break;
            }
            case java.sql.Types.REF:
            {
                resultType = "REF";
                break;
            }
            case java.sql.Types.DATALINK:
            {
                resultType = "DATALINK";
                break;
            }
            case java.sql.Types.BOOLEAN:
            {
                resultType = "BOOLEAN";
                break;
            }
            case java.sql.Types.ROWID:
            {
                resultType = "ROWID";
                break;
            }
            case java.sql.Types.NCHAR:
            {
                resultType = "NCHAR";
                break;
            }
            case java.sql.Types.NVARCHAR:
            {
                resultType = "NVARCHAR";
                break;
            }
            case java.sql.Types.LONGNVARCHAR:
            {
                resultType = "LONGNVARCHAR";
                break;
            }
            case java.sql.Types.NCLOB:
            {
                resultType = "NCLOB";
                break;
            }
            case java.sql.Types.SQLXML:
            {
                resultType = "SQLXML";
                break;
            }
            default:
                resultType = null;
        }
        return resultType;
    }

    public static int getSQLTypePrecision (int sqlType)
    {
        // returns the number of digits in a number
        // For datetime data types, the precision is the length in characters of the string representation (assuming
        // the maximum allowed precision of the fractional seconds component.
        // For binary data, the precision is the length in bytes.
        int resultType = 10; // default to integer precision, no real reason why
        switch (sqlType)
        {
            case java.sql.Types.BIT:
            {
                // corresponds to AS FieldType.BOOLEAN, 1 digit is used to represent true(1) or false(0)
                resultType = 1;
            }
            case java.sql.Types.BIGINT:
            {
                // corresponds to AS FieldType.LONG which takes 8 bytes of storage and can have a
                // max value of 9,223,372,036,854,775,807 for a precision of 19
                resultType = 19;
                break;
            }
            case java.sql.Types.CHAR:
            {
                // corresponds to AS FieldType.CHAR, 1 character is used to represent the value
                resultType = 1;
                break;
            }
            case java.sql.Types.INTEGER:
            {
                // corresponds to AS FieldType.INTEGER which uses 4 bytes for storage and can
                // have a max value of 2,147,483,647 for a precision of 10
                resultType = 10;
                break;
            }
            case java.sql.Types.SMALLINT:
            {
                // corresponds to AS FieldType.SHORT which uses 2 bytes for storage and can
                // have a max value of 32767
                resultType = 5;
                break;
            }
            case java.sql.Types.VARCHAR:
            {
                // corresponds to AS FieldType.STRING which stores data into a byte array which is
                // indexed in the Java layer by an int. So the precision is determined by the int index
                // which can have a maximum value of 2,147,483,647
                resultType = Integer.MAX_VALUE;
                break;
            }
            case java.sql.Types.REAL:
            {
                // for floating point numbers, the storage size of the value indicates the number
                // of bits that are used to store the mantissa of the float number in scientific
                // notation and, therefore, dictates the precision.
                // number of bytes is 4
                resultType = 7;
                break;
            }
            case java.sql.Types.DOUBLE:
            {
                // number of bytes is 8
                resultType = 15;
                break;
            }
            case java.sql.Types.DATE:
            {
                // 'yyyy-mm-dd'
                resultType = 10;
                break;
            }
            case java.sql.Types.TIME:
            {
                // 'hh:mm:ss'
                resultType = 8;
                break;
            }
            case java.sql.Types.TIMESTAMP:
            {
                // 'yyyy-mm-dd hh:mm:ss.nnnnnnnnn' java.sql.Timestamp.toString
                resultType = 29;
                break;
            }
            case java.sql.Types.BLOB:
            {
                // corresponds to AS FieldType.STRING which stores data into a byte array which is
                // indexed in the Java layer by an int. So the precision is determined by the int index
                // which can have a maximum value of 2,147,483,647
                resultType = Integer.MAX_VALUE;
                break;
            }
            default:
                resultType = 10;
        }
        return resultType;
    }

    public static int getSQLDisplaySize (int sqlType)
    {
        // returns the maximum number of characters needed to display the type as a string
        // typically this would be the same as the precision but we limit displaying
        // string and blob data to 80 characters
        int result = getSQLTypePrecision(sqlType);
        if (sqlType == java.sql.Types.VARCHAR)
            result = 80;
        else if (sqlType == java.sql.Types.BLOB)
            result = 0;  // don't allow the display of blob data
        return result;
    }

    public static int getSQLTypeDecimalDigits (int sqlType)
    {
        // returns the number of fractional digits
        int result = -1; // default returned if not applicable
        switch (sqlType)
        {
            case java.sql.Types.REAL:
            {
                result = 7;
                break;
            }
            case java.sql.Types.DOUBLE:
            {
                result = 15;
                break;
            }
            default:
                result = -1;
        }
        return result;
    }

    public static int getSQLTypeRadix (int sqlType)
    {
        // returns the radix for the given type
        int result = -1; // default returned if not applicable
        switch (sqlType)
        {
            case java.sql.Types.REAL:
            case java.sql.Types.DOUBLE:
            {
                result = 10;
                break;
            }
            default:
                result = -1;
        }
        return result;
    }



    //
    // Create a tuple with values that are always a string but since the type is also
    // stored, the user can tell how to convert the string value.
    public static Tuple createColumnEntry (String columnName, String columnValue, int columnSQLType)
    {
        // AS defaults to isNullable set to false
        // for JDBC metadata, allow columns to be nullable by default since the
        // metadata columns aren't stored in AS and so will never be key fields in AS
        return createColumnEntry(columnName, columnValue, columnSQLType, true);
    }
    public static Tuple createColumnEntry (String columnName, String columnValue, int columnSQLType, boolean columnNullable)
    {
        Tuple result = Tuple.create();
        result.put(ASSQLUtils.COLUMN_NAME, columnName);
        result.put(ASSQLUtils.COLUMN_VALUE, columnValue);
        result.put(ASSQLUtils.COLUMN_TYPE, columnSQLType);
        result.put(ASSQLUtils.COLUMN_NULLABLE, columnNullable);
        return result;
    }

    public static HashMap<String, FieldDef> getColumnSpec (List<Tuple[]> columnTuples)
    {
        HashMap<String, FieldDef> result = new HashMap<String, FieldDef>();
        // read the first array of Tuples and get the information needed from there
        Tuple[] firstRow = columnTuples.get(0);
        for (int i=0; i<firstRow.length; i++)
        {
            String columnName = (String)firstRow[i].get(ASSQLUtils.COLUMN_NAME);
            int sqlType = ((Integer)(firstRow[i].get(ASSQLUtils.COLUMN_TYPE))).intValue();
            boolean nullable = ((Boolean)(firstRow[i].get(ASSQLUtils.COLUMN_NULLABLE))).booleanValue();
            String asTypeStr = TypeUtil.getASType(sqlType);
            FieldDef.FieldType ftype = FieldDef.FieldType.getFieldType(asTypeStr);
            FieldDef fdef = FieldDef.create(columnName, ftype);
            fdef.setNullable(nullable);
            result.put(columnName, fdef);
        }
        return result;
    }

    public static Integer[] getColumnFieldTypes (List<Tuple[]> columnTuples)
    {
        ArrayList<Integer> columnTypes = new ArrayList<Integer>();
        // read the first array of Tuples and get the information needed from there
        Tuple[] firstRow = columnTuples.get(0);
        for (int i=0; i<firstRow.length; i++)
        {
            int sqlType = ((Integer)(firstRow[i].get(ASSQLUtils.COLUMN_TYPE))).intValue();
            columnTypes.add(sqlType);
        }
        Integer[] result = new Integer[columnTypes.size()];
        columnTypes.toArray(result);
        return result;
    }

    public static ArrayList<String> getColumnNames(List<Tuple[]> columnTuples)
    {
        ArrayList<String> result = new ArrayList<String>();
        // read the first array of Tuples and get the column names from there
        Tuple[] firstRow = columnTuples.get(0);
        for (int i=0; i<firstRow.length; i++)
        {
            String columnName = (String)firstRow[i].get(ASSQLUtils.COLUMN_NAME);
            result.add(columnName);
        }
        return result;
    }

    public static Object getJavaObject(Tuple inTuple)
    {
        // This method is intended to be used for getting values from Tuples
        // which were created by DatabaseMetaData calls. Each tuple has a
        // column name, column value, and column type
        // sometimes the value for a column could be null so we need to be
        // sure to set lastValueNull as if the null value was for an int,
        // 0 is supposed to be returned.
        Object result = null;
        String value = (String) inTuple.get(ASSQLUtils.COLUMN_VALUE);
        if (value == null)
            return result;

        try
        {
            int sqlType = ((Integer)(inTuple.get(ASSQLUtils.COLUMN_TYPE))).intValue();
            switch (sqlType)
            {
                // JDBC to Java type mappings from JDBC 4.0 spec
                case java.sql.Types.BIT:
                {
                    result = Boolean.parseBoolean(value);
                    break;
                }
                case java.sql.Types.BIGINT:
                {
                    result = Long.parseLong(value);
                    break;
                }
                case java.sql.Types.CHAR:
                {
                    result = value;
                    break;
                }
                case java.sql.Types.INTEGER:
                {
                    result = Integer.parseInt(value);
                    break;
                }
                case java.sql.Types.SMALLINT:
                {
                    result = Integer.parseInt(value);
                    break;
                }
                case java.sql.Types.VARCHAR:
                {
                    result = value;
                    break;
                }
                case java.sql.Types.REAL:
                {
                    result = Float.parseFloat(value);
                    break;
                }
                case java.sql.Types.DOUBLE:
                {
                    result = Double.parseDouble(value);
                    break;
                }
                case java.sql.Types.DATE:
                {
                    result = java.sql.Date.valueOf(value);
                    break;
                }
                case java.sql.Types.TIME:
                {
                    result = java.sql.Time.valueOf(value);
                    break;
                }
                case java.sql.Types.TIMESTAMP:
                {
                    result = java.sql.Timestamp.valueOf(value);
                    break;
                }
                case java.sql.Types.BLOB:
                {
                    byte[] byteArray = value.getBytes();
                    result = new SerialBlob(byteArray);
                    break;
                }
                default:
                    result = null;
            }
        }
        catch (Exception e)
        {
            result = null;
        }
        return result;
    }

}
