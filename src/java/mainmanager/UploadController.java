package mainmanager;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import java.nio.file.Files;
/**
 * Upload Controller used to manage incoming image streams, save them under
 * performing user and apply the images to the Animals/user profile.
 * @author Vladi Colton
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "UploadController", eager = true)
public class UploadController implements Serializable{
    private Part image;
    
    public UploadController()
    {
        
    }
    
    public void doUpload()
    {
        //make sure the user is logged in, if not return
        if(!SessionUtils.isUserConnected())
            return;
        
        try
        {
            //Check if user directory already exists - if no create
            File f = new File(UploadController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String str = f.getPath(); //Set relative path to the DB
            str = str.substring(0, str.indexOf("Adopt-A-Pet")+12) + "\\imagesUploads";
            File path = new File(str+SessionUtils.getUserId().toString());
            
            if (!path.exists() || !path.isDirectory())
            {
                boolean success = path.mkdir();
                if (!success)
                    return;
            }
            
            InputStream in = image.getInputStream();
            //File f = new File();
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }
}
