package repository;

import javax.persistence.*;
import entities.Persistable;

public abstract class Repository<T extends Persistable> {

    private final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("Adopt-A-PetPU");
    private final EntityManager em = emf.createEntityManager();
    private final EntityTransaction tx = em.getTransaction();

    private final Class<T> type;

    public Repository(Class<T> type) {
        this.type = type;
    }

    public T create(T t) {
        tx.begin();
        em.persist(t);
        tx.commit();
        return t;
    }

    public T find(long id) {
        return em.find(type, id);
    }

    public void remove(long id) {
        T t = find(id);
        if (t != null) {
            tx.begin();
            em.remove(t);
            tx.commit();
        }
    }

    public void update(T t) {
        Long id = t.getId();
        if (find(id) == null) {
            throw new EntityNotFoundException("entity with id " + id + " does not exist");
        }
        tx.begin();
        em.merge(t);
        tx.commit();
    }
}
