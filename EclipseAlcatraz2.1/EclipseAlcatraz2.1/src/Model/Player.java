/**
 * The Player class is subclass to the Creature class.  It adds the functionality of
 * the Score field.
 * The getScore method returns the score object.
 * The clone method for Player adds copying for the Score class.
 * The toString method adds the player score to the out string.
 * The equals method is the same as the method in Creature except that it compares Players.
 * The hashCode method is the same as the method in Creature.
 * 
 * @author  : Group 8 
 * @version : 2012 02 23, Version 1.4
 */

package Model;

public class Player extends Creature
{
	//Player is a subclass of Creature that has a serialVersionUID of 12L
	//Subclasses of Player should have a serialVersionUID of 121xL where x
	//is a value 1-9.	
    private static final long serialVersionUID = 121L;	
	
    // instance variables
    private Score score;

    /**
     * Main Constructor for objects of class Player
     * 
     * Creature parameters (see Creature)
     * @param score : a Score object
     * 
     */
    public Player(int id, int location, int xLoc, int yLoc, int width, int height, String picture,
    		      String name, Backpack backpack, Score score)
    {
        // initialize instance variables
        super(id,location,xLoc,yLoc,width,height,picture,name,backpack);
        this.score = score;        
    }
    
    
    /**
     * No argument Constructor for objects of class Player.
     * This is not meant to used and exists only for serialization.
     */
    public Player()
    {
        // initialize instance variables
        super();
        score = new Score();
    }

    /**
     * getScore returns the player's score as a Score object.
     * 
     * @return : the Score object
     */
    public Score getScore()
    {
        return score;
    }
    
    
    /**
     *  This is the clone method for Player.  It overrides the clone()
     *  method of Creature, and is meant to be overridden where needed by
     *  subclass methods. 
     *  This method is required for copying the Score object to make
     *  sure the clone is a deep copy. 
     */
    @Override  
    public Player clone()
    {
        Player copy = (Player)super.clone();
        
        //a copy of the location class
        Score scoreCopy = score.clone();          
        copy.score = scoreCopy;
        
        return copy;
    }    
     
    
    /**
     *  A toString method for Player that prints out the id, location, name,
     *  backpack weight, and score of the player.
     *  
     *  @return : A String with information on the id, location, name, backpack
     *            weight, and score of the player
     */
    @Override     
    public String toString()
    { 
    	return( super.toString() + ", Score: " + score.getScore() );
    }
    
    
    /**
     *  A basic equals method for Player that compares id, location, and name values.
     *  This is the same as the Creature equals method, except that it compares Player
     *  objects.
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
    	if(!(obj instanceof Player)) {
    		return false;  //Not of the same type
    	}
    	
    	Player other = (Player)obj;
    	
    	//Compare the id, location, and name of the Player to the id, location, and
    	//name of the other Player and return true or false
    	return( ( other.getObjectID() == this.getObjectID() ) &&
    			( other.getObjectLocation() == this.getObjectLocation() ) &&
    			( this.getName().equals(other.getName()) ) );
    }    
    
    
    /**
     *  A basic hashCode method for Player, for the id, location, and name fields
     *  that are compared in the equals method.
     *  This is exactly the same as the superclass method, but exists so
     *  eventual subclasses can override it.
     *  
     *  @return : an integer value
     */
    @Override
    public int hashCode()
    {            	
    	return super.hashCode();
    }
 
}