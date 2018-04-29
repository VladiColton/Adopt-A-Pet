package repository;

import entities.Owner;
import java.util.List;
import passwords.Passwords;

/**
 * <pre>
 * Owner repository.
 *
 * Handles all owner related persistency actions.
 * </pre>
 */
public class OwnerRepository extends Repository<Owner> {

    public OwnerRepository() {
        super(Owner.class);
    }

    /**
     * Checks if provided email is available, meaning no owner already
     * registered with it
     *
     * @param email email address
     * @return true if email is available, false otherwise.
     */
    public boolean isEmailAvailable(String email) {
        List<Long> resultList = em.createNamedQuery(Owner.GET_EMAIL_COUNT)
                .setParameter("email", email)
                .getResultList();
        Long ownersFound = resultList.get(0);

        return ownersFound == 0;
    }

    /**
     * Checks if provided login credentials are valid
     *
     * @param email user email address
     * @param password user raw password
     * @return true if user email exists and password is correct, false in any
     * other case.
     */
    public boolean isLoginValid(String email, String password) {
        List<String> resultList = em.createNamedQuery(Owner.GET_OWNER_PASSWORD)
                .setParameter("email", email)
                .getResultList();

        if (resultList.isEmpty()) {
            return false;
        }

        String hashedPassword = resultList.get(0);
        return Passwords.checkPassword(password, hashedPassword);
    }

    /**
     * Returns a managed Owner entity from the connected database.
     *
     * @param email user's email address
     * @return Owner by email argument, null if such owner doesn't exists.
     */
    public Owner getOwner(String email) {
        List<Owner> resultList = em.createNamedQuery(Owner.GET_OWNER)
                .setParameter("email", email)
                .getResultList();

        if (resultList.isEmpty()) {
            return null;
        }

        Owner o = resultList.get(0);
        return o;
    }
}
