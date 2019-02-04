package gui;

import game.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//import java.util.*;
//import javax.swing.event.*;

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
    private boolean magics;
    private boolean items;
    private boolean select;
    
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JTextField monHealth;
    private JTextField monName;
    private JTextArea log;
    private JScrollPane scroll;
    
    //TOGUI Inventory panel?
    
    private JPanel playStats;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    
    private JTextField playHealth;
    private JTextField playMana;
    //MAYBE Pot!
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
        gui.toString();
    }
    
    /**
     * Default constructor
     */
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
        magics = false;
        items = false;
        select = false;
        
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
    
    /**
     * Called when buttons are activated
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        boolean one = false, two = false, three = false;
        
        if(e.getSource().equals(btnOne))
        {
            one = true;
        }
        else if(e.getSource().equals(btnTwo))
        {
            two = true;
        }
        else if(e.getSource().equals(btnThree))
        {
            three = true;
        }
            
        //Actions
        if(select)
        {
            if(one)
            {
                int dmg = action.attack();
                addLog("You hit the " + action.getMonsterStats()[0] + " for " + dmg);
                playerDamage();
                resolve();
            }
            else if(two)
            {
                //TOGUI magic selection
            }
            else if(three)
            {
                //TOGUI item selection
            }
        }
        else if(rooms)
        {
            if(one)
            {
                newRoom(0);
            }
            else if(two)
            {
                newRoom(1);
            }
            else if(three)
            {
                newRoom(2);
            }
                
            select = true;
        }
        else if(magics)
        {
            if(one)
            {
                
            }
            else if(two)
            {
                
            }
            else if(three)
            {
                
            }
        }
        else if(items)
        {
            if(one)
            {
                
            }
            else if(two)
            {
                
            }
            else if(three)
            {
                
            }
        }
    }
    
    /**Generates player damage and adds a log*/
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
        
        setMonsterStats();
        setPlayerStats();
        
        switch(status)
        {
        case -1:
            gameOver();
            break;
            
        case 0:
            //Next Round, no action required
            break;
            
        case 1:
            action.getLoot();
            addLog("You defeated the " + action.getMonsterStats()[0]);
            action.nextMonster();
            action.getLoot();
            setRoomBtn();
            break;
        }
        
        setMonsterStats();
        setPlayerStats();
    }
    
    /**
     * sets the player stats text
     */
    private void setPlayerStats()
    {
        String[] stats = action.getPlayerStats();
        playHealth.setText(stats[0]);
        playMana.setText(stats[1]);
        /*
         * TOGUI refactor potion display 
         * playPots.setText(stats[2]);   
         */
        
    }
    
    /**
     * Sets the monster stats text
     */
    private void setMonsterStats()
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
    private void addLog(String entry)
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
    private void setLog()
    {
        String output = "";
        
        for(int i = 0; i < logText.size(); i++)
        {
            output += logText.get(i) + "\n";
        }
        
        setLog(output);
    }

    /**
     * Resets the log when true is passed
     * @param x True to reset log
     */
    private void resetLog(boolean x)
    {
        if(x)
        {
            setLog("");
            logText.clear();
        }
    }
    
    /**
     * Sets the entire log to the text
     * @param logText the text
     */
    private void setLog(String logText)
    {
        log.setText(logText);
    }
    
    /**
     * Shows a message and then quits the program
     * @param message the message
     */
    private void critErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
        System.exit(1);
    }

    /**
     * Shows a game over message box
     */
    private void gameOver()
    {
        int i = JOptionPane.showOptionDialog(null, "Game Over\nNew Game?", "Game", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, 
                null, null, null);
        
        if(i == JOptionPane.YES_OPTION)
        {
            newGame();
        }
        else
        {
            System.exit(1);
        }
    }
    
    /**
     * Resets the game to beginning state
     */
    private void newGame()
    {
        action.newGame();
        resetLog(true);
        setMonsterStats();
        setPlayerStats();
    }
}