package game.loot;

public class Completion
{
    public final boolean canHaveSecond;
    public final boolean actionCompleted;
    public final int damage;
    
    public Completion(boolean actionCompleted, boolean canHaveSecond, int damage)
    {
        this.canHaveSecond = canHaveSecond;
        this.actionCompleted = actionCompleted;
        this.damage = damage;
    }
    
    public Completion(boolean actionCompleted, boolean canHaveSecond)
    {
        this(actionCompleted, canHaveSecond, 0);
    }
}
