package objects.rooms;

import game.player.Player;
import interfaces.Room;

public class Library implements Room
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