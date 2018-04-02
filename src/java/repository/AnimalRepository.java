package repository;

import javax.persistence.*;
import entities.Animal;

public class AnimalRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Adopt-A-PetPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public Animal createAnimal(Animal animal) {
        tx.begin();
        em.persist(animal);
        tx.commit();
        return animal;
    }
    
    public Animal findAnimal(long id) {
        return em.find(Animal.class, id);
    }
    
    public void removeAnimal(long id) {
        Animal animal = findAnimal(id);
        if (animal != null) {
            tx.begin();
            em.remove(animal);
            tx.commit();
        }
    }
    //TODO - updating animal, can be done directly with JPQL
}
