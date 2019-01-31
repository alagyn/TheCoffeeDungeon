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
    
    public Inventory()
    {
        this.weapon = DEF_WEP;
        this.magics = DEF_MAG;
        this.items = DEF_ITM;
    }
    
    //Weapon
    public Weapon getWeapon()
    {
        return weapon;
    }
    
    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
    
    //Magic
    public Magic getMagic(int idx)
    {
        return magics[idx];
    }
    
    public void setMagics(int idx, Magic magic)
    {
        this.magics[idx] = magic;
    }
    
    //Items
    public Item getItems(int idx)
    {
        return items[idx];
    }
    
    public void setItems(int idx, Item item)
    {
        this.items[idx] = item;
    }

    //Gold
    public int getGold()
    {
        return gold;
    }

    public void addGold(int gold)
    {
        this.gold += gold;
    }
    
    
}
