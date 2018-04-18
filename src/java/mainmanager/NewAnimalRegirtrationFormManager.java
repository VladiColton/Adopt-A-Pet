package mainmanager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

/**
 * New Animal Registration form controller
 * @author Vladi Colton
 */
@SessionScoped /*Each user gets new instance of the been during the session (as defined in "web.xml" 60 min)*/
@ManagedBean (name = "newAnimalRegirtrationFormManager", eager = true)
public class NewAnimalRegirtrationFormManager {
    private double age;
    private String description;
    private String type;
    private String subType;
    private String name;
    
    public NewAnimalRegirtrationFormManager()
    {
        age = 0;
        name = "";
        type = "";
        description = "";
        subType = "";
    }
    
    public double getAge() 
    {
        return age;
    }

    public void setAge(double age) 
    {
        this.age = age;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getType() 
    {
        return type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getSubType() 
    {
        return subType;
    }

    public void setSubType(String subType) 
    {
        this.subType = subType;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }
    
    public String getOwnersLocation()
    {
        return SessionUtils.getUserLocation();
    }
    
    public String getOwnersPhone()
    {
        return Long.toString(SessionUtils.getUserPhone());
    }
    
    public boolean allPropertiesFilled(AjaxBehaviorEvent event)
    {
        return (!name.equalsIgnoreCase("") && age != 0 && !type.equalsIgnoreCase(""));
    }
    
    public void addNewAnimal(ActionEvent event)
    {
        //Verify that user connected and only then update the information
        if(!SessionUtils.isUserConnected())
            return;
    }
    
}
