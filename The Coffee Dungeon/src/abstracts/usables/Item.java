package abstracts.usables;

import game.Completion;

public abstract class Item
{
    private int cooldownTime;
    /*
     * TODO Item use limits and cooldowns
     * Item has x uses
     * Item is removed from inventory after all uses are expended
     */
    public abstract Completion use();
    public abstract void cooldown();
    
    public abstract String getName();
    public abstract String getDesc();
    
}
