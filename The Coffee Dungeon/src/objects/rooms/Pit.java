package objects.rooms;


import objects.abstracts.Room;

public class Pit extends Room
{
    static final String name = "Pit";
    
    public Pit()
    {
        super(null, null);
    }
    
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
        return name;
    }
}