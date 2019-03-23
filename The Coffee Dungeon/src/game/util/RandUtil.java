package game.util;

import java.util.Random;

public abstract class RandUtil
{
    private static Random rand = new Random();
    
    public static int weightRand(double[] weights)
    {
        if(weights == null)
        {
            throw new NullPointerException("Invalid weights: weightRand()");
        }
        
        double total = 0;
        
        for(int i = 0; i < weights.length; i++)
        {
            total += weights[i];
        }
        
        
        double num = rand.nextDouble() * total;
        double sum = 0;
        int index = -1;
        
        for(int i = 0; i < weights.length; i++)
        {
            sum += weights[i];
            if(num <= sum)
            {
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public static int integerRand(int max, int min)
    {
        int range = max - min + 1;
        return rand.nextInt(range) + min;
    }
    
    public static int integerRand(int max)
    {
        return integerRand(max, 0);
    }
}
