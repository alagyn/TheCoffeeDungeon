package objects.abstracts.usables.cooldown;

import objects.abstracts.usables.Usable;

public abstract class Cooldown extends Usable
{
    private int cooldownTime;
    
    public Cooldown(String name, String desc)
    {
        super(name, desc);
    }

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
    
    public int getCooldown()
    {
        return cooldownTime;
    }
    
}
