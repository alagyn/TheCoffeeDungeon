package game.player;

import interfaces.player.*;

public class Inventory
{
    private Weapon weapon;
    private Magic[] magics;
    private Item[] items;
    
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
    public Magic[] getMagics()
    {
        return magics;
    }
    
    public void setMagics(Magic[] magics)
    {
        this.magics = magics;
    }
    
    //Items
    public Item[] getItems()
    {
        return items;
    }
    
    public void setItems(Item[] items)
    {
        this.items = items;
    }
    
    
}
