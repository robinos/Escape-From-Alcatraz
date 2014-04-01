/**
 *  The GameEngine class is part of the "Escape from Medieval Alcatraz" Game System. 
 *  It currently allows for a very simple, text and graphical based adventure game.
 *  GameEngine loads game information from file, and then handles all game logic
 *  while communicating with the GUI.
 *  
 *  currentRoom is the room the player current is in.
 *  roomMap is a HashMap of all rooms keyed by location.
 *  gui is the game's GUI object.
 *  objectWatcher is the Observer that watches all GameObjects, reporting on the
 *  location of all items, and the path the player has taken through the game.
 *  player is an object of the player class, to keep track of player information.
 *  fileManager is an object of the FileManager class, used to create the game from
 *  file and save and load the game state.
 *  
 *  getCurrentRoom, getLastRoom, getRoomMap, getObjectWatcher, and getPlayer return
 *  their respective variables for use by other classes.
 *  setPlayer is used by the start menu to initialize the player when it creates the
 *  GameEngine.
 *  initializePlayer initializes the player object and places the player in the first
 *  room (game logic only).
 *  setGUI sets the current GUI used by the GameEngine.  This method is used when
 *  starting the game.
 *  printWelcome print the first text the player sees open entering the game.
 *  goRoom is called when the player attempts to switch rooms by clicking on a direction
 *  arrow in the GUI.  It allows the player to switch rooms if the direction is valid, and
 *  the door is unlocked (or the player has a valid key for that door).
 *  switchRooms is called by goRoom to signal the GUI to make the visual changes of switching
 *  rooms.
 *  itemExists is used primarily by the getdropObject method to check if a given object exists
 *  in a given room, but is also called by the GUI class.
 *  getdropObject is called when the player clicks on a game object in a room, or drops an
 *  object from the backpack.  This method attempts to move the object.  The moveObject method
 *  in the superclass GameObject updates the objectWatcher about all object movements, which in
 *  turn updates a HashMap of ArrayLists of items, stored by room.  The Player object's backpack
 *  and score are also updated by the getdropObject method.
 *  goBackToPreviousRoom is called when the player clicks on the Back button, and takes the
 *  player to the previous room
 *  saveSavegame saves the current game state
 *  loadSavegame loads a game state from file
 *  loadGameFile loads the game information from file, allowing the Engine and GUI to run
 *  varying games of a similar type.  The method loads the rooms, the doors between the rooms,
 *  and all the items that can be found in the rooms.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */


package Controller;
//import java.awt.Toolkit;
import java.util.Observable;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;  //LinkedList should be important for the Back command
import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;

import GUI.GUI;
import Model.Door;
import Model.Item;
import Model.GameData;
import Model.GameState;
import Model.ObjectWatcher;
import Model.Player;
import Model.Room;
import Model.Score;
import Model.Backpack;
import Model.Highscore;

public class GameEngine 
{
	//gameChoice holds the current choice of game file
	private static int gameChoice = 0;
	
	//currentRoom keeps track of the current room the player is in
    private Room currentRoom;
    
	//lastRoom keeps track of the last room of the game, the win
    //condition
    private Room lastRoom;    
    
    //A hashmap over the rooms that exist
	HashMap<Integer,Room> roomMap;
	
	//The gui
    private GUI gui;
    
    //objectWatcher keeps track of where items are located at all
    //times, and where the player is (and has been).
    private ObjectWatcher objectWatcher;
    
    //The player object
    private Player player;

    //The Highscore database
    //private Highscore highScore;

    //Score based on time...not yet implemented
	//private int timeScore = 1000;
	
