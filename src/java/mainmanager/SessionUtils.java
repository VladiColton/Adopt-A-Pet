package MainManager;

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
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                            .getExternalContext().getSession(false);
            return session.getAttribute("username").toString();
    }

    public static String getUserId() 
    {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }
}
