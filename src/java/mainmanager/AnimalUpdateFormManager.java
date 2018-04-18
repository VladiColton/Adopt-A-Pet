package mainmanager;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Update Animals information Form manager bean
 */
@SessionScoped
@ManagedBean(name = "animalUpdateFormManager", eager = true)
public class AnimalUpdateFormManager implements Serializable {
    
    public AnimalUpdateFormManager()
    {
        
    }
}
