package game.player;

import interfaces.usables.*;
import objects.usables.items.*;
import objects.usables.magics.*;
import objects.usables.weapons.*;

public class Inventory
{
    private static Inventory instance = new Inventory();
    
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
    private Inventory()
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
    public static Weapon getWeapon()
    {
        return instance.weapon;
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
    public static Magic getMagic(int idx)
    {
        return instance.magics[idx];
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
    public static Item getItems(int idx)
    {
        return instance.items[idx];
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
    public static int getMana()
    {
        return instance.mana;
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
    public static int getMaxMana()
    {
        return instance.maxMana;
    }

    public void removeItem(int idx)
    {
        items[idx] = null;
    }
    
    public void removeMagic(int idx)
    {
        magics[idx] = null;
    }

    public static String[] getItemNames()
    {
        String[] output = new String[INV_LENGTH];
        
        for(int i = 0; i < output.length; i++)
        {
            output[i] = instance.items[i].getName();
        }
        
        return output;
    }

    public static String[] getItemDescs()
    {
        String[] output = new String[INV_LENGTH];
        
        for(int i = 0; i < output.length; i++)
        {
            output[i] = instance.items[i].getDesc();
        }
        
        return output;
    }

    public static String[] getMagicNames()
    {
        String[] output = new String[INV_LENGTH];
        
        for(int i = 0; i < output.length; i++)
        {
            output[i] = instance.magics[i].getName();
        }
        
        return output;
    }

    public static String[] getMagicDescs()
    {
        String[] output = new String[INV_LENGTH];
        
        for(int i = 0; i < output.length; i++)
        {
            output[i] = instance.magics[i].getDesc();
        }
        
        return output;
    }
}
