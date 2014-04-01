/**
 * The FileManager class saves or loads an object to file.
 * 
 * The save method saves an object to a file of the given file name.
 * The load method loads an object from the given file name and returns
 * that object.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Controller;
import java.io.*;

public class FileManager {
	
	public FileManager() {	
	}  
	
    /**
     * Write an object to file. The default file name is game.sav
     * 
     * @param filename  : The String filename to be saved as.
     * @param Object object : The object to be saved.
     * 
     * @return : returns true if successful, false for invalid object or
     *           if an error was encountered
     */
    public boolean save(String filename, Object object)
    {
    	if(filename == null || filename == "") {
    	    filename = "game.sav";
    	}   	
    	
    	try {
    		//Open the file stream
    		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
    		   		
    		//Write the ObjectWatcher to file
    		if(object != null) {
        	    out.writeObject(object);        		
        	} 
        	//If not, invalid ObjectWatcher
        	else {
        		return false;
        	}
        	
        	//Close the file stream
    		out.close();
    		
    		return true;
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    

    /**
     * Load an object from file. The default file is game.sav
     * 
     * @param filename : The String filename to be loaded from.
     * @return         : returns the GameState object if successful, otherwise null
     */
    public Object load(String filename)
    {	   		
    	try {
    		//Open the file stream
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
    		
    		//Read the object
    		Object oData = in.readObject();
    		
    		//Close the file stream
    		in.close();

    		return oData;  		
    	}
    	catch(IOException ex) {
    		ex.printStackTrace();    		
    		return null;
    	}
    	catch(ClassNotFoundException e) {
    		e.printStackTrace();
    		return null;    		
    	}    	
    }  
}