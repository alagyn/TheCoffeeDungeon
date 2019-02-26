package objects.rooms;

import objects.abstracts.Monster;
import objects.abstracts.Room;
import objects.monsters.*;

public class Armory extends Room
{
    public static final String name = "Armory";
    public static final int MAX_GOLD = 10;
    
    private static final Monster[] monsters = { new Slime()};
    private static final double[] weights = { 1 };
    
    public Armory()
    {
        super(monsters, weights);
    }

    @Override
    public void giveLoot()
    {
        /* TOMAKE Armory
        player.addArmor();
        player.addGold(Room.rand(MAX_GOLD));
        */
    }
    
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDesc()
    {
        return null;
    }
}
