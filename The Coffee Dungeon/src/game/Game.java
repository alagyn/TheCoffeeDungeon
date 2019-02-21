package game;

import game.player.*;
import gui.GameGUI;

public class Game
{
    /**
     * Number of rooms per floor
     */
    public static final int NUM_ROOMS = 3;

    private static Game game = new Game();
    

    /**Default Constructor*/
    private Game()
    {
        try
        {
            nextMonster();
        }
        catch(IllegalArgumentException e)
        {
            GameGUI.critErrorMessage("Input file not found");
        }
    }
    
    public static Game getInst()
    {
        return game;
    }

    /**
     * Generates the next monster
     * @return the next random monster
     */
    public void nextMonster()
    {
        Horde.nextMonster();
    }

    /**
     * Sets the current room index
     * @param roomIdx The current room index
     */
    public void setCurrentRoomIndex(int roomIdx)
    {
        Dungeon.setChosenRoom(roomIdx);
    }

    

    /**
     * Checks if the player and or monster is still alive
     * @return 1 if player win. -1 if player lose, 0 if neither lose
     */
    public int combatResolve()
    {
        int output = 0;

        if(Player.isAlive() && !Horde.isCurrentAlive())
        {
            output = 1;
        } 
        else if (!Player.isAlive())
        {
            output = -1;
        }

        return output;
    }

    /**
     * Gets the current player stats
     * @return An array of 0:Health, 1:Mana 
     */
    public String[] getPlayerStats()
    {
        String[] output = new String[2];
        output[0] = "" + Player.getHealth();
        output[1] = "" + Inventory.getMana();

        return output;
    }

    /**
     * Gets the current monster stats
     * @return An array of 0:Name, 1:Health
     */
    public String[] getMonsterStats()
    {
        return Horde.getMonsterStats();
    }

    /**
     * Gets the current room names
     * @return An array of room names
     */
    public String[] getRooms()
    {
        return Dungeon.getRoomNames();
    }

    /**
     * Returns the current room descs
     * @return the current room descs
     */
    public String[] getRoomDescs()
    {
        return Dungeon.getRoomDescs();
    }
    
    /**
     * Activates a primary attack on a monster and returns the damage done
     * @return The damage done
     */
    public int attack()
    {
       int damage = Inventory.getWeapon().attack();
       Horde.damageMonster(damage);
       return damage;
    }
    
    /*
     * TODO Return values for completed actions
     * Command object?
     * i.e. whether the action could be completed
     * not enough mana/Cooldowns?
     * throw exceptions? gives a third return value
     */
    
    /**
     * Activates the magic at the inventory index
     * @param idx the index of the magic
     * @return true if another action is available
     * @throws IllegalArgumentException when not enough mana
     */
    public boolean magic(int idx)
    {
        if(idx >= 0)
        {
            return Inventory.getMagic(idx).activate();
        }
        else
        {
            throw new IllegalArgumentException("Invalid index");
        }
    }
    
    /**
     * Activates the item at the  inventory index
     * @param idx the index
     */
    public boolean item(int idx)
    {
        if(idx >= 0)
        {
            return Inventory.getItems(idx).use();
        }
        else
        {
            throw new IllegalArgumentException("Invalid index");
        }
    }
    
    /**
     * Activates a monster attack with the current monster
     * @return The damage done to the player
     */
    public void monsterAttack()
    {
        Horde.getCurrentMonster().attack();
    }
    
    /**
     * Resets action to the starting configuration
     */
    public void newGame()
    {
        //FIXME Update newGame
        /*
        player = new Player();
        inventory = new Inventory();
        horde = new Horde("data/mon.csv", -1);
        
        nextMonster();
        
        dungeon = new Dungeon(-1);
        */
    }
    
    public void giveLoot()
    {
        Dungeon.giveLoot();
    }
}