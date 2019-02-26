package objects.abstracts.usables;

import game.Completion;

public abstract class Item
{
    private int cooldownTime;
    
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
