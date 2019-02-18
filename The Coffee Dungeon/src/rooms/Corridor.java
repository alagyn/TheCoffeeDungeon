package rooms;
import game.player.Player;
import interfaces.*;

public class Corridor implements Room
{
    private static final String name = "Corridor";
    //private static final int MAX_GOLD = 15;
    
    public Corridor()
    {
        
    }
    
    @Override
    public void giveLoot(Player player)
    {
        /*TOMAKE loot
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
