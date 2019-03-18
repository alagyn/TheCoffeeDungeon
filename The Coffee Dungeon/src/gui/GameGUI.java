package gui;

import game.Game;
import game.loot.Completion;
import game.loot.Loot;
import game.player.Player;
import game.player.Player.UsableArray;
import game.player.PlayerStatus;
import objects.abstracts.usables.Usable.LootType;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The main Game GUI
 * @author alagyn
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
    
    private LootGUI lootGUI;
    
    private NewGameGUI newGameGUI;
    
    private JPanel playStats;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    
    private JTextField playHealth;
    private JTextField playMana;
    private JTextField playArmor;
    
    /**Background color*/
    private static final Color backcolor = new Color(220, 220, 220);
    /**Text color*/
    private static final Color textColor = new Color(20, 20, 20);
    /**Lines allowed in log*/
    private static final int LOG_LINES = 6;
    /**Window size and location*/
    private static final int X = 50, Y = 50, WIN_WIDTH = 500, WIN_HEIGHT = 400;
    
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
        
        GridBagLayout statGridBag = new GridBagLayout(); 
        playStats.setLayout(statGridBag);
        
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
        playArmor = new JTextField();
        playArmor.setEditable(false);
        
        log.setBorder(BorderFactory.createTitledBorder("Activity Log"));
        
        GridBagConstraints btnC = new GridBagConstraints();
        btnC.fill = GridBagConstraints.BOTH;
        btnC.gridheight = 2;
        btnC.weightx = 0.4;
        btnC.weighty = 1;
        btnC.insets = new Insets(10, 10, 10, 10);
        
        GridBagConstraints labelC = new GridBagConstraints();
        labelC.fill = GridBagConstraints.BOTH;
        labelC.gridheight = 2;
        labelC.weightx = 0.2;
        labelC.weighty = 1;
        labelC.insets = new Insets(10, 10, 10, 10);
        
        GridBagConstraints fieldC = new GridBagConstraints();
        fieldC.fill = GridBagConstraints.BOTH;
        fieldC.weightx = 0.5;
        fieldC.gridheight = 2;
        fieldC.weighty = 1;
        fieldC.gridwidth = GridBagConstraints.REMAINDER;
        fieldC.insets = new Insets(10, 10, 10, 10);
        
        statGridBag.setConstraints(btnOne, btnC);
        statGridBag.setConstraints(btnTwo, btnC);
        statGridBag.setConstraints(btnThree, btnC);
        
        statGridBag.setConstraints(playHealth, fieldC);
        statGridBag.setConstraints(playMana, fieldC);
        statGridBag.setConstraints(playArmor, fieldC);
        
        JLabel label1 = new JLabel("Health: "); 
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVerticalAlignment(SwingConstants.CENTER);
        JLabel label2 = new JLabel("Mana: ");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setVerticalAlignment(SwingConstants.CENTER);
        JLabel label3 = new JLabel("Armor: ");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setVerticalAlignment(SwingConstants.CENTER);        
        
        statGridBag.setConstraints(label1, labelC);
        statGridBag.setConstraints(label2, labelC);
        statGridBag.setConstraints(label3, labelC);
        
        playStats.add(btnOne);
          
        playStats.add(label1);
        playStats.add(playHealth);
        
        playStats.add(btnTwo);
        playStats.add(label2);
        playStats.add(playMana);
        
        playStats.add(btnThree);
        playStats.add(label3);
        playStats.add(playArmor);
        
        ////
        add(top);
        add(playStats);
        
        setVisible(false);
        ////
        
        secondAction = false;
        
        logText = new ArrayList<String>();
        
        
        itemGUI = new ItemGUI();
        magicGUI = new MagicGUI();
        roomGUI = new RoomGUI();
        
        lootGUI = new LootGUI();
        
        newGameGUI = new NewGameGUI();
        
        newGame();
    }
    
    public static void main(String[] args)
    {
        
    }
    
    /**
     * Returns the singleton instance
     * @return
     */
    public static GameGUI getInst()
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
        
        setPlayerStats();
        setMonsterStats();
        
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
            Game.getInst().getPlayer().regenHealth();
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
        playerDamage();
        Game.Status status = Game.getInst().combatResolve();
        
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
            
            startLootGUI(Game.getInst().getCurrentloot());
            
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
        PlayerStatus stats = Game.getInst().getPlayerStatus();
        
        playHealth.setText(stats.health);
        playMana.setText(stats.mana);
        playArmor.setText(stats.armor);
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
        
        Game.getInst().resetCurrentMonster();
        
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
        visible(false);
        newGameGUI.start();
    }

    private void startGUI(SelectionGUI gui)
    {
        enableBtns(false);
        gui.setUp();   
    }
    
    private void startLootGUI(Loot loot)
    {
        if(loot.type == LootType.NONE)
        {
            nextRooms();
        }
        else
        {
            lootGUI.setLoot(loot);
            startGUI(lootGUI);
        }
    }
    
    private void enableBtns(boolean b)
    {
        btnOne.setEnabled(b);
        btnTwo.setEnabled(b);
        btnThree.setEnabled(b);
    }
    
    private void nextRooms()
    {
        Game.getInst().resetCurrentMonster();
        startGUI(roomGUI);
    }
    
    public void visible(boolean b)
    {
        setVisible(b);
    }
    
    private abstract class SelectionGUI extends JFrame implements ActionListener
    {   
        private int index;
       
        public static final int SPACE = 3;
        
        private static final int ERROR = -2, BACK = -1;
        
        public JButton[] btns;
        public JButton back;
        
        public JPanel[] textPanels;
        public JTextPane[] descFields;
        
        public SelectionGUI(String title)
        {
            super(title);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            setLocation(GameGUI.this.getLocation().x + 500, GameGUI.this.getLocation().y);
            
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
            descFields = new JTextPane[SPACE];
            
            for(int i = 0; i < textPanels.length; i++)
            {
                textPanels[i] = new JPanel();
                descFields[i] = new JTextPane();
                descFields[i].setEditable(false);
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
                index = ERROR;
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
                    StyledDocument doc = descFields[i].getStyledDocument();
                    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

                    doc.addStyle("regular", def);
                    StyleConstants.setFontFamily(def, "SansSerif");

                    SimpleAttributeSet center = new SimpleAttributeSet();
                    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                    doc.setParagraphAttributes(0, doc.getLength(), center, false);
                    
                    descFields[i].setText("\n" + info[i]);
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
        
        //MAYBE Action Icons
        /*
         * Item/Magic icons
         * 
         */
        
        public GridBagLayout[] textGBag;
        private JTextArea[] useFields;
        
        public ActionGUI(String title)
        {
            super(title);

            setSize(WIDTH, HEIGHT);
            
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
            

            useFields = new JTextArea[SPACE];
            
            GridBagConstraints cDesc = new GridBagConstraints();
            cDesc.fill = GridBagConstraints.BOTH;
            cDesc.weightx = 1.0;
            cDesc.gridheight = 2;
            cDesc.weighty = 1;
            cDesc.insets = new Insets(0, 10, 0, 10);
            
            GridBagConstraints cUse = new GridBagConstraints();
            cUse.fill = GridBagConstraints.BOTH;
            cUse.weightx = 0.3;
            cUse.gridheight = 2;
            cUse.weighty = 1;
            cUse.insets = new Insets(0, 10, 0, 10);
            
            for(int i = 0; i < textPanels.length; i++)
            {
                textGBag[i].setConstraints(descFields[i], cDesc);
                textPanels[i].add(descFields[i]);
                
                useFields[i] = new JTextArea();
                useFields[i].setEditable(false);
                
                textGBag[i].setConstraints(useFields[i], cUse);
                textPanels[i].add(useFields[i]);
            }
            
        }
    
        //TOGUI Magic/item cooldown counter
        
        public void enableFields(UsableArray usable)
        {
            for(int i = 0; i < btns.length; i++)
            {
                boolean avail = !usable.checkNull(i) && usable.available(i);
                
                btns[i].setEnabled(avail);
                descFields[i].setEnabled(avail);
                useFields[i].setEnabled(avail);
            }
        }
        
        public void setUseFields(String[] info)
        {
            for(int i = 0; i < info.length; i++)
            {
                if(info[i] != null)
                {
                    useFields[i].setText("\n" + info[i]);
                }
                else
                {
                    useFields[i].setText("");
                }
                
            }
        }
    }
    
    private class ItemGUI extends ActionGUI
    {
        public ItemGUI()
        {
            super("Items");
            
            setVisible(false);
        }
    
        @Override
        public void setUp()
        {
            setBtnLabels(Game.getInst().getItemNames());
            setDesc(Game.getInst().getItemDescs());
            setUseFields(Game.getInst().getItemUses());
            
            enableFields(Game.getInst().getPlayer().getItemArray());
            
            setVisible(true);
        }

        public void activate(int i)
        {
            if(i >= 0)
            {
                Completion c = Game.getInst().item(i);
                if(c.actionCompleted)
                {
                    canHaveSecond = c.canHaveSecond;
                    endRound();
                }
            }
            
            closeWindow();
        }
        
    }

    private class MagicGUI extends ActionGUI
    {
        public MagicGUI()
        {
            super("Magics");
        }

        @Override
        public void setUp()
        {
            Player p = Game.getInst().getPlayer();
            setUseFields(Game.getInst().getPlayer().getManaCosts());
            
            setBtnLabels(p.getMagicNames());
            setDesc(p.getMagicDescs());
            
            enableFields(Game.getInst().getPlayer().getMagicArray());
            setVisible(true);
        }

        @Override
        public void activate(int i)
        {
            boolean close = true;
            
            if(i >= 0)
            {
                Completion c = Game.getInst().magic(i);
                if(c.actionCompleted)
                {
                    canHaveSecond = c.canHaveSecond;
                    endRound();
                }
                else
                {
                    JOptionPane.showMessageDialog(magicGUI, "Not Enough Mana");
                    close = false;
                }
            }
            
            if(close)
            {
                closeWindow();
            }
            
        }        
    }

    private class RoomGUI extends SelectionGUI
    {
        //TOGUI RoomGUI
        
        public RoomGUI()
        {
            super("Rooms");
            setSize(400, 250);
            
            GridBagLayout layout = new GridBagLayout();
            
            setLayout(layout);
            
            GridBagConstraints btnC = new GridBagConstraints();
            btnC.fill = GridBagConstraints.BOTH;
            btnC.weightx = .8;
            btnC.weighty = 1;
            btnC.insets = new Insets(10, 20, 10, 20);
            
            GridBagConstraints descC = new GridBagConstraints();
            descC.gridwidth = GridBagConstraints.REMAINDER;
            descC.weightx = 1;
            descC.fill = GridBagConstraints.BOTH;
            descC.weighty = 1;
            descC.insets = new Insets(10, 20, 10, 20);
            
            for(int i = 0; i < btns.length; i++)
            {
                layout.setConstraints(btns[i], btnC);
                layout.setConstraints(descFields[i], descC);
                add(btns[i]);
                add(descFields[i]);    
            }
            
            setVisible(true);
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
    
    private class LootGUI extends SelectionGUI
    {
        private static final String itemMessage = "Item";
        private static final String magicMessage = "Magic";
        
        private JTextArea lootName, lootDesc;

        private Loot loot;
        private JPanel lootPanel;
        
        public LootGUI()
        {
            super("");
            
            setSize(400, 200);
            setLayout(new GridLayout(4, 1));
            
            lootName = new JTextArea();
            lootDesc = new JTextArea();
            
            lootName.setEditable(false);
            lootDesc.setEditable(false);
            
            lootPanel = new JPanel();
            lootPanel.setLayout(new GridLayout(1, 2));
            add(lootPanel);
            
            lootPanel.add(lootName);
            lootPanel.add(lootDesc);
            
            for(int i = 0; i < SPACE; i++)
            {
                JPanel p = new JPanel();
                p.setLayout(new GridLayout(1, 2));
                add(p);
                p.add(btns[i]);
                p.add(descFields[i]);
            }
            
        }
        
        public void setLootDesc()
        {
            lootName.setText(loot.getLootName());
            lootDesc.setText(loot.getLootDesc());
        }
        
        public boolean replaceMessage(String type)
        {
            boolean output = false;
            if(type == null || type.length() <= 0)
            {
                throw new IllegalArgumentException("Invalid LootGUI type string");
            }
            
            int x = JOptionPane.showOptionDialog(null, "Replace " + type, "Game"
                    , JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE
                    , null, null, null);
            
            if(x == JOptionPane.YES_OPTION)
            {
                output = true;
            }
            
            return output;
        }
        
        public void setLoot(Loot loot)
        {
            if(loot != null)
            {
                this.loot = loot;
                setLootDesc();
                
                switch(loot.type)
                {
                    case ITEM:
                        setLootTitle(itemMessage);
                        setTitle("Item Loot");
                        break;
                        
                    case MAGIC:
                        setLootTitle(magicMessage);
                        setTitle("Magic Loot");
                        break;
                        
                    case WEAPON:
                        //TOGUI Weapon Loot GUI
                        break;
                        
                    default:
                        throw new IllegalArgumentException("Invalid Loot GUI start");
                }
            }
            else
            {
                throw new NullPointerException("Null Loot");
            }
        }
        
        private void setLootTitle(String title)
        {
            lootPanel.setBorder(BorderFactory.createTitledBorder("New " + title));
        }
        
        public void activate(int i)
        {
            boolean change = false;
            
            if(i >= 0)
            {
                Player p = Game.getInst().getPlayer();
                
                UsableArray a;
                String message;
                
                switch(loot.type)
                {
                    case ITEM:
                        a = p.getItemArray();
                        message = itemMessage;
                        break;
                        
                    case MAGIC:
                        a = p.getMagicArray();
                        message = magicMessage;
                        break;
                        
                    default:
                        throw new IllegalArgumentException("Invalid LootGUI start");
                }
                
                if(!a.checkNull(i))
                {
                    change = replaceMessage(message);
                }
                else
                {
                    change = true;
                }

                if(change)
                {
                    a.set(i, loot);
                    closeWindow();
                    nextRooms();
                }
            }
            
        }

        public void setUp()
        {
            boolean item = false;
            
            switch (loot.type)
            {
                case ITEM:
                    setLootTitle(itemMessage);
                    item = true;
                    break;

                case MAGIC:
                    setLootTitle(magicMessage);
                    break;
                    
                default:
                    throw new IllegalArgumentException();
            }
            
            Player p = Game.getInst().getPlayer();
            
            if(item)
            {
                setDesc(p.getItemDescs());
                setBtnLabels(p.getItemNames());
            }
            else
            {
                setDesc(p.getMagicDescs());
                setBtnLabels(p.getMagicNames());
            }
            
            setLootDesc();
            
            
            
            setVisible(true);
        }


    }

    private class NewGameGUI extends JFrame implements ActionListener
    {
        private static final String NEWGAME_LOGO = "projectDocs/BDD_Logo.png";

        private JButton startBtn, quitBtn;
        
        public NewGameGUI()
        {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(300, 300);
            setLocation(200, 200);
            
            GridBagLayout newGameBag = new GridBagLayout();
            
            setLayout(newGameBag);
            
            ImageIcon icon = new ImageIcon(NEWGAME_LOGO);
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            
            JLabel labelIcon = new JLabel("The Coffee Dungeon", icon, JLabel.CENTER);
            labelIcon.setVerticalTextPosition(JLabel.BOTTOM);
            labelIcon.setHorizontalTextPosition(JLabel.CENTER);
            
            startBtn = new JButton("New Game");
            quitBtn = new JButton("Quit");
            
            startBtn.addActionListener(this);
            quitBtn.addActionListener(this);
            
            GridBagConstraints logoC = new GridBagConstraints();
            logoC.weightx = 1;
            logoC.weighty = .5;
            logoC.fill = GridBagConstraints.BOTH;
            logoC.gridwidth = GridBagConstraints.REMAINDER;
            
            GridBagConstraints btnC = new GridBagConstraints();
            btnC.weightx = 1;
            btnC.fill = GridBagConstraints.BOTH;
            btnC.gridwidth = GridBagConstraints.REMAINDER;
            btnC.insets = new Insets(5, 50, 5, 50);
            
            GridBagConstraints panelC = new GridBagConstraints();
            panelC.weightx = 1;
            panelC.weighty = .7;
            panelC.gridwidth = GridBagConstraints.REMAINDER;
            
            JPanel panel = new JPanel();
            GridBagLayout panelG = new GridBagLayout();
            panel.setLayout(panelG);
            
            panelG.setConstraints(startBtn, btnC);
            panelG.setConstraints(quitBtn, btnC);
            
            panel.add(startBtn);
            panel.add(quitBtn);
            
            panel.setBorder(BorderFactory.createEtchedBorder());
            
            newGameBag.setConstraints(labelIcon, logoC);
            newGameBag.setConstraints(panel, panelC);
            
            add(labelIcon);
            add(panel);
            
            setVisible(false);
        }
        
        public void start()
        {
            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource().equals(quitBtn))
            {
                System.exit(0);
            }
            else if(event.getSource().equals(startBtn))
            {
                //TODO Tutorial
                startGUI(roomGUI);
                visible(true);
                setVisible(false);
            }
        }
        
    }
}