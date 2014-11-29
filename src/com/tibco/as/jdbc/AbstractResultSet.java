// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------
package com.tibco.as.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * AbstractResultSet contains all of the methods of the java.sql.ResultSet
 * interface which are not implemented by com.tibco.as.jdbc.ASResultSet. As
 * methods are added to ASResultSet, they are removed from this class. This
 * makes it easier to keep track of what is not yet supported.
 */
public class AbstractResultSet
{

    public <T> T unwrap (Class<T> iface) throws SQLException
    {
        return null;
    }

    public boolean isWrapperFor (Class<?> iface) throws SQLException
    {
        return false;
    }

    // -- Other unimplemented methods

    @Deprecated public BigDecimal getBigDecimal (int columnIndex, int scale) throws SQLException
    {
        return null;
    }

    public InputStream getAsciiStream (int columnIndex) throws SQLException
    {
        return null;
    }

    @Deprecated public InputStream getUnicodeStream (int columnIndex) throws SQLException
    {
        return null;
    }

    public InputStream getBinaryStream (int columnIndex) throws SQLException
    {
        return null;
    }

    @Deprecated public BigDecimal getBigDecimal (String columnLabel, int scale) throws SQLException
    {
        return null;
    }

    public InputStream getAsciiStream (String columnLabel) throws SQLException
    {
        return null;
    }

    @Deprecated public InputStream getUnicodeStream (String columnLabel) throws SQLException
    {
        return null;
    }

    public InputStream getBinaryStream (String columnLabel) throws SQLException
    {
        return null;
    }

    public SQLWarning getWarnings () throws SQLException
    {
        return null;
    }

    public void clearWarnings () throws SQLException
    {

    }

    public String getCursorName () throws SQLException
    {
        return null;
    }

    public Reader getCharacterStream (int columnIndex) throws SQLException
    {
        return null;
    }

    public Reader getCharacterStream (String columnLabel) throws SQLException
    {
        return null;
    }

    public BigDecimal getBigDecimal (int columnIndex) throws SQLException
    {
        return null;
    }

    public BigDecimal getBigDecimal (String columnLabel) throws SQLException
    {
        return null;
    }

    public void setFetchSize (int rows) throws SQLException
    {

    }

    public int getFetchSize () throws SQLException
    {
        return 0;
    }

    public Object getObject (int columnIndex, Map<String, Class<?>> map) throws SQLException
    {
        return null;
    }

    public Ref getRef (int columnIndex) throws SQLException
    {
        return null;
    }

    public Clob getClob (int columnIndex) throws SQLException
    {
        return null;
    }

    public Array getArray (int columnIndex) throws SQLException
    {
        return null;
    }

    public Object getObject (String columnLabel, Map<String, Class<?>> map) throws SQLException
    {
        return null;
    }

    public Ref getRef (String columnLabel) throws SQLException
    {
        return null;
    }

    public Clob getClob (String columnLabel) throws SQLException
    {
        return null;
    }

    public Array getArray (String columnLabel) throws SQLException
    {
        return null;
    }

    public Date getDate (int columnIndex, Calendar cal) throws SQLException
    {
        return null;
    }

    public Date getDate (String columnLabel, Calendar cal) throws SQLException
    {
        return null;
    }

    public Time getTime (int columnIndex, Calendar cal) throws SQLException
    {
        return null;
    }

    public Time getTime (String columnLabel, Calendar cal) throws SQLException
    {
        return null;
    }

    public Timestamp getTimestamp (int columnIndex, Calendar cal) throws SQLException
    {
        return null;
    }

    public Timestamp getTimestamp (String columnLabel, Calendar cal) throws SQLException
    {
        return null;
    }

    public URL getURL (int columnIndex) throws SQLException
    {
        return null;
    }

    public URL getURL (String columnLabel) throws SQLException
    {
        return null;
    }

    public void updateRef (int columnIndex, Ref x) throws SQLException
    {

    }

    public void updateRef (String columnLabel, Ref x) throws SQLException
    {

    }

    public void updateBlob (int columnIndex, Blob x) throws SQLException
    {

    }

    public void updateBlob (String columnLabel, Blob x) throws SQLException
    {

    }

    public void updateClob (int columnIndex, Clob x) throws SQLException
    {

    }

    public void updateClob (String columnLabel, Clob x) throws SQLException
    {

    }

    public void updateArray (int columnIndex, Array x) throws SQLException
    {

    }

    public void updateArray (String columnLabel, Array x) throws SQLException
    {

    }

    public RowId getRowId (int columnIndex) throws SQLException
    {
        return null;
    }

    public RowId getRowId (String columnLabel) throws SQLException
    {
        return null;
    }

    public void updateRowId (int columnIndex, RowId x) throws SQLException
    {

    }

    public void updateRowId (String columnLabel, RowId x) throws SQLException
    {

    }

    public int getHoldability () throws SQLException
    {
        return 0;
    }

