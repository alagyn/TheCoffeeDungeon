package interfaces.usables;

public interface Item
{
    /*
     * TODO Item use limits
     * Item has x uses
     * Item is removed from inventory after all uses are expended
     */
    public boolean use();
    
    public String getName();
    public String getDesc();
    public boolean hasSecondAction();
}
