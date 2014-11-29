// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.sql;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.FieldDef.FieldType;

public class TupleUtil
{

    public static void setTupleField (Tuple tuple, FieldDef fieldDef, String fieldName, Object fieldValue)
            throws IllegalArgumentException
    {
        if (tuple == null)
            throw new IllegalArgumentException("tuple parameter cannot be null");
        if (fieldDef == null)
            throw new IllegalArgumentException("fieldDef parameter cannot be null");
        if (fieldName == null)
            throw new IllegalArgumentException("fieldName parameter cannot be null");
        boolean nullable = fieldDef.isNullable();
        if (fieldValue == null && !nullable)
            throw new IllegalArgumentException("Column " + fieldName + " cannot be null");
        if (fieldValue == null && nullable)
        {
            // it is valid for this field to be null
            // do nothing and return as we can't cast null below
            return;
        }

        // now update the specified field's value
        FieldType fieldType = fieldDef.getType();
        switch (fieldType)
        {
            case STRING:
            {
                tuple.put(fieldName, (String) fieldValue);
                break;
            }

            case CHAR:
            {
                tuple.put(fieldName, (Character) fieldValue);
                break;
            }

            case BOOLEAN:
            {
                tuple.put(fieldName, (Boolean) fieldValue);
                break;
            }

            case SHORT:
            {
                tuple.put(fieldName, (Short) fieldValue);
                break;
            }

            case INTEGER:
            {
                tuple.put(fieldName, (Integer) fieldValue);
                break;
            }

            case LONG:
            {
                tuple.put(fieldName, (Long) fieldValue);
                break;
            }

            case FLOAT:
            {
                tuple.put(fieldName, (Float) fieldValue);
                break;
            }

            case DOUBLE:
            {
                tuple.put(fieldName, (Double) fieldValue);
                break;
            }

            case BLOB:
            {
                tuple.put(fieldName, (byte[]) fieldValue);
                break;
            }

            case DATETIME:
            {
                try
                {
                    if (fieldValue instanceof String)
                    {
                        Date d = DateFormat.getInstance().parse((String) fieldValue);
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(d.getTime());
                        DateTime dt = DateTime.create(c);

                        tuple.putDateTime(fieldName, dt);
                    }
                    else if (fieldValue instanceof DateTime)
                    {
                        tuple.putDateTime(fieldName, (DateTime) fieldValue);
                    }
                    else
                        throw new IllegalArgumentException("fieldValue contains invalid date value");
                    break;
                }
                catch (ParseException ex)
                {
                    throw new IllegalArgumentException("fieldValue contains invalid date value");
                }
            }

            default:
                throw new UnsupportedOperationException("Unsupported type [" + fieldType + "]");
        }
    }

