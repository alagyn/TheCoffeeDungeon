package game;

import java.util.*;
import java.io.*;

public class Horde
{
    private ArrayList<Monster> monsters;
    private double[] weights;
    private Random rand;
    
    public Horde(String fileName, int seed)
    {
        if(seed > 0)
        {
            rand = new Random(seed);
        }
        else
        {
            rand = new Random();
        }
        
        readFile(fileName);
        
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
                output = monsters.get(i);
                break;
            }
        }
        
        return output;
    }
    
    private void readFile(String fileName)
    {
        Scanner in;
        try
        {
            in = new Scanner(new File(fileName));
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

        monsters = new ArrayList<Monster>();
        
        try
        {
            in = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException("Invalid filename");
        }
        
        for(int i = 0; i < amnt; i++)
        {
            try
            {
                monsters.add(readMonster(in.nextLine()));
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Monster data error at line " + i);
                continue;
            }
            
        }
        
        in.close();
        
        weights = new double[amnt];
        
        for(int i = 0; i < monsters.size(); i++)
        {
            try
            {
                weights[i] = monsters.get(i).getWeight();
            }
            catch(IllegalArgumentException e)
            {
                i--;
                continue;
            }
        }
        
    }
    
    private Monster readMonster(String line)
    {
        Monster output = null;
        
        Scanner lineScan = new Scanner(line);

        String name;
        double weight;
        int health, minAtk, maxAtk;
        
        if(lineScan.hasNext())
        {
            name = lineScan.next();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid input");
        }

        if(lineScan.hasNextDouble())
        {
            weight = lineScan.nextDouble();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid input");
        }
        
        if(lineScan.hasNextInt())
        {
            health = lineScan.nextInt();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid input");
        }
        
        if(lineScan.hasNextInt())
        {
            minAtk = lineScan.nextInt();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid input");
        }
        
        if(lineScan.hasNextInt())
        {
            maxAtk = lineScan.nextInt();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid input");
        }

        output = new Monster(name, weight, health, minAtk, maxAtk);
        
        lineScan.close();
        
        return output;
    }
}