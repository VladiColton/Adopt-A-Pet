package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Animal Representation Class
 */
@Entity
public class Animal implements Persistable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double age;
    private String type;
    private String subType;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Lob
    private byte[] animalPic;

    @ManyToOne
    private Owner owner;

    protected Animal() {
    }

    private Animal(String name, double age, String type, String subType, String description, byte[] animalPic, Owner owner) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.subType = subType;
        this.description = description;
        this.animalPic = animalPic;
        this.owner = owner;

        this.creationDate = new Date();
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public byte[] getAnimalPic() {
        return animalPic;
    }

    public void setAnimalPic(byte[] animalPic) {
        this.animalPic = animalPic;
    }

    public Owner getOwner() {
        return owner;
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
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public static AnimalBuilder builder() {
        return new AnimalBuilder();
    }

    public static class AnimalBuilder {

        private String name;
        private double age;
        private String type;
        private String subType;
        private String description;
        private byte[] animalPic;
        private Owner owner;

        public AnimalBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AnimalBuilder age(double age) {
            this.age = age;
            return this;
        }

        public AnimalBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AnimalBuilder subType(String subType) {
            this.subType = subType;
            return this;
        }

        public AnimalBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AnimalBuilder animalPic(byte[] animalPic) {
            this.animalPic = animalPic;
            return this;
        }

        public AnimalBuilder owner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public Animal build() {
            if (this.owner == null) {
                throw new IllegalArgumentException("owner cannot be null");
            }
            return new Animal(name, age, type, subType, description, animalPic, owner);
        }
    }
}
