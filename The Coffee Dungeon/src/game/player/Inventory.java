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
    public static final int START_MANA = 3;
    
    private Weapon weapon;
    private Magic[] magics;
    private Item[] items;
    
    //MAYBE Armor?
    
    private int gold;
    private int mana, maxMana;
    
    /**
     * Default constructor
     */
    public Inventory()
    {
        this.weapon = DEF_WEP;
        this.magics = DEF_MAG;
        this.items = DEF_ITM;
        
        maxMana = START_MANA;
        mana = START_MANA;
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
    
    /**
     * Returns the current mana amount
     * @return the current mana amount
     */
    public int getMana()
    {
        return mana;
    }

    /**
     * Adds to the current mana
     * @param mana the mana to add
     */
    public void addMana(int mana)
    {
        if(mana >= 0)
        {
            this.mana += mana;
            if(this.mana > maxMana)
            {
                this.mana = maxMana;
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid mana amount");
        }
    }
    
    /**
     * Uses the passed amount of mana, returns true if possible
     * @param mana The amount to use
     * @return True if amount is available
     */
    public boolean useMana(int mana)
    {
        boolean output = false;
        
        if(this.mana - mana >= 0)
        {
            this.mana -= mana;
            output = true;
        }
        
        return output;
    }
    
    /**
     * Returns the max mana
     * @return the max mana
     */
    public int getMaxMana()
    {
        return maxMana;
    }

    public void removeItem(int idx)
    {
        items[idx] = null;
    }
    
    public void removeMagic(int idx)
    {
        magics[idx] = null;
    }
}
