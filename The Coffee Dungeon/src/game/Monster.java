package game;

public class Monster
{
    private int maxHealth;
    private int health;
    private int[] atk;
    
    private double weight;
    
    private String name;
    
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
    
    public int getHealth()
    {
        return health;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int[] getAtk()
    {
        return atk;
    }
    
    public void damage(int damage)
    {
        health -= damage;
    }
    
    public boolean isAlive()
    {
        return health > 0;
    }
    
    public double getWeight()
    {
        return weight;
    }
    
    public void reset()
    {
        health = maxHealth;
    }
}