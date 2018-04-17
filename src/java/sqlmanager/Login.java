package sqlmanager;

import java.sql.*;

/**
 * Login Class used to support user login details verification.
 * @author Vladi Colton
 */
public class Login {
    public static boolean validateUser(String email, String password)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        try
        {
            conn = SQLiteConnection.connectDB();
            if (conn == null)
                return false;
            ps = conn.prepareStatement("SELECT email, password FROM users WHERE email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                //result found, means valid inputs
                return true;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace(System.out);
        }
        finally
        {
            SQLiteConnection.closeConnection(conn);
        }
        return false;
    }
    
    public static Integer getUserDbID(String email)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        try
        {
            conn = SQLiteConnection.connectDB();
            if (conn == null)
                return null;
            ps = conn.prepareStatement("SELECT email, userID FROM users WHERE email=?");
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                //result found, means valid inputs
                Integer userID = rs.getInt("userID");
                return userID;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace(System.out);
        }
        finally
        {
            SQLiteConnection.closeConnection(conn);
        }
        return null;
    }
}
