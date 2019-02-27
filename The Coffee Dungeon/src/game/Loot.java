package game;

import objects.abstracts.usables.Usable;
import objects.abstracts.usables.Usable.LootType;

public class Loot
{
    private LootType type;
    private Usable loot;
    
    public Loot(LootType type, Usable loot)
    {
        if(type != null)
        {
            this.type = type;
        }
        else
        {
            throw new IllegalArgumentException("Null LootType");
        }
        
        if(type == LootType.NONE)
        {
            loot = null;
        }
        else if(loot == null)
        {
            throw new IllegalArgumentException("Invalid Null loot");
        }
    }
    
    public LootType getType()
    {
        return type;
    }
    
    public Usable getLoot()
    {
        return loot;
    }
}
