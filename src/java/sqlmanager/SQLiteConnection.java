package sqlmanager;

import java.sql.*;
/**
 * SQLite connection establishing
 * @author Vladi Colton
 */
public class SQLiteConnection {
    private Connection _dbConnection;
    
    public static Connection connectDB()
    {
        try
        {
            Class.forName("org.sqlite.JDBC"); //Causes to the class "org.sqlite.JDBC" to be initialized
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            return conn;
        }
        catch(Exception e)
        {
            return null;
        }   
    }
    
}
