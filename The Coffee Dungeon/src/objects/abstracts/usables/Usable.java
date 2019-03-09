package objects.abstracts.usables;

import game.loot.Completion;

public abstract class Usable
{
    //TOMAKE more items
    private String name;
    private String desc;
    private LootType type;
    
    public enum LootType
    {
        WEAPON,
        ITEM,
        MAGIC,
        NONE
    }
    
    public Usable(String name, String desc, LootType type)
    {
        if(name != null && name.length() > 0)
        {
            this.name = name;
        }
        else
        {
            throw new IllegalArgumentException("Invalid name");
        }
        
        if(desc != null && desc.length() > 0)
        {
            this.desc = desc;
        }
        else
        {
            throw new IllegalArgumentException("Invalid desc");
        }
        
        if(type != null)
        {
            this.type = type;
        }
        else
        {
            throw new IllegalArgumentException("Invalid LootType");
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDesc()
    {
        return desc;
    }
    
    public LootType getType()
    {
        return type;
    }
    
    public abstract Completion activate();
    public abstract boolean available();
    
}