    public static void setTupleField (Tuple tuple, FieldDef fieldDef, String fieldName, String fieldValue)
            throws IllegalArgumentException
    {
        if (tuple == null)
            throw new IllegalArgumentException("tuple parameter cannot be null");
        if (fieldDef == null)
            throw new IllegalArgumentException("fieldDef parameter cannot be null");
        if (fieldName == null)
            throw new IllegalArgumentException("fieldName parameter cannot be null");
        boolean nullable = fieldDef.isNullable();
        if (fieldValue == null && !nullable)
            throw new IllegalArgumentException("fieldValue parameter cannot be null");
        if (fieldValue.equalsIgnoreCase("null") && !nullable)
            throw new IllegalArgumentException("fieldValue parameter cannot be null");
        if ((fieldValue == null || fieldValue.equalsIgnoreCase("null")) && nullable)
        {
            // it is valid for this field to be null
            // do nothing but return as if we had set the field
            return;
        }

        FieldType fieldType = fieldDef.getType();
        switch (fieldType)
        {
            case STRING:
            {
                String sval = stripQuotesFromString((String) fieldValue);
                tuple.put(fieldName, sval);
                break;
            }

            case CHAR:
            {
                // chars are a string of length 1
                String cval = stripQuotesFromString((String)fieldValue);
                tuple.put(fieldName,  cval.charAt(0));
                break;
            }

            case BOOLEAN:
            {
                tuple.put(fieldName, Boolean.parseBoolean(fieldValue));
                break;
            }

            case SHORT:
            {
                try
                {
                    tuple.put(fieldName, Short.parseShort(fieldValue));
                    break;
                }
                catch (NumberFormatException ex)
                {
                    throw new IllegalArgumentException(
                            "fieldValue parameter must be a String representing a Short number");
                }
            }

            case INTEGER:
            {
                try
                {
                    tuple.put(fieldName, Integer.parseInt(fieldValue));
                    break;
                }
                catch (NumberFormatException ex)
                {
                    throw new IllegalArgumentException(
                            "fieldValue parameter must be a String representing an Integer number");
                }
            }

            case LONG:
            {
                try
                {
                    tuple.put(fieldName, Long.parseLong(fieldValue));
                    break;
                }
                catch (NumberFormatException ex)
                {
                    throw new IllegalArgumentException(
                            "fieldValue parameter must be a String representing a Long number");
                }
            }

            case FLOAT:
            {
                try
                {
                    tuple.put(fieldName, Float.parseFloat(fieldValue));
                    break;
                }
                catch (NumberFormatException ex)
                {
                    throw new IllegalArgumentException(
                            "fieldValue parameter must be a String representing a Float number");
                }
            }

            case DOUBLE:
            {
                try
                {
                    tuple.put(fieldName, Double.parseDouble(fieldValue));
                    break;
                }
                catch (NumberFormatException ex)
                {
                    throw new IllegalArgumentException(
                            "fieldValue parameter must be a String representing a Double number");
                }
            }

            case BLOB:
            {
                // blobs are given as a string of hex characters which
                // represent each byte of a byte array
                String sval = stripQuotesFromString((String) fieldValue);
                if (sval != null)
                {
                    // convert the hex string back into a byte array
                    byte[] ba = TypeUtil.hexStringToByteArray(sval);
                    tuple.put(fieldName, ba);
                }
                break;
            }

            case DATETIME:
            {
                // The date string comes to us with surrounding double quotes put on
                // by the grammar parser. AS needs those double quotes on strings in filters
                // so we cannot have the grammar remove them. Therefore, we need to
                // remove the double quotes here. Note: do not call stripQuotesFromString
                // as that will leave the double quotes if a string contains a space
                // which is not what we want for date/time strings.
                int len = ((String) fieldValue).length();
                String dateStr = ((String) fieldValue).substring(1, len - 1);

                // we need to support several ways of entering the date and time
                // SQL users will be used to entering the date and time one way and
                // AS users will be used to entering the date and time another way

                // first try typical SQL date and time formats
                boolean found = false;
                Date date = null;
                // java.sql.Timestamp format yyyy-mm-dd hh:mm:ss.fffffffff
                DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
                try
                {
                    date = dformat.parse(dateStr);
                    found = true;
                }
                catch (ParseException ex)
                {
                    // try parsing the string with java.sql.Time format  hh:mm:ss
                    dformat = new SimpleDateFormat("HH:mm:ss");
                }
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                        found = true;
                    }
                    catch (ParseException ex)
                    {
                        // try parsing the string with java.sql.Date format yyyy-mm-dd
                        dformat = new SimpleDateFormat("yyyy-MM-dd");
                    }
                }
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                        found = true;
                    }
                    catch (ParseException ex)
                    {
                        // try parsing the string with default java.util.Date format dow mon dd hh:mm:ss zzz yyyy
                        dformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    }
                }
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                        found = true;
                    }
                    catch (ParseException ex)
                    {
                        // try parsing the string with alternate java.util.Date format dow mon dd hh:mm:ss yyyy
                        dformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
                    }
                }
                // if still not found, try various other date/time parsing formats
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                        found = true;
                    }
                    catch (ParseException ex)
                    {
                        dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    }
                }
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                        found = true;
                    }
                    catch (ParseException ex)
                    {
                        // try parsing the string with with seconds added
                        dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                }
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                        found = true;
                    }
                    catch (ParseException ex)
                    {
                        // try parsing the string with time zone added
                        dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sszzz");
                    }
                }
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                        found = true;
                    }
                    catch (ParseException ex)
                    {
                        // try parsing the string with how AS formats DateTime strings
                        dformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    }
                }
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                        found = true;
                    }
                    catch (ParseException ex)
                    {
                        // try parsing the string with how AS formats DateTime strings for GMT
                        dformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'GMT'");
                        dformat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    }
                }
                if (!found)
                {
                    try
                    {
                        date = dformat.parse(dateStr);
                    }
                    catch (ParseException ex)
                    {
                        throw new IllegalArgumentException(
                                "ParseException - unsupported format for DateTime value specified: " + ex.getMessage());
                    }
                }
                if (found)
                {
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(date.getTime());
                    DateTime dt = DateTime.create(c);
                    tuple.putDateTime(fieldName, dt);
                }
                else
                {
                    throw new IllegalArgumentException("Unrecognized format for DateTime value: " + dateStr);
                }
                break;
            }

            default:
                throw new UnsupportedOperationException("Unsupported type [" + fieldType + "]");
        }
    }

    public static Tuple updateTuple (Tuple oldTuple, HashMap<String, String> newValues, SpaceDef spaceDef)
            throws IllegalArgumentException
    {
        if (oldTuple == null)
            throw new IllegalArgumentException("oldTuple parameter cannot be null");
        if (spaceDef == null)
            throw new IllegalArgumentException("spaceDef parameter cannot be null");

        Tuple newTuple = null;
        if (newValues == null || newValues.isEmpty())
        {
            // just return the old Tuple
            newTuple = oldTuple;
        }
        else
        {
            // For each field in the tuple, if there is a new value for the
            // field store that in the new tuple. Otherwise store the old value.
            newTuple = Tuple.create();
            Iterator<FieldDef> iter = spaceDef.getFieldDefs().iterator();
            while (iter.hasNext())
            {
                FieldDef fdef = iter.next();
                String columnName = fdef.getName();
                Object columnValue = newValues.get(columnName);
                if (columnValue != null)
                    TupleUtil.setTupleField(newTuple, fdef, columnName, (String) columnValue);
                else
                {
                    columnValue = oldTuple.get(columnName);
                    TupleUtil.setTupleField(newTuple, fdef, columnName, columnValue);
                }
            }
        }
        return newTuple;
    }

    public static String stripQuotesFromString (String sval)
    {
        String result = null;
        if (sval != null && !sval.isEmpty())
        {
            // if the string contains a single quote or space, leave the double quotes
            if (sval.indexOf("'") != -1 || sval.indexOf(" ") != -1)
                result = sval;
            else
            {
                if (sval.startsWith("\""))
                {
                    result = sval.substring(1, sval.length() - 1);
                }
                else
                {
                    result = sval;
                }
            }
        }
        return result;
    }

}
