package game;

import java.io.FileNotFoundException;

import game.player.*;
import gui.GameGUI;

public class Game
{
    /**
     * Number of rooms per floor
     */
    public static final int NUM_ROOMS = 3;

    private static Game game = new Game();
    
    private Player player;
    private Inventory inventory;
    
    private Dungeon dungeon;
    private Horde horde;
    //FIXME Remove monster instance from Game
    //Move to Horde
    private Monster monster;

    /**Default Constructor*/
    private Game()
    {
        try
        {
            player = new Player();
            inventory = new Inventory();
            horde = new Horde("data/mon.csv", -1);
            
            nextMonster();
            
            dungeon = new Dungeon(-1);
        }
        catch(IllegalArgumentException e)
        {
            GameGUI.critErrorMessage("Input file not found");
        }
    }
    
    public static Game getInstance()
    {
        return game;
    }

    /**
     * Generates the next monster
     * @return the next random monster
     */
    public Monster nextMonster()
    {
        if(monster != null)
        {
            monster.reset();
        }
        
        monster = horde.nextMonster();
        return monster;
    }

    /**
     * Sets the current room index
     * @param roomIdx The current room index
     */
    public void setCurrentRoomIndex(int roomIdx)
    {
        dungeon.setChosenRoom(roomIdx);
    }

    

    /**
     * Checks if the player and or monster is still alive
     * @return 1 if player win. -1 if player lose, 0 if neither lose
     */
    public int combatResolve()
    {
        int output = 0;

        if(player.isAlive() && !monster.isAlive())
        {
            output = 1;
        } 
        else if (!player.isAlive())
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
        output[0] = "" + player.getHealth();
        output[1] = "" + inventory.getMana();

        return output;
    }

    /**
     * Gets the current monster stats
     * @return An array of 0:Name, 1:Health
     */
    public String[] getMonsterStats()
    {
        String[] output = new String[2];
        output[0] = "" + monster.getName();
        output[1] = "" + monster.getHealth();

        return output;
    }

    /**
     * Gets the current room names
     * @return An array of room names
     */
    public String[] getRooms()
    {
        return dungeon.getRoomNames();
    }

    /**
     * Returns the current room descs
     * @return the current room descs
     */
    public String[] getRoomDescs()
    {
        return dungeon.getRoomDescs();
    }
    
    /**
     * Activates a primary attack on a monster and returns the damage done
     * @return The damage done
     */
    public int attack()
    {
       int damage = inventory.getWeapon().attack(player);
       monster.damage(damage);
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
            return inventory.getMagic(idx).activate(player, monster);
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
            return inventory.getItems(idx).use(player, monster);
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
    public int monsterAttack()
    {
        int dmg = horde.monsterAttack(monster);
        player.damage(dmg);
        return dmg;
    }
    
    /**
     * Resets action to the starting configuration
     */
    public void newGame()
    {
        player = new Player();
        inventory = new Inventory();
        horde = new Horde("data/mon.csv", -1);
        
        nextMonster();
        
        dungeon = new Dungeon(-1);
    }
    
    public Inventory getInventory()
    {
        return inventory;
    }

    public void giveLoot()
    {
        dungeon.giveLoot(player);
    }
}