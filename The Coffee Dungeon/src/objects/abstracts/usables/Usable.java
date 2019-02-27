package objects.abstracts.usables;

import game.Completion;

public abstract class Usable
{
    String name;
    String desc;
    
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
