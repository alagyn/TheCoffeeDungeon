package objects.abstracts.passives;

public abstract class PassiveItem
{
    public enum PassiveType
    {
        ATTACK,
        MAGIC,
        ROUND
    }
    
    public final PassiveType type;
    
    public abstract void activate();
    
    public PassiveItem(PassiveType type)
    {
        if(type != null)
        {
            this.type = type;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
}
