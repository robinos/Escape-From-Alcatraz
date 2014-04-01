/**
 * This class implements a simple graphical user interface with a
 * text output area, an image for the room, and buttons to interact with
 * the game (such as the direction and back buttons).  Clicking on an item
 * 'gets' it, and right clicking on the picture opens the backpack GUI, where
 * clicking on an item in the backpack will 'drop' it at the current location.
 * There is also a File, HighScore, and Help menu.
 * File allows you to choose a NewPlayer (including yourself for a new game),
 * save to the game, load to load a previous game, and quit to quite the game.
 * HighScore shows the high scores from the database.
 * Help allows you to choose help, giving a helping description of the game and
 * its commands, and About displays information about the game version.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
//import java.io.File;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Map;
import Controller.GameEngine;
import Model.Item;
import Model.Room;
//import Model.Door;
//import Model.ObjectWatcher;
//import Model.Highscore;

public class GUI implements MouseListener, ActionListener
{
	
    //private static final String VERSION = "Version 1.1";
	JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
	
    private GameEngine gameEngine;
    
    //private HighScoreFrame scoreGUI;
    
    private JFrame myMainFrame;
    private JTextArea textLog;
    private DLabel imageLabel;      
    private DButton north;
    private DButton east;
    private DButton south;
    private DButton west; 
    private DButton back;
    //private DButton look;
    //private DButton inventory;
    //private DButton help;
    
    private JLabel playername;
    
    int dlsize;
    int itemID = 0;
    public DLabel[] allItems;
    ArrayList<DLabel> dList;
    ArrayList<JMenuItem> backList = new ArrayList<JMenuItem>();
    HashMap<Integer,DLabel> labelList;   
    
    String hPath;
	setPath sPath=new setPath();
	String path=sPath.setThePath(hPath);

	private final int widthWindow = 540;
	private final int heightWindow = 400;
	
	
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public GUI(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        createGUI();
    }

    
    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        textLog.append(text);
        textLog.setCaretPosition(textLog.getDocument().getLength());
    }

    
    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        textLog.append(text + "\n");
        textLog.setCaretPosition(textLog.getDocument().getLength());
    }

    
    /**
     * Set up graphical user interface.  An image panel, a text log, command,
     * buttons, and a menu are added.
     */
    private void createGUI()
    {
    	/**Main Frame**/        
        myMainFrame = new JFrame("Medieval Alcatraz");
        
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	int X = (screen.width / 2) - (widthWindow / 2); // Center horizontally.
    	int Y = (screen.height / 2) - (heightWindow / 2); // Center vertically.

    	myMainFrame.setBounds(X,Y , widthWindow,heightWindow);
    	
    	makeMenuBar();
        
        JPanel contentPane = (JPanel)myMainFrame.getContentPane();
        function func = new function();
    	func.MousePointer(myMainFrame);
        
        //The movement buttons
        north = new DButton("north");
        north.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //Button pushed action                  
            gameEngine.goRoom("north");
            }
        });       
        north.setEnabled(false);         
        east = new DButton("east");
        east.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //Button pushed action                     
            gameEngine.goRoom("east");
            }
        });         
        east.setEnabled(false);                   
        west = new DButton("west");
        west.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //Button pushed action                     
            gameEngine.goRoom("west");
            }
        });         
        west.setEnabled(false);           
        south = new DButton("south");
        south.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //Button pushed action                     
            gameEngine.goRoom("south");
            }
        });   
        south.setEnabled(false);                  
        back = new DButton("back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //Button pushed action                 	
            gameEngine.goBackToPreviousRoom();
            }
        });         
        back.setEnabled(true);  
        
        DLabel lab = new DLabel("SPic");
        
        JPanel downPane = new JPanel();
        JPanel movementPane = new JPanel();
        //Add the movement Pane
        movementPane.add(new JLabel());
        movementPane.add(north);
        movementPane.add(new JLabel());
        movementPane.add(west);
        movementPane.add(back);        
        movementPane.add(east);
        movementPane.add(new JLabel());
        movementPane.add(south);
        movementPane.add(new JLabel());
        movementPane.setLayout(new GridLayout(3,3));
        movementPane.setBackground(Color.DARK_GRAY);
        movementPane.setOpaque(false);
        lab.setLayout(new BoxLayout(lab, BoxLayout.X_AXIS  ));
        lab.add(Box.createHorizontalGlue());
        lab.add(movementPane);
        
        /**Text Display**/
        textLog = new JTextArea();
        textLog.setEditable(false);
        textLog.setForeground(Color.white);
       // textLog.setBackground(Color.DARK_GRAY);
        textLog.setOpaque(false);
        JScrollPane listScroller = new JScrollPane(textLog);
        listScroller.setPreferredSize(new Dimension(410, 100));
        listScroller.setMinimumSize(new Dimension(300,96));
        listScroller.setHorizontalScrollBarPolicy(listScroller.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller.setVerticalScrollBarPolicy(listScroller.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroller.setOpaque(false);
        listScroller.getViewport().setOpaque(false);
        listScroller.setBorder( LineBorder.createGrayLineBorder());
        lab.add(listScroller);
        downPane.add(lab);
        downPane.setBackground(Color.LIGHT_GRAY);
        
        JPanel centerPanel = new JPanel();
        imageLabel = new DLabel("");
        centerPanel.add(imageLabel);
        centerPanel.setBackground(Color.lightGray);
        
        JPanel rightPanel = new JPanel();
        DLabel rPic = new DLabel("rPic");
       // rPic.setPreferredSize(new Dimension(120, 300));
        JLabel player = new JLabel("Player Name:");
        player.setForeground(Color.white);
        rPic.add(player);
        playername = new JLabel(gameEngine.getPlayer().getName());
        
        playername.setForeground(Color.white);
        rPic.add(playername);
        rPic.setLayout(new BoxLayout(rPic, BoxLayout.Y_AXIS  ));
        rPic.add(Box.createVerticalGlue());
        
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(downPane, BorderLayout.SOUTH);        
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EtchedBorder());
        contentPane.add(rPic, BorderLayout.EAST);
        contentPane.setSize(widthWindow, heightWindow );
        myMainFrame.setResizable(false);
        myMainFrame.pack();
        myMainFrame.setVisible(true);
        
        // add some event listeners to some components
        myMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
       
        //Initialize the GUI for the first room
        changeRoom(gameEngine.getCurrentRoom().getObjectID(), gameEngine.getObjectWatcher().getItemMap(), gameEngine.getRoomMap());
    }
    
    
    /**
     * makeMenuBar creates the menu.
     * 
     */
    private void makeMenuBar()
    {
    	
    	JMenuBar menuBar = new JMenuBar();
    	myMainFrame.setJMenuBar(menuBar);
    	
    	// Create file menu
    	JMenu fileMenu = new JMenu("File");
    	menuBar.add(fileMenu);
    	// Create high score menu    	
    	JMenu highScoreMenu = new JMenu("High Scores");
    	menuBar.add(highScoreMenu);
    	// Create help menu    	
    	JMenu helpMenu = new JMenu("Help");
    	menuBar.add(helpMenu);    	
    	
    	//Add New Player to the File menu
    	JMenuItem newPlayerMenuItem = new JMenuItem("New Player");
    	fileMenu.add(newPlayerMenuItem);
    	newPlayerMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            int i =	JOptionPane.showConfirmDialog(null, "If you continue all your progress will be lost",
            									 "Change Player", JOptionPane.OK_CANCEL_OPTION);
            	if( i == 0) {
            		myMainFrame.dispose();
            		new PlayerRegister();
            	}
            }
        });  	    	
    	//Add Save to the File menu
    	JMenuItem savegameMenuItem = new JMenuItem("Save");
    	fileMenu.add(savegameMenuItem);
    	savegameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        	    int returnVal = fileChooser.showSaveDialog(myMainFrame);
        	    if(returnVal != JFileChooser.APPROVE_OPTION)
        	    	return;
        	    
        	    File selectedFile = fileChooser.getSelectedFile();
        	    String fileName = fileChooser.getName(selectedFile);       	    
        	    
                gameEngine.saveSavegame(fileName);
            }
        });      	
    	//Add Load to the File menu
    	JMenuItem loadgameMenuItem = new JMenuItem("Load");
    	fileMenu.add(loadgameMenuItem);
    	loadgameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        	    int returnVal = fileChooser.showOpenDialog(myMainFrame);
        	    if(returnVal != JFileChooser.APPROVE_OPTION)
        	    	return;
        	    
        	    File selectedFile = fileChooser.getSelectedFile();
        	    String fileName = fileChooser.getName(selectedFile);
        	    
                gameEngine.loadSavegame(fileName);
                setPlayerNameOnLabel();
            }
        });
    	//Add Quit to the menu
    	JMenuItem quitMenuItem = new JMenuItem("Quit");
    	fileMenu.add(quitMenuItem);
    	quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });    	    	
    	
    	//Add High Score to the High Score menu    	
    	JMenuItem highScoreMenuItem = new JMenuItem("High Scores");
    	highScoreMenu.add(highScoreMenuItem);
    	highScoreMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HighScoreFrame();
            }
        });
    	
    	//Add Help to the Help menu    	
    	JMenuItem helpMenuItem = new JMenuItem("Help");
    	helpMenu.add(helpMenuItem);
    	helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	StringBuffer outputString = new StringBuffer(200);
            	outputString.append("Find the keys to open the doors that block you path, and gain freedom!\n");
            	outputString.append("Loot items along your path for a better chance in the world once you\n");
            	outputString.append("escape (this will increase your score).\n\n\n");
            	outputString.append("Movement:  The movement keys will take you 'north', 'east', 'west', and\n");
            	outputString.append("'south'.\n\n");             	
            	outputString.append("Back:  If you want to trace your path backwards, press the button between.\n");
            	outputString.append("the movement keys.\n\n");
            	outputString.append("Look: Rooms descriptions are written in the text field, including exists.\n\n");
            	outputString.append("Get:  left click on an item to pick it up!  There are only so many items your\n");
            	outputString.append("backpack can hold so choose well.\n\n");  
            	outputString.append("Drop:  Right click on the screen to see your backpack.  You can drop an item\n");
            	outputString.append("by selecting it.\n\n");
            	outputString.append("Good luck!");            	
            	JOptionPane.showMessageDialog(myMainFrame, outputString.toString(), "Help", JOptionPane.DEFAULT_OPTION); 
            }
        });
    	//Add About to the Help menu    	
    	JMenuItem aboutMenuItem = new JMenuItem("About");
    	helpMenu.add(aboutMenuItem);
    	aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(myMainFrame, "Escape from Medieval Alcatraz, version 1.4", "About", JOptionPane.OK_OPTION);
            }
        });     	
    }
    
    public void setPlayerNameOnLabel()
    {
    	playername.setText(gameEngine.getPlayer().getName());
    	myMainFrame.pack();
    }
    
    /**
     * changeRoom switches rooms for the GUI of the game, changing room picture and items
     * displayed, as well as activating direction buttons based on what directions are
     * valid for that room.
     * 
     * @param newRoomID : the ID of the room
     * @param itemMap   : a hash map of ArrayLists of Items, keyed by an integer
     *                    representing location
     * @param roomMap   : a hash map of Room objects, keyed by an integer representing
     *                    location
     */
    public void changeRoom(int newRoomID, HashMap<Integer,ArrayList<Item>> itemMap, HashMap<Integer,Room> roomMap)
    {  
        //Hide item pictures from the previous room
    	if(dList !=null)
    	for(int i = 0; i < dList.size();i++){
	                  dList.get(i).setVisible(false);       
	    }

        //Diable all exits
        north.setEnabled(false); 
        east.setEnabled(false); 
        west.setEnabled(false); 
        south.setEnabled(false);                                      
        
        //The room to move to
        Room room = roomMap.get(newRoomID);
        
        //Load the picture
        imageLabel.setIcon(new ImageIcon (path + room.getPicture()));
        //Add Items
        addItems(newRoomID, itemMap);
        
        //Check which exits are valid, and activate buttons for valid directions
        if(room.getExit("north") != null) {
        	north.setEnabled(true);
        }
        if(room.getExit("east") != null) {
        	east.setEnabled(true);
        }
        if(room.getExit("south") != null) {
        	south.setEnabled(true);
        }
        if(room.getExit("west") != null) {
        	west.setEnabled(true);
        }        
        
        myMainFrame.pack();         
    }
    
