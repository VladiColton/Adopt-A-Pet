package Entities;

/**
 * Owner representation Class
 * @author Vladi Colton
 */
public class Owner {
    private String _name;
    private int _phoneNum;
    private String _location;
    
    public Owner()
    {
        this._name = "Anonymous";
        this._location = "Unknown";
        this._phoneNum = 0;
    }
    public Owner(String name, String location, int phoneNum)
    {
        this._name = (name != null ? name : "Anonymous");
        this._location = (location != null ? location : "Unknown");
        this._phoneNum = phoneNum;
    }
    
    public void setName(String newName)
    {
        this._name = (newName != null ? newName : "Anonymous");
    }
    public String getName()
    {
        return this._name;
    }
    
    public void setLocation(String newLocation)
    {
        this._location = (newLocation != null ? newLocation : "Unknown");
    }
    public String getLocation()
    {
        return this._location;
    }
    
    public void setPhoneNum(int newPhoneNum)
    {
        this._phoneNum = (newPhoneNum >= 0 ? newPhoneNum : 0);
    }
    public int getPhoneNum()
    {
        return this._phoneNum;
    }
}
