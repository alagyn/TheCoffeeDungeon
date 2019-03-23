package objects.usables.weapons;

import game.loot.Completion;
import game.util.RandUtil;
import objects.abstracts.usables.weapon.Weapon;

public class Sword1 extends Weapon
{

    public Sword1()
    {
        super("Sword", "Swingy sword");
    }

    @Override
    public int attack()
    {
        return RandUtil.integerRand(5, 1);
    }

    @Override
    public Completion activate()
    {
        return null;
    }

    @Override
    public boolean available()
    {
        return false;
    }

}
