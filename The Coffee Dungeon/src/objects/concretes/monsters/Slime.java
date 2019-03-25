package objects.concretes.monsters;

import game.Game;
import gui.GameGUI;
import objects.abstracts.Monster;

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
        Game.getInst().getPlayer().damageWithArmor(5);
        GameGUI.getInst().addLog("Sliiime");
    }

}
