public class Room
{
    public static final int LOOT_AMNT = 4;
    
    private String name;
    private double[] loot;
    private int gold;
    
    public Room(String name, int gold, double pot, double wep, double arm, double mag)
    {
        this.name = name;
        
        if(gold < 0)
        {
            throw new IllegalArgumentException("Invalid gold amount");
        }
        this.gold = gold;
        
        if(pot + wep + arm + mag > 1)
        {
            throw new IllegalArgumentException("Exceeds max weight");
        }
        else if(pot < 0 || wep < 0 || arm < 0 || mag < 0)
        {
            throw new IllegalArgumentException("Weight below zero");
        }
        
        loot = new double[LOOT_AMNT];
        
        loot[0] = pot;
        loot[1] = wep;
        loot[2] = arm;
        loot[3] = mag;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getGold()
    {
        return gold;
    }
    
    public double[] getLoot()
    {
        return loot;
    }
}