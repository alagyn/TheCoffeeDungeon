package game.loot;

import objects.abstracts.usables.Usable;
import objects.abstracts.usables.Usable.LootType;
import objects.abstracts.usables.cooldown.Item;
import objects.abstracts.usables.cooldown.Magic;
import objects.abstracts.usables.weapon.Weapon;

public class Loot
{
    public final LootType type;
    private final Usable itemLoot;
    
    public Loot(Usable itemLoot)
    {
        if(itemLoot == null)
        {
            type = LootType.NONE;
            this.itemLoot = null;
        }
        else
        {
            this.itemLoot = itemLoot;
            type = itemLoot.getType();
        }
    }
    
    public Item getItemLoot()
    {
        if(type == LootType.ITEM)
        {
            return (Item)itemLoot;
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
            return (Weapon)itemLoot;
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
            return (Magic)itemLoot;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    public String getLootName()
    {
        return itemLoot.getName();
    }
    
    public String getLootDesc()
    {
        return itemLoot.getDesc();
    }
}
