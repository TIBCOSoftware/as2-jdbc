// -------------------------------------------------------------------
//  Copyright (c) 2012-2014 TIBCO Software, Inc.
//  All rights reserved.
//  For more information, please contact:
//  TIBCO Software Inc., Palo Alto, California, USA
// -------------------------------------------------------------------

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;
import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.as.space.browser.BrowserDef.DistributionScope;
import com.tibco.as.space.browser.BrowserDef.TimeScope;


/**
 * This class contains methods which are common to the ActiveSpaces JDBC Driver Examples.
 *
 */
public class UtilsJDBC
{
    /**
     * Displays the data in the given ResultSet.
     *
     * @param rs
     */
    public static void displayResultSet (ResultSet rs)
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

    /**
     * This method defines a space using the ActiveSpaces JDBC Driver which is the same space that
     * is used by most of the normal ActiveSpaces examples such as ASOperations. So this example can
     * be used with the other ActiveSpaces examples which use the default example space as long as
     * the metaspace used by this example is the same as is used to run the other ActiveSpaces
     * examples.
     *
     * In ActiveSpaces, a space is the equivalent of a database table. The table columns are called
     * the fields of the space. The types of the columns are mapped to their ActiveSpaces equivalent
     * type by the ActiveSpaces JDBC Driver.
     *
     * Create the default table/space for the normal ActiveSpaces examples, but do not join it. In
     * ActiveSpaces terms, this method defines the space.
     */
    public static void createTable (Statement statement, String spaceName, String minSeeders, String capacity,
            String replicationCount) throws Exception
    {
        // Format our SQL string to create a table.
        // Notice the use of double-quotes around field names which conflict with SQL keywords.
        // Also notice the comma separated list of ActiveSpaces SpaceDef settings which are appended
        // to the end of the string after the closing parenthesis. The names of the SpaceDef settings
        // are the same as what is specified on the command line for the normal ActiveSpaces examples:
        //   distribution_policy
        //   persistence || persistence_type    <= can use either, examples use persistence
        //   persistence_policy
        //   replication_policy
        //   replication_count
        //   min_seeders
        //   capacity
        //   eviction_policy
        //
        String sql = "CREATE TABLE " + spaceName
                + " (\"key\" INTEGER NOT NULL, value VARCHAR, \"time\" DATETIME, PRIMARY KEY (\"key\"))"
                + " MIN_SEEDERS " + minSeeders + ", CAPACITY " + capacity + ", REPLICATION_COUNT " + replicationCount;

        try
        {
            int result = statement.executeUpdate(sql);
            if (result == 1)
                System.out.println("Space " + spaceName + " created successfully!");
            else if (result == 0)
                System.out.println("A space with the name " + spaceName + " already exists.");
        }
        catch (SQLException ex)
        {
            throw new Exception("Error creating space: " + spaceName + "  Error info: " + ex.getMessage());
        }
    }

    public static void browseSpace(Space space, String filter) throws ASException
    {
        BrowserDef browserDef = BrowserDef.create();
        browserDef.setTimeScope(BrowserDef.TimeScope.SNAPSHOT);
        browserDef.setDistributionScope(DistributionScope.ALL);
        browserDef.setTimeout(BrowserDef.NO_WAIT);
        Browser browser = space.browse(BrowserType.GET, browserDef, filter);

        System.out.println();
        try
        {
            Tuple tuple = null;
            while ((tuple = browser.next()) != null)
            {
                System.out.println(tuple);
            }
        }
        catch (ASException ex)
        {
        }
        System.out.println();
        browser.stop();
    }

    public static void takeFromSpace(Space space, String filter) throws ASException
    {
        Browser browser = space.browse(BrowserType.TAKE, BrowserDef.create(0, TimeScope.SNAPSHOT), filter.toString());
        System.out.println();
        try
        {
            Tuple tuple = null;
            while ((tuple = browser.next()) != null)
            {
                System.out.println("Deleted tuple: ");
                System.out.println("\t" + tuple);
            }
        }
        catch (ASException ex)
        {
        }
        System.out.println();
        browser.stop();
    }

}
