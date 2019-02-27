package objects.rooms;

import game.Loot;
import objects.abstracts.*;

public class Corridor extends Room
{
    private static final String name = "Corridor";
    //private static final int MAX_GOLD = 15;
    
    public Corridor()
    {
        super(null, null);
    }
    
    @Override
    public Loot giveLoot()
    {
        /*TOMAKE Corridor
        player.addGold(Room.rand(MAX_GOLD));
        */
        return null;
    }
    
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDesc()
    {
        return name;
    }
}
