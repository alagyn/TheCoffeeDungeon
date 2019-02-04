package game;

import game.player.*;

public class Action
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
    private Monster monster;

    /**Default Constructor*/
    public Action()
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
        //TODO Item loot generation
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
        output[1] = "" + player.getMana();

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
     * Activates a primary attack on a monster and returns the damage done
     * @return The damage done
     */
    public int attack()
    {
       int damage = inventory.getWeapon().attack();
       monster.damage(damage);
       return damage;
    }
    
    /**
     * 
     */
    public void magic()
    {
        //TODO Magic action
    }
    
    /**
     * 
     */
    public void item(int idx)
    {
        //TODO Item action
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
}