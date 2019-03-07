package gui;

import game.Game;
import game.Player;
import game.loot.Completion;
import objects.abstracts.usables.Usable;

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
    
    private ItemLootGUI itemLootGUI;
    private MagicLootGUI magicLootGUI;
    private WeaponLootGUI weaponLootGUI;
    
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
        
        //FIXME Remove Potion label
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
        
        weaponLootGUI = new WeaponLootGUI();
        itemLootGUI = new ItemLootGUI();
        magicLootGUI = new MagicLootGUI();
        
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
            setPlayerStats();
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
        Game.Status status = Game.getInst().combatResolve();
        
        setMonsterStats();
        setPlayerStats();
        
        switch(status)
        {
        case LOSE:
            gameOver();
            break;
            
        case NEUTRAL:
            //Next Round, no action required
            break;
            
        case WIN:
            addLog("You defeated the " + Game.getCurrentMonsterName());
            Game.getInst().giveLoot();
            
            startLootGUI(Game.getInst().getCurrentloot().getType());
            
            /*
             * MAYBE Allow spells/items between rooms
             * Be able to use healing actions without a monster
             */
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
        //TOGUI New game menu
        startGUI(roomGUI);
    }

    private void startGUI(SelectionGUI gui)
    {
        enableBtns(false);
        gui.setUp();   
    }
    
    private void startLootGUI(Usable.LootType type)
    {
        if(type != null)
        {
            switch(type)
            {
                case WEAPON:
                    startGUI(weaponLootGUI);
                    break;
                    
                case ITEM:
                    startGUI(itemLootGUI);
                    break;
                    
                case MAGIC:
                    startGUI(magicLootGUI);
                    break;
                    
                case NONE:
                    //TODO No loot
                    //I.E stat bonus with no need for GUI
                    break;
            }
        }
    }
    
    private void enableBtns(boolean b)
    {
        btnOne.setEnabled(b);
        btnTwo.setEnabled(b);
        btnThree.setEnabled(b);
    }
    
    public void nextRooms()
    {
        Game.resetCurrentMonster();
        startGUI(roomGUI);
    }
    
    private abstract class SelectionGUI extends JFrame implements ActionListener
    {   
        private int index;
       
        public static final int SPACE = 3;
        
        private static final int ERROR = -2, BACK = -1;
        
        public JButton[] btns;
        public JButton back;
        
        public JPanel[] textPanels;
        public JTextArea[] textFields;
        
        public SelectionGUI(String title)
        {
            super(title);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
            
            textPanels = new JPanel[SPACE];
            textFields = new JTextArea[SPACE];
            
            for(int i = 0; i < textPanels.length; i++)
            {
                textPanels[i] = new JPanel();
                textFields[i] = new JTextArea();
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
                    textFields[i].setText(info[i]);
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
        
        public void closeWindow()
        {
            setVisible(false);
            enableBtns(true);
        }
    }

    private abstract class ActionGUI extends SelectionGUI
    {
        private static final int WIDTH = 500, HEIGHT = 300, INSET = 5;
        
        public GridBagLayout[] textGBag;
        
        public ActionGUI(String title)
        {
            super(title);

            setSize(WIDTH, HEIGHT);
            setLocation(GameGUI.this.getLocation().x + 500, GameGUI.this.getLocation().y);
            
            GridBagLayout gBag = new GridBagLayout();
            
            setLayout(gBag);
            
            GridBagConstraints cBtn = new GridBagConstraints();
            cBtn.weightx = 0.2;
            cBtn.fill = GridBagConstraints.BOTH;
            cBtn.gridheight = 2;
            cBtn.weighty = 1;
            cBtn.insets = new Insets(INSET, INSET, INSET, INSET);
            
            GridBagConstraints cPanel = new GridBagConstraints();
            cPanel.weightx = 1;
            cPanel.weighty = 1;
            cPanel.fill = GridBagConstraints.BOTH;
            cPanel.gridwidth = GridBagConstraints.REMAINDER;
            cPanel.gridheight = 2;
            cPanel.insets = new Insets(INSET, INSET, INSET, INSET);
            
            GridBagConstraints cBack = new GridBagConstraints();
            cBack.gridwidth = GridBagConstraints.REMAINDER;
            cBack.weightx = 0;
            //cBack.gridheight = 2;
            cBack.weighty = 0.3;
            cBack.fill = GridBagConstraints.BOTH;
            cBack.insets = new Insets(10, 150, 10, 150);
            
            for(int i = 0; i < btns.length; i++)
            {
                btns[i].setText("Btn: " + i);
                gBag.setConstraints(btns[i], cBtn);
                add(btns[i]);
                
                //XXX
                //textPanels[i].setText("Label: " + i);
                
                gBag.setConstraints(textPanels[i], cPanel);
                add(textPanels[i]);
            }
            
            textGBag = new GridBagLayout[SPACE];
            
            for(int i = 0; i < textPanels.length; i++)
            {
                textGBag[i] = new GridBagLayout();
                textPanels[i].setLayout(textGBag[i]);
            }
            
            gBag.setConstraints(back, cBack);
            add(back);
            
        }
    }
    
    private class ItemGUI extends ActionGUI
    {
        //TOGUI ItemGUI
        public ItemGUI()
        {
            super("Items");
            
            GridBagConstraints cDesc = new GridBagConstraints();
            cDesc.fill = GridBagConstraints.BOTH;
            cDesc.weightx = 1.0;
            cDesc.gridheight = 2;
            cDesc.weighty = 1;
            
            GridBagConstraints cUse = new GridBagConstraints();
            cUse.fill = GridBagConstraints.BOTH;
            cUse.weightx = 0.3;
            cUse.gridheight = 2;
            cUse.weighty = 1;
            
            for(int i = 0; i < textPanels.length; i++)
            {
                textGBag[i].setConstraints(textFields[i], cDesc);
                textPanels[i].add(textFields[i]);
                
                JLabel j = new JLabel("" + i);
                textGBag[i].setConstraints(j, cUse);
                textPanels[i].add(j);            }
            
            setVisible(true);
        }
    
        @Override
        public void setUp()
        {
            
            setBtnLabels(Game.getInst().getItemNames());
            setDesc(Game.getInst().getItemDescs());
            
            for(int i = 0; i < btns.length; i++)
            {
                if(Game.getInst().getPlayer().getItems(i) == null
                        || !Game.getInst().getPlayer().getItems(i).available())
                {
                    btns[i].setEnabled(false);
                    textPanels[i].setEnabled(false);
                }
            }
            
            //TOGUI Item uses and cooldowns
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
            
            closeWindow();
        }
        
    }

    private class MagicGUI extends ActionGUI
    {
        //TOGUI MagicGUI
        
        private JLabel mana;
        
        public MagicGUI()
        {
            super("Magics");
            
            mana = new JLabel();
        }
        
        public void setManaInfo(int amnt, int max)
        {
            mana.setText("Mana: " + amnt + "/" + max);
        }

        @Override
        public void setUp()
        {
            Player p = Game.getInst().getPlayer(); 
            int cur = p.getMana();
            int max = p.getMaxMana();
            setManaInfo(cur, max);
            
            setBtnLabels(p.getMagicNames());
            setDesc(p.getMagicDescs());
            
            for(int i = 0; i < btns.length; i++)
            {
                if(Game.getInst().getPlayer().getMagic(i) == null 
                        || !Game.getInst().getPlayer().getMagic(i).available())
                {
                    textPanels[i].setEnabled(false);
                    btns[i].setEnabled(false);
                }
            }
            
            //TOGUI Magic cooldowns
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
            
            closeWindow();
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
                add(textPanels[i]);
            }
            
            setVisible(false);
        }
        
        @Override
        public void setUp()
        {
            setBtnLabels(Game.getInst().getRoomNames());
            setDesc(Game.getInst().getRoomDescs());
            setVisible(true);
            
            Game.getInst().nextRooms();
        }

        @Override
        public void activate(int i)
        {
            if(i >= 0)
            {
                newRoom(i);
            }
            
            closeWindow();
        }
    }
    
    private class MagicLootGUI extends SelectionGUI
    {
        public MagicLootGUI()
        {
            super("Loot");
            
            //TOGUI LootGUI
        }

        @Override
        public void activate(int i)
        {
            
        }

        @Override
        public void setUp()
        {
            
        }
        
    }
    
    private class WeaponLootGUI extends SelectionGUI
    {
        public WeaponLootGUI()
        {
            super("Weapon loot");
        }
        
        @Override
        public void activate(int i)
        {
            
        }

        @Override
        public void setUp()
        {
            
        }
        
    }
    
    private class ItemLootGUI extends SelectionGUI
    {
        public ItemLootGUI()
        {
            super("Item loot");
        }
        
        @Override
        public void activate(int i)
        {
            if(i >= 0)
            {
                Game.getInst().getPlayer().removeItem(i);
                Game.getInst().getPlayer().setItems(i, Game.getInst().getCurrentloot().getItemLoot());
            }
            
            closeWindow();
            nextRooms();
        }

        @Override
        public void setUp()
        {
            
        }
        
    }

    
}