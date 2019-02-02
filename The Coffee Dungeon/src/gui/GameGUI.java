package gui;

import game.Action;

import javax.swing.*;
//import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//import java.util.*;

/**
 * The main Game GUI
 * @author benki
 *
 */
public class GameGUI extends JFrame implements ActionListener
{
    /**Reference to action*/
    private Action action;
    /**True if room text needs to be shown*/
    private boolean rooms;
    
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JTextField monHealth;
    private JTextField monName;
    private JTextArea log;
    private JScrollPane scroll;
    
    //TODO Inventory panel?
    
    private JPanel playStats;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    
    private JTextField playHealth;
    private JTextField playMana;
    //TODO Pot!
    private JTextField playPots;
    
    /**Background color*/
    private static final Color backcolor = new Color(220, 220, 220);
    /**Text color*/
    private static final Color textColor = new Color(20, 20, 20);
    /**Lines allowed in log*/
    private static final int LOG_LINES = 6;
    /**Window size and location*/
    private static final int X = 50, Y = 50, WIN_WIDTH = 600, WIN_HEIGHT = 500;
    
    private ArrayList<String> logText;
    
    public static void main(String[] args)
    {
        GameGUI gui = new GameGUI();
        gui.addLog("Hello");
        gui.addLog("World");
    }
    
    public GameGUI()
    {
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(X, Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        
        //Monster stats
        top = new JPanel();
        left = new JPanel();
        right = new JPanel();
        top.setLayout(new GridLayout(1, 2));
        left.setLayout(new GridLayout(4, 2 , 0, 10));
        right.setLayout(new BorderLayout());
        
        log = new JTextArea();
        scroll = new JScrollPane();
        monHealth = new JTextField();
        monName = new JTextField();
        
        monHealth.setEditable(false);
        monName.setEditable(false);
        
        left.add(new JPanel());
        left.add(new JPanel());
        left.add(new JLabel("Name:"));
        left.add(monName);
        
        left.add(new JLabel("Health:"));
        left.add(monHealth);
        
        right.add(log, BorderLayout.CENTER);
        
        log.setBackground(backcolor);
        log.setDisabledTextColor(textColor);
        
        log.setEditable(false);
        
        top.add(left);
        top.add(scroll);
        scroll.setViewportView(log);
        
        //Buttons
        playStats = new JPanel();
        playStats.setLayout(new GridLayout(3, 3, 20, 20));
        
        btnOne = new JButton();
        btnOne.addActionListener(this);
        btnTwo = new JButton();
        btnTwo.addActionListener(this);
        btnThree = new JButton();
        btnThree.addActionListener(this);
        
        //Stats
        playHealth = new JTextField();
        playHealth.setEditable(false);
        playMana = new JTextField();
        playMana.setEditable(false);
        playPots = new JTextField();
        playPots.setEditable(false);
        
        playStats.add(btnOne);
        playStats.add(new JLabel("Health:"));
        playStats.add(playHealth);
        
        playStats.add(btnTwo);
        playStats.add(new JLabel("Mana:"));
        playStats.add(playMana);
        
        playStats.add(btnThree);
        playStats.add(new JLabel("Potions:"));
        playStats.add(playPots);
        
        ////
        add(top);
        add(playStats);
        
        setVisible(true);
        ////
        
        rooms = true;
        
        try
        {
            action = new Action();
        }
        catch(IllegalArgumentException e)
        {
            critErrorMessage(e.getMessage());
        }
        
        logText = new ArrayList<String>();
        
        
        setPlayerStats();
        setRoomBtn();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnOne)
        {
            if(rooms)
            {
                newRoom(0);
            }
            else
            {
                int dmg = action.attack();
                addLog("You hit the " + action.getMonsterStats()[0] + " for " + dmg);
                playerDamage();
                resolve();
            }
        }
        else if(e.getSource() == btnTwo)
        {
            if(rooms)
            {
                newRoom(1);
            }
            else
            {
                //TODO Magic action
                //TODO Magic selection
                resolve();
            }
        }
        else if(e.getSource() == btnThree)
        {
            if(rooms)
            {
                newRoom(2);
            }
            else
            {
                //TODO Item action
                //TODO Item selection
                resolve();
            }
        }
    }
    
    private void playerDamage()
    {
        int dmg = action.monsterAttack();
        addLog("You take " + dmg + " points of damage");
    }
    
    /**
     * Sets the room at index to the current rooms
     * @param index The room index
     */
    private void newRoom(int index)
    {
        action.setIndex(index);
        rooms = false;
        setCombatBtn();
        setMonsterStats();
    }
    
    /**
     * Resolves a combat turn
     */
    private void resolve()
    {
        int status = action.combatResolve();
        
        System.out.println("Status " + status);
        
        switch(status)
        {
        case -1:
            //TODO lose
            gameOver();
            break;
            
        case 0:
            setMonsterStats();
            setPlayerStats();
            break;
            
        case 1:
            //TODO Item loot
            addLog("You defeated the " + action.getMonsterStats()[0]);
            action.nextMonster();
            action.getLoot();
            setRoomBtn();
            break;
        }
    }
    
    /**
     * sets the player stats text
     */
    public void setPlayerStats()
    {
        String[] stats = action.getPlayerStats();
        playHealth.setText(stats[0]);
        playMana.setText(stats[1]);
        //TODO playPots.setText(stats[2]);   
        
    }
    
    /**
     * Sets the monster stat text
     */
    public void setMonsterStats()
    {
        String[] stats = action.getMonsterStats();
        monName.setText(stats[0]);
        monHealth.setText(stats[1]);
    }
    
    /**
     * Sets the btn text to the next room names
     */
    private void setRoomBtn()
    {
        String[] names = action.getRooms();
        
        btnOne.setText(names[0]);
        btnTwo.setText(names[1]);
        btnThree.setText(names[2]);
    }
    
    /**
     * Sets the btn text to combat titles
     */
    private void setCombatBtn()
    {
        btnOne.setText("ATTACK");
        btnTwo.setText("MAGIC");
        btnThree.setText("ITEM");
    }
    
    /**
     * Adds a log entry to the combat log
     * @param entry The new entry
     */
    public void addLog(String entry)
    {
        if(logText.size() < LOG_LINES)
        {
            logText.add(entry);
        }
        else
        {
            logText.remove(0);
            logText.add(entry);
        }
        
        setLog();
        
    }
    
    /**
     * Pulls the log text from the stored data
     */
    public void setLog()
    {
        String output = "";
        
        for(int i = 0; i < logText.size(); i++)
        {
            output += logText.get(i) + "\n";
        }
        
        setLog(output);
    }

    /**
     * Sets the entire log to the text
     * @param logText the text
     */
    public void setLog(String logText)
    {
        log.setText(logText);
    }
    
    /**
     * Shows a message and then guits the program
     * @param message the message
     */
    public void critErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
        System.exit(1);
    }

    private void gameOver()
    {
        int i = JOptionPane.showOptionDialog(null, "Game Over\nNew Game?", "Game", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, 
                null, null, null);
        
        if(i == JOptionPane.YES_OPTION)
        {
            //TODO New game
        }
        else
        {
            System.exit(1);
        }
    }
}