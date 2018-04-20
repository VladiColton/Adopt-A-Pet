package mainmanager;

import entities.Owner;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import repository.OwnerRepository;


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
    private String _password;
    
    public UserUpdateInformationFormManager()
    {
        //Get all user information from the session
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
        return (_phoneNumber != 0);
    }
    
    public void updateRregistrationInfo(ActionEvent event)
    {
        //Verify that user connected and only then update the information
        if(!SessionUtils.isUserConnected())
            return;
        
        //Update session details to reflect imideatly all changes in the current session
        SessionUtils.setUserPhone(_phoneNumber);
        SessionUtils.setUserLocation(_streetAddress + ", " + _city);
        
        OwnerRepository rep = new OwnerRepository();
        Owner user = rep.getOwner(SessionUtils.getUserEmail());
        
        boolean needDBUpdate = false;
        if(!this._password.equalsIgnoreCase("") && !user.getPassword().equals(this._password))
        {
            //Replase existing password
            user.setPassword(_password);
            needDBUpdate = true;
        }
        if(!this._streetAddress.equalsIgnoreCase("") || !this._city.equalsIgnoreCase(""))
        {
            //Replase existing location
            user.setLocation(_streetAddress + ", " + _city);
            needDBUpdate = true;
        }
        if(this._phoneNumber != user.getPhoneNumber())
        {
            //Replase existing PhoneNumber
            user.setPhoneNumber(_phoneNumber);
            needDBUpdate = true;
        }
        
        //Update DB with new details if needed
        if(needDBUpdate)
        {
            rep.update(user);
        }
        
    }
}
