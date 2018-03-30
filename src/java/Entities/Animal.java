package Entities;

/**
 * Animal Representation Class
 * @author Vladi Colton
 */
public class Animal {
    private float _age;
    private String _description;
    private String _type;
    private String _subType;
    private String _name;
    private Owner _owner;
    
    public Animal(Owner owner, String description, String type, String subType, String name, float age)
    {
        this._description = description;
        this._name = name;
        this._type = type;
        this._subType = subType;
        this._owner = owner;
        
        this.setAge(age);
    }
    
    public final void setAge(float newAge)
    {
        this._age = newAge > 0 ? newAge : 0; 
    }
    public float getAge()
    {
        return this._age;
    }
   
    public void setDescription(String newDescription)
    {
        this._description = newDescription;
    }
    public String getDescription()
    {
        return this._description;
    }
    
    public void setName(String newName)
    {
        this._name = newName;
    }
    public String getName()
    {
        return this._name;
    }
    
    public void setType(String newType)
    {
        this._type = newType;
    }
    public String getType()
    {
        return this._type;
    }
    
    public void setSubType(String newSubType)
    {
        this._subType = newSubType;
    }
    public String getSubType()
    {
        return this._subType;
    }
    
    public void setOwner(Owner newOwner)
    {
        this._owner = newOwner;
    }
    public Owner getOwner()
    {
        return this._owner;
    }
}