//  /**
//  * Shows the high score GUI.
//  */
// public void showScoreGUI(Highscore score)
// {
// 	scoreGUI = new HighScoreFrame(score);
// }
 
    
    /**
     * addItems adds items as DLabels to the room picture, cycling through the
     * itemMap to determine which items are currently in the room.
     * The item objects hold information about their current location on the room
     * picture, and what display icon they have.
     * 
     * @param roomID  : The id of the room.
     * @param itemMap : The itemMap of items in all rooms.
     */
    public void addItems(int roomID, HashMap<Integer,ArrayList<Item>> itemMap)
    { 
           //Load the item pictures for the room    
    	   dList = new ArrayList<DLabel>() ;
           if( itemMap.containsKey( (Integer)(roomID) ) ) {
               ArrayList<Item> itemList = itemMap.get(roomID);                               
               if(itemList != null) {
            	   //For each item in the room
                   for(Item item : itemList) {
                      // if(item.equals(gameEngine.itemExists(item.getObjectID(), roomID))) {
                    	 
                	   //Add the DLabel of that item, with picture, at the given location
                	   DLabel temp =new DLabel(item.getPicture()) ;
                    	    temp.setName(item.getObjectID()+"");
                    	   imageLabel.add(temp);
                    	   if(itemID==item.getObjectID()) {
                    		   item.setXLoc(imageLabel.getX1()-20);
                    		   item.setYLoc(imageLabel.getY1()-20);
                    		   
                    	   }
                    	   temp.setBounds(item.getXLoc(), item.getYLoc(), item.getWidth(), item.getHeight());
                           temp.setVisible(true);
                           temp.addMouseListener(this);
                           dList.add(temp);
                       //}
                	  
                   }
                   //end for
               }
               //end if not null
           }        
           //end contains
    }
    
    /**
     * loadBackpack loads the backpack GUI when loading the game, first by
     * removing current items, then by adding the items from the savegame's
     * backpack. 
     *
     *@param itemMap : A hash map of ArrayLists of items, keyed by an Integer
     *                 representing location
     */
    public void loadBackpack(HashMap<Integer,ArrayList<Item>> itemMap) {
    	
    	//Get the array of items for the backpack (location 0)
    	ArrayList<Item> itemList = itemMap.get(0);
    	
    	//backList is an Array of JMenuItems used for backpack display.
    	//These are set false for previous items.
    	for(JMenuItem item : backList) {
			item.setVisible(false); 
			itemID = Integer.parseInt(item.getName());			
    	}
    	
    	//For each item in the savegame player's backpack, add them
    	//to the backpack GUI as JMenuItems (tItem).
    	//Since JMenuItems are usually creates by comparing to the DLabels
    	//used to display items on room pictures (normally held by dList),
    	//temporary DLabels are created to make the process easier.
    	for(Item item : itemList) {
     	    DLabel temp = new DLabel(item.getPicture());
   	        temp.setName(item.getObjectID()+"");
    		JMenuItem tItem  =  new JMenuItem(temp.getIcon());
		    tItem.setBackground(Color.DARK_GRAY);
		    tItem.setName(temp.getName());
		    tItem.addActionListener(this);    		
    		backList.add(tItem);
		    imageLabel.menu.add(tItem);   		
    	}
    }
    
    /**
     * MouseEvents are defined below.
     * MouseClicked allows the player to pick up items upon clicking its DLabel
     * mouseEntered, mouseExited, mousePressed, and mouseReleased are empty
     */
    public void mouseClicked(MouseEvent e){	
    	//dList is a defined list of item DLabels for a room
    	for(int i = 0; i < dList.size();i++){
    		    //If the component clicked exists in the dList for that location, the gameEngine
    		    //method getdropObject is called to attempt to get the object.  On a success,
    		    //the room DLabel is hidden, and the backpack JMenuItem is added. 
    		    if(e.getComponent()==dList.get(i)) {
			        if(gameEngine.getdropObject(Integer.parseInt(dList.get(i).getName()),true)) {  
				        dList.get(i).setVisible(false); 
				        JMenuItem tItem  =  new JMenuItem(dList.get(i).getIcon()); 
				        tItem.setBackground(Color.DARK_GRAY);
				        tItem.setName(dList.get(i).getName());
				        tItem.addActionListener(this);
				        backList.add(tItem);
				        imageLabel.menu.add(tItem);
	                }
    		    }
    	 } 	
    }
	public void mouseEntered(MouseEvent e) {	
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {	
	}
	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	/**
	 * This action performed deals with dropping items from the backpack menu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//backList is a defined list of JMenuItems used to display backpack items.
		//If one of them is clicked, the GameEngine method getdropObject is called to
		//drop the object, and if successful, the JMenuItem is removed from the backpack
		//GUI menu.  changeRoom is called to update the room items with the newly dropped
		//item.
		for(int i = 0; i <backList.size();i++){
			if(e.getSource() == backList.get(i)) {
				if(gameEngine.getdropObject(Integer.parseInt(backList.get(i).getName()),false)){
				    backList.get(i).setVisible(false);
				    itemID = Integer.parseInt(backList.get(i).getName());
				}
			}
			    
		}
		changeRoom(gameEngine.getCurrentRoom().getObjectID(), gameEngine.getObjectWatcher().getItemMap(), gameEngine.getRoomMap());
	}
	
}
