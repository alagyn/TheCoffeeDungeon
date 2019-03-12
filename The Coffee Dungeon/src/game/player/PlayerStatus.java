package game.player;

public class PlayerStatus
{
    public final int health, mana, armor;
    
    public PlayerStatus(int health, int mana, int armor)
    {
        if(health >= 0)
        {
            this.health = health;
        }
        else
        {
            throw new IllegalArgumentException("Invalid PlayerStatus health");
        }
        
        if(mana >= 0)
        {
            this.mana = mana;
        }
        else
        {
            throw new IllegalArgumentException("Invalid PlayerStatus mana");
        }
        
        if(armor >= 0)
        {
            this.armor = armor;
        }
        else
        {
            throw new IllegalArgumentException("Invalid PlayeStatus armor");
        }
    }
}
