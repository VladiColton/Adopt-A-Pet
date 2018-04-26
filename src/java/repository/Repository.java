package repository;

import javax.persistence.*;
import entities.Persistable;

public abstract class Repository<T extends Persistable> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Adopt-A-PetPU");
    protected final EntityManager em = emf.createEntityManager();

    private final Class<T> type;

    public Repository(Class<T> type) {
        this.type = type;
    }

    public T create(T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    }

    public T find(long id) {
        return em.find(type, id);
    }

    public void remove(long id) {
        T t = find(id);
        if (t == null) {
            return;
        }
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public void update(T t) {
        Long id = t.getId();
        if (find(id) == null) {
            throw new EntityNotFoundException("entity with id " + id + " does not exist");
        }
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }
}
