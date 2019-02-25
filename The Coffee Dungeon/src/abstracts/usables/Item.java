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
    
    public void cooldown()
    {
        if(cooldownTime > 0)
        {
            cooldownTime--;
        }
    }
    
    public void addCooldown(int i)
    {
        if(i > 0)
        {
            cooldownTime += i;
        }
        else
        {
            throw new IllegalArgumentException("Invalid cooldown");
        }
    }
    
    public boolean available()
    {
        return cooldownTime == 0;
    }
    
    public abstract Completion use();
    
    public abstract String getName();
    public abstract String getDesc();
    
}
