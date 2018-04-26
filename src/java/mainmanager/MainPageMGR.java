package mainmanager;

import entities.Animal;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import repository.AnimalRepository;

/**
 * Main Page manager class before user login
 */
@SessionScoped
@ManagedBean(name = "mainPageMGR", eager = true)
public class MainPageMGR implements Serializable {

    //private LinkedList<Animal> animals;
    private List<Animal> _animals;
    private String animalTypeToView;
    private int imageIndex;
    private List<Animal> listOfAnimals;

    public MainPageMGR() 
    {
        animalTypeToView = "all";
        imageIndex = -1;

    }

    public List<Animal> getAnimals() 
    {
        List<Animal> res;
        
        AnimalRepository animalsRep = new AnimalRepository();
        this._animals = animalsRep.getAllAnimals();
        
        if (animalTypeToView.equalsIgnoreCase("all")) {
            res = this._animals;
        } 
        else if(animalTypeToView.equalsIgnoreCase("Only My"))
        {
            String userMail = SessionUtils.getUserEmail();
            res = this._animals.stream().filter(a -> a.getOwner().getEmail().equalsIgnoreCase(userMail)).collect(Collectors.toList());
        }
        else 
        {
            //Build new sublist to return with only selected animals
            res = this._animals.stream().filter(a -> a.getType().equalsIgnoreCase(animalTypeToView)).collect(Collectors.toList());
        }

        //Before exit update the session with active list
        setAnimalsList(res);
        return res;
    }

    public void setAnimalFilter(String animalType) 
    {
        //Switch case
        switch (animalType) {
            case "dog":
            case "cat":
            case "parrot":
                animalTypeToView = animalType;
                break;
            default:
                animalTypeToView = "all";
                break;
        }
    }

    public void setAnimalsList(List<Animal> list) 
    {
        imageIndex = -1;
        listOfAnimals = list;
    }

    public StreamedContent getAnimalImageFromDB() 
    { 
        imageIndex++;
        if (listOfAnimals.size() == imageIndex) {
            imageIndex = imageIndex - 1;
        }
        return new DefaultStreamedContent(new ByteArrayInputStream(listOfAnimals.get(imageIndex).getAnimalPic()));
    }
}
