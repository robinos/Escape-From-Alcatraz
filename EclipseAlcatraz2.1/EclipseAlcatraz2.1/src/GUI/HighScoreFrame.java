/**
 * HighScoreFrame is the GUI for showing high scores to the player.  It extends
 * JFrame.
 */

package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.Highscore;

public class HighScoreFrame extends JFrame {
	
	//Instance variables
    private static final long serialVersionUID = 8L;	
	
	private final int widthWindow = 30;
	private final int heightWindow = 100;
	
	/**
	 * The Constructor for HighScoreFrame.
	 */
	public HighScoreFrame()
	{
		makeFrame();
	}
	
	
	/**
	 * makeFrame creates the high score frame.
	 */
	private void makeFrame()
	{
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	int X = (screen.width / 2) - (widthWindow / 2); // Center horizontally.
    	int Y = (screen.height / 2) - (heightWindow / 2); // Center vertically.

    	setBounds(X,Y , widthWindow,heightWindow);
		setTitle("High Scores");
		Container pane = getContentPane();
		//pane.setLayout();
		
		JLabel titleLabel = new JLabel("Top High Scores", JLabel.CENTER);
		titleLabel.setFont(new Font("Serif", Font.BOLD,20));
		pane.add(titleLabel, BorderLayout.NORTH);
		
		// Set the high score panel
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(1,2));
		listPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pane.add(listPanel, BorderLayout.CENTER);

		Highscore hs = Highscore.getInstance();
		
		JList list1 = new JList(hs.getHighScoreList("playername"));
		listPanel.add(list1);
		JList list2 = new JList(hs.getHighScoreList("score"));
		listPanel.add(list2);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();			
					}
				}
				);
		pane.add(closeButton, BorderLayout.SOUTH);
		
		setResizable(false);
		setVisible(true);
		pack();
	}

}
