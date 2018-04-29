package repository;

import javax.persistence.*;
import entities.Persistable;
import javax.faces.bean.SessionScoped;

/**
 * <pre>
 * Simple abstract repository for JPA entities.
 *
 * Implements general purpose JPA CRUD operations.
 * @param <T> persistable entity
 * </pre>
 */
@SessionScoped
public abstract class Repository<T extends Persistable> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Adopt-A-PetPU");
    protected final EntityManager em = emf.createEntityManager();

    private final Class<T> type;

    public Repository(Class<T> type) {
        this.type = type;
    }

    /**
     * Persists an entity to the connected database.
     *
     * @param t persistable entity
     * @return t (unpersisted)
     */
    public T create(T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
    }

    /**
     * Gets an entity from the connected database.
     *
     * @param id entity's id
     * @return persisted entity if id match found null otherwise.
     */
    public T find(long id) {
        return em.find(type, id);
    }

    /**
     * <pre>
     * Removes an entity from the connected database.
     *
     * If entity is found it will be removed, otherwise no action takes place.
     *
     * @param id entity's id
     * </pre>
     */
    public void remove(long id) {
        T t = find(id);
        if (t == null) {
            return;
        }
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    /**
     * Updates the given managed entity data in the connected database.
     *
     * @param t persisted entity to be updated
     * @throws EntityNotFoundException if entity with matching id doesn't exists
     * in connected db.
     */
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
