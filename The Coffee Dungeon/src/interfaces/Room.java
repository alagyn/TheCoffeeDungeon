package interfaces;

import java.util.Random;

public interface Room
{
    //FIXME Add monster weights to Room
    //Add monster list and weights
    public static int rand(int max)
    {
        Random rand = new Random();
        return rand.nextInt(max);
    }
    
    public void giveLoot();
    public String getName();
    public String getDesc();
}