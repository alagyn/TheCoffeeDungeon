package objects.abstracts.usables.cooldown;

public abstract class Item extends Cooldown
{
    private int remainingUses;
    private boolean unlimited;
    private boolean canHaveSecond;
    
    public Item(String name, String desc, int uses, boolean canHaveSecond)
    {
        super(name, desc, LootType.ITEM);
        
        if(uses <= 0)
        {
            unlimited = true;
            remainingUses = 0;
        }
        else
        {
            remainingUses = uses;
        }
        
        this.canHaveSecond = canHaveSecond;
    }
    
    /**
     * Checks if item is unlimited or has remaining uses
     * @return True if available for use
     */
    public boolean checkItemUse()
    {
        boolean output = false;
        
        if(unlimited || remainingUses > 0)
        {
            output = true;
        }
        
        return output;
    }
    
    public boolean isUlimited()
    {
        return unlimited;
    }
    
    public int getRemainingUses()
    {
        return remainingUses;
    }
    
    public boolean canHaveSecond()
    {
        return canHaveSecond;
    }
    
    public void useItem()
    {
        if(!unlimited)
        {
            remainingUses--;
        }
    }
    
}
