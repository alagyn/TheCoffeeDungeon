package objects.usables.weapons;

import java.util.Random;

import abstracts.usables.Weapon;

public class Sword1 extends Weapon
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
