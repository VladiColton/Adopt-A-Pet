package mainmanager;

import entities.Animal;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Update Animals information Form manager bean
 */
@SessionScoped
@ManagedBean(name = "animalUpdateFormManager", eager = true)
public class AnimalUpdateFormManager extends NewAnimalRegirtrationFormManager implements Serializable {

    public AnimalUpdateFormManager()
    {
        
    }
    
    
    
    public void animalSelectedListener(Animal selectedAnimal)
    {
        this.setName(selectedAnimal.getName());
        this.setAge(selectedAnimal.getAge());
        this.setType(selectedAnimal.getType());
        
        if (selectedAnimal.getSubType() != null)
            this.setSubType(selectedAnimal.getSubType());
        if(selectedAnimal.getDescription() != null)
            this.setDescription(selectedAnimal.getDescription());
    }
}
