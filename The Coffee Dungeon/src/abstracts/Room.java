package abstracts;

import java.util.Random;

public abstract class Room
{
    //FIXME Add monster weights to Room
    //Add monster list and weights
    public static int rand(int max)
    {
        Random rand = new Random();
        return rand.nextInt(max);
    }
    
    public abstract void giveLoot();
    public abstract String getName();
    public abstract String getDesc();
}