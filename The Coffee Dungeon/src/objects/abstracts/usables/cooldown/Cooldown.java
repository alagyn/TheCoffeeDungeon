package objects.abstracts.usables.cooldown;

import objects.abstracts.usables.Usable;

public abstract class Cooldown extends Usable
{
    private int cooldownTime;
    private boolean canHaveSecond;
    
    public Cooldown(String name, String desc, LootType type, boolean canHaveSecond)
    {
        super(name, desc, type);
        
        this.canHaveSecond = canHaveSecond;
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
    
    public boolean checkCoolDown()
    {
        return cooldownTime <= 0;
    }
    
    public boolean canHaveSecond()
    {
        return canHaveSecond;
    }
    
}
