// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.DateTime;

/**
 * This example demonstrates some simple usage of the ActiveSpaces JDBC Driver. In this example you
 * will see how to use the ActiveSpaces JDBC Driver to make a connection to a Metaspace (the
 * ActiveSpaces equivalent of a database), create a Space (the ActiveSpaces equivalent of a database
 * table, put some data into the Space and then read the data back out of the Space.
 *
 * To build this example, do the following:
 * <ol>
 * <li>Ensure that the Java JDK bin directory is in your path.</li>
 * <li>Ensure that the Ant bin directory is in your path.</li>
 * <li>Change directory up one level (to AS_HOME/examples/java/JDBCDriver) and type 'ant'. The jar files as-jdbcdriver.jar
 * and ExamplesJDBC.jar will be built.</li>
 * </ol>
 *
 * To run this example, you should ensure that your environment variables have been set up for
 * running ActiveSpaces as discussed under Post-Installation Tasks, Setting Environment Variables
 * of the TIBCO ActiveSpaces Installation document.
 * To run this example, you must have the following ActiveSpaces jar files in your classpath:
 * <ul>
 * <li>as-common.jar - ActiveSpaces Java API</li>
 * <li>as-jdbcdriver.jar - ActiveSpaces JDBC Driver</li>
 * <li>antlr-3.2.jar - ANTLR is used by the driver to parse SQL command strings</li>
 * <li>ExamplesJDBC.jar - Contains the examples for the ActiveSpaces JDBC Driver</li>
 * </ul>
 *
 * Since JDBC has no concept of providing resources to a database table as ActiveSpaces does for
 * spaces, all JDBC actions on a space/table are done as a LEECH by default. All references to a
 * space are closed after the JDBC action is completed. Therefore, unless you mix JDBC programming
 * with ActiveSpaces programming in your application and use the ActiveSpaces Java API to join a
 * space as a SEEDER, you will need to run another ActiveSpaces application, such as as-agent, as a
 * SEEDER in order to run this example.
 *
 * This example is coded so that it will use the default metaspace settings and as-agent can be
 * started without passing it any arguments. However, it is recommended that you change the
 * discovery setting in this example to use a unique port number. Using a unique port for tibpgm
 * will help to prevent conflicts with others who may be running ActiveSpaces examples or
 * applications with the default settings. For example, if you change your discovery URL in this
 * example to:
 * <ul>
 * <li>tibpgm://6699</li>
 * </ul>
 * then you would enter the following to start as-agent as a SEEDER for this example:
 * <ul>
 * <li>AS_HOME/bin/as-agent.exe -discovery tibpgm://6699</li>
 * </ul>
 *
 * Once as-agent is started as a SEEDER, you can then run SimpleJDBC by entering the following:
 * <ul>
 * <li>java SimpleJDBC</li>
 * </ul>
 */
public class SimpleJDBC
{
    // To use the ActiveSpaces JDBC Driver you need to know two things: the name of the
    // ActiveSpaces Driver and the ActiveSpaces JDBC URL. (Note: User names and passwords
    // are not currently supported.)
    public static String jdbcDriverName   = com.tibco.as.jdbc.ASDriver.class.getName();

    // The ActiveSpaces JDBC URL is used to make a connection to an ActiveSpaces Metaspace.
    // In JDBC terms, an ActiveSpaces Metaspace is the equivalent of a database.
    // The ActiveSpaces JDBC URL always begins with jdbc:tibco:as followed by any Metaspace
    // connection settings. If no settings are given, the normal default settings are used.
    // The Metaspace settings in the JDBC URL are separated by semicolons and are formatted
    // the same as you would normally specify the settings for ActiveSpaces with name/value pairs.
    //
    // The minimum JDBC URL that is always required is:
    //   jdbc:tibco:as
    // The metaspace name can be specified as:
    //   jdbc:tibco:as:<metaspace_name>
    // or
    //   jdbc:tibco:as;metaspace=<metaspace_name>
    // Notice that name/value pair settings are always preceded by a semicolon.
    //
    // An example of a more complex JDBC URL is:
    //   jdbc:tibco:as:examplems;discovery=tcp://10.0.1.8:6789;10.0.1.8:6790;member_name=jdbc1
    // Notice that this particular discovery setting contains an embedded semicolon just as you would
    // normally embed the semicolon when specifying this discovery setting for ActiveSpaces.
    //
    // The following settings are the defaults that are also used with the normal ActiveSpaces
    // examples. To use this example with the other ActiveSpaces examples, make sure that you run
    // this example with the same metaspace settings as the other examples.
    public static String metaspaceName    = "ms";
    // Important!! Change to tibpgm://<unique_port> to prevent conflicts
    public static String discovery        = "tibpgm";
    public static String memberName       = "SimpleJDBC-1";
    public static String jdbcURL          = "jdbc:tibco:as:" + metaspaceName + ";discovery=" + discovery
                                                  + ";member_name=" + memberName;

