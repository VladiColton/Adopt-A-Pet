package mainmanager;

import entities.Owner;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.event.AjaxBehaviorEvent;

/**
 * User Registration Form manager bean (Connects UI to User class)
 */
@SessionScoped
/*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean(name = "userRegistrationFormManager", eager = true)
public class UserRegistrationFormManager implements Serializable {

    private String _lastName;
    private String _firstName;
    private long _phoneNumber;
    private String _streetAddress;
    private String _city;
    private String _email;
    private String _password;
    private Owner _newOwner;

    public UserRegistrationFormManager() {
        _lastName = "";
        this._firstName = "";
        this._phoneNumber = 0;
        this._streetAddress = "";
        this._city = "";
        this._email = "";
        this._password = "";
    }

    public void setPassword(String pass) {
        this._password = pass;
    }

    public String getPassword() {
        return this._password;
    }

    public void setEmail(String email) {
        //Verify email address
        if(!email.contains("@") && !email.contains("."))
            this._email = "";
        else
            this._email = email;
    }

    public String getEmail() {
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

    public long getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
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
    public boolean allPropertiesFilled(AjaxBehaviorEvent event)
    {
        boolean res;
        res= (_phoneNumber != 0 && !_email.equalsIgnoreCase("") && !_password.equalsIgnoreCase("") && (!_firstName.equalsIgnoreCase("") || !_lastName.equalsIgnoreCase("")) && !_streetAddress.equalsIgnoreCase("") && !_city.equalsIgnoreCase(""));
        return res;
    }

    public void updateUserDetails(ActionEvent event) {
        _newOwner = Owner.builder()
                .name(_firstName + " " + _lastName)
                .location(_streetAddress + ", " + _city)
                .phoneNumber(_phoneNumber)
                .email(_email)
                .build();

        //Here to check the details and validation for the user next to the DB for example if already exists
        //If not exists add new user/owner to the DB according to the details recieved.
        //SQL Stuff
    }
}
