package mainmanager;

import entities.Owner;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import repository.OwnerRepository;

/**
 * Receive images from the DB
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "imageReciverController", eager = true)
public class ImageReciverController implements Serializable{
    private byte[] defaultAnimalProfileImage;
    
    
    public ImageReciverController() 
    {
        defaultAnimalProfileImage = null;
        try 
        {
            //Get directory with default image
            File f = new File(UploadController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String imageLocation = f.getPath(); //Set relative path to the DB
            imageLocation = imageLocation.substring(0, imageLocation.indexOf("Adopt-A-Pet")+12) + "\\web\\images";
            //Get default image
            File usrImageFile = new File(imageLocation + "\\DefaultAnimalProfileImage.png");
            //Create byte array for saving in the DB
            defaultAnimalProfileImage = Files.readAllBytes(usrImageFile.toPath());
        }
        catch (IOException ex) 
        {
            ex.printStackTrace(System.out);
        }
    }
    
    public StreamedContent getProfileImageFromDB() throws IOException 
    {
        OwnerRepository rep = new OwnerRepository();
        Owner user = rep.getOwner(SessionUtils.getUserEmail());

        byte[] image = user.getProfilePic();

        //Secure code if some user appears somehow without image from the DB
        if(image == null)
        {
            image = UploadController.setDefaultProfileImage();
        }
        
        return new DefaultStreamedContent(new ByteArrayInputStream(image));     
    }

    public StreamedContent getAnimalImageFromDB()
    {
        return new DefaultStreamedContent(new ByteArrayInputStream(defaultAnimalProfileImage));
    }
    
}
