package objects.abstracts.usables;

import game.Completion;
import game.Game;
import game.Player;

public abstract class Magic
{
    private int manaCost;
    private int cooldownTime;
    
    public Magic(int manaCost)
    {
        if(manaCost > 0)
        {
            this.manaCost = manaCost;
        }
    }
    
    public int getCost()
    {
        return manaCost;
    }
    
    public boolean available()
    {
        return cooldownTime == 0;
    }
    
    
    public abstract Completion activate();
    public abstract String getName();
    public abstract String getDesc();

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
    
    public Player getPlayer()
    {
        return Game.getInst().getPlayer();
    }
}
