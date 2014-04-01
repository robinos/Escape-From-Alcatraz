/**
 * The class Key is a subclass of Item.  It adds the variable owningDoorID which associates
 * the key with a door ID that it can unlock.
 * It also adds the method getOwningDoorID that returns the owningDoorID variable when called. 
 * While there are no current subclasses for key, the clone method exists in case any are made
 * The toString method extends the toString method of item, adding the owningDoorID
 * The equals method compares the owningDoorID when comparing keys
 * The hashCode method adds owningDoorID to the computations of the superclasses of Key
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Model;

public class Key extends Item
{
	//Key is a subclass of Item that has a serialVersionUID of 11L
	//Subclasses of Key should have a serialVersionUID of 111xL where x
	//is a value 1-9.	
    private static final long serialVersionUID = 111L;	
	
    // instance variables
    private int owningDoorID;
    
    /**
     * Main Constructor for objects of class Key.
     * 
     * Item Parameters (See Item)
     * @param owningDoorID : an integer value representing the door unlocked
     *                       by this key
     */
    public Key(int id, int location, int xLoc, int yLoc, int width, int height, String picture, String name,
		    int weight, int value, int owningDoorID)
    {
        // initialize instance variables
        super(id,location,xLoc,yLoc,width,height,picture,name,weight,value);
        this.owningDoorID = owningDoorID;
    }

    
    /**
     * No argument Constructor for objects of class Key.
     * This is not meant to used and exists only for serialization.
     */
    public Key()
    {
        // initialize instance variables
        super();
        this.owningDoorID = 0;
    }   
    
    
    /**
     * getOwningDoorID returns the id value of the owning door associated with
     * this key item.
     * 
     * @return : returns the integer id value of the owning door
     */
    public int getOwningDoorID()
    {
        // return the owning door's id
        return owningDoorID;
    }
    
    
    /**
     *  This is the clone method for Key.  It overrides the clone()
     *  method of Item, and is meant to be overridden where needed by
     *  subclass methods. 
     */
    @Override  
    public Key clone()
    {
       return (Key)super.clone();
    }    

       
    /**
     *  A toString method for Key that extends the toString of Item, printing out
     *  the id, location, weight, value, and owningDoorID of the item.
     *  
     *  @return : A String with information on the id, location, weight, value,
     *            and owningDoorID of the item
     */
    @Override     
    public String toString()
    { 
    	return( super.toString() + ", Unlocks Door: " + owningDoorID );
    }
    
    
    /**
     *  A basic equals method for Key that compares id, location, and
     *  owningDoorID values.
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
    	if(!(obj instanceof Key)) {
    		return false;  //Not of the same type
    	}
    	
    	Key other = (Key)obj;
    	
    	//Compare the id, location, and owningDoorID of the Key to the id, 
    	//location, and owningDoorID of the other Key and return true or false
    	return( ( other.getObjectID() == this.getObjectID() ) &&
    			( other.getObjectLocation() == this.getObjectLocation() ) &&
    			( other.getOwningDoorID() == owningDoorID) );
    }    
    
    
    /**
     *  A basic hashCode method for Key, for the id, location, and owningDoorID
     *  fields that are compared in the equals method.
     *  
     *  @return : an integer value
     */
    @Override
    public int hashCode()
    {        	    	
    	int result = super.hashCode();
    	result = 37 * result + owningDoorID;
    	
    	return(result);
    }
 
}
