package objects.abstracts.usables;

public abstract class Weapon extends Usable
{
    public Weapon(String name, String desc)
    {
        super(name, desc);
    }

    /**Returns an int damage value*/
    //TOMAKE Remember to have attacks add logs
    public abstract int attack();
}
