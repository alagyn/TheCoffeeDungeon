package game;

import java.util.*;
import java.io.*;

public class Horde
{
    private ArrayList<Monster> monsters;
    private double[] weights;
    private Random rand;
    
    /**
     * Default constructor
     * @param fileName The filename for the input file
     * @param seed the seed for random generation
     */
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
    
    /**
     * Generates and returns the next random monster
     * @return The next monster
     */
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
    
    /**
     * Reads an input file of monster stats
     * @param fileName The input filename
     */
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
                System.out.println("\t" + e.getMessage());
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
    
    /**
     * Creates a monster object from a line of data
     * @param line The data
     * @return The constructed monster
     * @throws IllegalArgumentException when invalid data
     */
    private Monster readMonster(String line)
    {
        Monster output = null;
        
        Scanner lineScan = new Scanner(line);

        lineScan.useDelimiter(",");
        
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
            throw new IllegalArgumentException("Invalid name");
        }

        if(lineScan.hasNextDouble())
        {
            weight = lineScan.nextDouble();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid weight");
        }
        
        if(lineScan.hasNextInt())
        {
            health = lineScan.nextInt();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid health");
        }
        
        if(lineScan.hasNextInt())
        {
            minAtk = lineScan.nextInt();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid minAtk");
        }
        
        if(lineScan.hasNextInt())
        {
            maxAtk = lineScan.nextInt();
        }
        else
        {
            lineScan.close();
            throw new IllegalArgumentException("Invalid maxAtk");
        }

        output = new Monster(name, weight, health, minAtk, maxAtk);
        
        lineScan.close();
        
        return output;
    }

    /**
     * Generates the random damage of a monster attack
     * @param monster The current monster
     * @return The damage
     */
    public int monsterAttack(Monster monster)
    {
        int output = 0;
        
        int[] atk = monster.getAtk();
        
        int min = atk[0], max = atk[1];
        
        int range = max - min + 1;
        output = rand.nextInt(range) + min;
        
        return output;
    }
}