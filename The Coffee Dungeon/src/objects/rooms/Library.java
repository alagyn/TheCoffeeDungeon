package objects.rooms;

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
    public void giveLoot()
    {
        /*TOMAKE Library
        player.addMagic();
        */
    }

    @Override
    public String getDesc()
    {
        return null;
    }
}