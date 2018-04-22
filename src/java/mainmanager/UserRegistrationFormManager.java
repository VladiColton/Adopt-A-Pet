package mainmanager;

import entities.Owner;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.faces.bean.*;
import javax.faces.event.AjaxBehaviorEvent;
import repository.OwnerRepository;

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

    public UserRegistrationFormManager() 
    {
        _lastName = "";
        this._firstName = "";
        this._phoneNumber = 0;
        this._streetAddress = "";
        this._city = "";
        this._email = "";
        this._password = "";
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
        OwnerRepository rep = new OwnerRepository();
        //Verify email address
        if(!email.contains("@") && !email.contains(".") && !rep.isEmailAvailable(email))
            this._email = "";
        else
            this._email = email;
    }

    public String getEmail() 
    {
        return this._email;
    }

    public String getLastName() 
    {
        return this._lastName;
    }

    public void setLastName(String newLastName) 
    {
        this._lastName = newLastName;
    }

    public String getFirstName() 
    {
        return this._firstName;
    }

    public void setFirstName(String newFirstName) 
    {
        this._firstName = newFirstName;
    }

    public long getPhoneNumber() 
    {
        return _phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) 
    {
        this._phoneNumber = phoneNumber;
    }

    public String getStreetAddress() 
    {
        return this._streetAddress;
    }

    public void setStreetAddress(String streetAddress) 
    {
        this._streetAddress = streetAddress;
    }

    public String getCity() 
    {
        return this._city;
    }

    public void setCity(String city) 
    {
        this._city = city;
    }
    
    public boolean allPropertiesFilled(AjaxBehaviorEvent event)
    {
        OwnerRepository rep = new OwnerRepository();
        return (_phoneNumber != 0 && !_email.equalsIgnoreCase("") && _email.contains("@") && _email.contains(".") && !_password.equalsIgnoreCase("") && (!_firstName.equalsIgnoreCase("") || !_lastName.equalsIgnoreCase("")) && !_streetAddress.equalsIgnoreCase("") && !_city.equalsIgnoreCase("") && rep.isEmailAvailable(_email));
    }

    public void updateUserDetails(ActionEvent event) 
    {
        OwnerRepository rep = new OwnerRepository();
        byte[] defaultProfPic = null;
        
        try 
        {
            //Get directory with default image
            File f = new File(UploadController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String imageLocation = f.getPath(); //Set relative path to the DB
            imageLocation = Paths.get(imageLocation.substring(0, imageLocation.indexOf("Adopt-A-Pet")+12), "web", "images").toString();
            //Get default image
            File usrImageFile = new File(Paths.get(imageLocation, "defaultUserImage.png").toString());
            //Create byte array for saving in the DB
            defaultProfPic = Files.readAllBytes(usrImageFile.toPath());
        }
        catch (IOException ex) 
        {
            ex.printStackTrace(System.out);
        }
        
        _newOwner = Owner.builder()
                .name(_firstName + " " + _lastName)
                .location(_streetAddress + ", " + _city)
                .phoneNumber(_phoneNumber)
                .email(_email)
                .password(_password)
                .profilePic(defaultProfPic)
                .build();
        
        rep.create(_newOwner);
        
    }
}
