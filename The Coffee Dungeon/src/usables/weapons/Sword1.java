package usables.weapons;

import java.util.Random;
import game.player.*;
import interfaces.usables.Weapon;

public class Sword1 implements Weapon
{
    Random rand;

    public Sword1()
    {
        rand = new Random();
    }

    @Override
    public int attack(Player player)
    {
        return rand.nextInt(5) + 1;
    }

}