	//The FileManager object, used for saving and loading objects to/from file
	private FileManager fileManager;
	
	
    /**
     * Constructor for objects of class GameEngine.  This creates the HashMap of
     * room objects in the game, creates the ObjectWatcher object, and creates the
     * FileManager object.  It also loads the rooms, items, and the objectWatcher
     * from file to create the game.
     */
    public GameEngine() 
    {    	
    	String gamefile;
    	
    	//Chooses which game to play, intended for later offering a choice
    	//of game, not currently implemented
    	if(gameChoice == 1) {
    	    gamefile = "longgame.gam"; 	
    	}
    	else { //gameChoice == 0
    		gamefile = "game.gam";
    	}
    	
    	//Initializes the HashMap of rooms
    	roomMap = new HashMap<Integer,Room>();
    	
    	//Initializes the ObjectWatcher
    	objectWatcher = new ObjectWatcher();
    	
    	//Initializes the FileManager object
    	fileManager = new FileManager();    	
    	
    	//Load game loads the game file, container rooms, door exits, and object locations
    	//By loading in the objectWatcher, the roomMap, and variables for which rooms
    	//begin and end the game.
    	loadGameFile(gamefile);
    	

    }

    
    /**
     * Returns the Room object that represents the room where the player
     * currently is.
     * 
     * @return the Room object of the current room variable 
     */
    public Room getCurrentRoom()
    {
    	return currentRoom;
    }
    
    
    /**
     * Returns the Room object that represents the last room of the game,
     * where the game ends.
     * 
     * @return the Room object of the last room variable 
     */
    public Room getLastRoom()
    {
    	return lastRoom;
    }    

    
    /**
     * Returns the hash map of rooms, keyed by location as an Integer
     * 
     * @return the HashMap of Room objects, keyed by Integer location 
     */
    public HashMap<Integer,Room> getRoomMap()
    {
    	return roomMap;
    }
    

