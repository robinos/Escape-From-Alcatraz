/**
 * The Item class extends the superclass GameObject adding the concepts of weight
 * and value.  
 * 
 * getItemWeight, and getItemValue return those respective values.
 * The clone method is provided so subclasses can override this method as needed.
 * The toString method adds weight and value to the return string of the toString
 * method of Game Class.
 * The equals method is the same as the GameObject method except that it compares
 * object that are instances of the Item class.
 * The hashCode method is the same as the GameObject method and exist so subclasses
 * can override it.
 * 
 * @author  : Group 8
 * @version : 2012-02-23 Version 1.4
 */

package Model;

public class Item extends GameObject implements Cloneable 
{
	//Item is a subclass of GameObject that has a serialVersionUID of 1L
	//Subclasses of Item should have a serialVersionUID of 11xL where x
	//is a value 1-9.
    private static final long serialVersionUID = 11L;	
	
    // instance variables   
    private int weight;
    private int value;
    
    /**
     * Main constructor for objects of class Item
     * 
     * Game Object parameters (see GameObject)
     * @param weight : an integer representing the weight of the item 
     * @param value :  an integer representing the worth of the item (for the score) 
     */
    public Item(int id, int location, int xLoc, int yLoc, int width, int height, String picture, String name,
    		    int weight, int value)
    {
        // initialize instance variables
        super(id,location,xLoc,yLoc,width,height,picture,name);
        this.weight = weight;
        this.value = value;
    }
 
    
    /**
     * No argument Constructor for objects of class Item.
     * This is not meant for use and exists for serialization.
     */
    public Item()
    {
        // initialize instance variables
        super();
        this.weight = 0;
        this.value = 0;
    }    
    
    
    /**
     * getItemWeight returns the weight value of the item.
     * 
     * @return : the integer weight value of the item.
     */
    public int getItemWeight()
    {
        return weight;
    }
    
    
    /**
     * getItemValue returns the abstract (monetary or score) value of the item.
     * 
     * @return : the integer value of the item.
     */
    public int getItemValue()
    {
        return value;
    }         

    
    /**
     *  This is the clone method for Item.  It overrides the clone()
     *  method of GameObject, and is meant to be overridden where needed by
     *  subclass methods. 
     */
    @Override  
    public Item clone()
    {
       return (Item)super.clone();
    }    

    
    /**
     *  A toString method for Item that prints out the id, location, weight, and
     *  value of the item.
     *  
     *  @return : A String with information on the id, location, weight, and value
     *            of the item
     */
    @Override     
    public String toString()
    { 
    	return( super.toString() + ", Weight: " + weight + ", Value: " + value );
    }
    
    
    /**
     *  A basic equals method for Item that compares id and location values.
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
    	if(!(obj instanceof Item)) {
    		return false;  //Not of the same type
    	}
    	
    	Item other = (Item)obj;
    	
    	//Compare the id and location of the Item to the id and location of
    	//the other Item and return true or false
    	return( ( other.getObjectID() == this.getObjectID() ) &&
    			( other.getObjectLocation() == this.getObjectLocation() ) );
    }    
    
    
    /**
     *  A basic hashCode method for Item, for the id and location fields
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

