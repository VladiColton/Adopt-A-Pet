package entities;

/**
 * Simple interface for JPA entities
 */
public interface Persistable {

    /**
     * Persisted Entity id getter
     * @return unique id of JPA entity
     */
    Long getId();
}
