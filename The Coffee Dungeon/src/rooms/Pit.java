package rooms;

import game.player.Player;
import interfaces.Room;

public class Pit implements Room
{
    static final String name = "Pit";
    
    public Pit()
    {}
    
    @Override
    public void giveLoot(Player player)
    {
        /*TOMAKE loot
        player.addWeapon();
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