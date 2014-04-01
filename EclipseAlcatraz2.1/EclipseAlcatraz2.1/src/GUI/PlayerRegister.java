/**
 * PlayerRegister is the starting GUI for "Escape from Medieval Alcatraz".
 * It allows creating a New Player, viewing High Scores, starting the game,
 * and loading a previous game.
 * 
 */

package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
//import java.io.IOException;

import javax.swing.*;

import Controller.GameEngine;
import Model.*;


public class PlayerRegister extends JFrame {
	
	//Instance variables
	JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));	
    private static final long serialVersionUID = 9L;	
	
	private String selectedPlayer = "";
	private JLabel selectedPlayerLabel;
	
	public GUI gui;
	public GameEngine engine;
	
	JPanel centerPane;	
	
	/**
	 * The Constructor for PlayerRegister
	 */
	public PlayerRegister()
	{
		makeFrame();	
	}
	

	/**
	 * makeFrame makes the frame.
	 */
	private void makeFrame()
	{	
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int X = (screen.width / 2) - (30 / 2); // Center horizontally.
		int Y = (screen.height / 2) - (100 / 2); // Center vertically.
		
		
		setBounds(X,Y , 30,100);
		Container pane = getContentPane();
		
		// Set Northlabel
		JLabel welcomeLabel = new JLabel("Escape from Alcatraz", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Serif", Font.BOLD,20));
		pane.add(welcomeLabel, BorderLayout.PAGE_START);
		
		// set right and left panel
		JPanel leftPanel = new JPanel();
		pane.add(leftPanel, BorderLayout.WEST);
		
		JPanel rightPanel = new JPanel();
		pane.add(rightPanel, BorderLayout.EAST);
		
		// Set a GridLayout in CENTER
		centerPane = new JPanel(new GridLayout(7,1));
		pane.add(centerPane, BorderLayout.CENTER);
		centerPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// The label that shows which player you have choosen
		JLabel selectPlayer = new JLabel("Select Player",JLabel.CENTER);
		centerPane.add(selectPlayer, BorderLayout.CENTER);
		
		Highscore hs = Highscore.getInstance();
		
		//The Existing Player combo box
		JComboBox existingPlayers = new JComboBox(hs.getHighScoreList("playername"));
		existingPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String tempselply = (String)cb.getSelectedItem();
						setPlayerName(tempselply);
						// Existing player chosen
			}
			});
		centerPane.add(existingPlayers, BorderLayout.CENTER);
		
		//The New Player button
		JButton newPlayerButton = new JButton("New Player");
		newPlayerButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String tempplayer = newPlayer();
						if ( tempplayer == null || tempplayer == "") {}
						else {
							setPlayerName(tempplayer);
						}
					}
				}
				);
		centerPane.add(newPlayerButton, BorderLayout.CENTER);
		
		JLabel infolabel = new JLabel("You have selected:");
		centerPane.add(infolabel, BorderLayout.CENTER);
		
		// The label that shows which player you have choosen
	    selectedPlayerLabel = new JLabel();
	    centerPane.add(selectedPlayerLabel, BorderLayout.CENTER);
		
	    //The Start Game Button
		JButton startGameButton = new JButton("Start game");
		startGameButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selectedPlayer != "")
						{
							startGameSession();
							dispose();
						}
					}
				}
				);
		centerPane.add(startGameButton, BorderLayout.CENTER);
		
		//The Load Button
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Load previous saved game
//						if(selectedPlayer != "")
//						{
							startGameSession();
							openSavedGameDialog();
							dispose();
//						}
					}						
				}
		        );
		
		centerPane.add(loadButton, BorderLayout.CENTER);
		centerPane.setPreferredSize(new Dimension(30,200));

		setResizable(false);
		setVisible(true);
		pack();
	}

	/**
	 * 	startGameSession starts a new game by initiate objects in right order
	 */
	private void startGameSession()
	{		
	    engine = new GameEngine();							
	    engine.setPlayer(getPlayerName());							    	
		gui = new GUI(engine);
		engine.setGUI(gui);
	}
	
	/**
	 * 	 opensSaveGameDialo opens a windows where you can choose your to load 
	 * 	 your previous saved game
	 */
	private void openSavedGameDialog()
	{
		//Chooses the file name
	    int returnVal = fileChooser.showOpenDialog(centerPane);
	    if(returnVal != JFileChooser.APPROVE_OPTION)
	    	return;
	    
	    File selectedFile = fileChooser.getSelectedFile();
	    String fileName = fileChooser.getName(selectedFile);
	    
	    engine.loadSavegame(fileName);
	    // Changes the players name label on the gui
	    gui.setPlayerNameOnLabel();
	    
	    setPlayerName(engine.getPlayer().getName());
	}
	
	/**
	 * getPlayerName gets the players name.
	 * 
	 * @return : a String representing the selected player's name
	 */
	private String getPlayerName()
	{
		return selectedPlayer;
	}
	
	
	/**
	 * setPlayerName sets the players name.
	 * 
	 * @param pn : a String representing the player's name
	 */	
	private void setPlayerName(String pn)
	{
		selectedPlayer = pn;
		selectedPlayerLabel.setText(pn);
		pack();
	}
	
	
	/**
	 * newPlayer opens a dialog window to get the a new players name.
	 * 
	 * @return : a String representing the new player's name
	 */
	private String newPlayer()
	{
		Highscore hs = Highscore.getInstance();
		String indata;
		try{
			indata = JOptionPane.showInputDialog("Enter your name");
			indata.trim();
			if( indata.length() == 0)	{
				JOptionPane.showMessageDialog(null,"Illegal name","Login", JOptionPane.ERROR_MESSAGE);
				return "";
			}
			else if(hs.playerExistCheck(indata))
			{
				JOptionPane.showMessageDialog(null,"Playername already exist","Login", JOptionPane.ERROR_MESSAGE);
				return "";
			}
			else
			{
				hs.insertNewPlayerToHighScore(indata);
				return indata;
			}
		}
		catch(Exception e){
			
		}
		return "";
	}

}
