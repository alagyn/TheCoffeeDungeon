package gui.selection;

import javax.swing.*;

public class MagicGUI extends SelectionGUI
{
    //TOGUI MagicGUI
    public static final int WIDTH = 500, HEIGHT = 500, X = 20, Y = 20;
    
    private JLabel mana;
    
    public MagicGUI()
    {
        super();
        
        mana = new JLabel();
        
        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void setManaInfo(int amnt, int max)
    {
        mana.setText("Mana: " + amnt + "/" + max);
    }

    
}
