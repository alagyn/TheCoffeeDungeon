package objects.usables.magics;

import game.Game;
import game.Player;
import game.loot.Completion;
import objects.abstracts.usables.cooldown.Magic;

public class FireBall extends Magic
{
    private static final int manaCost = 1;
    private static final int coolDownTime = 2;
    
    
    public FireBall()
    {
        super("Fire ball", "Stop that, you pyro", false, manaCost);
    }

    @Override
    public Completion activate()
    {
        Completion c;
        Player p = Game.getInst().getPlayer();
        if(available() && p.getMana() >= manaCost)
        {
            Game.damageMonster(10);
            addCooldown(coolDownTime);
            p.useMana(manaCost);
            c = new Completion(true, canHaveSecond());
        }
        else
        {
            c = new Completion(false, canHaveSecond());
        }
        
        return c;
    }

    @Override
    public boolean available()
    {
        return checkCoolDown();
    }

}
