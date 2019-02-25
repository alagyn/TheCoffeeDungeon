package objects.usables.weapons;

import java.util.Random;
import interfaces.usables.Weapon;

public class Sword1 implements Weapon
{
    //TOMAKE Sword1
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
