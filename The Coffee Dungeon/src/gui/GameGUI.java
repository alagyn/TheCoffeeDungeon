package gui;

import game.Completion;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The main Game GUI
 * @author alagyn *
 */
public class GameGUI extends JFrame implements ActionListener
{
    //TOGUI Improve Main GUI
    
    private static GameGUI instance = new GameGUI();
    
    /**True if an action has already happened*/
    private boolean secondAction;
    private boolean canHaveSecond;
    
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JTextField monHealth;
    private JTextField monName;
    private JTextArea log;
    private JScrollPane scroll;
    
    private ItemGUI itemGUI;
    private MagicGUI magicGUI;
    private RoomGUI roomGUI;
    
    private JPanel playStats;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    
    private JTextField playHealth;
    private JTextField playMana;
    
    /**Background color*/
    private static final Color backcolor = new Color(220, 220, 220);
    /**Text color*/
    private static final Color textColor = new Color(20, 20, 20);
    /**Lines allowed in log*/
    private static final int LOG_LINES = 6;
    /**Window size and location*/
    private static final int X = 50, Y = 50, WIN_WIDTH = 600, WIN_HEIGHT = 500;
    
    private ArrayList<String> logText;
    
    /**
     * Default constructor
     */
    private GameGUI()
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
        
        btnOne = new JButton("Attack");
        btnOne.addActionListener(this);
        btnTwo = new JButton("Magic");
        btnTwo.addActionListener(this);
        btnThree = new JButton("Items");
        btnThree.addActionListener(this);
        
        //Stats
        playHealth = new JTextField();
        playHealth.setEditable(false);
        playMana = new JTextField();
        playMana.setEditable(false);
        
        playStats.add(btnOne);
        playStats.add(new JLabel("Health:"));
        playStats.add(playHealth);
        
        playStats.add(btnTwo);
        playStats.add(new JLabel("Mana:"));
        playStats.add(playMana);
        
        playStats.add(btnThree);
        playStats.add(new JLabel("Potions:"));
        
        ////
        add(top);
        add(playStats);
        
        setVisible(true);
        ////
        
        secondAction = false;
        
        logText = new ArrayList<String>();
        
        
        itemGUI = new ItemGUI();
        magicGUI = new MagicGUI();
        roomGUI = new RoomGUI();
        
