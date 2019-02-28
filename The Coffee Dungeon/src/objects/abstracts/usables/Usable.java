package objects.abstracts.usables;

import game.Completion;

public abstract class Usable
{
    //TOMAKE more items
    private String name;
    private String desc;
    
    public enum LootType
    {
        WEAPON,
        ITEM,
        MAGIC,
        NONE
    }
    
    public Usable(String name, String desc)
    {
        if(name != null && name.length() > 0)
        {
            this.name = name;
        }
        
        if(desc != null && desc.length() > 0)
        {
            this.desc = desc;
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
    
    public abstract Completion activate();
    public abstract boolean available();
    
}