    /**
     * Returns the Object Watcher object that holds all information on object
     * locations and player movements.
     * 
     * @return the ObjectWatcher object stored in the variable objectWatcher.  
     */
    public ObjectWatcher getObjectWatcher()
    {
    	return objectWatcher;
    }
    
    
    /**
     * getPlayer returns the Player object
     * 
     * @return : the Player object
     */
    public Player getPlayer() {
    	return player;
    }    
    
    
    /**
     * setPlayer sets the Player object for the first time
     * 
     * @param playername : a String name for the player
     */
    public void setPlayer(String playername)
    {
    	initializePlayer(0,currentRoom.getObjectID(),playername,null,null);
    }
    
    
    /**
     * Initialize a player.  This is called to create a new player, giving an integer ID
     * unique among Player objects (not necessarily among GameObjects), an integer
     * location representing a room ID, a String for the player name, and a score object.
     * If the score object is null, a new one, with a score of 0, is created.  Likewise if
     * a backpack object is null, a new one is created.
     * This should be called when creating a new player, or when loading a save game from
     * file.
     * 
     * @param playerID      : an integer that is unique among Player objects
     * @param playerLocation: the initial location of the player (the
     *                         Room ID he/she starts in)
     * @param name          : The player's name
     * @param backpack      : The backpack object that belongs to this player.  If backpack
     *                      : is null, a new backpack object will be created in Player's
     *                        superclass, Creature.
     * @param score         : The score object that belongs to this player.  If
     *                        score is null, a new score object is created.
     */
    private void initializePlayer(int playerID, int playerLocation, String name, Backpack backpack, Score score)
    {
    	//If name is empty or null, use default
    	if(name == null || name == "") {
    		name = "player1";
    	}
    	
    	//If there is no Score object given, create one
    	if(score == null) {
    	   score = new Score();
    	}
    	
    	//create a new player object
        player = new Player(playerID,playerLocation,0,0,0,0,"",name, backpack, score);
        
        //Player is noted as an observable object, and the objectWatcher begins
        //tracking movement
        Observable player1 = player;
        player1.addObserver(objectWatcher);    	
        
        //move the player to the first room
        player.moveObject(currentRoom.getObjectID());        
    }    
    
    
    /**
     * This method is used by the Game Class to initialize the connection
     * between the GameEngine and the GUI.
     * 
     * @param userInterface : the GUI created by Game that interacts
     *                        with the GameEngine
     */
    public void setGUI(GUI userInterface)
    {
        gui = userInterface;
        printWelcome();
    }    
    
    
    /**
     * Print out the opening message for the player, and load the long
     * description for the current room.
     */
    private void printWelcome()
    {
        gui.print("\n");
        gui.println("Welcome "+player.getName() +" to Escape from Medieval Alcatraz!");
        gui.println("Escape from Medieval Alcatraz is a new and very simple adventure game.");
        gui.println("Choose 'help' from the help menu if you need help.");
        gui.print("\n");
        gui.println(currentRoom.getLongDescription());
    }
    
    
    /** 
     * The player tries to move in a direction. If there is an exit, goRoom
     * checks if the door is locked.  If the door is locked, it checks if the
     * player has the key.  If all conditions are met (or the door existed and
     * wasn't locked) the switchRooms method is called which handles changing
     * rooms.  The door becomes also becomes 'unlocked'.
     * If the player didn't have the key (or correct key), a message is shown
     * about the door being locked.  While it shouldn't be possible to try
     * to move in a direction that doesn't exist, a message exists for that too.
     * 
     * @param direction : The direction "north", "south", etc. which corresponds
     *                    to the button pushed in the GUI. 
     */
    public void goRoom(String direction) 
    {         
        // Try to leave current room.
    	
    	//Get the door object for the direction the player wants to travel
        Door door = currentRoom.getExit(direction);

        //Get the map of all item locations from objectWatcher
        HashMap<Integer,ArrayList<Item>> itemMap = objectWatcher.getItemMap();        
        
        int neededKeyID = 0;
        boolean unlocked = false;
        Room nextRoom = null;
        
        //If a door exists for the chosen direction (it should)
        if(door != null) {
        	//Get the room object on the 'other side' of the door
           nextRoom = door.getDoorRoom();        
        
           //Checks which key is associated with the door
           neededKeyID = door.getDoorKeyID();
        }
        
        //If there is no door in that direction (which shouldn't happen)
        //a message is shown via the text box in the GUI.
        if (nextRoom == null) {
            gui.println("There is no door!");
        }
        //If the door is locked
        else if(door.getDoorIsLocked()) {
        	//Get the ArrayList of items for the backpack (location 0)
            ArrayList<Item> itemList = itemMap.get(0);
            //If there are items in the backpack
            if(itemList != null){
            	//Cycle through the items in the backpack
                for(Item item : itemList) {               	
                	//If the item found is the needed key for this door
                    if(item.getObjectID() == neededKeyID) {  
                    	//Display a message about unlocked the door in the GUI
                        gui.println("You unlock the door with your key.");
                        //Set the door to unlocked (for following if statements)
                        unlocked = true;
                        //For game logic, set this door unlocked
                        door.setDoorIsLocked(false);                        
                        //Call the switchRooms method to actually switch rooms
                        switchRooms(nextRoom); 
                    }
                }
                //The right key wasn't found, tell the player
                if(!unlocked) {
                   gui.println("You don't have the right key.");                     
                }
            }
            //The player has no items in his/her backpack            
            else {
               gui.println("The door is locked!");                
            }
        }
        //Otherwise, the door exists, and it was not locked.
        else {
            //Call the switchRooms function to actually switch rooms
            switchRooms(nextRoom);           
        }
        
        //If that move took the player to the final room, end the game
        if(currentRoom.equals(lastRoom)) {
        	endGame();
        }
        
    }

    
    /** 
     * The switchRoom method in the GUI is called, drawing the next room and its
     * objects (if any).
     * The currentRoom variable is then set to the new room for GameEngine logic, and
     * the println method in the GUI is called to print out the long room description.
     * Finally, the player object is moved (automatically notifying objectWatcher).
     * Player movements are tracked for later use by the Back command.
     * 
     * @param nextRoom : The Room object to move to. 
     */   
    public void switchRooms(Room nextRoom) 
    {     
        //Get the map of all item locations from objectWatcher
        HashMap<Integer,ArrayList<Item>> itemMap = objectWatcher.getItemMap();   	

        //Call the GUI method for loading a new room, sending in the
        //parameters of room ID (for the new room), and the itemMap from
        //objectWatcher with the locations of all items        	
        gui.changeRoom(nextRoom.getObjectLocation(),itemMap,roomMap);        
        //The new room becomes the currentRoom         
        currentRoom = nextRoom;    
        //Print out the new room's long description          
        gui.println(currentRoom.getLongDescription());  
        //The player object is 'moved'.  The Player object will automatically
        //notify objectWatcher that it has moved.         
        player.moveObject(nextRoom.getObjectLocation());    	
    }
    
    
    /** 
     * Ends the game and displays high score information.
     */
    private void endGame()
    {
    	
    	String playerName = player.getName();
    	int playerScore = player.getScore().getScore();
    	Highscore hs = Highscore.getInstance();
    	
    	int latestScore = hs.getPlayerScore(playerName);
    	if( playerScore > latestScore) {
    		hs.updatePlayerHighScore(playerName, playerScore);
    		
            gui.println("Thank you for playing Alcatraz!.");
            gui.println("You finished with a score of: " + playerScore);
            gui.println("Congratulations that's a new personally best!");
    	}else{
            gui.println("Thank you for playing Alcatraz!.");
            gui.println("You finished with a score of: " + playerScore);
            gui.println("Please play again for better score.");
    	}
    	
        //Add score to high score database
        //if(highScore != null) {
        //    highScore.insertNewPlayerToHighScore(playerName);
        //    highScore.updatePlayerHighScore(playerName, playerScore);
        //}
        
        //Open up high score screen
        //gui.showScoreGUI(highScore);
    }


