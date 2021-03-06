package objects.concretes.rooms;

import game.loot.Loot;
import objects.abstracts.Monster;
import objects.abstracts.Room;
import objects.abstracts.RoomEvent;
import objects.concretes.events.FindPotion;
import objects.concretes.monsters.*;

import objects.concretes.usables.weapons.Sword1;

public class Armory extends Room
{
    public static final String name = "Armory";
    public static final String desc = "Sharp things inside";
    public static final int MAX_GOLD = 10;

    private static final Monster[] monsters =
    { new Slime() };
    private static final double[] weights =
    { 1 };

    public Armory()
    {
        super(name, desc, monsters, weights, new RoomEvent[]
        { new FindPotion() }, new double[]
        { 1 });
    }

    @Override
    public Loot giveLoot()
    {
        /*
         * TOMAKE Armory player.addArmor(); player.addGold(Room.rand(MAX_GOLD));
         */
        return new Loot(new Sword1());
    }
}
