package game;

public class Completion
{
    private boolean canHaveSecond;
    private boolean actionCompleted;
    
    public Completion(boolean canHaveSecond, boolean actionCompleted)
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
