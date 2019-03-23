package objects.abstracts;

import game.loot.Loot;
import game.util.RandUtil;

public abstract class Room
{
    private Monster[] monsters;
    private double[] monsterWeights;
    
    private RoomEvent[] events;
    private double[] eventWeights;
    
    private boolean monstersAvail, eventsAvail;
    
    private String name;
    private String desc;
    
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
        return monsters[RandUtil.weightRand(monsterWeights)];
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
    
    public double[] getEventWeights()
    {
        return eventWeights;
    }
}