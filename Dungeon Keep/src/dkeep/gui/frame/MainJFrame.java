package dkeep.gui.frame;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import dkeep.gui.menu.MenuController;

public class MainJFrame extends JFrame {

	public static final Dimension PREFERREDSIZE = new Dimension(650,500);
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Main menu's window constructor
	 */
	public MainJFrame() {
		super("Dungeon Keep");
		MenuController menuController = new MenuController(this);
		this.setContentPane(menuController.getMainPanel());
	}
	
	/**
	 * Sets up the graphical interface for the game,
	 * creating a new window frame.
	 */
	public static void createAndShowGUI() {
      
        JFrame frame = new MainJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(PREFERREDSIZE);
        frame.setPreferredSize(PREFERREDSIZE);
 
        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
    }
	
	/**
	 * Replaces the current panel being shown in the game's
	 * main frame for the one passed as a parameter.
	 * @param jPanel Panel to be portrayed
	 */
	public void changeContentPane(JPanel jPanel) {
		setContentPane(jPanel);
		revalidate();
		repaint();
	}
}
