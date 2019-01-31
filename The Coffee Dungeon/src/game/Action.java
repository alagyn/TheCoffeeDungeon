package game;
//import gui.*;

public class Action
{
    public static final int NUM_ROOMS = 3;

    private Player player;

    private Dungeon dungeon;
    private int index;
    private Horde horde;
    private Monster monster;

    public Action()
    {
        player = new Player();

        try
        {
            horde = new Horde("data/mon.csv", -1);
        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Critical error, file not found");
        }
        
        dungeon = new Dungeon(-1);
        
    }

    public Monster nextMonster()
    {
        monster = horde.nextMonster();
        return monster;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public void getLoot()
    {
        dungeon.giveLoot(index, player);
    }

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
            nextMonster();
        } 
        else if (!player.isAlive())
        {
            output = -1;
            monster.reset();
        }

        return output;
    }

    public String[] getPlayerStats()
    {
        String[] output = new String[3];
        output[0] = "" + player.getHealth();
        output[1] = "" + player.getMana();
        output[2] = "" + player.getPotions();

        return output;
    }

    public String[] getMonsterStats()
    {
        String[] output = new String[2];
        output[0] = "" + monster.getName();
        output[1] = "" + monster.getHealth();

        return output;
    }

    public String[] getRooms()
    {
        return dungeon.getRoomNames();
    }

    public void attack()
    {
        //TODO action 1
    }
    
    public void magic()
    {
        //TODO action 2
    }
    
    public void item()
    {
        //TODO action 3
    }
}