    /** 
     * itemExists checks if an item of the given objectID exists inside the room of
     * the given roomID.  If the item is found in that room, the item is returned.
     * Otherwise, null is returned.
     * 
     * @param objectID : The unique ID of the item.
     * @param roomID   : The unique ID of the room
     * @return         : the Item object if it exists in the given room, null if does not
     */
    public Item itemExists(int objectID, int roomID)
    { 
        //Get the HashMap of item locations from objectWatcher
        HashMap<Integer,ArrayList<Item>> itemMap = objectWatcher.getItemMap();
        
        //Get the ArrayList of items for this particular room
        ArrayList<Item> itemList = itemMap.get((Integer)roomID);
        
        Iterator<Item> it = itemList.iterator();
        
        //If there are items in this room
        if(itemList != null) {
          //Make sure that item ID exists in this room by looping through
          //all items in the room
           while( it.hasNext() ) {    
               Item temp = it.next();
               //If the object does exist, note it, and break out of the
               //loop
               if(objectID == temp.getObjectID()) {
                  return temp;
               }
               //End if matching object
            }
            //End for loop    	
        }
        //End if list not null   
    	return null;
    }


    /** 
     * When the player tries to take OR drop an object via the GUI, this method takes
     * care of all game logic related to taking or dropping the item.  The moveObject
     * method called by the item object notifies objectWatcher of the movement, which
     * then updates the related ArrayLists and HashMap.  The player's backpack object
     * adds or removes the weight associated with the item, and the player's score is
     * raised or reduced by the item's value.
     * This method does NOT adjust the picture in the backpack GUI or the item's
     * picture on the room picture (which is handled in the GUI).
     * 
     * @param objectID : The unique ID of the item the player is trying to get or drop.
     * @param isGet    : A boolean value that should be true is the player is trying to 'get'
     *                   an item, and false, if the player is trying to 'drop' and item
     * @return         : true if the object is taken or dropped, false if something went wrong
     */
    public boolean getdropObject(int objectID, boolean isGet) 
    {
        //Get the HashMap of item locations from objectWatcher
        HashMap<Integer,ArrayList<Item>> itemMap = objectWatcher.getItemMap();
        
        int roomID;
        
        if(isGet) {         
            //Get the unique RoomID of the current room
            roomID = currentRoom.getObjectID();        	
        }
        else {
        	//0 is the RoomID of the player's backpack
        	roomID = 0;
        }
        
        //Get the ArrayList of items for this particular room
        ArrayList<Item> itemList = itemMap.get(roomID);
       
        Item item = null;
        
        //If there are items in this room, the itemList will exist
        if(itemList != null) {
        	//Check that the item exists in the itemList for the room or
        	//backpack (null if not found)
        	item = itemExists(objectID, roomID);
            //If the item exists in the room or backpack
            if(item != null) {
            	//Get the player's backpack and score objects
                Backpack backpack = player.getBackpack();
                Score score = player.getScore();
                
                //If taking the object
                if(isGet) {               	
                	//Attempt to add the weight of the item to the backpack
                    if(! backpack.addWeight(item)) {
                    	//If this returns false, it can only mean that the backpack
                    	//was full
                        gui.println("Your backpack is full!");    
                        return false;
                    }
                    else {
                        //Move the item from the currentRoom to Backpack.
                    	//objectWatcher will automatically update the ArrayLists for
                    	//both rooms, and update the HashMap.
                        item.moveObject(0);                
                        //Add an amount to score equal to the item value
                        score.addToScore(item.getItemValue());                     	
                    }
                }
                //otherwise dropping the object
                else {
                    //Move the item from the Backpack to currentRoom.
                	//objectWatcher will automatically update the ArrayLists for
                	//both rooms, and update the HashMap.
                    item.moveObject(currentRoom.getObjectID());                  	
                	//Remove the weight of the item to the backpack
                    backpack.removeWeight(item);                    
                    //Add a negative amount to score equal to the item value
                    score.addToScore(-1*item.getItemValue());                    
                }                
                
                //Display information for the player
                gui.println("Backpack Weight: " + player.getBackpack().getCurrentWeight() +
                		" of " + player.getBackpack().getCapacity() );                 
                //Display information for the player
                gui.println("Score: " + player.getScore().getScore() );                     
                
                //The item was successfully taken or dropped, return true 
                return true;
            }
        }
        //End if not null
        
        //The item ID was not found, return false  	
    	return false;
    }
    
    
    
