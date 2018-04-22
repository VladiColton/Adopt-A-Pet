package repository;

import entities.Animal;
import java.util.List;

public class AnimalRepository extends Repository<Animal> {

    public AnimalRepository() {
        super(Animal.class);
    }
    
//    public List<Animal> getAnimals(int from, int amount) {
//        List<Animal> resultList = em.createNamedQuery(Animal.GET_ANIMALS_BY_DATE_DESC)
//                .setFirstResult(from)
//                .setMaxResults(amount)
//                .getResultList();
//    }
}
