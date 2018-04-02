package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Owner representation Class
 *
 * @author Vladi Colton
 */
public class Owner {

    private String name;
    private int phoneNumber;
    private String location;

    public Owner() {
        this.name = "Anonymous";
        this.location = "Unknown";
        this.phoneNumber = 0;
    }

    public Owner(String name, String location, int phoneNum) {
        this.name = (name != null ? name : "Anonymous");
        this.location = (location != null ? location : "Unknown");
        this.phoneNumber = phoneNum;
    }

    public void setName(String newName) {
        this.name = (newName != null ? newName : "Anonymous");
    }

    public String getName() {
        return this.name;
    }

    public void setLocation(String newLocation) {
        this.location = (newLocation != null ? newLocation : "Unknown");
    }

    public String getLocation() {
        return this.location;
    }

    public void setPhoneNum(int newPhoneNum) {
        this.phoneNumber = (newPhoneNum >= 0 ? newPhoneNum : 0);
    }

    public int getPhoneNum() {
        return this.phoneNumber;
    }
}