    /**
     * goBackToPreviousRoom takes the player back to the room they were
     * in previously (or nowhere if they have gone nowhere, or have
     * backed all the way back to their starting room)
     * 
     */
    public void goBackToPreviousRoom()
    {	
    	//If it isn't the final room of the game (where the game ends)
    	if(!(currentRoom.equals(lastRoom))) {
    	
    	    //Get the map of all item locations from objectWatcher
    	    HashMap<Integer,ArrayList<Item>> itemMap = objectWatcher.getItemMap();
    	    LinkedList<Integer> playerList = objectWatcher.getPlayerList(); 
    	
    	    //If the player has ever been to a room (should always be true)
    	    if(playerList.size() > 0) {
    		    int i;
    	
    		    //If the player has been to more than 1 room, remove their current room
    		    //from the list, and move back to the previous one
    		    if(playerList.size() > 1) {
    	           playerList.removeLast();
    	           i = playerList.getLast();    	       
    		    }
    		    //Otherwise the player has only ever been to the starting room, so don't
    		    //remove it from the list
    	        else {
    	           i = playerList.getLast();	
    	        }
    	       
    		    //If for some reason an invalid room is entered, default to room 1
    	        if(i < 1) {
    		        i = 1;
    	        }   	    
    	    
    	        //The previous room to move to
    	        Room nextRoom = roomMap.get(i);
    	
    	        //As long as its not the same room, move back
    	        if(!(currentRoom.equals(nextRoom))) {
    		
    	            //Call the GUI method for loading a new room, sending in the
    	            //parameters of room ID (for the new room), and the itemMap from
    	            //objectWatcher with the locations of all items
    	            gui.changeRoom(i, itemMap, roomMap);
    	
    	            //The new room becomes the current room
    	            currentRoom = nextRoom;
    	            //Print out the new room's long description
    	            gui.println(currentRoom.getLongDescription());
    	        }
    	        //End if not switching to the same room
    	    }
    	    //End if player has ever been to a room
    	}
    	//End if not final room
    }  
    
    
    /**
     * Initializes items when creating the game, or loading a save game from
     * file.
     * 
     * @param itemMap : a HashMap of item ArrayLists, keyed by integer location
     */
    public void initializeItems(HashMap<Integer,ArrayList<Item>> itemMap)
    {		
	    //The keys for its item map are noted
        Set<Integer> itemKeys = itemMap.keySet(); 
		
        //Add items to be observed by the new ObjectWatcher
  
        //For all array lists in the hash map (based on itemKeys)
	    for(Integer j : itemKeys) {
		    //Get each array list
		    ArrayList<Item> itemList = itemMap.get(j);
		   
		    if(itemList != null) {
		        //For all items in the list
			    for(Item item : itemList) {
			        //Make them observable by the new ObjectWatcher
				    Observable oItem = (Observable)item;
				    oItem.addObserver(objectWatcher);
				    //Place the items in the game world
				    item.moveObject(item.getObjectLocation());
			    }
		    }
        }     	
    }
    
    
    /**
     * saveSavegame saves the ObjectWatcher and Player object to file
     * 
     * @param filename : a String name for the file to save
     */
    public void saveSavegame(String filename)
    {		
    	//Create the GameState object
    	GameState gameState = new GameState(roomMap, objectWatcher.getItemMap(), player, objectWatcher.getPlayerList());
    	
    	if(fileManager.save(filename, gameState)==true) {
		    gui.println("Game saved!");    		
    	}
    	else {
    		gui.println("Game not saved! An error occured!");	    		
    	}
    }

    
    /***
     * loadSavegame loads the ObjectWatcher and player into the game.  All items are
     * set as observed by the game's objectWatcher (required), the player is re-initialized,
     * and the room is changed to the last room the player was in.  The roomMap is loaded
     * so that unlocked doors are remembered from the previous state, and the backpack GUI
     * is reloaded with items the player currently has.
     * 
     * @param filename
     */
    public void loadSavegame(String filename)
    {    		
    	//Load the ObjectWatcher with the FileManager object
    	GameState gameState = (GameState)fileManager.load(filename);
    	
    	//If the ObjectWatcher was successfully loaded
    	if(gameState != null) {   	
            //The data is loaded from file, and must now be initialized in game logic
    		
    	    //Create a new objectWatcher instance to wipe clean the old one    		
    	    objectWatcher = new ObjectWatcher();      		
    		
    	    //Load the roomMap to make sure unlocked doors stay unlocked
    	    roomMap = gameState.getRoomMap();     	    
    	    
    	    //The temporary ObjectWatcher's item map is retrieved
    	    HashMap<Integer,ArrayList<Item>> itemMap = gameState.getItemMap();
  		
    	    initializeItems(itemMap);
    		
    	    //Get the list of rooms the player has been to previously
    	    LinkedList<Integer> playerList = gameState.getPlayerList();  	    
    	    
    	    //If the list is not null
    	    if(playerList != null) {
    	        //The player room is the last room the player was in
                Room playerRoom = roomMap.get(gameState.getPlayerList().getLast());
    		     	
    		    //Call the GUI method for loading a new room, sending in the
    		    //parameters of room ID (for the new room), and the itemMap from
    		    //objectWatcher with the locations of all items        	
    		    gui.changeRoom(playerRoom.getObjectLocation(),itemMap,roomMap);        
    		    //The new room becomes the currentRoom         
    		    currentRoom = playerRoom;    
    		    //Print out the new room's long description          
    		    gui.println(currentRoom.getLongDescription());
            }
    		
    	    //Get the player object from the game state
    	    player = gameState.getPlayer();
    	    
    	    if(player != null) {
    	        //Make the player observable and add it to the object watcher
    	        Observable oPlayer = (Observable)player;
    	        oPlayer.addObserver(objectWatcher);     		
    		
    	        //Load the player into the game
    	        initializePlayer(player.getObjectID(), player.getObjectLocation(), player.getName(), player.getBackpack(), player.getScore());    		
    	    }
    	    else {
        		gui.println("Warning, null player error!");	    	    	
    	    }
    	        
    	    //Load the player's previous walk-path into the ObjectWatcher
    	    objectWatcher.setPlayerList(playerList);
    	    
    	    //Load the backpack GUI
    	    gui.loadBackpack(itemMap);    		  		     		   		    		   				
    		
		    gui.println("Game loaded!");
    	}
    	else {
    		gui.println("Game not loaded! An error occured!");	
    	}    		
    }
    
    
    /**
     * loadGameFile loads game information from file.  It loads the GameData object
     * which holds information about which room is the first room, what rooms exist
     * and how they connect to each-other, and what items exist in the game.  The
     * items are then loaded into the ObjectWatcher object, which begins observing
     * them.  The HashMap of rooms in loaded into this GameEngine object, and the
     * current room (currentRoom variable) is set based upon the first room.
     * Each room already contains exits that lead to one or more other rooms.
     * 
     * @param filename : a String filename for the game file to load
     */
    public void loadGameFile(String filename)
	{
    	//Default game file
    	if(filename == null || filename == "") {
    	    filename = "game.gam";
    	}		
    	
    	//Load the GameData object with the FileManager object
    	GameData gameData = (GameData)fileManager.load(filename);    		
    		
    	//The GameData's room map is retrieved
    	roomMap = gameData.getRoomMap();   		
    		
    	//The GameData's firstRoom integer is retrieved and used
    	//to identify the first room
    	currentRoom = roomMap.get(gameData.getFirstRoom());

    	//The GameData's lastRoom integer is retrieved and used
    	//to identify the last room
    	lastRoom = roomMap.get(gameData.getLastRoom());    		
    		
    	//The GameData's item map is retrieved
    	HashMap<Integer,ArrayList<Item>> itemMap = gameData.getItemMap();
    		
        //Add items to be observed by the Object Watcher
	    initializeItems(itemMap);            	
	}

  
	
}