package interfaces;

import java.util.Random;

import game.player.Player;

public interface Room
{
    public static int rand(int max)
    {
        Random rand = new Random();
        return rand.nextInt(max);
    }
    
    public void giveLoot(Player player);
    public String getName();
    public String getDesc();
}