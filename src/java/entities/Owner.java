package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import passwords.Passwords;

/**
 * Owner representation Class
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Owner.GET_EMAIL_COUNT,
            query = "SELECT COUNT(o) FROM Owner o WHERE o.email = :email"),
    @NamedQuery(name = Owner.GET_OWNER_PASSWORD,
            query = "SELECT o.passwordHash FROM Owner o WHERE o.email = :email")
})
public class Owner implements Persistable, Serializable {

    public static final String GET_EMAIL_COUNT = "Owner.getEmailCount";
    public static final String GET_OWNER_PASSWORD = "Owner.getOwnerPassword";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String name;

    private long phoneNumber;
    private String location;

    @Lob
    private byte[] profilePic;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Animal> animals = new HashSet<>();

    protected Owner() {
    }

    private Owner(String email, String passwordHash, String name, long phoneNumber, String location, byte[] profilePic) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.profilePic = profilePic;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = Passwords.hashPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owner)) {
            return false;
        }
        Owner other = (Owner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static OwnerBuilder builder() {
        return new OwnerBuilder();
    }

    public static class OwnerBuilder {

        private String email;
        private String passwordHash;
        private String name;
        private long phoneNumber;
        private String location;
        private byte[] profilePic;

        public OwnerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public OwnerBuilder password(String password) {
            this.passwordHash = Passwords.hashPassword(password);
            return this;
        }

        public OwnerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public OwnerBuilder phoneNumber(long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public OwnerBuilder location(String location) {
            this.location = location;
            return this;
        }

        public OwnerBuilder profilePic(byte[] profilePic) {
            this.profilePic = profilePic;
            return this;
        }

        public Owner build() {
            return new Owner(email, passwordHash, name, phoneNumber, location, profilePic);
        }
    }
}
