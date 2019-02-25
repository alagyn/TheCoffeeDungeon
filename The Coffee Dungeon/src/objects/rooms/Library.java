package objects.rooms;

import abstracts.Room;

public class Library extends Room
{
    static final String name = "Library";
    
    public Library()
    {}
    
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