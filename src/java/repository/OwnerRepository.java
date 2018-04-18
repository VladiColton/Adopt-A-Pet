package repository;

import entities.Owner;
import java.util.List;

public class OwnerRepository extends Repository<Owner> {

    public OwnerRepository() {
        super(Owner.class);
    }

    public boolean isEmailAvailable(String email) {
        List<Long> resultList = em.createNamedQuery(Owner.IS_EMAIL_AVAILABLE)
                .setParameter("email", email)
                .getResultList();
        Long ownersFound = resultList.get(0);

        return ownersFound == 0;
    }
}
