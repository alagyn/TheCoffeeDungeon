package rooms;

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
    public void giveLoot(Player player)
    {
        /*TOMAKE loot
        player.addMagic();
        */
    }

    @Override
    public String getDesc()
    {
        // TODO Auto-generated method stub
        return null;
    }
}