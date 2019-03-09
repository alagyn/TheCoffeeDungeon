package objects.abstracts;

import java.util.Random;

import game.loot.Loot;

public abstract class Room
{
    double[] weights;
    Monster[] monsters;
    private static Random rand = new Random();
    
    private String name;
    private String desc;
    
    public Room(String name, String desc, Monster[] monsters, double[] weights)
    {
        if(monsters.length > 0 && weights.length == monsters.length)
        {
            this.monsters = monsters;
            this.weights = weights;
        }
        else
        {
            throw new IllegalArgumentException();
        }
        
        if(name != null && name.length() > 0)
        {
            this.name = name;
        }
        else
        {
            throw new IllegalArgumentException();
        }
        
        if(desc != null && desc.length() > 0)
        {
            this.desc = desc;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    public Monster nextMonster()
    {
        double sum = 0;
        double num = rand.nextDouble();
        int output = -1;
        
        for(int i = 0; i < weights.length; i++)
        {
            sum += weights[i];
            if(num <= sum)
            {
                output = i;
            }
        }
        
        return monsters[output];
    }
    
    public abstract Loot giveLoot();

    public String getName()
    {
        return name;
    }
    
    public String getDesc()
    {
        return desc;
    }
}