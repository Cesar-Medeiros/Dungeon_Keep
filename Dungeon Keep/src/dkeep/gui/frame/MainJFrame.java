package dkeep.gui.frame;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dkeep.gui.menu.MenuController;
import dkeep.gui.menu.MenuPanel;


public class MainJFrame extends JFrame {

	public static final Dimension PREFERREDSIZE = new Dimension(650,500);
	
	private static final long serialVersionUID = 1L;
	
	public MainJFrame() {
		super("Dungeon Keep");
		
		MenuPanel menu = new MenuPanel(new MenuController(this));
		this.setContentPane(menu);
	}
	
	public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new MainJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(PREFERREDSIZE);
        frame.setPreferredSize(PREFERREDSIZE);
 
        //Center the frame
        frame.setLocationRelativeTo(null);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public void changeContentPane(JPanel jPanel) {
		setContentPane(jPanel);
		revalidate();
		repaint();
	}
	
	
}
