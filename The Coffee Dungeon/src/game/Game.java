package game;

import game.loot.Completion;
import game.loot.Loot;
import objects.abstracts.Monster;

public class Game
{
    /**
     * Number of rooms per floor
     */
    public static final int NUM_ROOMS = 3;

    private static Game instance = new Game();
    private Monster currentMonster;
    private Loot currentLoot;

    private Player player;
    private Dungeon dungeon;
    
    public enum Status
    {
        LOSE,
        WIN,
        NEUTRAL
    }
    
    /**Default Constructor*/
    private Game()
    {
        dungeon = new Dungeon(-1);
    }
    
    /**
     * Generates the next monster
     * @return the next random monster
     */
    public void nextMonster()
    {
        instance.currentMonster = dungeon.getCurrentRoom().nextMonster();
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
    public Status combatResolve()
    {
        Status output = Status.NEUTRAL;

        if(player.isAlive() && !currentMonster.isAlive())
        {
            output = Status.WIN;
        } 
        else if (!player.isAlive())
        {
            output = Status.LOSE;
        }
        
        player.cooldowns();

        //TODO Mana regen
        /*
         * More max mana
         * 1-2 per round
         * 3-4 when entering room
         */
        
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
    public String[] getRoomNames()
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
       int damage = player.getWeapon().attack();
       damageMonster(damage);
       return damage;
    }
    
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
            return player.getItems(idx).activate();
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

    
    public void giveLoot()
    {
        currentLoot = dungeon.getCurrentRoom().giveLoot();
    }
    
    public Loot getCurrentloot()
    {
        return currentLoot;
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
    
    public String[] getItemNames()
    {
        return player.getItemNames();
    }
    
    public String[] getItemDescs()
    {
        return player.getItemDescs();
    }

    public Player getPlayer()
    {
        return player;
    }
    
    public void nextRooms()
    {
        dungeon.nextRooms();
    }

    public String[] getItemUses()
    {
        String[] output = { "", "", ""};
        
        for(int i = 0; i < Player.INV_LENGTH; i++)
        {
            if(player.getItems(i) == null)
            {
                output[i] = null;
                continue;
            }
            
            if(player.getItems(i).isUlimited())
            {
                output[i] = "Unlimited";
            }
            else
            {
                output[i] = "" + player.getItems(i).getRemainingUses();
            }
        }
        
        return output;
    }
}