package mainmanager;

import entities.Owner;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 * User Registration Form manager bean (Connects UI to User class)
 * @author Vladi Colton
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "userRegistrationFormManager")
public class UserRegistrationFormManager implements Serializable{
    private String _lastName;
    private String _firstName;
    private Long _phoneNumber;
    private String _streetAddress;
    private String _city;
    private String _email;
    private String _password;
    private Owner _newOwner;
    
    
    public UserRegistrationFormManager()
    {
        this._newOwner = new Owner();
    }
    
    public void setPassword(String pass)
    {
        this._password = pass;
    }
    
    public String getPassword()
    {
        return this._password;
    }
    
    public void setEmail(String email)
    {
        this._email = email;
    }
    
    public String getEmail()
    {
        return this._email;
    }
    
    public String getLastName() {
        return this._lastName;
    }

    public void setLastName(String newLastName) {
        this._lastName = newLastName;
    }
    
    public String getFirstName() {
        return this._firstName;
    }

    public void setFirstName(String newFirstName) {
        this._firstName = newFirstName;
    }
    
    public Long getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this._phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return this._streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this._streetAddress = streetAddress;
    }
    
    public String getCity() {
        return this._city;
    }

    public void setCity(String city) {
        this._city = city;
    }
    
    public String upgateUserDetails()
    {
        _newOwner.setName(this._firstName + this._lastName); //Update Name
        _newOwner.setLocation(this._streetAddress + this._city); //Update Location
        _newOwner.setPhoneNumber(this._phoneNumber); //Update PhoneNumber
        _newOwner.setEmail(this._email); //Update EMail address
        
        //Here to check the details and validation for the user next to the DB for example if already exists
        //If not exists add new user/owner to the DB according to the details recieved.
        //SQL Stuff
        
        
        return "index";
    }
}
