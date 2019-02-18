package game;

import game.player.*;

public class Game
{
    /**
     * Number of rooms per floor
     */
    public static final int NUM_ROOMS = 3;

    private Player player;
    private Inventory inventory;
    
    private Dungeon dungeon;
    private int roomIdx;
    private Horde horde;
    //FIXME Remove monster instance from Game
    //Move to Horde
    //MAYBE Change Game to singleton
    private Monster monster;

    /**Default Constructor*/
    public Game()
    {
        player = new Player();
        inventory = new Inventory();
        horde = new Horde("data/mon.csv", -1);
        
        nextMonster();
        
        dungeon = new Dungeon(-1);
        
    }

    /**
     * Generates the next monster
     * @return the next random monster
     */
    public Monster nextMonster()
    {
        monster = horde.nextMonster();
        return monster;
    }

    /**
     * Sets the current room index
     * @param roomIdx The current room index
     */
    public void setIndex(int roomIdx)
    {
        this.roomIdx = roomIdx;
    }

    /**
     * Gives loot to the player
     */
    public void getLoot()
    {
        /*
         * TODO Item loot generation
         * Loot gen up to the room?
         * Item rand weights?
         */
        dungeon.giveLoot(roomIdx, player);
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
            monster.reset();
            getLoot();
            dungeon.nextRooms();
            monster.reset();
        } 
        else if (!player.isAlive())
        {
            output = -1;
            monster.reset();
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
}