/**
 * The class GameObject is the class that holds the basic variable information
 * and methods for all types of item and creature subclasses.  The id is used to
 * identify individual items from inside ArrayLists and HashMaps later.  The location
 * variable keeps track of which room the game object is located in for game logic.
 * xLoc, yLoc, width, and height hold information for the GUI on where the item
 * should be drawn, and the picture string holds the name of the object's picture for
 * example "key" or "dungeon.gif"
 * 
 * getObjectID, getObjectLocation, getXLoc, getYLoc, getWidth, getHeight, and getPicture
 * return those respective values.
 * compareScreenDistance allows comparing the distance of GameObjects in the GUI from
 * each-other.  This is not currently used.
 * moveObject takes care of location logic when adding and removing from locations.
 * clone provides basic clone logic for duplicating a game object.
 * toString provides a basic toString method for output of id and location
 * equals provides a basic equals method that test the id and location of the game object
 * hashCode provides a basic hash code method based equal the fields used in equals.
 * 
 * @author  : Group 8
 * @version : 2012-02-23 Version 1.4
 */

package Model;
import java.util.Observable;
import java.io.*;

public class GameObject extends Observable implements Cloneable, Serializable 
{
	//Subclasses of GameObject should have a serialVersionUID of 1xL where x
	//is a value 1-9.		
    private static final long serialVersionUID = 1L;	
	
    // instance variables
    private int id;
    private int location;
    private int xLoc;
    private int yLoc;
    private int width;
    private int height;    
    private String picture;  
    private String name;
    
    
    /**
     * Main constructor for objects of class GameObject.
     * id does not have to be unique for the GameObject but should be unique
     * for the GameObject subtypes of "Item", "Creature", and "Room"
     * 
     * @param id       : an integer value representing a game object id
     * @param location : an integer value representing the location of the
     *                   game object
     * @param xLoc     : an integer value representing the x-coordinate of
     *                   the GameObject in the GUI
     * @param yLoc     : an integer value representing the y-coordinate of
     *                   the GameObject in the GUI
     * @param width    : an integer value representing the width of the
     *                   GameObject in the GUI
     * @param height   : an integer value representing the height of the
     *                   GameObject in the GUI
     * @param picture  : a String representing the picture of the GameObject
     *                   to be displayed in the GUI, such as "dungeon.gif"
     * @param name     : a String naming the GameObject, such as "Key", or
     *                   "Bob the Monster"
     */
    public GameObject(int id, int location, int xLoc, int yLoc, int width, int height, String picture, String name)
    {
        // initialize instance variables
        this.id = id;
        this.location = location;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.width = width;
        this.height = height; 
        this.picture = picture;
        this.name = name;
    }
    
