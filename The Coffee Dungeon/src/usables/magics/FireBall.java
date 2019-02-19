package usables.magics;

import game.Monster;
import game.player.*;
import interfaces.usables.Magic;

public class FireBall implements Magic
{
    //TOMAKE FireBall
    
    @Override
    public boolean activate(Player player, Monster monster)
    {
        return false;
    }

    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public String getDesc()
    {
        return null;
    }

    @Override
    public boolean hasSecondAction()
    {
        return false;
    }

}
