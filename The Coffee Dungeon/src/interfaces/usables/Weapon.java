package interfaces.usables;

import game.player.Player;

public interface Weapon
{
    /**Returns an int damage value*/
    public int attack(Player player);
}
