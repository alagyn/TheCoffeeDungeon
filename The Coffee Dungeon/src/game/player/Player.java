package game.player;

import game.loot.Completion;
import game.loot.Loot;

import objects.abstracts.passives.PassiveItem;
import objects.abstracts.usables.cooldown.*;
import objects.abstracts.usables.weapon.Weapon;
import objects.concretes.usables.items.HealPotion;
import objects.concretes.usables.magics.FireBall;
import objects.concretes.usables.weapons.Sword1;

import java.util.ArrayList;

public class Player
{
    /** Default weapon */
    private static final Weapon DEF_WEP = new Sword1();
    /** Default Magics */
    private static final Magic[] DEF_MAG =
    { new FireBall(), null, null };
    /** Default Items */
    private static final Item[] DEF_ITM =
    { null, new HealPotion(), null };

    public static final int INV_LENGTH = 3;
    public static final int START_MANA = 1;

    public static final int START_HEALTH = 20, START_HEALTH_REGEN = 5, START_MANA_REGEN = 1;

    private int maxHealth, health, healthRegen, manaRegen;

    private Weapon weapon;
    private MagicArray magicArray;
    private ItemArray itemArray;
    private ArrayList<PassiveItem> attackPassives, magicPassives, roundPassives;

    /*
     * MAYBE Rand percent of armor differing amounts of damage reduction
     */

    private int armor;

    private int gold;
    private int mana, maxMana;

    /**
     * Default constructor
     */
    public Player()
    {
        this.weapon = DEF_WEP;
        this.magicArray = new MagicArray(DEF_MAG);
        this.itemArray = new ItemArray(DEF_ITM);

        this.attackPassives = new ArrayList<PassiveItem>();
        this.magicPassives = new ArrayList<PassiveItem>();
        this.roundPassives = new ArrayList<PassiveItem>();

        this.armor = 1;

        maxMana = START_MANA;
        mana = START_MANA;
        maxHealth = START_HEALTH;
        health = START_HEALTH;
        healthRegen = START_HEALTH_REGEN;
        manaRegen = START_MANA_REGEN;
    }

    public int getArmor()
    {
        return armor;
    }

    public void addArmor(int i)
    {
        if (i > 0)
        {
            armor += i;
        } else
        {
            throw new IllegalArgumentException("Invalid armor");
        }
    }

    // Weapon
    /**
     * Returns the current weapon
     * 
     * @return the current weapon
     */
    public Weapon getWeapon()
    {
        return weapon;
    }

    /**
     * Sets the current weapon
     * 
     * @param weapon the new weapon
     */
    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    // Gold
    /**
     * Returns the player's gold
     * 
     * @return the player's gold
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * Adds gold to the players inventory
     * 
     * @param gold Amount to add
     */
    public void addGold(int gold)
    {
        if (gold >= 0)
        {
            this.gold += gold;
        } else
        {
            throw new IllegalArgumentException("Invalid Gold");
        }
    }

    /**
     * Returns the current mana amount
     * 
     * @return the current mana amount
     */
    public int getMana()
    {
        return mana;
    }

    /**
     * Adds to the current mana
     * 
     * @param mana the mana to add
     */
    public void addMana(int mana)
    {
        if (mana >= 0)
        {
            this.mana += mana;
            if (this.mana > maxMana)
            {
                this.mana = maxMana;
            }
        } else
        {
            throw new IllegalArgumentException("Invalid mana amount");
        }
    }

    /**
     * Uses the passed amount of mana, returns true if possible
     * 
     * @param mana The amount to use
     * @return True if amount is available
     */
    public boolean useMana(int mana)
    {
        boolean output = false;

        if (this.mana - mana >= 0)
        {
            this.mana -= mana;
            output = true;
        }

        return output;
    }

    /**
     * Returns the max mana
     * 
     * @return the max mana
     */
    public int getMaxMana()
    {
        return maxMana;
    }

    public String[] getItemNames()
    {
        String[] output = new String[INV_LENGTH];

        for (int i = 0; i < output.length; i++)
        {
            if (itemArray.get(i) == null)
            {
                output[i] = "";
            } else
            {
                output[i] = itemArray.get(i).getName();
            }
        }

        return output;
    }

    public String[] getItemDescs()
    {
        String[] output = new String[INV_LENGTH];

        for (int i = 0; i < output.length; i++)
        {
            if (itemArray.get(i) == null)
            {
                output[i] = "";
            } else
            {
                output[i] = itemArray.get(i).getDesc();
            }
        }

        return output;
    }

    public String[] getMagicNames()
    {
        String[] output = new String[INV_LENGTH];

        for (int i = 0; i < output.length; i++)
        {
            if (magicArray.get(i) == null)
            {
                output[i] = "";
            } else
            {
                output[i] = magicArray.get(i).getName();
            }

        }

        return output;
    }

    public String[] getMagicDescs()
    {
        String[] output = new String[INV_LENGTH];

        for (int i = 0; i < output.length; i++)
        {
            if (magicArray.get(i) == null)
            {
                output[i] = "";
            } else
            {
                output[i] = magicArray.get(i).getDesc();
            }
        }

        return output;
    }

    public void allCooldowns()
    {
        for (int i = 0; i < INV_LENGTH; i++)
        {
            if (itemArray.get(i) != null)
            {
                itemArray.get(i).cooldown();
            }

            if (magicArray.get(i) != null)
            {
                magicArray.get(i).cooldown();
            }
        }
    }

