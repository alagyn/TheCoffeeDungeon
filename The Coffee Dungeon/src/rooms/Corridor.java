package rooms;
import interfaces.*;
import game.Player;

public class Corridor implements Room
{
    private static final String name = "Corridor";
    private static final int MAX_GOLD = 15;
    
    public Corridor()
    {
        
    }
    
    @Override
    public void giveLoot(Player player)
    {
        player.addGold(Room.rand(MAX_GOLD));
    }
    
    @Override
    public String getName()
    {
        return name;
    }
}