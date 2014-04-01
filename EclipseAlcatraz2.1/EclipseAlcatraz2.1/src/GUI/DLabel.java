/**
 * The DLabel class is an extension of JLabel to provide new functionality for
 * pictures and locating mouse clicks on the picture.
 * 
 */

package GUI;
import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DLabel extends JLabel
{
	//Instance variables
	JPopupMenu menu = new JPopupMenu("Popup");
	int x1, y1;
    private static final long serialVersionUID = 7L;	
	String hPath;	
	setPath sPath=new setPath();
	String path=sPath.setThePath(hPath);
	 
	
	/**
	 * DLabel Constructor
	 * 
	 * @param str : a String representing a picture filename
	 */
    public DLabel(String str)
    {
	    this.setIcon(new ImageIcon(path + str + ".gif"));
	    addMouseListener(new PopupTriggerListener());
    }
    
    
    /**
     * PopupTriggerListener is an internal class in DLabel that extends
     * MouseAdapter.
     */
    class PopupTriggerListener extends MouseAdapter {
        public void mousePressed(MouseEvent ev) {
    	    x1 = ev.getX(); y1= ev.getY();

            if (ev.isPopupTrigger()) {
                menu.show(ev.getComponent(), ev.getX(), ev.getY());
            }
        }

        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                menu.show(ev.getComponent(), ev.getX(), ev.getY());
            }
        }

        public void mouseClicked(MouseEvent ev) {
        }
    
        public JPopupMenu menu (){
    	    return menu;
        }
    }
	
    
    /**
     * getX1 gets the x-coordinate clicked
     * 
     * @return : integer value x1
     */
    public int getX1(){
	    return x1;
    }
    
    
    /**
     * getY1 gets the y-coordinate clicked
     * 
     * @return : integer value y1
     */
    public int getY1(){
	    return y1;
    }

}