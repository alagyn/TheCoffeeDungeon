package objects.monsters;

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
        Game.getInst().getPlayer().damage(2);
        GameGUI.getInst().addLog("Sliiime");
    }

}
