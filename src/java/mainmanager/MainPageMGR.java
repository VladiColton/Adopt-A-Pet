package mainmanager;

import entities.Animal;
import entities.Owner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;
/**
 * Main Page manager class before user login
 * @author Vladi Colton
 */
@RequestScoped
@ManagedBean (name = "mainPageMGR", eager = true)
public class MainPageMGR implements Serializable {
    //private LinkedList<Animal> animals;
    private List<Animal> _animals;
    public MainPageMGR()
    {
        this._animals = new ArrayList<>();
        this._animals.add(new Animal(new Owner("Sivan", "Eilat, Israel", 509062183, "Sivi@Sivi.com"), "testAnimal", "type1", "subtype", "Animal_01", 10));
        this._animals.add(new Animal(new Owner("Vladi Colton", "Nesher", 546903018, "Vladi@Vladi.com"), "testAnimal", "type1", "subtype", "Animal_02", 10));
        this._animals.add(new Animal(null, "testAnimal", "type1", "subtype", "Animal_03", 10));
        this._animals.add(new Animal(null, "testAnimal", "type1", "subtype", "Animal_04", 10));
        this._animals.add(new Animal(null, "testAnimal", "type1", "subtype", "Animal_05", 10));
        this._animals.add(new Animal(null, "testAnimal", "type1", "subtype", "Animal_06", 10));
    }
    
    public String getPets()
    {
        return this._animals.get(0).getName();
    }
    
    public List<Animal> getAnimals()
    {
        return this._animals;
    }
    
}
