// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

package com.tibco.as.sql;

import java.nio.ByteBuffer;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;

public class TypeUtil
{
    public static int getSQLType (FieldDef fdef)
    {
        int resultType = java.sql.Types.VARCHAR; // default to VARCHAR, no real reason
        if (fdef == null)
            return resultType;

        FieldType fieldType = fdef.getType();
        switch (fieldType)
        {
            case CHAR:
            {
                resultType = java.sql.Types.CHAR;
            }
            case STRING:
            {
                resultType = java.sql.Types.VARCHAR;
                break;
            }
            case BOOLEAN:
            {
                resultType = java.sql.Types.BIT;
                break;
            }
            case SHORT:
            {
                resultType = java.sql.Types.SMALLINT;
                break;
            }
            case INTEGER:
            {
                resultType = java.sql.Types.INTEGER;
                break;
            }
            case LONG:
            {
                resultType = java.sql.Types.BIGINT;
                break;
            }
            case FLOAT:
            {
                resultType = java.sql.Types.REAL;
                break;
            }
            case DOUBLE:
            {
                resultType = java.sql.Types.DOUBLE;
                break;
            }
            case BLOB:
            {
                resultType = java.sql.Types.BLOB;
                break;
            }
            case DATETIME:
            {
                resultType = java.sql.Types.TIMESTAMP;
                break;
            }
            default:
                throw new UnsupportedOperationException("Unsupported type [" + fieldType + "]");
        }
        return resultType;
    }

