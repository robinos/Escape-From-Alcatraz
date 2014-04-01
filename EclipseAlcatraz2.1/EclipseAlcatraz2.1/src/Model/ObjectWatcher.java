/**
 * The class ObjectWatcher watches the location status of objects of the GameObject class and
 * subclasses.  It keeps track of Item locations with a HashMap, with each location key having
 * an array list of items currently located there.  It also keeps track of player movements with
 * the playerList LinkedList of Location objects, adding new locations last in the list.
 * When creating a game, the ObjectWatcher is loaded with the locations of all items.
 * The update method is used to make alterations whenever a change happens.
 * The getItemMap method returns the HashMap itemMap, and the getPlayerList method returns
 * the LinkedList playerList.
 * The clone method creates a deep copy of the HashMap and LinkedList for the copy of ObjectWatcher.
 * The toString method uses the toString methods of HashMap and LinkedList to return a string
 * of values.
 * The equals method uses the equals methods of HashMap and LinkedList to compare them to another
 * ObjectWatcher object, and the HashCode method calculates its value using these two fields and
 * their respective hashCode methods.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Model;
import java.util.Observer;
import java.util.Observable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.io.*;


public class ObjectWatcher implements Observer, Cloneable, Serializable
{
    private static final long serialVersionUID = 2L;	
	
    // instance variables
    //The HashMap holding item locations
    private HashMap<Integer,ArrayList<Item>> itemMap;
    //The LinkedList holding locations the player has been
    private LinkedList<Integer> playerList;

    
    /**
     * No argument Constructor for objects of class ObjectWatcher
     * This needs to exist for serialization, but also a Constructor
     * with arguments is not actually needed.
     */
    public ObjectWatcher()
    {
        // initialize instance variables
        itemMap = new HashMap<Integer,ArrayList<Item>>();
        playerList = new LinkedList<Integer>();
    }   

    /**
     * update is an overwritten method of Observer, used in reaction to a change happening
     * in an observed class.  In this case it updates the instance variable HashMap itemMap,
     * removing references to the Item at the old location, and adding it's reference to the
     * new location.
     * It also updates the locations that the Player has visited, adding the most recent
     * Location first in the linked list.
     * Note: more objects than Items and Player could be added later
     * 
     * @param o   : an Observable object (which should be an GameObject object of some sort)
     * @param arg : an Object (which should be a Location object) 
     */
    public void update(Observable o, Object arg)
    {        
        /**Item update code**/
        if(o instanceof Item && arg instanceof Integer) {                        
            Item item = (Item)o;
            int oldLocation = (Integer)arg;
            int newLocation = item.getObjectLocation();
            
            //remove old item from array list            
            ArrayList<Item> newList = itemMap.get((Integer)oldLocation);
       
            if(newList != null) {
                //For loop while there is another element
                for(Iterator<Item> it = newList.iterator();it.hasNext(); ) {              
                   if(item.getObjectID() == it.next().getObjectID()) {
                       it.remove();
                   }
                   //End if matching id
               }
               //End for loop
                
               //Update the item map with the new status with the item removed              
               itemMap.put((Integer)oldLocation,newList);                
            }
            //if list not null
  
            
            //If not 'destroyed', which is sent to location 9999
            if(! (newLocation == 9999) ) {               
                //add the item to the new location's array list
                if( ! ( itemMap.containsKey((Integer)newLocation) ) ) {
                    newList = new ArrayList<Item>();
                    newList.add(item);
                    itemMap.put((Integer)newLocation,newList);
                }
                else {
                    newList = itemMap.get((Integer)newLocation);
                    //not checking for duplicates yet, add the item to the list
                    newList.add(item);
                    //Update the item map with the new status                  
                    itemMap.put((Integer)newLocation,newList);
                }
                //End contains key
            }
            //End if not destroyed         
        }
        //End if valid instances of Item and Integer
        
        
        /**Player update code**/
        if(o instanceof Player && arg instanceof Integer) {  
            Player player = (Player)o;
        	int location = player.getObjectLocation(); 
            
            //Add new locations the player visits Last in the linked list,
            //which makes last element always the current
            playerList.addLast(location);
        }
        //End if valid instances of Player and Integer            
    }

    
    /**
     * getItemMap returns a HashMap of an array list of Item based on their location.
     * 
     * @return : a HashMap of an ArrayList of the Item class, with the key Location
     */    
    public HashMap<Integer,ArrayList<Item>> getItemMap()
    {
        return itemMap;
    }
 
    
    /**
     * getPlayerList returns a LinkedList of the locations the player has been to as
     * Location objects, in order of current room is last element -> first room is
     * first element.
     * 
     * @return : a LinkedList of the Location class, representing the player locations
     */    
    public LinkedList<Integer> getPlayerList()
    {
        return playerList;
    } 
    
    
    /**
     * setPlayerList sets the LinkedList of the locations the player has been to as
     * Integer objects, in order of current room is last element -> first room is
     * first element.  This is used when loading the game.
     * 
     * @return : a LinkedList of the Location class, representing the player locations
     */    
    public void setPlayerList(LinkedList<Integer> playerList)
    {
        this.playerList = playerList;
    }     
    
    
    /**
     *  This is the clone method for ObjectWatcher.  It overrides the clone()
     *  method of object, and is meant to be overridden where needed by
     *  subclass methods. 
     */
    @Override      
    public ObjectWatcher clone()
    {
       try
       {
    	   ObjectWatcher copy = (ObjectWatcher)super.clone();  	   
    	   
           //new HashMap
           copy.itemMap = new HashMap<Integer, ArrayList<Item>>(); 
            
           Set<Integer> itemKeys = itemMap.keySet();        
           
           //copy the mapped elements of itemMap
           for(Integer iLoc : itemKeys) {
              ArrayList<Item> newList = new ArrayList<Item>(); 
              
           	  ArrayList<Item> tempList = itemMap.get(iLoc);
           	  
              //copy all elements of tempList to newList
              for(int i=0;i<tempList.size();i++) {
                  newList.set(i,tempList.get(i).clone());
              }
              
              //put the element of itemMap in the copy's itemMap
              copy.itemMap.put(iLoc,newList);
           }
           //End for loop for itemMap elements 
          
           
           //new playerList
           copy.playerList = new LinkedList<Integer>();
            
            //copy of all elements of playerList
            for(int i=0;i<playerList.size();i++)
            {
                copy.playerList.set(i,playerList.get(i));
            }           
           
           return copy;
       }
       catch(CloneNotSupportedException e)
       {
          throw new InternalError();
       }
    }      
    
    
    /**
     *  A toString method for ObjectWatcher that uses the toString methods of
     *  HashMap and LinkedList.
     *  
     *  @return : String with basic information about ObjectWatcher
     */
    public String toString()
    {   	
    	StringBuffer outputString = new StringBuffer(100);  
    	
    	//Use the toString capability of Item to give toString data
    	//about all elements stored in the itemMap
        Set<Integer> itemKeys = itemMap.keySet();        
        
        //for all elements of itemMap
        for(Integer iLoc : itemKeys) {
        	ArrayList<Item> tempList = itemMap.get(iLoc);
        	
        	outputString.append("Room ID " + iLoc + ": ");
        	
            //toString all item elements of tempList
            for(int i=0;i<tempList.size();i++) {
        	     if(tempList.get(i) != null) {
                     outputString.append(tempList.get(i).toString() + ", ");
        	     }
            }
            //End for loop for tempList elements
        }
        //End for loop for itemMap elements
        
        
        //If the playerList exists
        if(playerList != null) { 
            outputString.append(", Player Location History: ");        	
            //toString all item elements of playerList
            for(Integer iLoc : playerList) {
        	     if(iLoc != null) {
                     outputString.append(iLoc + ", ");
        	     }
            }
        }      	
    	
    	return ("ObjectWatcher: " + outputString.toString());
    }
    
    
    /**
     *  An equals method for ObjectWatcher that compares the HashMap and
     *  LinkedList.  This will need to be updated if more functionality is
     *  added.
     *  
     *  @param obj : the object for comparison 
     *  @return    : a boolean value true or false 
     */
    public boolean equals(Object obj)
    { 
    	if(this == obj) {
    		return true;  //The same object
    	}
    	if(!(obj instanceof ObjectWatcher)) {
    		return false;  //Not of the same type
    	}
    	
    	ObjectWatcher other = (ObjectWatcher)obj;
         
    	if(! (itemMap.equals(other.getItemMap()) ) ) {
        	//If the HashMaps aren't equal, return false    		
    		return false;
    	}
        else if(! ( playerList.equals(other.getPlayerList()) ) ) {
        	//If the lists aren't equal, return false
        	return false;
        }   	
        else {
        	//If the hash maps and lists are equal, return true
            return true;
        }
    }    
    
    
    /**
     *  A basic hashCode method for ObjectWatcher, for the HashMap itemMap and
     *  LinkedList playerList fields that are compared in the equals method.
     *  If the equals method is updated, this will need to be updated as well.
     *  
     *  @return : an integer value
     */
    public int hashCode()
    {     
    	int result = 17;
    	
    	result = 37 * result + itemMap.hashCode();
        result = 37 * result + playerList.hashCode();    	
        
    	return result;
    }
    
}