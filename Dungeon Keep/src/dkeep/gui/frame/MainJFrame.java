package dkeep.gui.frame;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import dkeep.gui.menu.MenuController;

public class MainJFrame extends JFrame {

	public static final Dimension PREFERREDSIZE = new Dimension(650,500);
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Main menu's window constructor
	 */
	public MainJFrame() {
		super("Dungeon Keep");
		MenuController menuController = new MenuController(this);
		this.setContentPane(menuController.getMainPanel());
	}
	
	/**
	 * @brief Creates the game's GUI
	 *
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
	 * @brief Changes the main JFrame's content panel
	 * @param jPanel Panel to be portrayed
	 *
	 * Replaces the current panel being shown in the game's
	 * main frame for the one passed as a parameter.
	 */
	public void changeContentPane(JPanel jPanel) {
		setContentPane(jPanel);
		revalidate();
		repaint();
	}
}
