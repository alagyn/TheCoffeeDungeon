package game;

import abstracts.Monster;
//FIXME horde monster init

public class Horde
{
    private static Horde instance = new Horde();
    
    //FIXME Remove weights from horde and update monsters array
    //private double[] weights;
    
    
    /**
     * Default constructor
     */
    private Horde()
    {
        
    }
    
    /**
     * Reads an input file of monster stats
     * @param fileName The input filename
     * @deprecated
     */
    @SuppressWarnings(value= {"unused"})
    private void readFile(String fileName)
    {
        /*
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
                //monsters.add(readMonster(in.nextLine()));
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
        */
    }
    
    
    /**
     * Creates a monster object from a line of data
     * @param line The data
     * @return The constructed monster
     * @throws IllegalArgumentException when invalid data
     * @deprecated
     */
    @SuppressWarnings(value= {"unused"})
    private Monster readMonster(String line)
    {
        /*
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

        //FIXME monster init
        
        
        lineScan.close();
        
        return output;
        */
        return null;
    }
    
    
    /**
     * Generates the random damage of a monster attack
     * @param monster The current monster
     * @return The damage
     * @deprecated
     */
    public int monsterAttack(Monster monster)
    {
        //FIXME Monster attack
       /*
        int output = 0;
        
        int[] atk = monster.getAtk();
        
        int min = atk[0], max = atk[1];
        
        int range = max - min + 1;
        output = rand.nextInt(range) + min;
        
        return output;
        */
        
        return 0;
    }
    
    public static Monster getCurrentMonster()
    {
        return instance.currentMonster;
    }

    public static void setCurrentMonster(Monster nextMonster)
    {
        instance.currentMonster = nextMonster;
    }
}