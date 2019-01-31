package rooms;

import game.player.Player;
import interfaces.Room;

public class Storage implements Room
{
    public static final String name = "Storage";
    public static final int MAX_GOLD = 10;
    
    public Storage()
    {    }

    @Override
    public void giveLoot(Player player)
    {
        /*TODO
        player.addPotion();
        player.addGold(Room.rand(MAX_GOLD));
        */
    }

    @Override
    public String getName()
    {
        return name;
    }

}
