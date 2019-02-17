package gui;

import game.Game;
import game.player.Inventory;

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
    /**Reference to game*/
    private Game game;
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
    //FIXME Remove potion text field
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
        
        itemGUI = new ItemGUI();
        magicGUI = new MagicGUI();
        roomGUI = new RoomGUI();
        
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
        
        secondAction = false;
        
        try
        {
            game = new Game();
        }
        catch(IllegalArgumentException e)
        {
            critErrorMessage(e.getMessage());
        }
        
        logText = new ArrayList<String>();
        
        setPlayerStats();
        //TODO Starting room select
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
            int dmg = game.attack();
            addLog("You hit the " + game.getMonsterStats()[0] + " for " + dmg);
            playerDamage();
            resolve();
        }
        else if(magic)
        {
            startGUI(magicGUI);
            
            //TODO Insufficient mana behavior
            //TODO second turn functionality
            //TODO item use limits/cooldowns
            //TODO Resolve magic/items after use
        }
        else if(item)
        {
            startGUI(itemGUI);
        }
    
        //TOGUI Room selection panel
        
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
        int dmg = game.monsterAttack();
        addLog("You take " + dmg + " points of damage");
    }
    
    /**
     * Sets the room at index to the current rooms
     * @param index The room index
     */
    private void newRoom(int index)
    {
        if(index >= 0)
        {
            game.setIndex(index);
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
        int status = game.combatResolve();
        
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
            game.getLoot();
            addLog("You defeated the " + game.getMonsterStats()[0]);
            game.nextMonster();
            game.getLoot();
            /*
             * MAYBE Allow spells/items between rooms
             * Be able to use healing actions without a monster
             */
            //TODO after room selection
            startGUI(roomGUI);
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
        String[] stats = game.getPlayerStats();
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
        String[] stats = game.getMonsterStats();
        monName.setText(stats[0]);
        monHealth.setText(stats[1]);
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
        game.newGame();
        resetLog(true);
        setMonsterStats();
        setPlayerStats();
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
    
    public Game getGame()
    {
        return game;
    }

    private abstract class SelectionGUI extends JFrame implements ActionListener
    {   
        Inventory inventory;
        private int index;
        
        public static final int SPACE = 3;
        
        private JButton[] btns;
        private JButton back;
        
        private JLabel[] labels;
        
        public SelectionGUI(Inventory inventory)
        {
            this.inventory = inventory;
            index = 0;
            
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
            
            activate(index);
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
            super(game.getInventory());
        }
    
        @Override
        public void setUp()
        {
            String[] desc = new String[Inventory.INV_LENGTH];
            String[] btnLabel = new String[Inventory.INV_LENGTH];
            
            for(int i = 0; i < Inventory.INV_LENGTH; i++)
            {
                desc[i] = inventory.getItems(i).getDesc();
                btnLabel[i] = inventory.getItems(i).getName();
            }
            
            setBtnLabels(btnLabel);
            setDesc(desc);
            
            //TODO Item use label
            setVisible(true);
        }

        
        @Override
        public void activate(int i)
        {
            if(i >= 0)
            {
                game.item(i);
                canHaveSecond = inventory.getItems(i).hasSecondAction();
                endRound();
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
            super(game.getInventory());
            
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
            String[] desc = new String[Inventory.INV_LENGTH];
            String[] btnLabel = new String[Inventory.INV_LENGTH];
            
            for(int i = 0; i < Inventory.INV_LENGTH; i++)
            {
                desc[i] = inventory.getMagic(i).getDesc();
                btnLabel[i] = inventory.getMagic(i).getName();
            }
            
            setManaInfo(inventory.getMana(), inventory.getMaxMana());
            setBtnLabels(btnLabel);
            setDesc(desc);
            
            setVisible(true);
        }

        
        @Override
        public void activate(int i)
        {
            if(i >= 0)
            {
                game.magic(i);
                canHaveSecond = inventory.getMagic(i).hasSecondAction();
                endRound();
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
            super(game.getInventory());
        }
        
        @Override
        public void setUp()
        {
            setBtnLabels(game.getRooms());
            setDesc(game.getRoomDescs());
        }

        
        @Override
        public void activate(int i)
        {
            // TODO room activate
            
        }
    }
}