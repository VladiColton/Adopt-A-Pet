package mainmanager;

import entities.Animal;
import entities.Owner;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import repository.AnimalRepository;
import repository.OwnerRepository;
import static sun.misc.IOUtils.readFully;

/**
 * Update Animals information Form manager bean
 */
@SessionScoped
@ManagedBean(name = "animalUpdateFormManager", eager = true)
public class AnimalUpdateFormManager extends NewAnimalRegirtrationFormManager implements Serializable {
    private Long id;
    private List<Animal> listOfAnimalregisteredByUser;
    private Part image;
    
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
    
    public Part getImage()
    {
        return image;
    }
    public void setImage(Part image)
    {
        this.image = image;
    }
    
    public void deleteAnimal()
    {
        //Make sure user is connected
        if(!SessionUtils.isUserConnected())
            return;
        
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
        //Make sure user is connected
        if(!SessionUtils.isUserConnected())
            return;
        
        //Find the animal to be deleted in the list
        for(int i = 0; i < listOfAnimalregisteredByUser.size(); i++)
        {
            if(listOfAnimalregisteredByUser.get(i).getId().compareTo(this.id) == 0)
            {
                Animal animalToEdit = listOfAnimalregisteredByUser.get(i);
                
                //Check if animals need to be updated            
                if(!animalToEdit.getName().equalsIgnoreCase(this.getName()) || (animalToEdit.getAge() != this.getAge()) || !animalToEdit.getType().equalsIgnoreCase(this.getType()) || !animalToEdit.getSubType().equalsIgnoreCase(this.getSubType()) || !animalToEdit.getDescription().equalsIgnoreCase(this.getDescription()) || image != null)
                {
                    //Update Animal Details
                    animalToEdit.setName(this.getName());
                    animalToEdit.setAge(this.getAge());
                    animalToEdit.setType(this.getType());
                    animalToEdit.setSubType(this.getSubType());
                    animalToEdit.setDescription(this.getDescription());
                    
                    //Check if animal image need to be updated and update it
                    if(this.image != null)
                    {
                        try
                        {
                            InputStream in = image.getInputStream();
                            //Create byte array to save the image in the DB
                            byte[] fileAsByteArray = readFully(in, Integer.MAX_VALUE, true/*ignored since Integer.MAX_VALUE set*/);
                            animalToEdit.setAnimalPic(fileAsByteArray);
                        }
                        catch(IOException ex)
                        {
                            ex.printStackTrace(System.out);
                        }
                    }
                    
                    //Update selected animal in the DB
                    AnimalRepository animalRep = new AnimalRepository();
                    animalRep.update(animalToEdit);
                }
                break;
            }
        }
    }
}
