package game.player;

import interfaces.player.*;
import items.weapons.*;
import items.items.*;
import items.magics.*;

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
    public void setMagics(int idx, Magic magic)
    {
        //TODO Duplicate checks?
        this.magics[idx] = magic;
    }
    
    //Items
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
    public void setItems(int idx, Item item)
    {
        //TODO Duplicate checking
        this.items[idx] = item;
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
