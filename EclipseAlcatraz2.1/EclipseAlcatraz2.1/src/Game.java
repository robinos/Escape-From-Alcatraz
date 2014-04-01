import GUI.PlayerRegister;

/**
 *  This class is the main class of the "Escape from Medieval Alcatraz" application. 
 *  "Medieval Alcatraz" is a very simple, text and graphical based adventure
 *  game.  Users can pick up keys to unlock doors, valuable items to increase their
 *  score, and attempt to win the game by leaving the castle.
 * 
 *  This main class starts the player register, where a player logs into the game.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

public class Game
{
	private PlayerRegister pr;
	
    public Game() 
    {
    }
    
    
    /**
     * Main for the Game.  This initializes the game and
     * calls the selectPlayer method.
     */    
    public static void main(String[] arg)
    {
        Game game = new Game();
        game.selectPlayer();
    }
    
    
    /**
     * selectPlayer creates a new PlayerRegister object that brings
     * up the starting screen where the player registers their name
     * for the game.
     * 
     */
    private void selectPlayer()
    {
    	pr = new PlayerRegister();
    	
    }
}
