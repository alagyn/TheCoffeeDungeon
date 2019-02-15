package game.player;

import usables.items.*;
import usables.magics.*;
import usables.weapons.*;
import interfaces.usables.*;

public class Inventory
{
    /**Default weapon*/
    private static final Weapon DEF_WEP = new Sword1();
    /**Default Magics*/
    private static final Magic[] DEF_MAG = {new FireBall(), null, null};
    /**Default Items*/
    private static final Item[] DEF_ITM = {new HealPotion(), null, null};
    
    public static final int INV_LENGTH = 3;
    
    private Weapon weapon;
    private Magic[] magics;
    private Item[] items;
    
    //MAYBE Armor?
    
    private int gold;
    
    /**
     * Default constructor
     */
    public Inventory()
    {
        this.weapon = DEF_WEP;
        this.magics = DEF_MAG;
        this.items = DEF_ITM;
    }
    
    //Weapon
    /**
     * Returns the current weapon
     * @return the current weapon
     */
    public Weapon getWeapon()
    {
        return weapon;
    }
    
    /**
     * Sets the current weapon
     * @param weapon the new weapon
     */
    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
    
    //Magic
    /**
     * Returns the magic at the index
     * @param idx the index
     * @return the magic
     */
    public Magic getMagic(int idx)
    {
        return magics[idx];
    }
    
    /**
     * Sets the magic at the index
     * @param idx the index
     * @param magic the new magic
     */
    public boolean setMagics(int idx, Magic magic)
    {
        boolean output = true;
        
        for(int i = 0; i < magics.length; i++)
        {
            if(magics[i].equals(magic))
            {
                output = false;
                break;
            }
        }
        
        if(output)
        {
            this.magics[idx] = magic;
        }
        
        return output;
    }
    
    //Items////////
    
    /**
     * Returns the item at the index
     * @param idx the index
     * @return the item
     */
    public Item getItems(int idx)
    {
        return items[idx];
    }
    
    /**
     * Sets the item at the index
     * @param idx the index
     * @param item the item
     */
    public boolean setItems(int idx, Item item)
    {
        boolean output = true;
        
        for(int i = 0; i < items.length; i++)
        {
            if(items[i].equals(item))
            {
                output = false;
                break;
            }
        }
        
        if(output)
        {
            this.items[idx] = item;
        }
        
        return output;
    }

    //Gold
    /**
     * Returns the player's gold
     * @return the player's gold
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * Adds gold to the players inventory
     * @param gold Amount to add
     */
    public void addGold(int gold)
    {
        if(gold >= 0)
        {
            this.gold += gold;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Gold");
        }    
    }
    
}