    public void updateNString (int columnIndex, String nString) throws SQLException
    {

    }

    public void updateNString (String columnLabel, String nString) throws SQLException
    {

    }

    public void updateNClob (int columnIndex, NClob nClob) throws SQLException
    {

    }

    public void updateNClob (String columnLabel, NClob nClob) throws SQLException
    {

    }

    public NClob getNClob (int columnIndex) throws SQLException
    {
        return null;
    }

    public NClob getNClob (String columnLabel) throws SQLException
    {
        return null;
    }

    public SQLXML getSQLXML (int columnIndex) throws SQLException
    {
        return null;
    }

    public SQLXML getSQLXML (String columnLabel) throws SQLException
    {
        return null;
    }

    public void updateSQLXML (int columnIndex, SQLXML xmlObject) throws SQLException
    {

    }

    public void updateSQLXML (String columnLabel, SQLXML xmlObject) throws SQLException
    {

    }

    public String getNString (int columnIndex) throws SQLException
    {
        return null;
    }

    public String getNString (String columnLabel) throws SQLException
    {
        return null;
    }

    public Reader getNCharacterStream (int columnIndex) throws SQLException
    {
        return null;
    }

    public Reader getNCharacterStream (String columnLabel) throws SQLException
    {
        return null;
    }

    // -- methods which are not implemented and will throw an SQLFeatureNotSupportedException
    // -- since we only support TYPE_FORWARD_ONLY result sets which are read-only.

    public boolean isLast () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void afterLast () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean last () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean absolute (int row) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean relative (int rows) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean previous () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean rowUpdated () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean rowInserted () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public boolean rowDeleted () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNull (int columnIndex) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBoolean (int columnIndex, boolean x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateByte (int columnIndex, byte x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateShort (int columnIndex, short x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateInt (int columnIndex, int x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateLong (int columnIndex, long x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateFloat (int columnIndex, float x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateDouble (int columnIndex, double x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBigDecimal (int columnIndex, BigDecimal x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateString (int columnIndex, String x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBytes (int columnIndex, byte[] x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateDate (int columnIndex, Date x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateTime (int columnIndex, Time x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateTimestamp (int columnIndex, Timestamp x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream (int columnIndex, InputStream x, int length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream (int columnIndex, InputStream x, int length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream (int columnIndex, Reader x, int length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateObject (int columnIndex, Object x, int scaleOrLength) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateObject (int columnIndex, Object x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNull (String columnLabel) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBoolean (String columnLabel, boolean x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateByte (String columnLabel, byte x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateShort (String columnLabel, short x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateInt (String columnLabel, int x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateLong (String columnLabel, long x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateFloat (String columnLabel, float x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateDouble (String columnLabel, double x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBigDecimal (String columnLabel, BigDecimal x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateString (String columnLabel, String x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBytes (String columnLabel, byte[] x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateDate (String columnLabel, Date x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateTime (String columnLabel, Time x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateTimestamp (String columnLabel, Timestamp x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream (String columnLabel, InputStream x, int length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream (String columnLabel, InputStream x, int length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream (String columnLabel, Reader reader, int length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateObject (String columnLabel, Object x, int scaleOrLength) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateObject (String columnLabel, Object x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void insertRow () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateRow () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void deleteRow () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void refreshRow () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void cancelRowUpdates () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void moveToInsertRow () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void moveToCurrentRow () throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream (int columnIndex, Reader x, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream (String columnLabel, Reader reader, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream (int columnIndex, InputStream x, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream (int columnIndex, InputStream x, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream (int columnIndex, Reader x, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream (String columnLabel, InputStream x, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream (String columnLabel, InputStream x, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream (String columnLabel, Reader reader, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob (int columnIndex, InputStream inputStream, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob (String columnLabel, InputStream inputStream, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob (int columnIndex, Reader reader, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob (String columnLabel, Reader reader, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob (int columnIndex, Reader reader, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob (String columnLabel, Reader reader, long length) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream (int columnIndex, Reader x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNCharacterStream (String columnLabel, Reader reader) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream (int columnIndex, InputStream x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream (int columnIndex, InputStream x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream (int columnIndex, Reader x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateAsciiStream (String columnLabel, InputStream x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBinaryStream (String columnLabel, InputStream x) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateCharacterStream (String columnLabel, Reader reader) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob (int columnIndex, InputStream inputStream) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateBlob (String columnLabel, InputStream inputStream) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob (int columnIndex, Reader reader) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateClob (String columnLabel, Reader reader) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob (int columnIndex, Reader reader) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public void updateNClob (String columnLabel, Reader reader) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException
    {
        throw new SQLFeatureNotSupportedException();
    }

}
