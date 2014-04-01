/**
 * The function class sets a custom mouse pointer.
 */

package GUI;
import java.awt.Image;
import java.awt.Point;
import javax.swing.*;

public class function
{
	//Instance variables
	String hPath;
	setPath sPath=new setPath();
	String path=sPath.setThePath(hPath);
	final Image cursorImage = new ImageIcon(path + "mouse.gif").getImage();
	final Point hotspot = new Point(10, 10);
	final String name = "My Cursor";

	//Constructor for function
	public function()
	{
	}
	
	
	/**
	 * MousePointer sets a custom mouse pointer in the JFrame
	 * 
	 * @param frame : a JFrame
	 */
	public void MousePointer(JFrame frame)
	{
		frame.setCursor(frame.getToolkit().createCustomCursor(cursorImage, hotspot, name));
	}

	
	/**
	 * MousePointerDialog sets a custom mouse pointer in the JDialog frame
	 * 
	 * @param frame : a JDialog frame
	 */
	public void MousePointerDialog(JDialog frame)
	{
		frame.setCursor(frame.getToolkit().createCustomCursor(cursorImage, hotspot, name));
	}
	
}
