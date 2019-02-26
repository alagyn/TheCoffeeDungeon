package game;

import java.util.*;

import objects.abstracts.*;
import objects.rooms.*;

public class Dungeon
{
    /**
     * All of the available rooms
     */
    private static final Room[] ROOMS = 
        {
            /*
            new Corridor(),
            new Storage(),
            new Library(),
            new Pit(),
            */
            new Armory(),
            new Armory(),
            new Armory()
        };
    
    /**
     * The indexes of the current rooms in the rooms array
     */
    private int[] currentRooms;
    private int chosenRoom;
    /**
     * Random generator
     */
    private Random rand;
    
    /**
     * Default constructor
     * @param seed The seed for random generation
     */
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
        
        currentRooms = new int[3];
        chosenRoom = -1;
        
    }
   
    /**
     * Generates the next 3 rooms
     */
    public void nextRooms()
    {
        System.out.println("Resetting Array");
        for(int i = 0; i < currentRooms.length; i++)
        {
            currentRooms[i] = -1;
        }
        
        Arrays.toString(currentRooms);
        
        for(int i = 0; i < currentRooms.length; i++)
        {
            boolean same = false;
            do
            {
                //System.out.println("Randing");
                int gen =  rand.nextInt(ROOMS.length);
                //System.out.println("Randed");
                
                for(int x = 0; x < currentRooms.length; x++)
                {
                    if(gen == currentRooms[x])
                    {
                        same = true;
                    }
                }
                
                if(!same)
                {
                    currentRooms[i] = gen;
                }
                
            }while(same);
        } 
    }
   
    public Room getCurrentRoom()
    {
        return Dungeon.ROOMS[currentRooms[chosenRoom]];
    }
    
    /**
     * Returns the current room names
     * @return the current room names
     */
    public String[] getRoomNames()
    {
        String[] output = new String[currentRooms.length];
        
        for(int i = 0; i < currentRooms.length; i++)
        {
            output[i] = ROOMS[currentRooms[i]].getName();
        }
        
        return output;
    }
    
    /**
     * Returns the desc of the current rooms
     * @return the desc of the current rooms
     */
    public String[] getRoomDescs()
    {
        String[] output = new String[currentRooms.length];
        
        for(int i = 0; i < output.length; i++)
        {
            output[i] = ROOMS[currentRooms[i]].getDesc();
        }
        
        return output;
    }
    
    /**
     * Activates loot function of the room at the index
     * @param index The rooms index
     */
    public void giveLoot()
    {
        /*
         * TODO Item loot generation
         * Loot gen up to the room?
         * Item rand weights?
         * Selection GUI (slot selection/ replace)
         */
    
        //TOMAKE more items
        ROOMS[chosenRoom].giveLoot();
    }
    
    /**
     * Set's the current chosen room to the index
     * @param idx
     */
    public void setChosenRoom(int idx)
    {
        if(idx >= 0)
        {
            chosenRoom = idx;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

 
}