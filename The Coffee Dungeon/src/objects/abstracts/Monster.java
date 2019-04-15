package objects.abstracts;

public abstract class Monster
{
    private int maxHealth;
    private int health;
    
    private String name;
    
    /**
     * Default constructor
     * @param name Monster name
     * @param health Monster health
     */
    public Monster(String name, int health)
    {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
    }
    
    /**
     * Returns the current health
     * @return the current health
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Returns the monster's name
     * @return the monster's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Damages the monster for a set amount
     * @param damage The incoming damage
     */
    public void damage(int damage)
    {
        health -= damage;
    }
    
    /**
     * Checks if the current health is above zero
     * @return True if health is above zero
     */
    public boolean isAlive()
    {
        return health > 0;
    }
    
    /**
     * resets the monster's health to maximum
     */
    public void reset()
    {
        health = maxHealth;
    }

    public abstract int attack();
}