package objects.abstracts.usables.weapon;

import objects.abstracts.usables.Usable;

public abstract class Weapon extends Usable
{
    public Weapon(String name, String desc)
    {
        super(name, desc, LootType.WEAPON);
    }

    /**Returns an int damage value*/
    //TOMAKE Remember to have attacks add logs
    public abstract int attack();
}
