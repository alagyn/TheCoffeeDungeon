package game.loot;

import objects.abstracts.usables.Usable;
import objects.abstracts.usables.Usable.LootType;
import objects.abstracts.usables.cooldown.Item;
import objects.abstracts.usables.cooldown.Magic;
import objects.abstracts.usables.weapon.Weapon;

public class Loot
{
    private LootType type;
    private Usable loot;
    
    public Loot(Usable loot)
    {
        if(loot == null)
        {
            type = LootType.NONE;
        }
        else
        {
            this.loot = loot;
            type = loot.getType();
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
