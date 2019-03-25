package game;

import game.loot.Loot;
import game.util.RandUtil;
import objects.abstracts.*;
import objects.rooms.*;

public class RoomManager
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
     * Default constructor
     * @param seed The seed for random generation
     */
    public RoomManager(int seed)
    {
        currentRooms = new int[3];
        chosenRoom = -1;
    }
   
    /**
     * Generates the next 3 rooms
     */
    public void nextRooms()
    {   
        for(int i = 0; i < currentRooms.length; i++)
        {
            currentRooms[i] = -1;
        }
        
        for(int i = 0; i < currentRooms.length; i++)
        {
            boolean same;
            do
            {
                same = false;
                int gen =  RandUtil.integerRand(ROOMS.length - 1);
                
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
            }
            while(same);
        } 
    }
   
    public Room getCurrentRoom()
    {
        return RoomManager.ROOMS[currentRooms[chosenRoom]];
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
    public Loot giveLoot()
    {
        return ROOMS[chosenRoom].giveLoot();
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