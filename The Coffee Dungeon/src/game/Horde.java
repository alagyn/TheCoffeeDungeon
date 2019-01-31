package game;

import java.util.*;
import java.io.*;

public class Horde
{
    private Monster[] monsters;
    private double[] weights;
    private Random rand;
    
    public Horde(String filename, int seed)
    {
        if(seed > 0)
        {
            rand = new Random(seed);
        }
        else
        {
            rand = new Random();
        }
        
        Scanner in;
        try
        {
            in = new Scanner(new File(filename));
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException("Invalid filename");
        }
        
        int amnt = 0;
        
        while(in.hasNext())
        {
            in.nextLine();
            amnt++;
        }

        monsters = new Monster[amnt];
        
        try
        {
            in = new Scanner(new File(filename));
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException("Invalid filename");
        }
        
        in.useDelimiter(",");
        
        for(int i = 0; i < amnt; i++)
        {
            String name = in.next();
            double weight = in.nextDouble();
            int health = in.nextInt();
            int minAtk = in.nextInt();
            int maxAtk = in.nextInt();
            
            monsters[i] = new Monster(name, weight, health, minAtk, maxAtk);
        }
        
        weights = new double[monsters.length];
        
        in.close();
        
        for(int i = 0; i < monsters.length; i++)
        {
            weights[i] = monsters[i].getWeight();
        }
        
        nextMonster();
    }
    
    public Monster nextMonster()
    {
        double gen = rand.nextDouble();
        double sum = 0;
        
        Monster output = null;
        
        for(int i = 0; i < weights.length; i++)
        {
            sum += weights[i];
            if(gen <= sum)
            {
                output = monsters[i];
                break;
            }
        }
        
        return output;
    }
    
}