    // JDBC Driver objects
    Connection           connection;
    Statement            statement;

    // ActiveSpaces space settings
    public static String spaceName        = "myspace";
    public static String minSeeders       = "1";
    public static String capacity         = "-1";
    public static String replicationCount = "0";

    public static void main (String[] args)
    {
        SimpleJDBC example = null;
        try
        {
            example = new SimpleJDBC();
            example.initializeDriver();
            example.run();
        }
        catch (ASException as)
        {
            as.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (example != null)
            {
                example.discard();
            }
        }
    }

    public void initializeDriver () throws ASException
    {
        // Register the ActiveSpaces JDBC Driver with the JDBC DriverManager
        try
        {
            Class.forName(jdbcDriverName);
        }
        catch (ClassNotFoundException ex)
        {
            throw new ASException(ASStatus.NOT_FOUND, ex);
        }

        // Make a connection to the ActiveSpaces Metaspace defined by the JDBC URL
        try
        {
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
        }
        catch (SQLException ex)
        {
            throw new ASException(ASStatus.SYS_ERROR, ex);
        }

    }

    public void run () throws Exception
    {
        UtilsJDBC.createTable(statement, spaceName, minSeeders, capacity, replicationCount);

        // put some data into the space
        String sdt = DateTime.create().toFilterString();
        String sql = "INSERT INTO " + spaceName + " (\"key\", value, \"time\") values (1, 'Halloween', " + sdt + ")";
        statement.executeUpdate(sql);

        sdt = DateTime.create().toString();
        sql = "INSERT INTO " + spaceName + " (\"key\", value, \"time\") values (2, 'All Hallow''s Eve', '" + sdt + "')";
        statement.executeUpdate(sql);

        sdt = "2012-11-22 14:25";
        sql = "INSERT INTO " + spaceName + " (\"key\", value, \"time\") values (3, 'Thanksgiving', '" + sdt + "')";
        statement.executeUpdate(sql);

        // retrieve the data and display it
        sql = "SELECT * FROM " + spaceName;
        ResultSet rs = statement.executeQuery(sql);
        displayResultSet(rs);
        rs.close();

        // update data in the space
        // Note: The WHERE clause is passed thru to ActiveSpaces as an ActiveSpaces filter.
        //       Therefore the WHERE clause should conform to what AS expects for a filter
        //       except in the use of double quotes around strings. The JDBC driver takes care
        //       of converting single quotes, as expected in SQL where clauses, to double quotes
        //       as expected for filter strings.
        sql = "UPDATE " + spaceName + " SET value='Christmas' WHERE key=2";
        statement.executeUpdate(sql);

        // retrieve the updated data and display it
        sql = "SELECT * FROM " + spaceName + " WHERE key=2";
        rs = statement.executeQuery(sql);
        displayResultSet(rs);
        rs.close();

        // delete data from the space
        // WARNING!!! without a where clause all data is deleted from the space
        sql = "DELETE FROM " + spaceName + " WHERE value = 'Halloween'";
        statement.executeUpdate(sql);

        // retrieve the data and display it
        sql = "SELECT * FROM " + spaceName;
        rs = statement.executeQuery(sql);
        displayResultSet(rs);
        rs.close();

    }

    public void discard ()
    {
        try
        {
            if (statement != null)
                statement.close();
            statement = null;
            if (connection != null)
                connection.close();
            connection = null;
        }
        catch (SQLException ex)
        {
            System.out.println("Exception closing JDBC connection...");
            ex.printStackTrace();
        }
    }

    /**
     * Displays the data in the given ResultSet.
     *
     * @param rs
     */
    protected void displayResultSet (ResultSet rs)
    {
        if (rs == null)
            return;
        try
        {
            while (rs.next())
            {
                int key = rs.getInt("key");
                String value = rs.getString("value");
                Date date = rs.getDate("time");
                Time time = rs.getTime("time");
                System.out.printf("ResultSet row: [%d] [%s] [%s] [%s] %n", key, value, date.toString(), time.toString());
            }
            System.out.println();
        }
        catch (SQLException ex)
        {
            // do nothing
        }
    }
}
