package game;

import java.util.*;
import rooms.*;
import interfaces.*;

public class Dungeon
{
    public static final Room[] rooms = 
        {
                new Corridor(),
                new Storage(),
                new Armory(),
                new Library(),
                new Pit()
        };
    
    private int[] index;
    private Random rand;
    
    public Dungeon(int seed)
    {
        if(seed > 0)
        {
            rand = new Random(seed);
        }
        else
        {
            rand = new Random();
        }
        
        index = new int[3];
        
    }
   
    public String[] nextRooms()
    {
        for(int i = 0; i < index.length; i++)
        {
            index[i] = rand.nextInt(rooms.length);
        }
        
        String[] output = new String[index.length];
        
        for(int i = 0; i < index.length; i++)
        {
            output[i] = rooms[index[i]].getName();
        }
        
        return output;
    }
   
    public void giveLoot(int index, Player player)
    {
        rooms[index].giveLoot(player);
    }
    
}