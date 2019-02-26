package game;

import objects.abstracts.Monster;

public class Game
{
    /**
     * Number of rooms per floor
     */
    public static final int NUM_ROOMS = 3;

    private static Game instance = new Game();
    private Monster currentMonster;

    private Player player;
    
    /**Default Constructor*/
    private Game()
    {
        player = new Player();   
    }
    
    /**
     * Generates the next monster
     * @return the next random monster
     */
    public static void nextMonster()
    {
        instance.currentMonster = Dungeon.getCurrentRoom().nextMonster();
    }

    /**
     * Sets the current room index
     * @param roomIdx The current room index
     */
    public static void setCurrentRoomIndex(int roomIdx)
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

        if(player.isAlive() && !currentMonster.isAlive())
        {
            output = 1;
        } 
        else if (!player.isAlive())
        {
            output = -1;
        }
        
        player.cooldowns();

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
        output[1] = "" + player.getMana();

        return output;
    }

    /**
     * Gets the current room names
     * @return An array of room names
     */
    public static String[] getRoomNames()
    {
        return Dungeon.getRoomNames();
    }

    /**
     * Returns the current room descs
     * @return the current room descs
     */
    public static String[] getRoomDescs()
    {
        return Dungeon.getRoomDescs();
    }
    
    /**
     * Activates a primary attack on a monster and returns the damage done
     * @return The damage done
     */
    public int attack()
    {
       int damage = player.getWeapon().attack();
       damageMonster(damage);
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
    public Completion magic(int idx)
    {
        if(idx >= 0)
        {
            return player.getMagic(idx).activate();
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
    public Completion item(int idx)
    {
        if(idx >= 0)
        {
            return player.getItems(idx).use();
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
    public static void monsterAttack()
    {
        instance.currentMonster.attack();
    }
    
    /**
     * Resets action to the starting configuration
     */
    public void newGame()
    {
        player = new Player();
        nextMonster();
        Dungeon.reset();   
    }
    
    public static void resetCurrentMonster()
    {
        instance.currentMonster.reset();
    }
    
    public static boolean isCurrentAlive()
    {
        return instance.currentMonster.isAlive();
    }

    public static void damageMonster(int damage)
    {
        instance.currentMonster.damage(damage);
    }

    
    public static void giveLoot()
    {
        Dungeon.giveLoot();
    }

    public static String getCurrentMonsterName()
    {
        return instance.currentMonster.getName();
    }

    public static String getCurrentMonsterHealth()
    {
        return "" + instance.currentMonster.getHealth();
    }

    public static Game getInst()
    {
        return instance;
    }

    public String[] getMagicNames()
    {
        return player.getMagicNames();
    }

    public String[] getMagicDescs()
    {
        return player.getMagicDescs();
    }

    public Player getPlayer()
    {
        return player;
    }
}