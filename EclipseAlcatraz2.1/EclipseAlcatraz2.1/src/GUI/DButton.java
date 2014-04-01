/**
 * The DButton class is an extension of JButton that allows picture representation
 * instead of text.
 */

package GUI;
import java.awt.*;
//import java.awt.event.*;
//import java.io.File;
import javax.swing.*;
//import javax.swing.border.Border;
//import javax.swing.border.CompoundBorder;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;


public class DButton extends JButton {
	
    //Instance variables
    private static final long serialVersionUID = 6L;		
	
	String hPath;
	setPath sPath=new setPath();
	String path=sPath.setThePath(hPath);

    /**
     * Constructor for DButton
     * 	
     * @param str : String representing filename for the image icon
     */
    public DButton(String str)
    {
	    setIcon(new ImageIcon(path + str + ".gif"));
	    setPreferredSize(new Dimension(32, 32));
	
	    setDisabledIcon(new ImageIcon(path + str +"1"+".gif"));
	    //setBorder( LineBorder.createGrayLineBorder());

        //   setForeground(Color.BLACK);
        setBackground(Color.BLACK);
//	    Border line = new LineBorder(Color.BLACK);
//	    Border margin = new EmptyBorder(5, 15, 5, 15);
//	    Border compound = new CompoundBorder(line, margin);
//	    setBorder(compound);
    }
    
}
