package interfaces.usables;

import game.Completion;

public interface Item
{
    /*
     * TODO Item use limits and cooldowns
     * Item has x uses
     * Item is removed from inventory after all uses are expended
     */
    public Completion use();
    public void cooldown();
    
    public String getName();
    public String getDesc();
    
}
