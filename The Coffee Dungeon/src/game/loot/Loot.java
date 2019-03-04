package game.loot;

import objects.abstracts.usables.Usable;
import objects.abstracts.usables.Usable.LootType;
import objects.abstracts.usables.Weapon;
import objects.abstracts.usables.cooldown.Item;
import objects.abstracts.usables.cooldown.Magic;

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
    
    public Item getItemLoot()
    {
        if(type == LootType.ITEM)
        {
            return (Item)loot;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    public Weapon getWeaponLoot()
    {
        if(type == LootType.WEAPON)
        {
            return (Weapon)loot;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    public Magic getMagicLoot()
    {
        if(type == LootType.MAGIC)
        {
            return (Magic)loot;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
}
