package mainmanager;

import entities.Animal;
import entities.Owner;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.*;

/**
 * Main Page manager class before user login
 */
@SessionScoped
@ManagedBean(name = "mainPageMGR", eager = true)
public class MainPageMGR implements Serializable {

    //private LinkedList<Animal> animals;
    private List<Animal> _animals;
    private String animalTypeToView;

    public MainPageMGR() {
        animalTypeToView = "all";
        
        byte[] animalProfPic = null;
        try
        {
            //Get directory with default image
            File f = new File(UploadController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String imageLocation = f.getPath(); //Set relative path to the DB
            imageLocation = imageLocation.substring(0, imageLocation.indexOf("Adopt-A-Pet")+12) + "\\web\\images";
            //Get default image
            File usrImageFile = new File(imageLocation + "\\DefaultAnimalProfileImage.png");
            //Create byte array for saving in the DB
            animalProfPic = Files.readAllBytes(usrImageFile.toPath());
        }
        catch (IOException ex) 
        {
            ex.printStackTrace(System.out);
        }
        
        //Create animals
        this._animals = new ArrayList<>();
        
        Animal an1 = new Animal(Owner.builder().name("Sivan" + " " + "Schrier").location("Eilat" + ", " + "Israel").phoneNumber(546903018).email("Sivan@sivan.com").build(), "testAnimal", "dog", "subtype", "Animal_01", 10);
        an1.setAnimalPic(animalProfPic);
        this._animals.add(an1);
        Animal an2 = new Animal(Owner.builder().name("Vladi" + " " + "Colton").location("Nesher" + ", " + "Israel").phoneNumber(546903018).email("Vladi@Vladi.com").build(), "testAnima2", "type2", "subtype", "Animal_02", 12);
        an2.setAnimalPic(animalProfPic);
        this._animals.add(an2);
        Animal an3 = new Animal(null, "testAnimal desk3", "type3", "subtype2", "Animal_03", 16);
        an3.setAnimalPic(animalProfPic);
        this._animals.add(an3);
        Animal an4 = new Animal(null, "testAnimal desk4", "type4", "subtype3", "Animal_04", 177);
        an4.setAnimalPic(animalProfPic);
        this._animals.add(an4);
    }
    
    public List<Animal> getAnimals() 
    {
        if (animalTypeToView.equalsIgnoreCase("all"))
        {
            return this._animals;
        }
        else
        {
            //Build new sublist to return with only selected animals
            List<Animal> test = this._animals.stream().filter(a -> a.getType().equalsIgnoreCase(animalTypeToView)).collect(Collectors.toList());
            return test;
        }
    }
    
    public void setAnimalFilter(String animalType)
    {
        //Switch case
        switch(animalType)
        {
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
}
