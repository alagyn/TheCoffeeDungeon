package objects.abstracts.usables.cooldown;

public abstract class Magic extends Cooldown
{
    private int manaCost;
    private boolean canHaveSecond;
    
    public Magic(String name, String desc, boolean canHaveSecond, int manaCost)
    {
        super(name, desc);
        
        this.canHaveSecond = canHaveSecond;
        
        if(manaCost > 0)
        {
            this.manaCost = manaCost;
        }
    }
    
    public int getCost()
    {
        return manaCost;
    }
    
    public boolean canHaveSecond()
    {
        return canHaveSecond;
    }
}
