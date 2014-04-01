/**
* The GameState class holds all information for saving and loading the game.
 * The itemMap keeps track of where all items are located.
 * The roomMap is required to keep track of which doors are locked.
 * The player object holds the game's player data.
 * The playerList LinkedList holds integers representing the path the player has taken
 * through the game.
 * 
 * The getItemMap method returns the HashMap itemMap, and the setItemMap method sets the
 * itemMap to a given HashMap of ArrayList<Item> keyed by an Integer value representing
 * room location.
 * 
 * The getRoomMap method returns the HashMap RoomMap, and the setRoomMap method sets the
 * roomMap to a given HashMap of Room objects, keyed by an Integer representing location.
 * 
 * The getPlayer method return the Player object, and the setPlayer method sets the current
 * player object.
 * 
 * The getPlayerList method returns the LinkedList of Integers, indicating the locations the
 * player has moved to in the game.  The setPlayerList sets this list.
 * 
 * The clone method creates a deep copy of the HashMaps, Player object, and LinkedList for
 * the copy of GameState.
 * The toString method uses the toString methods of involved classes to return a string
 * with room data, item data, player data, and player location data.
 * The equals method uses the equals methods of HashMap, Player, and LinkedList to compare
 * them to another GameState object, and the HashCode method calculates its value using
 * these fields and their respective hashCode methods.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Model;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.io.*;


public class GameState implements Cloneable, Serializable
{
    private static final long serialVersionUID = 6L;	
	
    // instance variables
    //The HashMap holding room locations
	private HashMap<Integer,Room> roomMap;  
    //The HashMap holding item locations
    private HashMap<Integer,ArrayList<Item>> itemMap;
    //The player object of the player in game
    private Player player;
    //The LinkedList holding locations the player has been
    private LinkedList<Integer> playerList;    
    
    
    /**
     * A no argument Constructor for objects of class GameState
     * needs to exist for serialization.
     */
    public GameState()
    {
        // initialize instance variables
        itemMap = null;
        roomMap = null;
        player = null;
        playerList = null;
    }   
    
    
    /**
     * A Constructor for objects of class GameState that takes the arguments
     * of the room HashMap, the item HashMap, the player object, and the
     * LinkedList of previous player locations. 
     */
    public GameState(HashMap<Integer,Room> roomMap, HashMap<Integer, ArrayList<Item>> itemMap, Player player,
    		         LinkedList<Integer> playerList)
    {
        // initialize instance variables
        this.roomMap = roomMap;
        this.itemMap = itemMap;
        this.player = player;
        this.playerList = playerList;
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
     * setItemMap sets itemMap, the HashMap of an array list of Item based on their location.
     * 
     * @param : a HashMap of an ArrayList of the Item class, with the key Location
     */    
    public void setItemMap(HashMap<Integer,ArrayList<Item>> itemMap)
    {
        this.itemMap = itemMap;
    }    
    
    
    /**
     * getRoomMap returns a HashMap of Rooms, keyed by their location in the game world
     * 
     * @return : a HashMap of Room objects, keyed by Integers representing their location
     */    
    public HashMap<Integer,Room> getRoomMap()
    {
        return roomMap;
    }   
    
    
    /**
     * setRoomMap sets the roomMap HashMap of Rooms, keyed by their location in the game world
     * 
     * @param : a HashMap of Room objects, keyed by Integers representing their location
     */    
    public void setRoomMap(HashMap<Integer,Room> roomMap)
    {
        this.roomMap = roomMap;
    }     
    
    
    /**
     * getPlayer returns the player object, which corresponds to the player playing
     * the game.
     * 
     * @return : a Player object
     */    
    public Player getPlayer()
    {
        return player;
    }     
    
    
    /**
     * setPlayer sets the player object, which corresponds to the player playing
     * the game.
     * 
     * @return : a Player object
     */    
    public void setPlayer(Player player)
    {
        this.player = player;
    }  
 
    
    /**
     * getPlayerList returns a LinkedList of the locations the player has been to as
     * Integer objects, in order of current room is last element -> first room is
     * first element.
     * 
     * @return : a LinkedList of Integers, representing the player locations
     */    
    public LinkedList<Integer> getPlayerList()
    {
        return playerList;
    } 
    
    
    /**
     * setPlayerList sets the LinkedList of the locations the player has been to as
     * Integer objects, in order of current room is last element -> first room is
     * first element.
     * 
     * @param : a LinkedList of Integers, representing the player locations
     */    
    public void setPlayerList(LinkedList<Integer> playerList)
    {
        this.playerList = playerList;
    }     
    
    
    /**
     *  This is the clone method for GameState.  It overrides the clone()
     *  method of object, and is meant to be overridden where needed by
     *  subclass methods.
     *  First, the elements of the itemMap, including all ArrayLists within
     *  the itemMap, are copied.  Then, the elements of the roomMap are
     *  copied.  Then the player object, and finally the LinkedList playerList
     *  is copied.
     */
    @Override      
    public GameState clone()
    {
       try
       {
    	   //The superclass method handles the firstRoom variable
    	   GameState copy = (GameState)super.clone();  	   
    	   
           //new itemMap HashMap
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
          

           //new roomMap HashMap
           copy.roomMap = new HashMap<Integer, Room>(); 
            
           Set<Integer> roomKeys = roomMap.keySet();        
           
           //copy the mapped elements of roomMap
           for(Integer iLoc : roomKeys) {             
              //put the element of itemMap in the copy's itemMap
              copy.roomMap.put(iLoc,roomMap.get(iLoc).clone());
           }
           //End for loop for roomMap elements 
           
           //Copy the player object
           copy.player = player.clone();
           
           //new playerList
           copy.playerList = new LinkedList<Integer>();
           
           //copy the elements of playerList
           for(Integer iLoc : playerList) {
        	   copy.playerList.add(iLoc);
           }
           
           return copy;
       }
       catch(CloneNotSupportedException e)
       {
          throw new InternalError();
       }
    } 
    
    
    /**
     *  A toString method for GameState that uses the toString methods of
     *  Room and Item to display the information held in the two HashMaps,
     *  and the toString method of the Player object.  Data from the
     *  playerList is in Integer form and easy to display.
     *  The rooms are displayed first, then all items, the the player
     *  object, and then the data in the playerList.
     *  
     *  @return : String with information about GameState
     */
    public String toString()
    { 
    	StringBuffer outputString = new StringBuffer(100);  	
    	
    	//Use the toString capability of Room to give toString data
    	//about all elements stored in the roomMap
        Set<Integer> roomKeys = roomMap.keySet();        
        
        //for all elements of roomMap
        for(Integer iLoc : roomKeys) {
        	Room room = roomMap.get(iLoc);
        	outputString.append(room.toString() + ", ");
        }
        //End for loops for roomMap elements
        
    	
    	//Use the toString capability of Item to give toString data
    	//about all elements stored in the itemMap
        Set<Integer> itemKeys = itemMap.keySet();        
        
        //for all elements of itemMap
        for(Integer iLoc : itemKeys) {
        	ArrayList<Item> tempList = itemMap.get(iLoc);
        	
        	outputString.append(roomMap.get(iLoc).getName() + ": ");
        	
            //toString all item elements of tempList
            for(int i=0;i<tempList.size();i++) {
        	     if(tempList.get(i) != null) {
                     outputString.append(tempList.get(i).toString() + ", ");
        	     }
            }
            //End for loop for tempList elements
        }
        //End for loop for itemMap elements
        
        //Use the toString method of player, if a player object exists
        if(player != null) {
        	outputString.append("Player: " + player.toString() + ", ");
        } 
        
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
        
    	return ("GameState: " + outputString.toString());
    }
    
    
    /**
     *  An equals method for GameState that compares the HashMaps, the Player object,
     *  and the LinkedList.  This will need to be updated if more functionality is added.
     *   
     *  @param obj : the object for comparison 
     *  @return    : a boolean value true or false 
     */
    public boolean equals(Object obj)
    { 
    	if(this == obj) {
    		return true;  //The same object
    	}
    	if(!(obj instanceof GameState)) {
    		return false;  //Not of the same type
    	}
    	
    	GameState other = (GameState)obj;
            	
    	if(! ( itemMap.equals(other.getItemMap() ) ) ) {
        	//If the itemMaps aren't equal, return false    		
    		return false;
    	}
        else if(! ( roomMap.equals(other.getRoomMap() ) ) ) {
        	//If the roomMaps aren't equal, return false
        	return false;
        }  
        else if(! ( player.equals(other.getPlayer() ) ) ) {
        	//If the player objects aren't equal, return false
        	return false;
        } 
        else if(! ( playerList.equals(other.getPlayerList() ) ) ) {
        	//If the playerLists aren't equal, return false
        	return false;
        }     	
        else {
        	//If the hash maps, player object, and linked list are equal,
        	//return true
            return true;
        }
    }    
    
    
    /**
     *  A basic hashCode method for GameState, for the fields of GameState: itemMap,
     *  roomMap, player, and playerList.  These are compared in the equals method.  
     *  If the equals method is updated, this method will need to be updated as well.
     *  
     *  @return : an integer value
     */
    public int hashCode()
    {     
    	int result = 17;
   	
    	result = 37 * result + itemMap.hashCode();
        result = 37 * result + roomMap.hashCode();  
    	result = 37 * result + player.hashCode(); 
    	result = 37 * result + playerList.hashCode();          
    	return result;
    }
    
}
