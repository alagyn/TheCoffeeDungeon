package items.weapons;

import interfaces.player.Weapon;
import java.util.Random;

public class Sword1 implements Weapon
{
    Random rand;

    public Sword1()
    {
        rand = new Random();
    }

    @Override
    public int attack()
    {
        return rand.nextInt(5) + 1;
    }

}