    /**
     * No argument constructor for objects of class GameObject.
     * This is not meant for use beyond serialization.
     */
    public GameObject()
    {
        // initialize instance variables
        id = 0;
        location = 0;
        xLoc = 0;
        yLoc = 0;
        width = 0;
        height = 0; 
        picture = ""; 
        name = "";         
    }
   
    
    /**
     * getObjectID returns the id value of the game object.  This does not have
     * to be unique for the GameObject, but should be unique for the 'type' of
     * GameObject (ie. Item, Creature, Room).
     * 
     * @return : the integer id value of the game object.
     */
    public int getObjectID()
    {
        return id;
    } 
    
    
    /**
     * getObjectLocation returns the location of the game object as an integer.
     * 
     * @return : the integer variable of the game object.
     */
    public int getObjectLocation()
    {
        return location;
    }     
    
    
    /**
     * getXLoc returns the default x-coordinate value of the game object for the purposes of
     * drawing it with the GUI.
     * 
     * @return : The integer x-coordinate picture screen location
     */
    public int getXLoc()
    {
        return xLoc;
    }
    
    
    /**
     * getYLoc returns the default y-coordinate value of the game object for the purposes of
     * drawing it with the GUI.
     * 
     * @return : The integer y-coordinate picture screen location
     */
    public int getYLoc()
    {
        return yLoc;
    }
    
    
    /**
     * setXLoc set the x-coordinate value of the game object for the purposes of drawing
     * it with the GUI (for example when dropped).
     * 
     * @param xLoc : The integer x-coordinate picture screen location
     */
    public void setXLoc(int xLoc){
    	this.xLoc = xLoc;
    }
    
    
    /**
     * setYLoc set the y-coordinate value of the game object for the purposes of drawing
     * it with the GUI (for example when dropped).
     * 
     * @param yLoc : The integer y-coordinate picture screen location
     */
    public void setYLoc(int yLoc){
    	this.yLoc = yLoc;
    }
    
    
    /**
     * getWidth returns the default width value of the game object for the purposes of
     * drawing it with the GUI.
     * 
     * @return : the integer width value of the game object.
     */
    public int getWidth()
    {
        return width;
    }
    
    
    /**
     * getHeight returns the default height value of the game object for the purposes of
     * drawing it with the GUI.
     * 
     * @return : the integer height value of the game object.
     */
    public int getHeight()
    {
        return height;
    }    
    
    
    /**
     * getPicture returns the filename String for the picture associated with
     * the game object, for the purposes of drawing it with the GUI.
     * 
     * @return : the String picture value of the game object.
     */
    public String getPicture()
    {
        return picture;
    }
    
    
    /**
     * getName returns the name String associated with the game object.
     * 
     * @return : the String name value of the game object.
     */
    public String getName()
    {
        return name;
    }
      
    
    /**
     * compareScreenDistance compares the relative distance between the graphical
     * representation of two objects.  It assumes the x,y coordinate is for the
     * center of each object, and adds half the width and height as the actual
     * border of the object.
     * This method is not current used.
     * 
     * @return : an integer array holding the difference between the two locations.
     */
    public int[] compareScreenDistance(int xLoc, int yLoc, int width, int height)
    {
    	int[] screenDistance = {0,0,0,0};
    	
    	screenDistance[0] = (this.xLoc + this.width/2) - (xLoc + width/2);
    	screenDistance[1] = (this.yLoc + this.height/2) - (yLoc + height/2);
    	
        return screenDistance;
    }
    
    
    /**
     * moveObject is the base method for adding or removing a game object from a location.
     * Note this only handles the logical location of the game object, not
     * its actual disappearance as a sprite or graphic from the GUI
     * 
     * @param newLocation : the new location for the game object in question
     */
    public void moveObject(int newLocation)
    {               	   
    	   //Save the old location
           int oldLocation = new Integer(location); 
           
           //Set the item to the new location            
           location = newLocation;
           
           //Notify the ObjectWatcher of the change in location           
           setChanged();
           
           //Notify observers of the change (This notifies the ObjectWatcher set to
           //observe GameObjects);
           notifyObservers(oldLocation);
    }    

    
    /**
     *  This is the clone method for GameObject.  It overrides the clone()
     *  method of object, and is meant to be overridden where needed by
     *  subclass methods. 
     */
    @Override      
    public GameObject clone()
    {
       try
       {
          return(GameObject)super.clone();
       }
       catch(CloneNotSupportedException e)
       {
          throw new InternalError();
       }
    } 
    
    
    /**
     *  A toString method for GameObject that returns the id and location of the
     *  GameObject.
     *  
     *  @return : String with information on the id and location of the GameObject
     */
    public String toString()
    { 
    	return ("Game Object of ID: " + id + " and Location " + location + " ");
    }
    
    
    /**
     *  An equals method for GameObject that compares id and location values.
     *  
     *  @param obj : the object for comparison 
     *  @return    : a boolean value true or false 
     */
    public boolean equals(Object obj)
    { 
    	if(this == obj) {
    		return true;  //The same object
    	}
    	if(!(obj instanceof GameObject)) {
    		return false;  //Not of the same type
    	}
    	
    	GameObject other = (GameObject)obj;
    	
    	//Compare the id and location of GameObject to the id and location
    	//of the other GameObject and return true or false
    	return( ( other.getObjectID() == id ) && (other.getObjectLocation() == location) );
    }    
    
    
    /**
     *  A basic hashCode method for GameObject, for the id and location fields
     *  that are compared in the equals method.
     *  
     *  @return : an integer value
     */
    public int hashCode()
    {     
    	int result = 17;
    	
    	result = 37 * result + id;
        result = 37 * result + location;    	
    	
    	return result;
    }
    
}
