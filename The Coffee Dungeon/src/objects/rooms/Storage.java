package objects.rooms;

import objects.abstracts.Room;

public class Storage extends Room
{
    public static final String name = "Storage";
    public static final int MAX_GOLD = 10;
    
    public Storage()
    { 
        super(null, null);
    }

    @Override
    public void giveLoot()
    {
        /*TOMAKE Storage
        player.addPotion();
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
        return name;
    }

}
