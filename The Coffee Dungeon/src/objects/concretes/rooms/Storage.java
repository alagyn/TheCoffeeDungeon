package objects.concretes.rooms;

import game.loot.Loot;
import objects.abstracts.Room;

public class Storage extends Room
{
    public static final String name = "Storage";
    public static final int MAX_GOLD = 10;
    
    public Storage()
    { 
        super(name, "asdf", null, null);
    }

    @Override
    public Loot giveLoot()
    {
        /*TOMAKE Storage
        player.addPotion();
        player.addGold(Room.rand(MAX_GOLD));
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
