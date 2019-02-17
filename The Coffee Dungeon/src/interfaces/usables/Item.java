package interfaces.usables;

import game.Monster;
import game.player.Player;

public interface Item
{
    /*
     * TODO Item use limits
     * Item has x uses
     * Item is removed from inventory after all uses are expended
     */
    public boolean use(Player player, Monster monster);
    
    public String getName();
    public String getDesc();
    public boolean hasSecondAction();
}
