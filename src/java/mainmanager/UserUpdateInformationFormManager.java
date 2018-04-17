package mainmanager;

import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;


/**
 * Manages update user information form
 * @author Vladi Colton
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "userUpdateInformationFormManager", eager = true)
public class UserUpdateInformationFormManager{
    private long _phoneNumber;
    private String _streetAddress;
    private String _city;
    private String _email;
    private String _password;
    
    public UserUpdateInformationFormManager()
    {
        //Get all user information from the session
        this._email = SessionUtils.getUserEmail();
        this._streetAddress = "";
        this._city = "";
        this._password = "";
        this._phoneNumber = SessionUtils.getUserPhone();
    }
    
    public long getPhoneNumber() 
    {
        return _phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) 
    {
        if(phoneNumber != 0)
            this._phoneNumber = phoneNumber;
    }

    public String getStreetAddress() 
    {
        return _streetAddress;
    }
    public void setStreetAddress(String streetAddress) 
    {
        this._streetAddress = streetAddress;
    }

    public String getCity() 
    {
        return _city;
    }
    public void setCity(String city) 
    {
        this._city = city;
    }

    public String getEmail() 
    {
        return _email;
    }
    public void setEmail(String email) 
    {
        if(email.contains("@") && email.contains("."))
            this._email = email;
    }

    public String getPassword() 
    {
        return _password;
    }
    public void setPassword(String password) 
    {
        this._password = password;
    }
    
    public String getUserName()
    {
        return SessionUtils.getUserName();
    }
    
    public boolean allPropertiesFilled(AjaxBehaviorEvent event)
    {
        return (_email.contains("@") && _email.contains(".") && _phoneNumber != 0);
    }
    
    public void updateRregistrationInfo(ActionEvent event)
    {
        //Verify that user connected and only then update the information
        if(!SessionUtils.isUserConnected())
            return;
        
        //Update session details to reflect imideatly all changes in the current session
        SessionUtils.setUserPhone(_phoneNumber);
        SessionUtils.setUserLocation(_streetAddress + ", " + _city);
        
        //DO NOT REMOVE THIS COMMENTS
        
        //Here need to send new data recieved to the DB for saving
        //Password
        //Location
        //Phone
        //email address (By userID Saved in SessionUtils) 
    }
}
