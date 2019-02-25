package objects.usables.magics;

import abstracts.usables.Magic;
import game.Completion;
import game.player.Inventory;

public class FireBall extends Magic
{
    private static final int manaCost = 1;
    private static final int coolDownTime = 2;
    private static final boolean canHaveSecond = false;
    
    public FireBall()
    {
        super(manaCost);
    }

    @Override
    public Completion activate()
    {
        Completion c;
        
        if(available() && Inventory.getMana() >= manaCost)
        {
            //TOMAKE FireBall
            //Use magic
            addCooldown(coolDownTime);
            Inventory.useMana(manaCost);
            c = new Completion(canHaveSecond, true);
        }
        else
        {
            c = new Completion(canHaveSecond, false);
        }
        
        return c;
    }

    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public String getDesc()
    {
        return null;
    }

}
