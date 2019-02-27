package objects.usables.weapons;

import objects.abstracts.usables.Weapon;
import java.util.Random;

import game.Completion;

public class Sword1 extends Weapon
{
    //TOMAKE Sword1
    Random rand;

    public Sword1()
    {
        super("Sword", "Swingy sword");
        rand = new Random();
    }

    @Override
    public int attack()
    {
        return rand.nextInt(5) + 1;
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
