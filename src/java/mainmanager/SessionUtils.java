package mainmanager;

/**
 * Here we obtain a session for each user logged through the getUserId method thereby
 * associating a session id to a particular user id.
 * Available Session attributes for use:
 * (long)"userphone", (String)"useremail", (String)"userlocation", (String)"username", (boolean)"isUserConnected"
 * @author Vladi Colton
 */
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import repository.OwnerRepository;
import entities.Owner;

public class SessionUtils {

    public static HttpSession getSession() 
    {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() 
    {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getUserName() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
    protected static void setUserName(String userName)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("username", userName);
    }
    
    public static String getUserEmail() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("useremail").toString();
    }
    protected static void setUserEmail(String email)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("useremail", email);
    }
    
    public static String getUserLocation() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("userlocation").toString();
    }
    protected static void setUserLocation(String userLocation)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("userlocation", userLocation);
    }
    
    public static long getUserPhone() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (long)session.getAttribute("userphone");
    }
    protected static void setUserPhone(long phone)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("userphone", phone);
    }
    
    public static boolean isUserConnected()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("isUserConnected").toString().equalsIgnoreCase("true");
    }
    protected static void setIsUserConnected(boolean connectionState)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("isUserConnected", connectionState); //Set if user connected
    }
    protected static void setUserDetailsToSession(String mailAddress)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        //Get user by mail from DB
        OwnerRepository rep = new OwnerRepository();
        Owner usr = rep.getOwner(mailAddress);
        
        if (usr != null)
        {
            //Set sessions details for the user
            session.setAttribute("userphone", usr.getPhoneNumber());
            session.setAttribute("useremail", mailAddress);
            session.setAttribute("userlocation", usr.getLocation());
            session.setAttribute("username", usr.getName());
        }
    }
}
