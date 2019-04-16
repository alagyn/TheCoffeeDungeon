package game.loot;

public class Completion
{
    public final boolean canHaveSecond;
    public final boolean actionCompleted;
    
    public Completion(boolean actionCompleted, boolean canHaveSecond)
    {
        this.canHaveSecond = canHaveSecond;
        this.actionCompleted = actionCompleted;
    }
    
}
