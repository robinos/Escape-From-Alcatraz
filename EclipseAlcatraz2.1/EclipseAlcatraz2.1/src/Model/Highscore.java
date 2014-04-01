/**
 * This class handles the query to the online database that records the high
 * score from all players who have finished the game.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Model;
import java.sql.*;

public class Highscore {
	
	String player_name;
	String username = "alcatraz123";
	String password = "12345r";
	private static Highscore instance = null;
	
	private Highscore() {}
	
	
	/**
	 * This is a singleton.
	 * 
	 * @return : Highscore object
	 */
	public static synchronized Highscore getInstance()
	{
		if ( instance == null )
			instance = new Highscore();
		return instance;
	}
	
	
	/**
	 * Returns a player's score.
	 * 
	 * @param name : a String representing the player's name
	 * @return     : an integer value representing the player's score
	 */
	public int getPlayerScore(String name)
	{	
		int sendscore = 0;
		try {			
			String query = "SELECT score FROM alcatraz123.highscore WHERE playername = '"+name+"'" ;
			Connection con = DriverManager.getConnection(
			        "jdbc:mysql://alcatraz123.dolotube.com:3306/", username,
			        password);
			Statement stmt = con.createStatement();			
			ResultSet rs = stmt.executeQuery(query);

		    if (rs.next())
		    {
		    	sendscore = rs.getInt(1);
		    }		    		    		    
		    rs.close();
		    return sendscore;
		
		} catch (SQLException e) {
			e.printStackTrace();
			
			return sendscore;
		}		
	}
	
	
	/**
	 * Inserts a new player to the database with the beginning score of 0
	 * 
	 * @param name : A String representing a new player's name
	 */
	public void insertNewPlayerToHighScore(String name)
	{	
		int startscore = 0;
		try {			
			String query = "INSERT INTO alcatraz123.highscore(playername,score) VALUES ('" + name + "', "+startscore+")" ;
			Connection con = DriverManager.getConnection(
			        "jdbc:mysql://alcatraz123.dolotube.com:3306/", username,
			        password);
			Statement stmt = con.createStatement();			
			stmt.executeUpdate(query);
	    		    		    
		    con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Updates an existing players score.
	 * 
	 * @param name     : a String representing the name of the player
	 * @param newScore : the integer value of the new score
	 */
	public void updatePlayerHighScore(String name, int newScore)
	{	
		try {			
			String query = "UPDATE alcatraz123.highscore SET score = "+newScore+" WHERE playername = '" +name+ "'";
			Connection con = DriverManager.getConnection(
			        "jdbc:mysql://alcatraz123.dolotube.com:3306/", username,
			        password);
			Statement stmt = con.createStatement();			
			stmt.executeUpdate(query);
	    		    		    
		    con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}


	/**
	 * Returns a list of scores or players sorted by highest score. The in-parameter can
	 * be player name or score dependent upon which list you want.
	 * 
	 * @param coloumn : a String representing your choice of column
	 * @return        : a String Array holding a list or scores or players
	 */
	public String[] getHighScoreList(String coloumn)
	{	
		
		String[] highscorelist = new String[getNumberOfPlayers()];
		int counter = 0;
		try {			
			String query = "SELECT playername, score FROM alcatraz123.highscore ORDER BY score DESC LIMIT 10" ;
			Connection con = DriverManager.getConnection(
			        "jdbc:mysql://alcatraz123.dolotube.com:3306/", username,
			        password);
			Statement stmt = con.createStatement();			
			ResultSet rs = stmt.executeQuery(query);
			
			
			if ( coloumn == "score") {
				while (rs.next())
				{
					highscorelist[counter] = Integer.toString(rs.getInt(coloumn));
					counter++;
				}
			}	
			else if ( coloumn == "playername") {
				while (rs.next())
			    {
			    	highscorelist[counter] = rs.getString(coloumn);
			    	counter++;
			    }
			}
			
		    rs.close(); 
		    
		    return highscorelist;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return highscorelist;
		}
		
		
	}
	

	/**
	 * Return the number of players on the High Score list.
	 * 
	 * @return : an integer value
	 */
	public int getNumberOfPlayers()
	{	
		int counter = 0;
		try {			
			String query = "SELECT playername, score FROM alcatraz123.highscore ORDER BY score DESC" ;
			Connection con = DriverManager.getConnection(
			        "jdbc:mysql://alcatraz123.dolotube.com:3306/", username,
			        password);
			Statement stmt = con.createStatement();			
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next())
			{
				counter++;
			}
						
		    rs.close();	    
		    return counter;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return counter;
		}		
	}
	

	/**
	 * Returns boolean if a player's name exists
	 * 
	 * @param name : a String name representing the player's name
	 * @return     : true if the name exists, false otherwise
	 */
	public boolean playerExistCheck(String name)
	{	
		
		try {			
			String query = "SELECT playername FROM alcatraz123.highscore WHERE playername = '"+name+"'" ;
			Connection con = DriverManager.getConnection(
			        "jdbc:mysql://alcatraz123.dolotube.com:3306/", username,
			        password);
			Statement stmt = con.createStatement();			
			ResultSet rs = stmt.executeQuery(query);

		    if (rs.next())
		    {
		    	rs.close();
		    	return true;
		    }		    		    		    
		    else
		    {	
		    	rs.close();
		    	return false;
		    }
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}		
	}
	
}
