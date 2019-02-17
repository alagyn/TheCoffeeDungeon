package interfaces.usables;

import game.Monster;
import game.player.Player;

public interface Magic
{
    public boolean activate(Player player, Monster monster);
    public String getName();
    public String getDesc();
    public boolean hasSecondAction();
}