    /**
     * Damages the player a set amount
     * 
     * @param damage The incoming damage
     */
    public void damageThroughArmor(int damage)
    {
        health -= damage;
    }

    public void damageWithArmor(int damage)
    {
        health -= damage - armor;
    }

    /**
     * Checks if the player's health is above zero
     * 
     * @return True is health is above zero
     */
    public boolean isAlive()
    {
        return health > 0;
    }

    /**
     * Returns the current health amount
     * 
     * @return the current health amount
     */
    public int getHealth()
    {
        return health;
    }

    public void regenHealth()
    {
        addHealth(healthRegen);
    }

    public void regenMana()
    {
        addMana(manaRegen);
    }

    /**
     * Adds to the current health
     * 
     * @param health the health to add
     */
    public void addHealth(int health)
    {
        if (health >= 0)
        {
            this.health += health;
            if (this.health > maxHealth)
            {
                this.health = maxHealth;
            }
        } else
        {
            throw new IllegalArgumentException("Invalid health");
        }
    }

    public ItemArray getItemArray()
    {
        return itemArray;
    }

    public MagicArray getMagicArray()
    {
        return magicArray;
    }

    public String[] getManaCosts()
    {
        String[] output =
        { "", "", "" };

        for (int i = 0; i < INV_LENGTH; i++)
        {
            if (!magicArray.checkNull(i))
            {
                output[i] += magicArray.get(i).getCost() + " mana";
            }
        }

        return output;
    }

    public Completion useMagic(int idx)
    {
        Completion c = magicArray.use(idx);
        if(c.actionCompleted)
        {
            int x = useMagPassives(c.damage);
        }
        return c;
    }

    public int useMagPassives(int damage)
    {
        int d = 0;
        for (PassiveItem x : magicPassives)
        {
            d += x.activate(damage);
        }
        
        return d;
    }

    public int useAtkPassives(int damage)
    {
        int d = 0;
        for (PassiveItem x : attackPassives)
        {
            d += x.activate(damage);
        }
        
        return d;
    }

    public void useRoundPassives(int damage)
    {
        for (PassiveItem x : roundPassives)
        {
            x.activate(damage);
        }
    }

    public Completion useItem(int idx)
    {
        return itemArray.use(idx);
    }

    public String[] getItemUses()
    {
        String[] output =
        { "", "", "" };

        for (int i = 0; i < Player.INV_LENGTH; i++)
        {
            if (itemArray.get(i) == null)
            {
                output[i] = null;
                continue;
            }

            if (itemArray.get(i).isUlimited())
            {
                output[i] = "Unlimited";
            } else
            {
                output[i] = itemArray.get(i).getRemainingUses() + " remaining uses";
            }
        }

        return output;
    }

    public void addPassive(PassiveItem p)
    {
        if (p == null)
        {
            throw new NullPointerException();
        }

        switch (p.type)
        {
            case ATTACK:
                attackPassives.add(p);
                break;

            case MAGIC:
                magicPassives.add(p);
                break;

            case ROUND:
                roundPassives.add(p);
                break;
        }
    }

    public interface UsableArray
    {
        boolean checkNull(int i);

        boolean available(int i);

        void set(int i, Loot loot);

        Completion use(int i);
    }

    private class ItemArray implements UsableArray
    {
        private Item[] items;

        public ItemArray(Item[] items)
        {
            if (items.length == INV_LENGTH)
            {
                this.items = items.clone();
            } else
            {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean checkNull(int i)
        {
            if (i >= 0 && i < items.length)
            {
                return items[i] == null;
            } else
            {
                throw new IndexOutOfBoundsException();
            }

        }

        @Override
        public void set(int i, Loot loot)
        {
            if (i >= 0 && i < items.length)
            {
                if (loot == null)
                {
                    throw new IllegalArgumentException();
                }

                items[i] = loot.getItemLoot();
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }

        public Item get(int i)
        {
            if (i >= 0 && i < items.length)
            {
                return items[i];
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public boolean available(int i)
        {
            if (i >= 0 && i < INV_LENGTH)
            {
                boolean output = false;

                if (items[i] != null)
                {
                    output = items[i].available();
                }

                return output;
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public Completion use(int i)
        {
            if (i >= 0 && i < INV_LENGTH)
            {
                return items[i].activate();
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private class MagicArray implements UsableArray
    {
        private Magic[] magics;

        public Magic get(int i)
        {
            if (i >= 0 && i < magics.length)
            {
                return magics[i];
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }

        public MagicArray(Magic[] magics)
        {
            if (magics.length == INV_LENGTH)
            {
                this.magics = magics.clone();
            } else
            {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean checkNull(int i)
        {
            if (i >= 0 && i < magics.length)
            {
                return magics[i] == null;
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public void set(int i, Loot loot)
        {
            if (i >= 0 && i < magics.length)
            {
                if (loot == null)
                {
                    throw new IllegalArgumentException();
                }

                magics[i] = loot.getMagicLoot();
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public boolean available(int i)
        {
            if (i >= 0 && i < INV_LENGTH)
            {
                boolean output = false;

                if (magics[i] != null)
                {
                    output = magics[i].available();
                }

                return output;
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public Completion use(int i)
        {
            if (i >= 0 && i < INV_LENGTH)
            {
                return magics[i].activate();
            } else
            {
                throw new IndexOutOfBoundsException();
            }
        }

    }
}
