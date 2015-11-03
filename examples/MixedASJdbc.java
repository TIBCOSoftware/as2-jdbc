// -------------------------------------------------------------------
//  Copyright (c) 2012-2015 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.IndexDef.IndexType;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;

/**
 * This example demonstrates how you can use the ActiveSpaces JDBC Driver in conjunction with the
 * ActiveSpaces Java API. This example is the same as SimpleJDBC except the ActiveSpaces Java API
 * is first used to create a connection to a metaspace and to create and join a space as a
 * seeder. You will then see how the ActiveSpaces JDBC Driver is used to connect to the existing
 * metaspace and how the driver references existing spaces.
 *
 * By default, the ActiveSpaces JDBC Driver will join a space as a LEECH when performing
 * its functions. For an application to join a space as a SEEDER, the ActiveSpaces Java
 * API must be used to join the space as a SEEDER before using the ActiveSpaces JDBC
 * Driver to perform actions on the space. If a space has been previously joined by
 * an application before the ActiveSpaces JDBC Driver is used, the JDBC Driver will
 * join the space without changing the distribution role when performing its functions.
 *
 * This example is useful in the following scenarios:
 * <ol>
 * <li>You want your application to act as a SEEDER for an ActiveSpaces space.</li>
 * <li>You have legacy applications which use SQL to insert, update or delete data and
 * now want to integrate ActiveSpaces into those applications.</li>
 * <li>Your application developers are more comfortable using SQL commands for manipulating
 * data in ActiveSpaces.</li>
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
 * <li>java MixedASJdbc</li>
 * </ul>
 */
public class MixedASJdbc
{
    // ActiveSpaces metaspace settings
    static String metaspaceName    = "ms";
    // Important!! Change to tibpgm://<unique_port> to prevent conflicts
    static String discoveryURL     = "tibpgm";
    static String listenURL        = "tcp";
    static String memberName       = "MixedASJdbc";

    // ActiveSpaces space settings
    static String spaceName        = "myspace";
    int           minSeeders       = 1;
    int           capacity         = -1;
    int           replicationCount = 0;

    // To use the ActiveSpaces JDBC Driver you need to know two things: the name of the
    // ActiveSpaces Driver and the ActiveSpaces JDBC URL. (Note: User names and passwords
    // are not currently supported.)
    static String jdbcDriverName   = com.tibco.as.jdbc.ASDriver.class.getName();
    // In this example, we use the ActiveSpace API to get a metaspace object and pass it
    // to the JDBC driver. So the JDBC URL should not contain any metaspace settings but only
    // the info needed to identify itself to the JDBC Driver Manager.
    static String jdbcURL          = "jdbc:tibco:as";

    // ActiveSpaces objects
    // For this example, we join our space as a seeder. If a space already exists,
    // the ActiveSpaces JDBC Driver will get a reference to the existing space
    // and the distribution role of the space will not be changed. The JDBC Driver
    // will always release its references to the spaces it joins. So if an
    // application wants to remain as a seeder for a space, it needs to retain
    // a reference to the space outside of the JDBC Driver.
    Metaspace     metaspace;
    Space         space;

    // JDBC Driver objects
    Connection    connection;
    Statement     statement;

    public static void main (String[] args)
    {
        MixedASJdbc example = null;
        try
        {
            example = new MixedASJdbc();
            example.initializeAS();
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

    public void initializeAS () throws ASException
    {
        metaspace = connectMetaspace();
        space = connectSpace();
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

        // Make a connection to our existing ActiveSpaces Metaspace object
        try
        {
            // For the case where we want to create a JDBC connection to a metaspace
            // for which we already have the metaspace object, we put the metaspace
            // object into a Properties object. The JDBC URL should not contain any
            // metaspace settings or you will get an error.
            java.util.Properties info = new java.util.Properties();
            info.put(Metaspace.class.getName(), metaspace);
            connection = DriverManager.getConnection(jdbcURL, info);
            statement = connection.createStatement();
        }
        catch (SQLException ex)
        {
            throw new ASException(ASStatus.SYS_ERROR, ex);
        }

    }

    public void run () throws Exception
    {
        // This method is the same as SimpleJDBC.run() except that we do not create the
        // space using the JDBC driver since the space already exists.
        // The SQL commands to update the data in the space just reference the space by
        // name as usual.

        // put some data into the space which already exists
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
        UtilsJDBC.displayResultSet(rs);
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
        UtilsJDBC.displayResultSet(rs);
        rs.close();

        // delete data from the space
        // WARNING!!! without a where clause all data is deleted from the space
        sql = "DELETE FROM " + spaceName + " WHERE value = 'Halloween'";
        statement.executeUpdate(sql);

        // retrieve the data and display it
        sql = "SELECT * FROM " + spaceName;
        rs = statement.executeQuery(sql);
        UtilsJDBC.displayResultSet(rs);
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
     * This method uses the basic metaspace settings defined above and creates a connection to a
     * metaspace.
     *
     * @return An Activespaces Metaspace object.
     * @throws ASException
     */
    public Metaspace connectMetaspace () throws ASException
    {
        // create and populate a MemberDef object
        MemberDef memberDef = MemberDef.create();
        memberDef.setDiscovery(discoveryURL);
        memberDef.setListen(listenURL);
        memberDef.setMemberName(memberName);

        // create and populate a context tuple
        Tuple context = Tuple.create();
        context.put("platform", "java");
        context.put("jointime", DateTime.create());
        memberDef.setContext(context);

        // connect to the metaspace
        return Metaspace.connect(metaspaceName, memberDef);
    }

    /**
     * This methods uses the basic space settings defined above, defines and then joins a space as a
     * SEEDER.
     *
     * @return An ActiveSpaces space object.
     * @throws ASException
     */
    public Space connectSpace () throws ASException
    {
        // Create the space definition
        SpaceDef spaceDef = SpaceDef.create().setName(spaceName);
        // Put the field definitions into the space definition
        spaceDef.putFieldDef(FieldDef.create("key", FieldType.INTEGER));
        spaceDef.putFieldDef(FieldDef.create("value", FieldType.STRING).setNullable(true));
        spaceDef.putFieldDef(FieldDef.create("time", FieldType.DATETIME).setNullable(true));
        // define the key fields, by default the key index is of type HASH
        spaceDef.setKeyDef(KeyDef.create().setFieldNames("key").setIndexType(IndexType.HASH));

        if (replicationCount >= -1)
        {
            spaceDef.setReplicationCount(replicationCount);
        }
        if (minSeeders > 0)
        {
            spaceDef.setMinSeederCount(minSeeders);
        }
        if (capacity != -1)
        {
            // capacity has been set to other than the default
            // set the capacity to the new setting
            spaceDef.setCapacity(capacity);
        }
        metaspace.defineSpace(spaceDef);
        Space space = metaspace.getSpace(spaceName, DistributionRole.SEEDER);
        // wait until space is ready
        space.waitForReady();
        return space;
    }
}
