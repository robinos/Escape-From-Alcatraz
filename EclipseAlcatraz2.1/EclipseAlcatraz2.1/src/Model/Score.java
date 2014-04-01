/**
 * The Score class holds the player's score value, and all methods related to it.
 * It has a score field of type integer.
 * Score exists as its own class instead of a field in the Player class because
 * more functionality was meant to be added (and does not yet exist).
 * 
 * getScore and setScore return and change the score respectively.
 * addToScore adjusts score value by a given integer value (which may be negative).
 * The clone method allows score to be cloned.
 * The toString method returns a string with score information.
 * The equals method compares the score of instances of the Score object.
 * The hashCode method computes a value based on the score field.
 * 
 * @author  : Group 8 
 * @version : 2012 02 23, Version 1.4
 */

package Model;
import java.io.*;

public class Score implements Serializable
{
	//Subclasses of Score should have a serialVersionUID of 5xL where x
	//is a value 1-9.	
    private static final long serialVersionUID = 5L;	
	
    // instance variables
    private int score;
    
    
    /**
     * No argument Constructor for objects of class Score.  This is
     * required for serialization.
     */
    public Score()
    {
        // initialize instance variables
        score = 0;
    }

    
    /**
     * getScore returns an integer value
     * 
     * @return : an integer value
     */
    public int getScore()
    {
        return score;
    }
    
    
    /**
     * setScore sets the score to a given integer value
     * 
     * @param : an integer value
     */
    public void setScore(int score)
    {
        this.score = score;
    }  
    

    /**
     * addToScore adds (or subtracts when given a negative value)
     * the given value to the score
     * 
     * @param : an integer value
     */
    public void addToScore(int score)
    {
        this.score += score;
    }
    
    
    /**
     *  This is the clone method for Score.  It is meant to be
     *  overridden where needed by subclass methods. 
     */
    @Override      
    public Score clone()
    {
       try
       {         
          return (Score)super.clone();
       }
       catch(CloneNotSupportedException e)
       {
          throw new InternalError();
       }
    }

    
    /**
     *  A toString method for Score that returns a string describing the score
     *  
     *  @return : String with information on the score
     */
    public String toString()
    { 
    	return ("Score: " + score);
    }
    
    
    /**
     *  A basic equals method for Score that compares score values.
     *  
     *  @param obj : the object for comparison 
     *  @return    : a boolean value true or false 
     */
    public boolean equals(Object obj)
    { 
    	if(this == obj) {
    		return true;  //The same object
    	}
    	if(!(obj instanceof Score)) {
    		return false;  //Not of the same type
    	}
    	
    	Score other = (Score)obj;
    	
    	//Compare the score of the Score object to the score of the other Score
    	//object and return true or false
    	return(other.getScore() == score);
    }    
    
    
    /**
     *  A basic hashCode method for Score, for the score field that is
     *  compared in the equals method.
     *  
     *  @return : an integer value
     */
    public int hashCode()
    {     
    	int result = 17;
    	
    	result = 37 * result + score;   	
    	
    	return result;
    }
    
}
