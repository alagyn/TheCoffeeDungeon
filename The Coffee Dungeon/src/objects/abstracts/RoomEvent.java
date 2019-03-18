package objects.abstracts;

import javax.swing.JOptionPane;

import game.loot.Loot;

public abstract class RoomEvent
{
    public abstract Loot startEvent();
    
    public void createMessageBox(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }
}