        newGame();
    }
    
    public static void main(String[] args)
    {
        
    }
    
    /**
     * Returns the singleton instance
     * @return
     */
    public GameGUI getInst()
    {
        return instance;
    }
    
    /**
     * Called when buttons are activated
     */
    @Override
    public void actionPerformed(ActionEvent event)
    {
        boolean attack = false, magic = false, item = false;
        canHaveSecond = false;
        
        if(event.getSource().equals(btnOne))
        {
            attack = true;
        }
        else if(event.getSource().equals(btnTwo))
        {
            magic = true;
        }
        else if(event.getSource().equals(btnThree))
        {
            item = true;
        }
        
        if(attack)
        {
            int dmg = Game.getInst().attack();
            addLog("You hit the " + Game.getCurrentMonsterName() + " for " + dmg);
            playerDamage();
            canHaveSecond = false;
            endRound();
        }
        else if(magic)
        {
            startGUI(magicGUI);
        }
        else if(item)
        {
            startGUI(itemGUI);
        }
    }
    
    private void endRound()
    {
        if(secondAction) 
        {
            secondAction = false;
        }
        else
        {
            secondAction = canHaveSecond;
        }
        
        if(!secondAction)
        {
          resolve();
        }
    }
    
    /**Generates player damage and adds a log*/
    private void playerDamage()
    {
        Game.monsterAttack();
    }
    
    /**
     * Sets the room at index to the current rooms
     * @param index The room index
     */
    private void newRoom(int index)
    {
        if(index >= 0)
        {
            Game.getInst().setCurrentRoomIndex(index);
            Game.getInst().nextMonster();
            setMonsterStats();
        }
        else
        {
            throw new IllegalArgumentException("Invalid index");
        }
        
    }
    
    /**
     * Resolves a combat turn
     */
    private void resolve()
    {
        int status = Game.getInst().combatResolve();
        
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
            addLog("You defeated the " + Game.getCurrentMonsterName());
            //TODO Loot GUI
            Game.getInst().giveLoot();
            Game.resetCurrentMonster();
            
            startGUI(roomGUI);
            /*
             * MAYBE Allow spells/items between rooms
             * Be able to use healing actions without a monster
             */
            
            //startGUI(roomGUI);
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
        String[] stats = Game.getInst().getPlayerStats();
        playHealth.setText(stats[0]);
        playMana.setText(stats[1]);
    }
    
    /**
     * Sets the monster stats text
     */
    private void setMonsterStats()
    {
        
        monName.setText(Game.getCurrentMonsterName());
        monHealth.setText(Game.getCurrentMonsterHealth());
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
    public static void critErrorMessage(String message)
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
        Game.getInst().newGame();
        
        resetLog(true);
        //TODO Move stat calls
        //setPlayerStats();
        //setMonsterStats();
        //TODO New game menu
        System.out.print("Starting roomGUI");
        startGUI(roomGUI);
    }

    private void startGUI(SelectionGUI gui)
    {
        enableBtns(false);
        gui.setUp();   
    }
    
    private void enableBtns(boolean b)
    {
        btnOne.setEnabled(b);
        btnTwo.setEnabled(b);
        btnThree.setEnabled(b);
    }
    
    private abstract class SelectionGUI extends JFrame implements ActionListener
    {   
        private int index;
       
        public static final int SPACE = 3;
        
        private static final int ERROR = -2, BACK = -1;
        
        public JButton[] btns;
        public JButton back;
        
        public JLabel[] labels;
        
        public SelectionGUI(String title)
        {
            super(title);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            index = 0;
            
            btns = new JButton[SPACE];
            
            for(int i = 0; i < btns.length; i++)
            {
                btns[i] = new JButton();
            }
            
            for(int i = 0; i < btns.length; i++)
            {
                btns[i].addActionListener(this);
            }
            
            back = new JButton("Back");
            back.addActionListener(this);
            
            labels = new JLabel[SPACE];
            
            for(int i = 0; i < labels.length; i++)
            {
                labels[i] = new JLabel();
            }
            
            setVisible(false);
        }
        

        @Override
        public void actionPerformed(ActionEvent e)
        {
            index = ERROR;
            if(e.getSource().equals(back))
            {
                index = BACK;
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
            
            if(index != ERROR)
            {
                activate(index);
            }
            
        }
        
        public abstract void activate(int i);
        
        public abstract void setUp();
        
        public void setDesc(String[] info)
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
        
        public void setBtnLabels(String[] info)
        {
            if(info.length <= SPACE)
            {
                for(int i = 0; i < info.length; i++)
                {
                    btns[i].setText(info[i]);
                }
            }
            else
            {
                throw new IllegalArgumentException();
            }
        }
        
    }

    private class ItemGUI extends SelectionGUI
    {
    
        //TOGUI ItemGUI
        public ItemGUI()
        {
            super("Items");
        }
    
        @Override
        public void setUp()
        {
            
            setBtnLabels(Game.getInst().getMagicNames());
            setDesc(Game.getInst().getMagicDescs());
            
            //TODO Item use label
            setVisible(true);
        }

        public void activate(int i)
        {
            if(i >= 0)
            {
                Completion c = Game.getInst().item(i);
                if(c.actionCompleted())
                {
                    canHaveSecond = c.canHaveSecond();
                    endRound();
                }
            }
            
            enableBtns(true);
            setVisible(false);
        }
        
    }

    private class MagicGUI extends SelectionGUI
    {
        //TOGUI MagicGUI
        public static final int WIDTH = 500, HEIGHT = 500, X = 20, Y = 20;
        
        private JLabel mana;
        
        public MagicGUI()
        {
            super("Magics");
            
            mana = new JLabel();
            
            setSize(WIDTH, HEIGHT);
            setLocation(X, Y);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        
        public void setManaInfo(int amnt, int max)
        {
            mana.setText("Mana: " + amnt + "/" + max);
        }

        @Override
        public void setUp()
        {
            //TODO MagicGUI setUp()
            setManaInfo(0, 0);
            /*
            setManaInfo(Player.getMana(), Player.getMaxMana());
            setBtnLabels(Player.getItemNames());
            setDesc(Player.getItemDescs());
            */
            setVisible(true);
        }

        
        @Override
        public void activate(int i)
        {
            if(i >= 0)
            {
                Completion c = Game.getInst().magic(i);
                if(c.actionCompleted())
                {
                    canHaveSecond = c.canHaveSecond();
                    endRound();
                }
            }
            
            enableBtns(true);
            setVisible(false);
        }        
    }

    private class RoomGUI extends SelectionGUI
    {
        //TOGUI RoomGUI
        
        public RoomGUI()
        {
            super("Rooms");
            setSize(500, 300);
            setLocation(GameGUI.this.getLocation());
            
            setLayout(new GridLayout(3, 2));
            
            for(int i = 0; i < btns.length; i++)
            {
                add(btns[i]);
                add(labels[i]);
            }
            
            setVisible(false);
        }
        
        @Override
        public void setUp()
        {
            setBtnLabels(Game.getInst().getRoomNames());
            setDesc(Game.getInst().getRoomDescs());
            setVisible(true);
            System.out.println("Room");
        }

        @Override
        public void activate(int i)
        {
            if(i >= 0)
            {
                newRoom(i);
            }
            
            setVisible(false);
            enableBtns(true);
        }
    }
}