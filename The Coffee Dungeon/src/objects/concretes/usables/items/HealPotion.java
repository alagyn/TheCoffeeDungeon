package objects.concretes.usables.items;

import game.Game;
import game.loot.Completion;
import objects.abstracts.usables.cooldown.Item;

public class HealPotion extends Item
{   
    //TOMAKE HealPotion
    /*
     * Implement uses
     */
    
    public HealPotion()
    {
        super("Health Potion", "Drink up", 3, true);
    }

    @Override
    public boolean available()
    {
        return checkCoolDown() && checkItemUse();
    }

    @Override
    public Completion activate()
    {
        Completion output;
        
        if(available())
        {
            Game.getInst().getPlayer().addHealth(5);
            output = new Completion(true, canHaveSecond());
            useItem();
        }
        else
        {
            output = new Completion(false, canHaveSecond());
        }
        
        return output;
    }

}
