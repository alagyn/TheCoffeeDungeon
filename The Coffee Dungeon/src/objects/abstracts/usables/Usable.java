package objects.abstracts.usables;

import game.Completion;

public abstract class Usable
{
    private String name;
    private String desc;
    
    public enum UsableType
    {
        WEAPON,
        ITEM,
        MAGIC   
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
