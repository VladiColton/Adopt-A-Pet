package mainmanager;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 * Update Animals information Form manager bean
 */
@SessionScoped
@ManagedBean(name = "animalUpdateFormManager", eager = true)
public class AnimalUpdateFormManager extends NewAnimalRegirtrationFormManager implements Serializable {
    private Long id;
    
    public AnimalUpdateFormManager()
    {
        this.id = new Long(0);
    }
    
    public String getId()
    {
        return this.id.toString();
    }
    
    public void setId(String newId)
    {
        this.id = Long.valueOf(newId);
    }
    
    public void deleteAnimal()
    {
        //Add here delete process of animal from the data base
        int test = 0 ;
    }
    
    public void updateAnimalDetails()
    {
        //Save animal details to the DB
        int test = 0 ;
    }
}
