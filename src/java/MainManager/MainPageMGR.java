package MainManager;

import Entities.*;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
/**
 * Main Page manager class before user login
 * @author Vladi Colton
 */
@RequestScoped
@ManagedBean (name = "mainPageMGR")
public class MainPageMGR implements Serializable {
    private LinkedList<Animal> animals;
    
    public MainPageMGR()
    {
        animals = new LinkedList<Animal>();
        animals.add(new Animal(new Owner(), "testAnimal", "type1", "subtype", "test", 10));
        
    }
    
    public String getPets()
    {
        //return animals.get(0).getName();
        return "Dog";
    }
    
}
