// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import com.tibco.as.jdbc.ASConnection;
import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;

/**
 * This example demonstrates how you can use the ActiveSpaces JDBC Driver in conjunction with the
 * ActiveSpaces Java API. In this example, the ActiveSpaces JDBC Driver is used to create a
 * connection to a metaspace and then create a space. The ActiveSpaces Java API is then used to
 * manipulate the data in the space.
 *
 * By default, the ActiveSpaces JDBC Driver will join a space as a LEECH when performing its
 * functions. For an application to join a space as a SEEDER, the ActiveSpaces Java API must be used
 * to join the space as a SEEDER before using the ActiveSpaces JDBC Driver to perform actions on the
 * space. If a space has been previously joined by an application before the ActiveSpaces JDBC
 * Driver is used, the JDBC Driver will join the space without changing the distribution role when
 * performing its functions.
 *
 * This example is useful in the following scenarios:
 * <ol>
 * <li>You want your application to act as a SEEDER for an ActiveSpaces space.</li>
 * <li>Your application is moving away from using SQL commands to interface to ActiveSpaces but
 * still want to use SQL to create tables/spaces.</li>
 * </ol>
 *
 * To build this example, do the following:
 * <ol>
 * <li>Ensure that the Java JDK bin directory is in your path.</li>
 * <li>Ensure that the Ant bin directory is in your path.</li>
 * <li>Change directory up one level (to AS_HOME/examples/java/JDBCDriver) and type 'ant'. The jar files as-jdbcdriver.jar and
 * ExamplesJDBC.jar will be built.</li>
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
 * This example will show you how you can join a space as a SEEDER and then update the data in the
 * space using the ActiveSpaces JDBC Driver. Therefore, it is not necessary to run as-agent with
 * this example.
 *
 * This example is coded so that it will use the default metaspace settings. However, it is
 * recommended that you change the discovery setting in this example to use a unique port number.
 * Using a unique port for tibpgm will help to prevent conflicts with others who may be running
 * ActiveSpaces examples or applications with the default settings. For example, you could change
 * your discovery URL in this example to: tibpgm://6699 if you knew that no one else on the network
 * was also using tibpgm with port 6699.
 *
 * Once you have modified the code to use a unique port for discovery with tibpgm, you have built
 * as-jdbcdriver.jar and ExamplesJDBC.jar and properly set up your environment, you run the
 * MixedASJdbc example by entering the following:
 * <ul>
 * <li>java MixedJdbcAS</li>
 * </ul>
 */
public class MixedJdbcAS
{
    // ActiveSpaces metaspace settings
    static String        metaspaceName    = "ms";
    // Important!! Change to tibpgm://<unique_port> to prevent conflicts
    static String        discoveryURL     = "tibpgm";
    static String        listenURL        = "tcp";
    static String        memberName       = "MixedJdbcAS";

    // ActiveSpaces space settings
    public static String spaceName        = "myspace";
    public static String minSeeders       = "1";
    public static String capacity         = "-1";
    public static String replicationCount = "0";

    // To use the ActiveSpaces JDBC Driver you need to know two things: the name of the
    // ActiveSpaces Driver and the ActiveSpaces JDBC URL. (Note: User names and passwords
    // are not currently supported.)
    static String        jdbcDriverName   = com.tibco.as.jdbc.ASDriver.class.getName();
    public static String jdbcURL          = "jdbc:tibco:as:" + metaspaceName + ";discovery=" + discoveryURL
                                                  + ";listen=" + listenURL + ";member_name=" + memberName;

    // ActiveSpaces objects
    Metaspace            metaspace;
    Space                space;

    // JDBC Driver objects
    // The ASConnection object implements java.sql.Connection but has some additional methods
    // specific to ActiveSpaces that we need to use in this example.
    ASConnection         connection;
    Statement            statement;

    public static void main (String[] args)
    {
        MixedJdbcAS example = null;
        try
        {
            example = new MixedJdbcAS();
            example.initializeJDBC();
            example.initializeAS();
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

    public void initializeJDBC () throws ASException
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

        try
        {
            // Establish a connection to the metaspace and then define the space using
            // a CREATE TABLE SQL command
            connection = (ASConnection) DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
            UtilsJDBC.createTable(statement, spaceName, minSeeders, capacity, replicationCount);
        }
        catch (Exception e)
        {
            throw new ASException(ASStatus.SYS_ERROR, e);
        }
    }

    public void initializeAS () throws ASException
    {
        // For this example, the JDBC Driver has already connected to the metaspace
        // and defined the space. So we get the metaspace object from the driver
        // and then join the space as a SEEDER using the ActiveSpaces Java API.
        metaspace = connection.getMetaspace();
        space = metaspace.getSpace(spaceName, DistributionRole.SEEDER);
    }

    public void run () throws Exception
    {
        // For this example, we use the ActiveSpaces Java API to manipulate the data in the space.
        // The result should be the same as the result of running SimpleJDBC.
        String keyfield = "key";
        String timefield = "time";
        String valuefield = "value";

        // put some data into the space which we have previously joined
        Tuple tuple = Tuple.create();
        tuple.put(keyfield, 1);
        tuple.put(timefield, DateTime.create());
        tuple.put(valuefield, "Halloween");
        space.put(tuple);

        tuple = Tuple.create();
        tuple.put(keyfield, 2);
        tuple.put(timefield, DateTime.create());
        tuple.put(valuefield, "All Hallow's Eve");
        space.put(tuple);

        tuple = Tuple.create();
        tuple.put(keyfield, 3);
        // 11/22/2012 14:25:00 GMT
        long Nov22TwoThousandTwelve = 1353594300000L;
        Date date = new Date(Nov22TwoThousandTwelve);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        tuple.put(timefield, DateTime.create(calendar));
        tuple.put(valuefield, "Thanksgiving");
        space.put(tuple);

        // retrieve the data and display it
        UtilsJDBC.browseSpace(space, "");

        // update data in the space
        tuple = Tuple.create();
        tuple.put(keyfield, 2);
        Tuple oldValue = space.get(tuple);
        oldValue.put(valuefield, "Christmas");
        space.put(oldValue);

        // retrieve the updated data and display it
        UtilsJDBC.browseSpace(space, "key=2");

        // delete data from the space
        UtilsJDBC.takeFromSpace(space, "value=\"Halloween\"");

        // retrieve the data and display it
        UtilsJDBC.browseSpace(space, "");
    }

    public void discard ()
    {
        try
        {
            if (space != null)
                space.close();
            space = null;
            // we do not close the metaspace, when the connection is closed
            // the metaspace will be closed
            metaspace = null;

            if (statement != null)
                statement.close();
            statement = null;
            if (connection != null)
                connection.close();
            connection = null;
        }
        catch (Exception ex)
        {
            System.out.println("Exception closing JDBC connection...");
            ex.printStackTrace();
        }
    }
}
