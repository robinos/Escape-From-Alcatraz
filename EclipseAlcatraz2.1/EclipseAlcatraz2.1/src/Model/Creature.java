/**
 * The Creature class is a subclass of GameObject representing all creatures.
 * It is the superclass to Player.
 * The Creature class adds a field for a backpack.  Backpack is its own object
 * type that handles item weight issues.
 * The getBackpack method returns the backpack object.
 * The setBackpack method sets the backpack object, and is primarily used when
 * restoring a saved game.
 * The clone method of creature adds deep copying for the Backpack class and
 * otherwise exists to allow overriding cloning methods in subclasses.
 * The toString method adds name and backpack weight to the output string.
 * The equals method compares id, location, and name.
 * The hashCode method adds name to the calculation.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Model;

public class Creature extends GameObject implements Cloneable
{
	//Creature is a subclass of GameObject that has a serialVersionUID of 1L
	//Subclasses of Creature should have a serialVersionUID of 12xL where x
	//is a value 1-9.	
    private static final long serialVersionUID = 12L;	
	
    // instance variables
    private Backpack backpack;

    /**
     * Main Constructor for objects of class Creature
     * 
     * Game Object parameters (see GameObject)
     * @param backpack : The backpack object for the creature
     */
    public Creature(int id, int location, int xLoc, int yLoc, int width, int height, String picture, String name, Backpack backpack)
    {
        //initialize instance variables
        super(id,location,xLoc,yLoc,width,height,picture,name);
        
        //A backpack has the same initial id and location as it's creature, id being unique for the backpack
        //as it is for the creature, but location is not currently used for backpacks, and neither are the
        //graphical variables inherited from of GameObject, though they could be used in a later version.
        //If the given backpack is null, one is created.
        if(backpack == null) {
            this.backpack = new Backpack( id, location, xLoc, yLoc, width, height, picture, "Backpack", 0, 10);
        }
        else {
        	this.backpack = backpack;
        }
    }

    /**
     * No argument Constructor for objects of class Creature.
     * This is not meant to be used and exists only for serialization.
     */
    public Creature()
    {
        //initialize instance variables
        super();
        //a backpack has the same id and location as its creature
        backpack = new Backpack( 0, 1, 0, 0, 0, 0, "", "", 0, 10);
    }     
    
    
    /**
     * getBackpack returns the creature's backpack.
     * 
     * @return : the Backpack object representing the creature's backpack 
     */
    public Backpack getBackpack()
    {
        return backpack;
    } 
    
   
    /**
     * setBackpack sets the creatures backpack.
     * 
     * @param : the Backpack object representing the creature's backpack 
     */
    public void setBackpack(Backpack backpack)
    {
        this.backpack = backpack;
    }
    
    
    /**
     *  This is the clone method for Creature.  It overrides the clone()
     *  method of GameObject, and is meant to be overridden where needed by
     *  subclass methods. 
     *  This method is required for copying the Backpack object to make
     *  sure the clone is a deep copy.
     */
    @Override  
    public Creature clone()
    {
        Creature copy = (Creature)super.clone();
        
        //a copy of the Backpack class
        Backpack backpackCopy = backpack.clone();          
        copy.backpack = backpackCopy;
        
        return copy;
    }    
     
    
    /**
     *  A toString method for Creature that prints out the id, location, name,
     *  and backpack weight of the creature.
     *  
     *  @return : A String with information on the id, location, name, and
     *            backpack weight of the creature
     */
    @Override     
    public String toString()
    { 
    	return( super.toString() + ", Name: " + this.getName() + ", Carried Weight: " + backpack.getCurrentWeight() );
    }
    
    
    /**
     *  A basic equals method for Creature that compares id, location, and name values.
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
    	if(!(obj instanceof Creature)) {
    		return false;  //Not of the same type
    	}
    	
    	Creature other = (Creature)obj;
    	
    	//Compare the id, location, and name of the Creature to the id, location, and
    	//name of the other Creature and return true or false
    	return( ( other.getObjectID() == this.getObjectID() ) &&
    			( other.getObjectLocation() == this.getObjectLocation() )&&
    			( other.getName().equals(this.getName()) ) );
    }    
    
    
    /**
     *  A basic hashCode method for Creature, for the id, location, and name fields
     *  that are compared in the equals method.
     *  
     *  @return : an integer value
     */
    @Override
    public int hashCode()
    {        
    	int result = super.hashCode();
    	
    	result = 37 * result + this.getName().hashCode(); 
    	
    	return result;
    }
 
}