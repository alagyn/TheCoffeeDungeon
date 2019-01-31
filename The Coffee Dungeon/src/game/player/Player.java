package game.player;

public class Player
{   
    public static final int 
        START_HEALTH = 20,
        START_MANA = 3;
    
    private int 
        maxHealth, health, 
        mana, maxMana;
    
    public Player()
    {
        maxHealth = START_HEALTH;
        health = START_HEALTH;
        
        maxMana = START_MANA;
        mana = START_MANA;
    }
    
    public void damage(int damage)
    {
        health -= damage;
    }
    
    public boolean isAlive()
    {
        return health > 0;
    }
    
    public int getMana()
    {
        return mana;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public void addMana(int mana)
    {
        this.mana += mana;
        if(this.mana > maxMana)
        {
            this.mana = maxMana;
        }
    }
    
    public void addHealth(int health)
    {
        this.health += health;
        if(this.health > maxHealth)
        {
            this.health = maxHealth;
        }
    }
}