package rooms;

import game.player.Player;
import interfaces.Room;

public class Armory implements Room
{
    public static final String name = "Armory";
    public static final int MAX_GOLD = 10;
    
    public Armory()
    {
        
    }

    @Override
    public void giveLoot(Player player)
    {
        /* TOMAKE loot
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
        // TODO Auto-generated method stub
        return null;
    }
}
