package game;

public class Player
{   
    public static final int 
        START_HEALTH = 20,
        START_MANA = 3,
        START_BLK = 2,
        START_POT = 2;
        
    public static final int[]
        START_ATK = {10, 15},
        START_HEAL = {1, 10},
        START_MAGIC = {5, 15};
    
    private int 
        maxHealth, health, 
        mana, maxMana,
        blk;
        
    private int[]
        atk, heal, magic;
        
    private int gold, potions;
    
    public Player()
    {
        maxHealth = START_HEALTH;
        health = START_HEALTH;
        
        maxMana = START_MANA;
        mana = START_MANA;
        
        atk = START_ATK;
        heal = START_HEAL;
        magic = START_MAGIC;
        
        blk = START_BLK;
        
        gold = 0;
        potions = START_POT;
    }
    
    public void damage(int damage)
    {
        health -= damage;
    }
    
    public boolean isAlive()
    {
        return health > 0;
    }
    
    public int[] getAtk()
    {
        return atk;
    }
    
    public int getBlk()
    {
        return blk;
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
    
    public void addPotion()
    {
        potions++;
    }
    
    public void addWeapon()
    {
        atk[0]++;
        atk[1]++;
    }
    
    public void addArmor()
    {
        blk++;
    }
    
    public void addMagic()
    {
        magic[0]++;
        magic[1]++;
        heal[0]++;
        heal[1]++;
    }
    
    public void addGold(int gold)
    {
        this.gold += gold;
    }
    
    public int getPotions()
    {
        return potions;
    }
    
    public int getGold()
    {
        return gold;
    }
}