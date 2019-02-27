package objects.rooms;

import game.Loot;
import objects.abstracts.Room;

public class Library extends Room
{
    static final String name = "Library";
    
    public Library()
    {
        super(null, null);
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public Loot giveLoot()
    {
        /*TOMAKE Library
        player.addMagic();
        */
        return null;
    }

    @Override
    public String getDesc()
    {
        return name;
    }
}