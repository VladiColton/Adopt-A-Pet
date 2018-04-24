package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Animal Representation Class
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Animal.GET_ANIMALS_BY_DATE_DESC,
            query = "SELECT a FROM Animal a ORDER BY a.creationDate DESC")
//        ,
//    @NamedQuery
})
public class Animal implements Persistable, Serializable {

    public static final String GET_ANIMALS_BY_DATE_DESC = "Animal.getAnimalsByDateDesc";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double age;

    @Column(nullable = false)
    private String type;

    private String subType;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Lob
    private byte[] animalPic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    protected Animal() {
    }

    private Animal(String name, double age, String type, String subType, String description, byte[] animalPic) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.subType = subType;
        this.description = description;
        this.animalPic = animalPic;

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
    
    public void setOwner(Owner owner) {
        this.owner = owner;
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

        public Animal build() {
            return new Animal(name, age, type, subType, description, animalPic);
        }
    }
}
