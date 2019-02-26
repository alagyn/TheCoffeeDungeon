package objects.monsters;

import abstracts.Monster;

public class Slime extends Monster
{
    private static final String name = "Slime";
    private static final int maxHealth = 10;
    
    public Slime()
    {
        super(name, maxHealth);
    }

    @Override
    public void attack()
    {
        //TOMAKE monsters
    }

}
