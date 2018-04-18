package repository;

import entities.Owner;
import java.util.List;
import passwords.Passwords;

public class OwnerRepository extends Repository<Owner> {

    public OwnerRepository() {
        super(Owner.class);
    }

    public boolean isEmailAvailable(String email) {
        List<Long> resultList = em.createNamedQuery(Owner.GET_EMAIL_COUNT)
                .setParameter("email", email)
                .getResultList();
        Long ownersFound = resultList.get(0);

        return ownersFound == 0;
    }

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
