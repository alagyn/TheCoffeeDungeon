package rooms;

import interfaces.Room;
import game.Player;

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
        player.addMagic();
    }
}