package repository;

import entities.Animal;

public class AnimalRepository extends Repository<Animal> {

    public AnimalRepository() {
        super(Animal.class);
    }
}
