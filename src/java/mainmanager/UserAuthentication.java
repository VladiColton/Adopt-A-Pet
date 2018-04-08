package mainmanager;

import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import sqlmanager.*;

/**
 * User authentication class responsible to authenticate users vs DB
 * @author Vladi Colton
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "userAuthentication", eager = true)
public class UserAuthentication implements Serializable{
    private String _mailAddress;
    private String _password;
    private String _autoErrMsg;
    private boolean _isUserConnected;
    
    public UserAuthentication()
    {
        _mailAddress = "";
        _password = "";
        _autoErrMsg = "";
        _isUserConnected = false;
        
        //Set user session Attributes to stay connected during session
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("isUserConnected", this._isUserConnected);
    }
    
    public void setEmail(String email)
    {
        this._mailAddress = email;
    }
    public String getEmail()
    {
        return this._mailAddress;
    }
    
    public void setPassword(String password)
    {
        this._password = password;
    }
    public String getPassword()
    {
        return this._password;
    }
    
    public String getAutoErrorMSG() 
    {
	return _autoErrMsg;
    }
    public void setAutoErrorMSG(String msg) 
    {
        this._autoErrMsg = msg;
    }
    
    public boolean getIsUserConnected()
    {
        boolean test = SessionUtils.isUserConnected();
        return SessionUtils.isUserConnected();
    }
    
    public void validateEmailPassword(ActionEvent event)
    {
        boolean valid = false;
        
        if(_mailAddress != null && _password != null)
        {
            valid = Login.validateUser(_mailAddress, _password); //validate user name and password with DB
        }
        
        if(valid)
        {
            //Add here to create Owner object from
            //the information in the DB and set in the session
            this.setAutoErrorMSG("");
            this._isUserConnected = true;
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("useremail", this._mailAddress);
            session.setAttribute("isUserConnected", this._isUserConnected);
        }
        else
        {
            this._isUserConnected = false;
            this.setAutoErrorMSG("Incorrect Email or Password!!");
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd", "Please enter correct username and Password"));
        }
    }
    
    //Logout event, invalidate session
    public String logout() 
    {
	HttpSession session = SessionUtils.getSession();
        this._mailAddress = "";
        this._password = "";
        this.setAutoErrorMSG("");
        this._isUserConnected = false;
	session.invalidate();
        return "index.xhtml?faces-redirect=true";
    }
}