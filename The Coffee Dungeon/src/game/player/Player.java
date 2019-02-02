package game.player;

public class Player
{   
    /**
     * Beginning stats
     */
    public static final int 
        START_HEALTH = 20,
        START_MANA = 3;
    
    private int 
        maxHealth, health, 
        mana, maxMana;
    
    /**
     * Defualt constructor
     */
    public Player()
    {
        maxHealth = START_HEALTH;
        health = START_HEALTH;
        
        maxMana = START_MANA;
        mana = START_MANA;
    }
    
    /**
     * Damages the player a set amount
     * @param damage The incoming damage
     */
    public void damage(int damage)
    {
        health -= damage;
    }
    
    /**
     * Checks if the player's health is above zero
     * @return True is health is above zero
     */
    public boolean isAlive()
    {
        return health > 0;
    }
    
    /**
     * Returns the current mana amount
     * @return the current mana amount
     */
    public int getMana()
    {
        return mana;
    }
    
    /**
     * Returns the current health amount
     * @return the current health amount
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Adds to the current mana
     * @param mana the mana to add
     */
    public void addMana(int mana)
    {
        if(mana >= 0)
        {
            this.mana += mana;
            if(this.mana > maxMana)
            {
                this.mana = maxMana;
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid mana amount");
        }
    }
    
    /**
     * Adds to the current health
     * @param health the health to add
     */
    public void addHealth(int health)
    {
        if(health >= 0)
        {
            this.health += health;
            if(this.health > maxHealth)
            {
                this.health = maxHealth;
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid health");
        }
    }
}