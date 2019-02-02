package game;

import java.util.*;

import game.player.Player;
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
    
    /**
     * The indexes of the current rooms in the rooms array
     */
    private int[] index;
    /**
     * Random generator
     */
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
        
        nextRooms();
    }
   
    /**
     * Generates the next 3 rooms
     */
    public void nextRooms()
    {
        for(int i = 0; i < index.length; i++)
        {
            index[i] = rand.nextInt(rooms.length);
        } 
    }
   
    /**
     * Returns the current room names
     * @return the current room names
     */
    public String[] getRoomNames()
    {
        String[] output = new String[index.length];
        
        for(int i = 0; i < index.length; i++)
        {
            output[i] = rooms[index[i]].getName();
        }
        
        return output;
    }
    
    public void giveLoot(int index, Player player)
    {
        //TODO Inventory loot
        //TODO Make more items
        rooms[index].giveLoot(player);
    }
    
}