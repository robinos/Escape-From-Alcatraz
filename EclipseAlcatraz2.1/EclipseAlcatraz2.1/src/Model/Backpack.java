/**
 * The Backpack class is a subclass of GameObject.  It adds currentWeight and
 * Capacity as fields.
 * The getCurrentWeight and getCapacity methods return these fields.
 * The isFull method checks to see if the backpack would become full if the given
 * weight was added, returning true or false.
 * The addWeight method adds the weight of the given item unless the backpack would
 * become over-full (greater weight than capacity).  The removeWeight method removes
 * the weight of the given item.
 * The clone method allows any subclasses of Backpack to override the clone method.
 * The toString method creates a string description of the id, currentWeight, and
 * capacity of the Backpack object.
 * The equals method compares id, current weight, and capacity for instances of
 * Backpack.
 * The hashCode method also compares id, current weight, and capacity.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Model;

public class Backpack extends GameObject
{
	//Backpack is a subclass of GameObject that has a serialVersionUID of 1L
	//Subclasses of Backpack should have a serialVersionUID of 15xL where x
	//is a value 1-9.	
    private static final long serialVersionUID = 15L;	
	
    // instance variables
    private int currentWeight;
    private int capacity;

    /**
     * Main constructor for objects of class Backpack
     * 
     * Game Object parameters (see Game Object)
     * @param currentWeight : an integer representing the current weight of the new
     *                        backpack (normally empty and 0)
     * @param capacity      : an integer representing maximum capacity of the backpack
     *                        before it is full
     */
    public Backpack(int id, int location, int xLoc, int yLoc, int width, int height, String picture,
    		        String name, int currentWeight, int capacity)
    {
        // initialize instance variables
        super(id,location,xLoc,yLoc,width,height,picture,name);
        this.currentWeight = currentWeight;
        this.capacity = capacity;
    }


    /**
     * No argument constructor for objects of class Backpack
     * This is not meant to be used and exists for serialization.
     */
    public Backpack()
    {
        // initialize instance variables
        super(0,1,0,0,0,0,"","Backpack");
        this.currentWeight = 0;
        this.capacity = 10;
    }
    
    
    /**
     * getCurrentWeight returns the current weight of the backpack
     * 
     * @return : the integer currentWeight value of the backpack
     */
    public int getCurrentWeight()
    {
        return currentWeight;
    }
    
    
    /**
     * getCapacity returns the capacity value of the backpack
     * 
     * @return : the integer capacity value of the backpack
     */
    public int getCapacity()
    {
        return capacity;
    }    
    
    
    /**
     * boolean isFull returns true if the backpack is too full to accept the item
     * 
     * @param weight : the weight of the item to be added
     * @return       : returns true if currentWeight plus added weight
     *                 is greater than capacity, otherwise false
     */
    public boolean isFull(int weight)
    {
        //It shouldn't be possible to have a currentWeight greater than
        //capacity, but this catches all, just in case
        if( (currentWeight + weight) > capacity) {
           return true;
        }
        
        return false;
    }
    
    
    /**
     * addWeight adds item weight to the backpack
     * 
     * @param item : the item whose weight should be added to the backpack  
     * @return     : true if the weight was successfully added, otherwise false
     */
    public boolean addWeight(Item item)
    {    
         if(item != null) {
            if( ! (isFull( item.getItemWeight() ) ) ) {
               currentWeight += item.getItemWeight();
               return true;
            }
            //End if not full
        }
        //End if not null
            
        return false;
    }
       
    
    /**
     * removeWeight removes item weight from the backpack
     * 
     * @param item : the item whose weight should be removed from the backpack 
     * @return     : true if the item weight was successfully removed, otherwise false
     */
    public boolean removeWeight(Item item)
    {    
        if(item != null) {
            currentWeight -= item.getItemWeight();
            return true;
        }
        //End if not null
        
        return false;
    }
    
    
    /**
     *  This is the clone method for Backpack.  It is meant to be
     *  overridden where needed by subclass methods.
     *  Since backpack has only primitive variables, the superclass
     *  method is all that is needed. 
     */
    @Override      
    public Backpack clone()
    {
       return (Backpack)super.clone();
    }
    
    
    /**
     *  A toString method for Backpack that prints out the id, currentWeight, and
     *  capacity of the backpack.
     *  
     *  @return : A String with information on the id, location, currentWeight, and
     *            capacity of the backpack
     */
    @Override     
    public String toString()
    { 
    	return( "Game Object (Backpack) of ID: " + this.getObjectID() + ", Current Weight: " +
                 currentWeight + ", capacity: " + capacity );
    }
    
    
    /**
     *  A basic equals method for Backpack that compares id value, current
     *  weight and capacity values to another Backpack object.
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
    	if(!(obj instanceof Backpack)) {
    		return false;  //Not of the same type
    	}
    	
    	Backpack other = (Backpack)obj;
    	
    	//Compare the id, currentWeight, and capacity of the Backpack to
    	//the id, currentWeight, and capacity of the other Backpack
    	//object and return true or false
    	return( ( other.getObjectID() == this.getObjectID() ) &&
    	        ( other.currentWeight == currentWeight ) &&
    	        ( other.capacity == capacity ) );
    }    
    
    
    /**
     *  A basic hashCode method for Backpack, for the id, current weight
     *  and capacity fields that are compared in the equals method.
     *  
     *  @return : an integer value
     */
    @Override
    public int hashCode()
    {        	
    	int result = 17;
    	
    	result = 37 * result + this.getObjectID();  	
    	result = 37 * result + currentWeight;
    	result = 37 * result + capacity;    	
    	
    	return result;
    }
 
}