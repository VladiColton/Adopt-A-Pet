package mainmanager;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 * User authentication class responsible to authenticate users vs DB
 * @author Vladi Colton
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "userAuthentication")
public class UserAuthentication implements Serializable{
    private String _mailAddress;
    String _password;
    
    public UserAuthentication()
    {
        
    }
    
    
    
    
    
    
}
