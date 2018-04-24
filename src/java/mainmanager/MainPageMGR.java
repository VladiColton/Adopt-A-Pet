package mainmanager;

import entities.Animal;
import entities.Owner;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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

    public MainPageMGR() {
        animalTypeToView = "all";
        imageIndex = -1;

        byte[] animalProfPic = null;
        try {
            //Get directory with default image
            File f = new File(UploadController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String imageLocation = f.getPath(); //Set relative path to the DB
            imageLocation = Paths.get(imageLocation.substring(0, imageLocation.indexOf("Adopt-A-Pet") + 12), "web", "images").toString();
            //Get default image
            File usrImageFile = new File(Paths.get(imageLocation, "DefaultAnimalProfileImage.png").toString());
            //Create byte array for saving in the DB
            animalProfPic = Files.readAllBytes(usrImageFile.toPath());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        //Create animals
        this._animals = new ArrayList<>();

        // Use new builder
        Animal an1 = Animal.builder()
                .name("Animal_01")
                .age(10)
                .type("dog")
                .subType("subtype")
                .description("testAnimal")
                .animalPic(animalProfPic)
//                .owner(Owner.builder().name("Sivan" + " " + "Schrier").location("Eilat" + ", " + "Israel").phoneNumber(546903018).email("Sivan@sivan.com").build())
                .build();
        this._animals.add(an1);
        
        Animal an2 = Animal.builder()
                .name("Animal_02")
                .age(12)
                .type("cat")
                .subType("subtype")
                .description("testAnima2")
                .animalPic(animalProfPic)
//                .owner(Owner.builder().name("Vladi" + " " + "Colton").location("Nesher" + ", " + "Israel").phoneNumber(546903018).email("Vladi@Vladi.com").build())
                .build();
        this._animals.add(an2);
        
       
    }

    public List<Animal> getAnimals() {
        List<Animal> res;
        if (animalTypeToView.equalsIgnoreCase("all")) {
            res = this._animals;
        } else {
            //Build new sublist to return with only selected animals
            res = this._animals.stream().filter(a -> a.getType().equalsIgnoreCase(animalTypeToView)).collect(Collectors.toList());
        }

        //Before exit update the session with active list
        setAnimalsList(res);
        return res;
    }

    public void setAnimalFilter(String animalType) {
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

    public void setAnimalsList(List<Animal> list) {
        imageIndex = -1;
        listOfAnimals = list;
    }

    public StreamedContent getAnimalImageFromDB() {
        imageIndex++;
        if (listOfAnimals.size() == imageIndex) {
            imageIndex = imageIndex - 1;
        }
        return new DefaultStreamedContent(new ByteArrayInputStream(listOfAnimals.get(imageIndex).getAnimalPic()));
    }
}
