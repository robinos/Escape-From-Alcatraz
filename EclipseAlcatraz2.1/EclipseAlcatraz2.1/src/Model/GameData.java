/**
 * The GameData class holds all information for creating a new game with the EclipseAlcatraz
 * game system.
 * 
 * The getFirstRoom method returns the integer representing the first room, and the
 * setFirstRoom method sets the firstRoom variable
 * 
 * The getItemMap method returns the HashMap itemMap, and the setItemMap method sets the
 * itemMap to a given HashMap of ArrayList<Item> keyed by an Integer value representing
 * room location.
 * addItem adds an item to the ArrayList of items for its current location.  It is not
 * added if that item already exists.  If it didn't already exist, the ArrayList is then
 * added to the HashMap itemMap.  
 * overwriteItem adds an item as above, but overwrites any existing item of the same ID.
 * 
 * The getRoomMap method returns the HashMap RoomMap, and the setRoomMap method sets the
 * roomMap to a given HashMap of Room objects, keyed by an Integer representing location.
 * addRoom adds a room to the roomMap if it does not already exist.
 * overwriteRoom adds a room to the roomMap, overwriting any previous Room object.
 * 
 * The clone method creates a deep copy of the HashMaps for the copy of GameData.
 * The toString method displays the integer values and cycles through the HashMaps to make a
 * string of all contained data.
 * The equals compares integers and otherwise uses the equals methods of HashMap to compare
 * one GameData object to another GameData object.
 * The HashCode method calculates its value using these fields and their respective hashCode
 * methods.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Model;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.io.*;


public class GameData implements Cloneable, Serializable
{
    private static final long serialVersionUID = 6L;	
	
    // instance variables
    //The first room for the game
    int firstRoom;
    //The last room for the game, reaching here wins the game
    int lastRoom;
    //The HashMap holding room locations
	private HashMap<Integer,Room> roomMap;  
    //The HashMap holding item locations
    private HashMap<Integer,ArrayList<Item>> itemMap;
    
    
    /**
     * No argument Constructor for objects of class GameData
     * This needs to exist for serialization, but also a Constructor
     * with arguments is not actually needed.
     */
    public GameData()
    {
        // initialize instance variables
    	firstRoom = 1;  //By default the first room is 1
        lastRoom = 2; //By default the last room is 2    	
        itemMap = new HashMap<Integer,ArrayList<Item>>();
        roomMap = new HashMap<Integer,Room>();
    }   

   
    /**
     * getfirstRoom returns the integer value representing the starting room for the
     * game.
     * 
     * @return : an integer value
     */     
    public int getFirstRoom()
    {
        return firstRoom;
    } 
    
    
    /**
     * setfirstRoom sets the integer value representing the starting room for the
     * game.
     * 
     * @param : an integer value
     */     
    public void setFirstRoom(int firstRoom)
    {
        this.firstRoom = firstRoom;
    }     

    
    /**
     * getLastRoom returns the integer value representing the final room for the
     * game (reaching here ends the game).
     * 
     * @return : an integer value
     */     
    public int getLastRoom()
    {
        return lastRoom;
    } 
    
    
    /**
     * setLastRoom sets the integer value representing the final room for the
     * game (reaching here ends the game).
     * 
     * @param : an integer value
     */     
    public void setLastRoom(int firstRoom)
    {
        this.lastRoom = firstRoom;
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
     * addItem adds an item to the ArrayList of items in the HashMap of item locations,
     * as long as it is not null and does not already exist in the ArrayList and HashMap
     * The method works, assuming items have unique IDs.
     * 
     * @param : an Item object
     */    
    public void addItem(Item item)
    {
        boolean exists = false;
        
    	//If the item object is not null
        //if(! (item == null) ) {
        	//If the room location exists
        	//if( itemMap.containsKey(item.getObjectLocation()) ) {
        		ArrayList<Item> tempList = itemMap.get(item.getObjectLocation());
        		
        		if(tempList != null) {
                    Iterator<Item> it = tempList.iterator(); ;
        		
        		    //While there is another item in the room
        		    while(it.hasNext()) {
        			    //If the item already exists, break out
        			    if(it.next().getObjectID() == item.getObjectID()) {
        				    exists = true;
        				    break;
        			    }
        		    }
        		    //End for
        		}
        		else {
            		tempList = new ArrayList<Item>();
        		}
        		
        		
        		//The item was not found in the itemList, so add it
        		if(exists == false) {
        			tempList.add(item);
        			//Add the new array to the itemMap
        			itemMap.put(item.getObjectLocation(),tempList);
        		}
        	//}
        	//End if room location exists
        //}
        //End if not null
    }
    
    
    /**
     * overwriteItem adds an item to the ArrayList of items in the HashMap of item locations,
     * as long as it is not null and overwrites any previous versions in the ArrayList of the
     * HashMap.
     * The method works, assuming items have unique IDs.
     * 
     * @param : an Item object
     */    
    public void overwriteItem(Item item)
    {      
    	//If the room object is not null
        if(! (item == null) ) {
        	//If the room location exists
        	if( itemMap.containsKey(item.getObjectLocation()) ) {
        		
        		ArrayList<Item> itemList = itemMap.get(item.getObjectLocation());
        		
        		//For each item in the room, if a duplicate exists, remove it
                for(Iterator<Item> it = itemList.iterator();it.hasNext(); ) {
        			if(it.next().getObjectID() == item.getObjectID()) {
        				it.remove();
        			}
        		}
        		//End for
        		
        		//Add the item to the itemList
        	    itemList.add(item);
        	    
        	    //Add the new array to the itemMap
        	    itemMap.put(item.getObjectLocation(),itemList);
        	}
        	//End if room location exists
        }
        //End if not null
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
     * addRoom adds a room to the HashMap of rooms, as long as it is not null and does not
     * already exist in the HashMap
     * 
     * @param : a Room object
     */    
    public void addRoom(Room room)
    {
    	//If the room object is not null
        if(! (room == null) ) {
        	//If the roomMap does not already container that room, add it
        	if(! ( roomMap.containsKey(room.getObjectID()) ) ) {
        		roomMap.put(room.getObjectID(), room);
        	}
        }
    }
    
    
    /**
     * overwriteRoom adds a room to the HashMap of rooms, overwriting any previous room
     * object for that location
     * 
     * @param : a Room object
     */    
    public void overwriteRoom(Room room)
    {
    	//If the room object is not null
        if(! (room == null) ) {
        	//Add the room to the room map
            roomMap.put(room.getObjectID(), room);
        }
    }    
    
    
    /**
     *  This is the clone method for GameData.  It overrides the clone()
     *  method of object, and is meant to be overridden where needed by
     *  subclass methods.
     *  First, the elements of the itemMap, including all ArrayLists within
     *  the itemMap, are copied.  Then, the elements of the roomMap are
     *  copied. 
     */
    @Override      
    public GameData clone()
    {
       try
       {
    	   //The superclass method handles the firstRoom variable
    	   GameData copy = (GameData)super.clone();  	   
    	   
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
           
           return copy;
       }
       catch(CloneNotSupportedException e)
       {
          throw new InternalError();
       }
    } 
    
    
    /**
     *  A toString method for GameData that uses the toString methods of
     *  Room and Item to display the firstRoom variable, lastRoom variable,
     *  and the information held in the two HashMaps.
     *  The first room is displayed first, then all rooms, and then
     *  all items.
     *  
     *  @return : String with information about GameData
     */
    public String toString()
    { 
    	StringBuffer outputString = new StringBuffer(100);
    	
    	//The firstRoom integer
    	outputString.append("First room: " + firstRoom + " ");   	
    	
    	//The lastRoom integer
    	outputString.append("Last room: " + lastRoom + " ");     	
    	
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
        
    	return ("GameData: " + outputString.toString());
    }
    
    
    /**
     *  An equals method for GameData that compares the firstRoom variable and
     *  the HashMaps.  This will need to be updated if more functionality is added.
     *   
     *  @param obj : the object for comparison 
     *  @return    : a boolean value true or false 
     */
    public boolean equals(Object obj)
    { 
    	if(this == obj) {
    		return true;  //The same object
    	}
    	if(!(obj instanceof GameData)) {
    		return false;  //Not of the same type
    	}
    	
    	GameData other = (GameData)obj;
         
    	if(! ( firstRoom == other.getFirstRoom() ) ) {
    		//If the first rooms aren't equal, return false
    		return false;
    	}
    	else if(! ( lastRoom == other.getLastRoom() ) ) {
    		//If the last rooms aren't equal, return false
    		return false;
    	}    	
    	else if(! ( itemMap.equals(other.getItemMap() ) ) ) {
        	//If the itemMaps aren't equal, return false    		
    		return false;
    	}
        else if(! ( roomMap.equals(other.getRoomMap() ) ) ) {
        	//If the roomMaps aren't equal, return false
        	return false;
        }    	
        else {
        	//If the hash maps are equal, return true
            return true;
        }
    }    
    
    
    /**
     *  A basic hashCode method for GameData, for the fields of GameGata: firstRoom,
     *  lastRoom, itemMap, and roomMap.  These are compared in the equals method.
     *  Player and playerList cannot be counted on existing, so they are not included.  
     *  If the equals method is updated, this method will need to be updated as well.
     *  
     *  @return : an integer value
     */
    public int hashCode()
    {     
    	int result = 17;

    	result = 37 * result + firstRoom; 
    	result = 37 * result + lastRoom;     	
    	result = 37 * result + itemMap.hashCode();
        result = 37 * result + roomMap.hashCode();    	        
    	return result;
    }
    
}