    public static String getASType (int sqlType)
    {
        String resultType = null;
        switch (sqlType)
        {
            case java.sql.Types.BIT:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.BOOLEAN.name();
            }
            case java.sql.Types.BIGINT:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.LONG.name();
                break;
            }
            case java.sql.Types.CHAR:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.CHAR.name();
                break;
            }
            case java.sql.Types.INTEGER:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.INTEGER.name();
                break;
            }
            case java.sql.Types.SMALLINT:
            case java.sql.Types.TINYINT:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.SHORT.name();
                break;
            }
            case java.sql.Types.VARCHAR:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.STRING.name();
                break;
            }
            case java.sql.Types.REAL:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.FLOAT.name();
                break;
            }
            case java.sql.Types.DOUBLE:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.DOUBLE.name();
                break;
            }
            case java.sql.Types.DATE:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.DATETIME.name();
                break;
            }
            case java.sql.Types.TIME:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.DATETIME.name();
                break;
            }
            case java.sql.Types.TIMESTAMP:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.DATETIME.name();
                break;
            }
            case java.sql.Types.BLOB:
            {
                resultType = com.tibco.as.space.FieldDef.FieldType.BLOB.name();
                break;
            }
            default:
                resultType = null;
        }
        return resultType;
    }

    /**
     * Retrieves the value of the given object as a <code>boolean</code> in the
     * Java programming language.
     * <P>
     * If the object's value is null, <code>false</code> is returned. If the object's value is a string of
     * "0" or "false", <code>false</code> is returned. If the object's value is a number with the value
     * 0, <code>false</code> is returned. If the object's value is a string of "1" or "true",
     * <code>true</code> is returned. If the object's value is a number with the value 1,
     * <code>true</code> is returned.
     * <P>
     *
     * @param object
     *            the object to return as a boolean
     * @return the object's value as a boolean
     * @exception SQLException
     *                if the object cannot be returned as a boolean.
     *
     */
    public static boolean getBoolean (Object object) throws SQLException
    {
        if (object == null)
            return false;
        if (object instanceof Boolean)
            return (Boolean) object;
        if (object instanceof Character && ((Character) object).equals('0'))
            return false;
        if (object instanceof Character && ((Character) object).equals('1'))
            return true;
        if (object instanceof String && ((String) object).equals("0"))
            return false;
        if (object instanceof String && ((String) object).equals("1"))
            return true;
        if (object instanceof String && ((String) object).equalsIgnoreCase("false"))
            return false;
        if (object instanceof String && ((String) object).equalsIgnoreCase("true"))
            return true;
        if (object instanceof Short && ((Short) object) == 0)
            return false;
        if (object instanceof Short && ((Short) object) == 1)
            return true;
        if (object instanceof Integer && ((Integer) object) == 0)
            return false;
        if (object instanceof Integer && ((Integer) object) == 1)
            return true;
        if (object instanceof Long && ((Long) object) == 0)
            return false;
        if (object instanceof Long && ((Long) object) == 1)
            return true;
        else
            throw new SQLDataException("Cannot convert " + object.getClass().getSimpleName() + " to a boolean");

    }

    /**
     * Retrieves the value of the given object as a <code>byte</code> in the Java programming
     * language.
     * <P>
     * If the object's value is null, <code>0</code> is returned.
     * <P>
     *
     * @param object
     *            the object to return as a byte
     * @return the object's value as a byte
     * @exception SQLException
     *                if the object cannot be returned as a byte.
     */
    public static byte getByte (Object object) throws SQLException
    {
        byte result;
        if (object == null)
            result = 0;
        else if (object instanceof Short)
            result = ((Short) object).byteValue();
        else if (object instanceof Integer)
            result = ((Integer) object).byteValue();
        else if (object instanceof Long)
            result = ((Long) object).byteValue();
        else if (object instanceof Float)
            result = ((Float) object).byteValue();
        else if (object instanceof Double)
            result = ((Double) object).byteValue();
        else if (object instanceof Character)
        {
            try
            {
                result = Byte.parseByte(((Character) object).toString());
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }

        }
        else if (object instanceof String)
        {
            try
            {
                result = Byte.parseByte((String) object);
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }

        }
        else
            throw new SQLDataException("Cannot convert " + object.getClass().getSimpleName() + " to a byte");
        return result;
    }

    /**
     * Retrieves the value of the given object as a <code>char</code> in the Java programming
     * language.
     * <P>
     * If the object's value is null, <code>0</code> is returned.
     * <P>
     *
     * @param object
     *            the object to return as a char
     * @return the object's value as a char
     * @exception SQLException
     *                if the object cannot be returned as a char.
     */
    public static char getChar (Object object) throws SQLException
    {
        char result;
        try
        {
            if (object == null)
                result = 0;
            else if (object instanceof Character)
            {
                result = ((Character)object).charValue();
            }
            else if (object instanceof String)
            {
                result = ((String)object).charAt(0);
            }
            else
            {
                byte bval = getByte(object);
                Character cval = new Character((char)bval);
                result = cval.charValue();
            }
        }
        catch (Exception e)
        {
            throw new SQLDataException("Cannot convert " + object.getClass().getSimpleName() + " to a char");
        }
        return result;
    }

    /**
     * Retrieves the value of the given object as a <code>short</code> in the Java programming
     * language.
     * <P>
     * If the object's value is null, <code>0</code> is returned.
     * <P>
     *
     * @param object
     *            the object whose value to return as a short
     * @return the object's value as a short
     * @exception SQLException
     *                if the object cannot be returned as a short.
     */
    public static short getShort (Object object) throws SQLException
    {
        short result;
        if (object == null)
            return 0;
        if (object instanceof Short)
            return ((Short) object).shortValue();
        if (object instanceof Integer)
            return ((Integer) object).shortValue();
        if (object instanceof Long)
            return ((Long) object).shortValue();
        if (object instanceof Float)
            return ((Float) object).shortValue();
        if (object instanceof Double)
            return ((Double) object).shortValue();
        if (object instanceof Character)
        {
            try
            {
                result = Short.parseShort(((Character) object).toString());
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }

        }
        else if (object instanceof String)
        {
            try
            {
                result = Short.parseShort((String) object);
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }

        }
        else
            throw new SQLDataException("Cannot convert " + object.getClass().getSimpleName() + " to a short");
        return result;
    }

    /**
     * Retrieves the value of the given object as an <code>int</code> in the
     * Java programming language.
     *
     * If the object's value is null, <code>0</code> is returned.
     *
     * @param object
     *            the object whose value to return as an int
     * @return the object's value as an int
     * @exception SQLException
     *                if the object's value cannot be returned as an int.
     */
    public static int getInt (Object object) throws SQLException
    {
        int result;
        if (object == null)
            return 0;
        if (object instanceof Integer)
            return ((Integer)object).intValue();
        if (object instanceof Short)
            return ((Short) object).intValue();
        if (object instanceof Long)
            return ((Long) object).intValue();
        if (object instanceof Float)
            return ((Float) object).intValue();
        if (object instanceof Double)
            return ((Double) object).intValue();
        if (object instanceof Character)
        {
            try
            {
                result = Integer.parseInt(((Character) object).toString());
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }

        }
        else if (object instanceof String)
        {
            try
            {
                result = Integer.parseInt((String) object);
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }

        }
        else
            throw new SQLDataException("Cannot convert " + object.getClass().getSimpleName() + " to an int");
        return result;
    }

    /**
     * Retrieves the value of the given object as a <code>long</code> in the
     * Java programming language.
     *
     * If the object's value is null, <code>0</code> is returned.
     *
     * @param object
     *            the object whose value should be returned as a long
     * @return the object's value as a long
     * @exception SQLException
     *                if the value of the object cannot be returned as a long.
     */
    public static long getLong (Object object) throws SQLException
    {
        long result;
        if (object == null)
            return 0;
        if (object instanceof Long)
            return ((Long)object).longValue();
        if (object instanceof Integer)
            return ((Integer)object).longValue();
        if (object instanceof Short)
            return ((Short) object).longValue();
        if (object instanceof Float)
            return ((Float) object).longValue();
        if (object instanceof Double)
            return ((Double) object).longValue();
        if (object instanceof Character)
        {
            try
            {
                result = Long.parseLong(((Character) object).toString());
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }
        }
        else if (object instanceof String)
        {
            try
            {
                result = Long.parseLong((String) object);
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }
        }
        else
            throw new SQLDataException("Cannot convert " + object.getClass().getSimpleName() + " to a long");
        return result;
    }

    /**
     * Retrieves the value of the given object as a <code>float</code> in the
     * Java programming language.
     *
     * If the object's value is null, <code>0</code> is returned.
     *
     * @param object
     *            the object whose value to return as a float
     * @return the object's value as a float
     * @exception SQLException
     *                if the object's value cannot be returned as a float.
     */
    public static float getFloat (Object object) throws SQLException
    {
        float result;
        if (object == null)
            return 0;
        if (object instanceof Float)
            return ((Float)object).floatValue();
        if (object instanceof Double)
            return ((Double)object).floatValue();
        if (object instanceof Short)
            return ((Short) object).floatValue();
        if (object instanceof Integer)
            return ((Integer) object).floatValue();
        if (object instanceof Long)
            return ((Long) object).floatValue();
        if (object instanceof String)
        {
            try
            {
                result = Float.parseFloat((String) object);
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }
        }
        else
            throw new SQLSyntaxErrorException("Cannot convert " + object.getClass().getSimpleName() + " to a float");
        return result;
    }


    /**
     * Retrieves the value of the given object as a <code>double</code> in the
     * Java programming language.
     *
     * If the object's value is null, <code>0</code> is returned.
     *
     * @param object
     *            the object whose value to return
     * @return the object's value as a double
     * @exception SQLException
     *                if the object cannot be returned as a double.
     */
    public static double getDouble (Object object) throws SQLException
    {
        double result;
        if (object == null)
            return 0;
        if (object instanceof Double)
            return ((Double)object).doubleValue();
        if (object instanceof Float)
            return ((Float)object).doubleValue();
        if (object instanceof Short)
            return ((Short) object).doubleValue();
        if (object instanceof Integer)
            return ((Integer) object).doubleValue();
        if (object instanceof Long)
            return ((Long) object).doubleValue();
        if (object instanceof String)
        {
            try
            {
                result = Double.parseDouble((String) object);
            }
            catch (NumberFormatException ex)
            {
                throw new SQLDataException(ex);
            }
        }
        else
            throw new SQLSyntaxErrorException("Cannot convert " + object.getClass().getSimpleName() + " to a double");
        return result;
    }

    /**
     * Retrieves the value of the given object as a <code>byte</code> array in
     * the Java programming language.
     *
     * If the object's value is null, <code>null</code> is returned.
     *
     * @param object
     *            the object whose value to return as a byte array
     * @return the object's value as a byte array
     * @exception SQLException
     *                if the object cannot be returned as a byte array.
     */
    public static byte[] getBytes (Object object) throws SQLException
    {
        byte[] result;
        if (object == null)
            result = null;
        else if (object instanceof Boolean)
        {
            result = ((Boolean)object).toString().getBytes();
        }
        else if (object instanceof Short)
        {
            ByteBuffer buffer = ByteBuffer.allocate(2);
            buffer.putShort((Short) object);
            buffer.flip();
            result = buffer.array();
        }
        else if (object instanceof Integer)
        {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.putInt((Integer) object);
            buffer.flip();
            result = buffer.array();
        }
        else if (object instanceof Long)
        {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putLong((Long) object);
            buffer.flip();
            result = buffer.array();
        }
        else if (object instanceof Float)
        {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.putFloat((Float) object);
            buffer.flip();
            result = buffer.array();
        }
        else if (object instanceof Double)
        {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putDouble((Double) object);
            buffer.flip();
            result = buffer.array();
        }
        else if (object instanceof Character)
        {
            ByteBuffer buffer = ByteBuffer.allocate(2);
            buffer.putChar(((Character) object).charValue());
            buffer.flip();
            result = buffer.array();
        }
        else if (object instanceof String)
        {
            result = ((String) object).getBytes();
        }
        else if (object.getClass().getSimpleName().equals("byte[]"))
        {
            result = (byte[])object;
        }
        else
            throw new SQLDataException("Cannot convert " + object.getClass().getSimpleName() + " to byte array");

        return result;
    }

    /**
     * Retrieves the value of the given object as a <code>String</code> in
     * the Java programming language.
     *
     * If the object's value is null, <code>null</code> is returned.
     *
     * @param object
     *            the object whose value to return as a String
     * @return the object's value as a String
     * @exception SQLException
     *                if the object cannot be returned as a String.
     */
    public static String getString (Object object) throws SQLException
    {
        String result;
        if (object == null)
            result = null;
        else if (object instanceof Boolean)
        {
            result = ((Boolean)object).toString();
        }
        else if (object instanceof Short)
        {
            result = ((Short)object).toString();
        }
        else if (object instanceof Integer)
        {
            result = ((Integer)object).toString();
        }
        else if (object instanceof Long)
        {
            result = ((Long)object).toString();
        }
        else if (object instanceof Float)
        {
            result = ((Float)object).toString();
        }
        else if (object instanceof Double)
        {
            result = ((Double)object).toString();
        }
        else if (object instanceof Character)
        {
            result = ((Character)object).toString();
        }
        else if (object instanceof String)
        {
            result = (String) object;
        }
        else if (object.getClass().getSimpleName().equals("byte[]"))
        {
            byte[] ba =(byte[])object;
            result = bytesToHexString(ba);
        }
        else
            throw new SQLDataException("Cannot convert " + object.getClass().getSimpleName() + " to String");

        return result;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHexString(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s)
    {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
