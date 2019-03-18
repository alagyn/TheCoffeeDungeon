package objects.events;

import game.loot.Loot;
import objects.abstracts.RoomEvent;
import objects.usables.items.HealPotion;

public class FindPotion extends RoomEvent
{
    public Loot startEvent()
    {
        createMessageBox("You found a Potion");
        return new Loot(new HealPotion());
    }
}
