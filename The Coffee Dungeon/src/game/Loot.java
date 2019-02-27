package game;

import objects.abstracts.usables.Usable;
import objects.abstracts.usables.Usable.UsableType;

public class Loot
{
    private UsableType type;
    private Usable loot;
    
    public Loot(UsableType type, Usable loot)
    {
        if(type != null)
        {
            this.type = type;
        }
        
        if(loot != null)
        {
            this.loot = loot;
        }
    }
    
    public UsableType getType()
    {
        return type;
    }
    
    public Usable getLoot()
    {
        return loot;
    }
}
