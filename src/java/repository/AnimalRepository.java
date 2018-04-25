package repository;

import entities.Animal;
import java.util.List;

public class AnimalRepository extends Repository<Animal> {

    public AnimalRepository() {
        super(Animal.class);
    }

    /**
     * get all animals from database sorted by creation date descending
     *
     * @return animals list
     */
    public List<Animal> getAllAnimals() {
        List<Animal> resultList = em.createNamedQuery(Animal.GET_ANIMALS_BY_DATE_DESC).getResultList();
        return resultList;
    }
}
