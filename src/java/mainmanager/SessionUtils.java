package mainmanager;

/**
 * Here we obtain a session for each user logged through the getUserId method thereby
 * associating a session id to a particular user id.
 * @author Vladi Colton
 */
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public static void setUserName(String userName, String email)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        //session.setAttribute("username", Login.getUserName(email)); //Get users phone number
        session.setAttribute("username", "ReadFromDB");
    }
    
    public static String getUserEmail() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("useremail").toString();
    }
    public static void setUserEmail(String mail)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("useremail", mail);
    }
    
    public static String getUserLocation() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("userlocation").toString();
    }
    public static void setUserLocation(String userLocation)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        //session.setAttribute("userlocation", Login.getUserLocation(_mailAddress)); //Get User Location
        session.setAttribute("userlocation", "Read FromDB"); //Temp till db read User Location
    }
    
    public static long getUserPhone() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (long)session.getAttribute("userphone");
    }
    public static void setUserPhone(long phone)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        //session.setAttribute("userphone", Login.getUserPhoneNum(_mailAddress)); //Get users phone number
        session.setAttribute("userphone", (long)123456);
    }
    
    public static boolean isUserConnected()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("isUserConnected").toString().equalsIgnoreCase("true");
    }
    public static void setIsUserConnected(boolean connectionState)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("isUserConnected", connectionState); //Set if user connected
    }
    
    public static Integer getUserId() 
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null)
            return (Integer) session.getAttribute("userid");
        else
            return null;
    }
}
