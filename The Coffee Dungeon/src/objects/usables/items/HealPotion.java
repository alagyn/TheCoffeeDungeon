package objects.usables.items;

import game.loot.Completion;
import objects.abstracts.usables.cooldown.Item;

public class HealPotion extends Item
{
    //private int uses;
    
    //TOMAKE HealPotion
    /*
     * Implement uses
     * Static vs field?
     */
    
    public HealPotion()
    {
        super("Health Potion", "Drink up");
    }
    
    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public String getDesc()
    {
        return null;
    }

    @Override
    public boolean available()
    {
        return false;
    }

    @Override
    public Completion activate()
    {
        return null;
    }

}
