package game;

import game.loot.Completion;
import game.loot.Loot;
import game.player.Player;
import game.player.PlayerStatus;
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
        player.regenMana();
        
        /* TOMAKE Refactor mana scaling
         * More max mana
         * 1-2 per round
         * 3-4 when entering room
         */
        
        return output;
    }

    /**
     * Gets the current player status
     * @return a PlayerStatus of the current stats 
     */
    public PlayerStatus getPlayerStatus()
    {
        return new PlayerStatus(player.getHealth(), player.getMana(), player.getArmor());
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
            return player.useMagic(idx);
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
            return player.getItem(idx).activate();
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
    
    public void resetCurrentMonster()
    {
        currentMonster.reset();
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
            if(player.getItem(i) == null)
            {
                output[i] = null;
                continue;
            }
            
            if(player.getItem(i).isUlimited())
            {
                output[i] = "Unlimited";
            }
            else
            {
                output[i] = player.getItem(i).getRemainingUses() + " remaining uses";
            }
        }
        
        return output;
    }

}