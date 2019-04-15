package objects.abstracts.passives;


public class PassiveCompletion
{
    public final int damage, health;
    
    public PassiveCompletion(int damage, int health)
    {
        if(damage >= 0)
        {
            this.damage = damage;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Passive damage");
        }
        
        if(health >= 0)
        {
            this.health = health;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Passive health");
        }
    }

}
