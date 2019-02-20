package game.player;

public class Player
{   
    private static Player instance = new Player();
    
    /**
     * Beginning stats
     */
    public static final int 
        START_HEALTH = 20;
        
    
    private int 
        maxHealth, health; 
      
    /**
     * Defualt constructor
     */
    private Player()
    {
        maxHealth = START_HEALTH;
        health = START_HEALTH;
    }
    
    /**
     * Damages the player a set amount
     * @param damage The incoming damage
     */
    public static void damage(int damage)
    {
        instance.health -= damage;
    }
    
    /**
     * Checks if the player's health is above zero
     * @return True is health is above zero
     */
    public static boolean isAlive()
    {
        return instance.health > 0;
    }
    
    /**
     * Returns the current health amount
     * @return the current health amount
     */
    public static int getHealth()
    {
        return instance.health;
    }
    
    /**
     * Adds to the current health
     * @param health the health to add
     */
    public static void addHealth(int health)
    {
        if(health >= 0)
        {
            instance.health += health;
            if(instance.health > instance.maxHealth)
            {
                instance.health = instance.maxHealth;
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid health");
        }
    }
}