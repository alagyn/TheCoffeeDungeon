package usables.items;

import game.Monster;
import game.player.*;
import interfaces.usables.Item;

public class HealPotion implements Item
{
    //TOMAKE HealPotion
    @Override
    public boolean use(Player player, Monster monster)
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
