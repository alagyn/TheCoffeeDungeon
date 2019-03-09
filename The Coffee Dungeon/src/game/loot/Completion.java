package game.loot;

public class Completion
{
    private boolean canHaveSecond;
    private boolean actionCompleted;
    
    public Completion(boolean actionCompleted, boolean canHaveSecond)
    {
        this.canHaveSecond = canHaveSecond;
        this.actionCompleted = actionCompleted;
    }
    
    public boolean canHaveSecond()
    {
        return canHaveSecond;
    }
    
    public boolean actionCompleted()
    {
        return actionCompleted;
    }
}
