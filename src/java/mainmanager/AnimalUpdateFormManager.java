package mainmanager;

import entities.Animal;
import entities.Owner;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import repository.OwnerRepository;

/**
 * Update Animals information Form manager bean
 */
@SessionScoped
@ManagedBean(name = "animalUpdateFormManager", eager = true)
public class AnimalUpdateFormManager extends NewAnimalRegirtrationFormManager implements Serializable {
    private Long id;
    private List<Animal> listOfAnimalregisteredByUser;
    
    public AnimalUpdateFormManager()
    {
        this.id = new Long(0);
    }
    
    public List<Animal> getListOfAnimalregisteredByUser() 
    {
        //Get the list of the Animals registered by the connected user
        OwnerRepository ownerRep = new OwnerRepository();
        Owner connectedUser = ownerRep.getOwner(SessionUtils.getUserEmail());
        listOfAnimalregisteredByUser = connectedUser.getAnimals();

        return listOfAnimalregisteredByUser;
    }
    public void setListOfAnimalregisteredByUser(List<Animal> listOfAnimalregisteredByUser) 
    {
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
        OwnerRepository ownerRep = new OwnerRepository();
        Owner connectedUser = ownerRep.getOwner(SessionUtils.getUserEmail());
        
        //Find the animal to be deleted in the list
        for(int i = 0; i < listOfAnimalregisteredByUser.size(); i++)
        {
            if(listOfAnimalregisteredByUser.get(i).getId().compareTo(this.id) == 0)
            {
                //Remove selected animal from the DB
                connectedUser.removeAnimal(listOfAnimalregisteredByUser.get(i));
                ownerRep.update(connectedUser);
                break;
            }
        }
    }
    
    public void updateAnimalDetails()
    {
        //Save animal details to the DB
        int test = 0 ;
    }
}
