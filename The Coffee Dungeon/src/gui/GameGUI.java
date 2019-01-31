package gui;

import game.Action;

import javax.swing.*;
//import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.*;

public class GameGUI extends JFrame implements ActionListener
{
    private Action action;
    private boolean rooms;
    
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JTextField monHealth;
    private JTextField monName;
    private JTextArea log;
    
    private JPanel playStats;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    
    private JTextField playHealth;
    private JTextField playMana;
    private JTextField playPots;
    
    private static final Color backcolor = new Color(220, 220, 220);
    private static final Color textColor = new Color(20, 20, 20);
    
    private static final int 
        WIDTH = 400,
        HEIGHT = 400,
        X = 20, Y = 20;
    
    public static void main(String[] args)
    {
        GameGUI gui = new GameGUI();
        gui.addLog("Hello\n");
        gui.addLog("World\n");
    }
    
    public GameGUI()
    {
        setSize(WIDTH, HEIGHT);
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
        top.add(log);
        
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
        action = new Action();
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
                //action
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
                //action
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
                //action
                resolve();
            }
        }
    }
    
    private void newRoom(int index)
    {
        action.setIndex(index);
        rooms = false;
        setCombatBtn();
        action.nextMonster();
        setMonsterStats();
    }
    
    private void resolve()
    {
        int status = action.combatResolve();
        
        switch(status)
        {
        case -1:
            //lose
            break;
            
        case 0:
            setMonsterStats();
            setPlayerStats();
            break;
            
        case 1:
            action.getLoot();
            setRoomBtn();
            break;
        }
    }
    
    public void setPlayerStats()
    {
        String[] stats = action.getPlayerStats();
        playHealth.setText(stats[0]);
        playMana.setText(stats[1]);
        playPots.setText(stats[2]);   
    }
    
    public void setMonsterStats()
    {
        String[] stats = action.getMonsterStats();
        monName.setText(stats[0]);
        monHealth.setText(stats[1]);
    }
    
    private void setRoomBtn()
    {
        String[] names = action.getRooms();
        
        btnOne.setText(names[0]);
        btnTwo.setText(names[1]);
        btnThree.setText(names[2]);
    }
    
    private void setCombatBtn()
    {
        btnOne.setText("ATK");
        btnTwo.setText("MAG");
        btnThree.setText("POT");
    }
    
    public void addLog(String entry)
    {
        log.append(entry);
    }
}