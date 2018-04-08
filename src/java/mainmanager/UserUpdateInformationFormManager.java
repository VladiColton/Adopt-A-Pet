package mainmanager;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Manages update user information form
 * @author Vladi Colton
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "userUpdateInformationFormManager", eager = true)
public class UserUpdateInformationFormManager implements Serializable {
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
    }
    
    public long getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this._phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return _streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this._streetAddress = streetAddress;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(String city) {
        this._city = city;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }
    
    public void updateRregistrationInfo(ActionEvent event)
    {
        //Verify that user connected and only then update the information
        if(!SessionUtils.isUserConnected())
            return;
        
        //DO NOT REMOVE THIS COMMENTS
        
        //Here need to send new data recieved to the DB for saving
        //
        //
        //
        
        //Here need to update session details to work with updated one live
        //
        //
        //
    }
}
