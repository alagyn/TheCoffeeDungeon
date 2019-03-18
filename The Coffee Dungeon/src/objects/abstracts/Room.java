package objects.abstracts;

import java.util.Random;

import game.loot.Loot;

public abstract class Room
{
    private Monster[] monsters;
    private double[] monsterWeights;
    
    private RoomEvent[] events;
    private double[] eventWeights;
    
    private boolean monstersAvail, eventsAvail;
    
    private static Random rand = new Random();
    
    private String name;
    private String desc;
    
    //TODO Events
    //MAYBE Monster based events
    /*
     * Event that only happens if a certain monster is killed
     */
    
    public Room(String name, String desc, 
            Monster[] monsters, double[] monsterWeights, 
            RoomEvent[] events, double[] eventWeights)
    {
        if((monsters == null && monsterWeights == null) ||
                ((monsters != null && monsters.length > 0) &&
                (monsterWeights != null && monsterWeights.length == monsters.length)))
        {
            this.monsters = monsters;
            this.monsterWeights = monsterWeights;
            
            monstersAvail = monsters != null;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Monsters: " + name);
        }
        
        if((events != null && events.length > 0) &&
                (eventWeights != null && eventWeights.length == events.length))
        {
            this.events = events;
            this.eventWeights = eventWeights;
            
            eventsAvail = events != null;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Room Events: " + name);
        }
        
        if(name != null && name.length() > 0)
        {
            this.name = name;
        }
        else
        {
            throw new IllegalArgumentException();
        }
        
        if(desc != null && desc.length() > 0)
        {
            this.desc = desc;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    public Room(String name, String desc, RoomEvent[] events, double[] eventWeights)
    {
        this(name, desc, null, null, events, eventWeights);
    }
    
    public Monster nextMonster()
    {
        Monster output = null;
        
        if(monsters != null)
        {
            double sum = 0;
            double num = rand.nextDouble();
            int index = -1;
            
            for(int i = 0; i < monsterWeights.length; i++)
            {
                sum += monsterWeights[i];
                if(num <= sum)
                {
                    index = i;
                }
            }
            
            output = monsters[index];
        }
        
        return output;
    }
    
    public abstract Loot giveLoot();

    public String getName()
    {
        return name;
    }
    
    public String getDesc()
    {
        return desc;
    }
    
    public boolean monstersAvail()
    {
        return monstersAvail;
    }
    
    public boolean eventsAvail()
    {
        return eventsAvail;
    }
    
    public Loot startEvent(int idx)
    {
        return events[idx].startEvent();
    }
}