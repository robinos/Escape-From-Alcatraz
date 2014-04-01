/**
 * The Door class has no subclasses but is implemented to work with them.
 * Door is used by the Room class for defining exits.
 * The Door class has the fields of id, room, isLocked, and keyID.
 * 
 * The methods getDoorID, getDoorRoom, getDoorIsLocked, and getKeyID return
 * these values respectively.
 * The clone method allows copies of the Door class to be made.
 * The toString method returns a String with id, room name, isLocked, and keyID values.
 * The equals method compares id, room, isLocked, and keyID fields.
 * The hashCode method computes its value based on id, room, isLocked, and keyID fields.
 * 
 * @author  : Group 8 
 * @version : 2012 02 23, Version 1.4
 */

package Model;
import java.io.*;

public class Door implements Cloneable, Serializable
{
	//Subclasses of Door should have a serialVersionUID of 3xL where x
	//is a value 1-9.		
    private static final long serialVersionUID = 3L;
    
    // instance variables
    
    //The unique ID of the door, note that a door that leads north
    //from room 1 to room 2 is NOT the same as the door that leads
    //south from room 2 to room 1.  All 'directions' are unique doors.
    private int id;
    //The room that one reaches by traveling through this door
    private Room room;
    //True if the door is locked, otherwise false
    private boolean isLocked;
    //The ObjectID of the key that unlocks this door
    private int keyID;

    
    /**
     * Main Constructor for objects of class Door
     * 
     * @param id       : the id of this door
     * @param room     : the Room object this door connects to
     * @param isLocked : true or false if the door is locked
     */
    public Door(int id, Room room, boolean isLocked, int keyID)
    {
        // initialize instance variables
        this.id = id;
        this.room = room;
        this.isLocked = isLocked;
        this.keyID = keyID;
    }

    
    /**
     * No argument Constructor for objects of class Door
     */
    public Door()
    {
        // initialize instance variables
        this.id = 0;
        this.room = new Room();
        this.isLocked = false;
    }    
    
    
    /**
     * getDoorID returns the id of the door.
     * 
     * @return : int id
     */
    public int getDoorID()
    {
        return id;
    }
    
    
    /**
     * getDoorRoom returns the Room object that this door leads to.
     * 
     * @return : Room room
     */
    public Room getDoorRoom()
    {
        return room;
    }
    
    
    /**
     * getKeyID returns the integer ID value of the key associated with
     * this door
     * 
     * @return : the integer value of the key ID
     */
    public int getDoorKeyID()
    {
        return keyID;
    }

    
    /**
     * setKeyID sets the integer ID value of the key associated with
     * this door
     * 
     * @param : the integer value of the key ID
     */
    public void setDoorKeyID(int keyID)
    {
        this.keyID = keyID;
    }    
    
    
    /**
     * getDoorIsLocked returns true if the door is locked, and otherwise
     * false
     * 
     * @return : boolean isLocked
     */
    public boolean getDoorIsLocked()
    {
        return isLocked;
    }   
    
    
    /**
     * setDoorIsLocked returns true if the door is locked, and otherwise
     * false
     * 
     * @return : boolean isLocked
     */
    public void setDoorIsLocked(boolean isLocked)
    {
        this.isLocked = isLocked;
    }
    
    
    /**
     *  This is the clone method for Door.  It overrides the clone()
     *  method of object, and is meant to be overridden where needed by
     *  subclass methods.
     *  This method is required for copying the Room object to make
     *  sure the clone is a deep copy.    
     */
    @Override      
    public Door clone()
    {
       try
       {
          Door copy = (Door)super.clone();
          
          //a copy of the location class
          copy.room = room.clone();          
          
          return copy;
       }
       catch(CloneNotSupportedException e)
       {
          throw new InternalError();
       }
    } 
    
    
    /**
     *  A toString method for Door that returns the id, connected Room name, isLocked
     *  value, and keyID.
     *  
     *  @return : String with information on the id, connected Room ID, and locked status
     */
    public String toString()
    { 
    	return ("Door of ID: " + id + " connects to Room ID: " + room.getName() + ", Locked: " + isLocked +
    			", Unlocking key: " + keyID);
    }
    
    
    /**
     *  A basic equals method for Door that compares id, connected Room, isLocked value,
     *  and keyID.
     *  (Doors should have unique id values but greater comparison can be valuable when
     *  creating GameData files).
     *  
     *  @param obj : the object for comparison 
     *  @return    : a boolean value true or false 
     */
    public boolean equals(Object obj)
    { 
    	if(this == obj) {
    		return true;  //The same object
    	}
    	if(!(obj instanceof Door)) {
    		return false;  //Not of the same type
    	}
    	
    	Door other = (Door)obj;
    	
    	//Compare the id, room, isLocked and keyID values of Door to the id of the
    	//other Door and return true or false
    	return( (other.getDoorID() == id ) &&
    			(other.room == room) &&
    			(other.isLocked == isLocked) &&
    			(other.keyID == keyID) );
    }    
    
    
    /**
     *  A basic hashCode method for the Door object, using the id, room, isLocked,
     *  and keyID fields compared in the equals method.
     *  
     *  @return : an integer value
     */
    public int hashCode()
    {     
    	int result = 17;
    	
    	result = 37 * result + id;
    	result = 37 * result + room.hashCode();
    	result = 37 * result + ( isLocked ? 1 : 0 );
    	result = 37 * result + keyID;    	
    	
    	return result;
    }
    
}
