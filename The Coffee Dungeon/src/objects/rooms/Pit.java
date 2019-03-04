package objects.rooms;


import game.loot.Loot;
import objects.abstracts.Room;

public class Pit extends Room
{
    static final String name = "Pit";
    
    public Pit()
    {
        super(null, null);
    }
    
    @Override
    public Loot giveLoot()
    {
        /*TOMAKE Pit
        player.addWeapon();
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