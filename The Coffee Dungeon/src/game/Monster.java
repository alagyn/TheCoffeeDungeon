package game;

import game.player.*;

public abstract class Monster
{
    /*
     * FIXME Refactor monster data structure
     * monsters with abilities
     */
    
    private int maxHealth;
    private int health;
    private int[] atk;
    
    private double weight;
    
    private String name;
    
    /**
     * Default constructor
     * @param name Monster name
     * @param weight Random generation weight
     * @param health Monster health
     * @param minAtk Minimum attack
     * @param maxAtk maximum attack
     */
    public Monster(String name, double weight, int health, int minAtk, int maxAtk)
    {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        
        this.atk = new int[2];
        this.atk[0] = minAtk;
        this.atk[1] = maxAtk;
        
        this.weight = weight;
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
     * Returns an array for the attack values
     * @return an array of 0:minAtk, 1:maxAtk
     */
    public int[] getAtk()
    {
        return atk;
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
     * Returns the random gen weight
     * @return the random gen weight
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * resets the monster's health to maximum
     */
    public void reset()
    {
        health = maxHealth;
    }

    public abstract void attack(Player player);
}