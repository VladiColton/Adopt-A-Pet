package mainmanager;

import entities.Animal;
import entities.Owner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;

/**
 * Main Page manager class before user login
 *
 * @author Vladi Colton
 */
@RequestScoped
@ManagedBean(name = "mainPageMGR", eager = true)
public class MainPageMGR implements Serializable {

    //private LinkedList<Animal> animals;
    private List<Animal> _animals;

    public MainPageMGR() {
        // TODO - remove file
        this._animals = new ArrayList<>();

        this._animals.add(new Animal(Owner.builder().name("Sivan" + " " + "Schrier").location("Eilat" + ", " + "Israel").phoneNumber(546903018).email("Sivan@sivan.com").build(), "testAnimal", "type1", "subtype", "Animal_01", 10));
        this._animals.add(new Animal(Owner.builder().name("Vladi" + " " + "Colton").location("Nesher" + ", " + "Israel").phoneNumber(546903018).email("Vladi@Vladi.com").build(), "testAnima2", "type2", "subtype", "Animal_02", 12));
        this._animals.add(new Animal(null, "testAnimal", "type1", "subtype", "Animal_03", 10));
        this._animals.add(new Animal(null, "testAnimal", "type1", "subtype", "Animal_04", 10));
        this._animals.add(new Animal(null, "testAnimal", "type1", "subtype", "Animal_05", 10));
        this._animals.add(new Animal(null, "testAnimal", "type1", "subtype", "Animal_06", 10));
        
    }

    public List<Animal> getAnimals() 
    {
        return this._animals;
    }
}
