/**
 * setPath sets the path the game searches for picture files.
 */

package GUI;
import java.io.File;
public class setPath{

	/**
	 * The Constructor for setPath
	 */
	public setPath()
	{
	}
	
	/**
	 * setThePath sets the filepath for pictures for the game.
	 * 
	 * @param p : a string representing the absolute path
	 * @return  : a string representing the file path for use by other classes
	 */
	public String setThePath(String p) {
		File bildmap = new File("Gif");
		p = bildmap.getAbsolutePath();
		return (p+"\\");
	}
}

