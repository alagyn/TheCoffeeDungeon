package interfaces.player;

import game.player.Player;

public interface Item
{
    /*
     * TODO Item use limits
     * Item has x uses
     * Item is removed from inventory after all uses are expended
     */
    public boolean use(Player player);
}
