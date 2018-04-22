package mainmanager;

import entities.Animal;
import entities.Owner;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import repository.AnimalRepository;
import repository.OwnerRepository;

/**
 * Update Animals information Form manager bean
 */
@SessionScoped
@ManagedBean(name = "animalUpdateFormManager", eager = true)
public class AnimalUpdateFormManager extends NewAnimalRegirtrationFormManager implements Serializable {
    private Long id;
    private List<Animal> listOfAnimalregisteredByUser;
    private boolean needsUpdate;
    
    public AnimalUpdateFormManager()
    {
        needsUpdate = true;
        this.id = new Long(0);
    }
    
    public List<Animal> getListOfAnimalregisteredByUser() 
    {
        if(needsUpdate)
        {
            //Get the list of the Animals registered by the connected user
            OwnerRepository ownerRep = new OwnerRepository();
            Owner connectedUser = ownerRep.getOwner(SessionUtils.getUserEmail());
            listOfAnimalregisteredByUser = connectedUser.getAnimals();
            needsUpdate = false;
        }
        
        if(listOfAnimalregisteredByUser == null)
        {
            listOfAnimalregisteredByUser.add(Animal.builder().name("No Registered animals Yet").build());
        }
            
        return listOfAnimalregisteredByUser;
    }
    public void setListOfAnimalregisteredByUser(List<Animal> listOfAnimalregisteredByUser) {
        this.listOfAnimalregisteredByUser = listOfAnimalregisteredByUser;
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
        AnimalRepository animalsRep = new AnimalRepository();
        
        //Remove selected animal from the DB by ID
        animalsRep.remove(id);
        
        //Set to update animals list
        needsUpdate = true;
    }
    
    public void updateAnimalDetails()
    {
        //Save animal details to the DB
        int test = 0 ;
    }
}
