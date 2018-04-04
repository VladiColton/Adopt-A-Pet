/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlmanager;

import java.sql.*;

/**
 * Login Class used to support user login details verification.
 * @author Vladi Colton
 */
public class Login {
    private Connection _conn = null;
    private ResultSet _rs = null;
    private PreparedStatement _pst = null;
    
    public Login()
    {
        this._conn = SQLiteConnection.connectDB();
    }
    
    public boolean isDBConnected()
    {
        return this._conn != null ? true : false;
        
    }
}
