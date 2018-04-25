package mainmanager;

import entities.Owner;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import repository.OwnerRepository;

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
        SessionUtils.isUserConnected();
        return SessionUtils.isUserConnected();
    }
    
    public void validateEmailPassword(ActionEvent event)
    {
        boolean valid = false;
        OwnerRepository rep = new OwnerRepository();
        
        if(_mailAddress != null && _password != null)
        {
            valid = rep.isLoginValid(_mailAddress, _password); //Validate user name and password with DB
        }
        
        if(valid)
        {
            //Add here to create Owner object from
            //the information in the DB and set in the session
            this.setAutoErrorMSG("");
            this._isUserConnected = true;
            SessionUtils.setUserDetailsToSession(_mailAddress);
            SessionUtils.setIsUserConnected(_isUserConnected);
        }
        else
        {
            this._isUserConnected = false;
            this.setAutoErrorMSG("Incorrect Email or Password!!");
        }
    }
    
    public StreamedContent getProfileImageFromDB()
    {
        OwnerRepository rep = new OwnerRepository();
        Owner user = rep.getOwner(SessionUtils.getUserEmail());

        byte[] image = user.getProfilePic();

        //Secure code if some user appears somehow without image from the DB
        if(image == null)
        {
            image = getDefaultProfileImage();
        }
        
        return new DefaultStreamedContent(new ByteArrayInputStream(image));     
    }
    
    private byte[] getDefaultProfileImage()
    {
        byte[] defaultProfPic = null;
        
        try 
        {
            //Get directory with default image
            File f = new File(UserAuthentication.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String imageLocation = f.getPath(); //Set relative path to the DB
            imageLocation = Paths.get(imageLocation.substring(0, imageLocation.indexOf("Adopt-A-Pet")+12), "web", "images").toString();
            //Get default image
            File usrImageFile = new File(Paths.get(imageLocation, "defaultUserImage.png").toString());
            //Create byte array for saving in the DB
            defaultProfPic = Files.readAllBytes(usrImageFile.toPath());
        }
        catch(IOException ex)
        {
            ex.printStackTrace(System.out);
        }
        
        return defaultProfPic;
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