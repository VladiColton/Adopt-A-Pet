package mainmanager;

import entities.Owner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import repository.OwnerRepository;
import sun.misc.IOUtils;


/**
 * Upload Controller used to manage incoming image streams, save them under
 * performing user and apply the images to the Animals/user profile.
 * @author Vladi Colton
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "uploadController", eager = true)
public class UploadController implements Serializable{
    private Part image;
    private boolean isForProfile;
    
    public UploadController()
    {
        this.isForProfile = false;
    }
    
    public void doUpload()
    {
        //make sure image selected by user and need to be uploaded and user logged in, if not return
        if(!SessionUtils.isUserConnected() || image == null)
            return;
        
        try
        {
            InputStream in = image.getInputStream();
            //Create byte array to save the image in the DB
            byte[] fileAsByteArray = IOUtils.readFully(in, Integer.MAX_VALUE, true/*ignored since Integer.MAX_VALUE set*/);
            
            if (isForProfile)// If for user profile picture
            {
                //Save the image in the DB for the connected user
                OwnerRepository rep = new OwnerRepository();
                Owner usr  = rep.getOwner(SessionUtils.getUserEmail());
                usr.setProfilePic(fileAsByteArray);
                rep.update(usr);
                isForProfile = false;
            }
            else // Animal image
            {
                //Add here animal image uploading code
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace(System.out);
        }
    }
    
    public void doUploadForProfile()
    {
        isForProfile = true;
        doUpload();
    }
    
    protected static byte[] setDefaultProfileImage()
    {
        //Check that the user connected
        if(!SessionUtils.isUserConnected())
            return null;
        
        OwnerRepository rep = new OwnerRepository();
        Owner user = rep.getOwner(SessionUtils.getUserEmail());
        byte[] defaultProfPic = null;
        
        try 
        {
            //Get directory with default image
            File f = new File(UploadController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String imageLocation = f.getPath(); //Set relative path to the DB
            imageLocation = imageLocation.substring(0, imageLocation.indexOf("Adopt-A-Pet")+12) + "\\web\\images";
            //Get default image
            File usrImageFile = new File(imageLocation + "\\defaultUserImage.png");
            //Create byte array for saving in the DB
            defaultProfPic = Files.readAllBytes(usrImageFile.toPath());
        }
        catch (IOException ex) 
        {
            ex.printStackTrace(System.out);
        }
        
        //Update user profile image
        user.setProfilePic(defaultProfPic);
        rep.update(user);
        return defaultProfPic;
        
    }
    
    public Part getImage()
    {
        return image;
    }

    public void setImage(Part image)
    {
        this.image = image;
    }
}
