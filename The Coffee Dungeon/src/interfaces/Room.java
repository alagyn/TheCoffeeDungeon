package interfaces;

import game.Player;
import java.util.Random;

public interface Room
{
    public static int rand(int max)
    {
        Random rand = new Random();
        return rand.nextInt(max);
    }
    
    public void giveLoot(Player player);
    public String getName();
}