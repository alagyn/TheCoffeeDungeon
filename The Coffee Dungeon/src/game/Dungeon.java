package game;

import java.util.*;

import game.player.Player;
import rooms.*;
import interfaces.*;

public class Dungeon
{
    /**
     * All of the available rooms
     */
    private static final Room[] ROOMS = 
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
        
        nextRooms();
    }
   
    /**
     * Generates the next 3 rooms
     */
    public void nextRooms()
    {
        for(int i = 0; i < currentRooms.length; i++)
        {
            currentRooms[i] = rand.nextInt(ROOMS.length);
        } 
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
     * @param player The current player
     */
    public void giveLoot(int index, Player player)
    {
        //TODO Inventory loot
        //TOMAKE more items
        ROOMS[index].giveLoot(player);
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
    
    //FIXME fix new current room index
}