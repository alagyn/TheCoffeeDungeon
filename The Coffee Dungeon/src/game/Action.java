package game;
//import gui.*;

public class Action
{
    public static final int NUM_ROOMS = 3;

    private Player player;

    private Dungeon dungeon;
    private String[] rooms;
    private int index;
    private Horde horde;
    private Monster monster;

    public Action()
    {
        player = new Player();
        rooms = new String[NUM_ROOMS];

        try
        {
            horde = new Horde("data/mon.csv", -1);
        } catch (IllegalArgumentException e)
        {
            //
        }

        try
        {
            dungeon = new Dungeon(-1);
        } catch (IllegalArgumentException e)
        {
            //
        }
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
            rooms = dungeon.nextRooms();
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
        return rooms;
    }

}