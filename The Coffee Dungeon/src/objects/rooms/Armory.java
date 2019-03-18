package objects.rooms;

import game.loot.Loot;
import objects.abstracts.Monster;
import objects.abstracts.Room;
import objects.monsters.*;
import objects.usables.items.HealPotion;

public class Armory extends Room
{
    public static final String name = "Armory";
    public static final String desc = "Sharp things inside";
    public static final int MAX_GOLD = 10;
    
    private static final Monster[] monsters = { new Slime()};
    private static final double[] weights = { 1 };
    
    public Armory()
    {
        super(name, desc, monsters, weights);
    }

    @Override
    public Loot giveLoot()
    {
        /* 
         * TOMAKE Armory
         * player.addArmor();
         * player.addGold(Room.rand(MAX_GOLD));
        */
        return new Loot(new HealPotion());
    }
}
