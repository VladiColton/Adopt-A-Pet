package sqlmanager;

import java.io.File;
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
            File f = new File(SQLiteConnection.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String str = f.getPath(); //Set relative path to the DB
            str = str.substring(0, str.indexOf("Adopt-A-Pet")+12) + "\\resources";
            
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + str + "\\database.sqlite");
            return conn;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.out);
            return null;
        }   
    }
    
    public static void closeConnection(Connection conn)
    {
        try
        {
            conn.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }
    
}
