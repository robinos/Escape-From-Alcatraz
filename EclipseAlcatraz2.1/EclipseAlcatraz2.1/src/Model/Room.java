/**
 * Room is a subclass of GameObject that adds the fields of
 * description: a String description of the Room,
 * and exits: a HashMap of a key exit direction "North, South, East, West" with the
 * value of a Door object.
 * Room could use GameObject's fields of xLoc, yLoc, height, and weight to define picture
 * size, if needed.  As of now it only uses the Picture String to define the room
 * picture. 
 * 
 * The setExit method associates a text direction with a Door object by adding it to
 * the HashMap.
 * The getShortDescription method returns the field description.
 * The getLongDescription method is meant to be used when first entering a room, and
 * gives the short description plus a list of available exits.
 * getExitString is used by getLongDescription to form the exit list based on the
 * HashMap keys for that room.
 * getExit returns the Door object related to a direction string. 
 * clone allows Room to be cloned, making sure it is a deep copy.
 * toString provides a string of id, location, description, and exits information.
 * equals compares id and location, and is the same as GameObject's equals method
 * except that it compares Room objects.
 * The hashCode method is the same as GameObject's method, and exists so eventual
 * subclasses to Room would be able to overwrite it.
 * 
 * @author  : Group 8 
 * @version : 2012 02 23, Version 1.4 
 */

package Model;
import java.util.Set;
import java.util.HashMap;


public class Room extends GameObject
{
    private static final long serialVersionUID = 13L;		
	
    // instance variables
    private String description;
    private HashMap<String, Door> exits;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court yard",
     * It is preceded by "You are " when displaying the room.
     * 
     * @param description : The room's description.
     */
    public Room(int id, int location, int xLoc, int yLoc, int width, int height, String picture,
    		    String name, String description) 
    {
        super(id,location,xLoc,yLoc,width,height,picture, name);
        this.description = description;
        
        exits = new HashMap<String, Door>();
    }
    
    
    /**
     * No argument Constructor for objects of class Room.
     * This is not meant for use beyond serialization.
     */    
    public Room() 
    {
        super();
        this.description = "";
        
        exits = new HashMap<String, Door>();
    }    

    
    /**
     * Define an exit from this room.
     * 
     * @param direction : The direction of the exit ("North", "South", "East", or "West").
     * @param neighbor  : The Room to which the exit leads.
     * @param isLocked  : true if locked, otherwise meant to be false 
     * @param keyID     : The integer ID value of the key object used to unlock this door,
     *                    or 0 if this door is not locked 
     */
    public void setExit(String direction, int id, Room neighbor, boolean isLocked, int keyID) 
    {
    	//A new Door object is created
        Door door = new Door(id, neighbor, isLocked, keyID);
      
        //The Door is keyed to a direction and added to the HashMap exits
        //for this Room object.
        exits.put(direction, door);
    }
    
    
    /**
     * getShortDescription returns the short text for the Room defined in the
     * Constructor.
     * 
     * @return : The short description of the room.
     */
    public String getShortDescription()
    {
        return description;
    }   
    
    
    /**
     * getLongDescription returns a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     
     * @return : A long description of this room.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    
    /**
     * getExitString returns a string describing the room's exits, for example
     * "Exits: north west".
     * 
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        
        //Get the keys "North", "South", etc. from the exits HashMap
        Set<String> keys = exits.keySet();
        
        //Adds the exits to the string
        for(String exit : keys) {
            returnString += " " + exit;
        }
        
        return returnString;
    }

    
    /**
     * Return the Door from this room in direction "direction".
     * If there is no room in that direction, return null.
     * 
     * @param direction : The exit's direction.
     * @return          : The Door object in the given direction.
     */
    public Door getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    
    /**
     *  This is the clone method for Room.  It overrides the clone()
     *  method of GameObject, and is meant to be overridden where needed by
     *  subclass methods. 
     *  It is needed to make a copy of the exits HashMap, and description
     *  String.
     */
    @Override  
    public Room clone()
    {
        Room copy = (Room)super.clone();
        
        //a shallow copy of the HashMap class
        copy.exits = new HashMap<String, Door>(); 
         
        Set<String> keys = exits.keySet();        
        
        //copy the mapped elements of exits
        for(String strDirection : keys) {
        	copy.exits.put( strDirection, copy.exits.get(strDirection) );
        }       
        
        copy.description = new String(description);
        
        return copy;    	
    }    

    
    /**
     *  A toString method for Rooms that returns the id, location, description,
     *  and exits as a string.
     *  
     *  @return : A String with information on the id, location, description, and
     *            exits.
     */
    @Override     
    public String toString()
    { 
    	return( super.toString() + ", Description: " + description + getLongDescription() );
    }
    
    
    /**
     *  A basic equals method for Room that compares id and location values.  Since
     *  these should be unique, there is no reason to compare exits.
     *  
     *  @param obj : the object for comparison 
     *  @return    : a boolean value true or false 
     */
    @Override
    public boolean equals(Object obj)
    { 
    	if(this == obj) {
    		return true;  //The same object
    	}
    	if(!(obj instanceof Room)) {
    		return false;  //Not of the same type
    	}
    	
    	Room other = (Room)obj;
    	
    	//Compare the id and location of the Room to the id and location of
    	//the other Room and return true or false
    	return( ( other.getObjectID() == this.getObjectID() ) &&
    			( other.getObjectLocation() == this.getObjectLocation() ) );
    }    
    
    
    /**
     *  A basic hashCode method for Room, for the id and location fields
     *  that are compared in the equals method.
     *  
     *  @return : an integer value
     */
    @Override
    public int hashCode()
    {        	
    	return(super.hashCode());
    }
 
}
