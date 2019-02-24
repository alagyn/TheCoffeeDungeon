package objects.rooms;

import game.player.Player;
import interfaces.Room;

public class Pit implements Room
{
    static final String name = "Pit";
    
    public Pit()
    {}
    
    @Override
    public void giveLoot()
    {
        /*TOMAKE Pit
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
        return null;
    }
}