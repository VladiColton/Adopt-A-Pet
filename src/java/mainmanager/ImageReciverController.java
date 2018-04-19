package mainmanager;

import entities.Owner;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
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

    public ImageReciverController() 
    {
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
        
        return new DefaultStreamedContent(new ByteArrayInputStream(image), "image2/png");     
    }
    
}
