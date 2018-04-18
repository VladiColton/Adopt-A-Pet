package mainmanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

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
        //make sure the user is logged in, if not return
        if(!isForProfile && !SessionUtils.isUserConnected())
            return;
        
        try
        {
            //Check if user directory already exists - if no create
            File f = new File(UploadController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String str = f.getPath(); //Set relative path to the DB
            str = str.substring(0, str.indexOf("Adopt-A-Pet")+12) + "\\imagesUploads";
            File path = new File(str+"\\"+SessionUtils.getUserEmail());
            
            if (!path.exists() || !path.isDirectory())
            {
                boolean success = path.mkdir();
                if (!success)
                    return;
            }
            
            InputStream in = image.getInputStream();
            String midString = "\\";
            if (isForProfile)
            {
                midString = midString+"profile.";
                isForProfile = false;
            }
            File pic = new File(str+"\\"+SessionUtils.getUserEmail() + midString + image.getSubmittedFileName());
            pic.createNewFile();
            FileOutputStream out = new FileOutputStream(pic);
            
            byte[] buffer = new byte[1024];
            int length;
            
            while((length = in.read(buffer))>0)
            {
                out.write(buffer, 0, length);
            }
            
            out.close();
            in.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }
    
    public void doUploadForProfile()
    {
        isForProfile = true;
        doUpload();
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
