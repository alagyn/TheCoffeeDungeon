package gui.selection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.player.Inventory;


public abstract class SelectionGUI extends JFrame implements ActionListener
{   
    private int index;
    private boolean action;
    
    public static final int SPACE = 3;
    
    private JButton[] btns;
    private JButton back;
    
    private JLabel[] labels;
    
    public SelectionGUI()
    {
        index = 0;
        action = false;
        
        btns = new JButton[SPACE];
        
        for(int i = 0; i < btns.length; i++)
        {
            btns[i].addActionListener(this);
        }
        
        back = new JButton("Back");
        back.addActionListener(this);
        
        labels = new JLabel[SPACE];
    }
    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(back))
        {
            index = -1;
        }
        else
        {
            for(int i = 0; i < btns.length; i++)
            {
                if(e.getSource().equals(btns[i]))
                {
                    index = i;
                    break;
                }
            }
        }
        
        action = true;
    }
    
    public void setLabels(String[] info)
    {
        if(info.length <= SPACE)
        {
            for(int i = 0; i < info.length; i++)
            {
                labels[i].setText(info[i]);
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    
    public boolean hasSelected()
    {
        return action;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public abstract void setUp(Inventory inventory);
}
