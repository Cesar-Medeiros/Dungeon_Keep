package dkeep.gui.menu;

import javax.swing.JPanel;

import dkeep.gui.frame.MainJFrame;
import dkeep.gui.game_panel.GameController;

public class MenuController {
	private MainJFrame mainJFrame;
	private MenuPanel mainPanel;
	
	/**
	 * @brief Menu's controller constructor
	 * @param mainJFrame Menu's frame
	 *
	 * Creates a controller to the menu window frame.
	 */
	public MenuController(MainJFrame mainJFrame) {
		mainPanel = new MenuPanel(this);
		this.mainJFrame = mainJFrame;
	}

	/**
	 * @brief Handles action performed on the play button
	 *
	 * Creates a game controller responsible for initializing a
	 * new game, and changes the current panel of the frame to 
	 * the game's panel.
	 */
	public void pressPlay() {
		GameController gameController = new GameController();
		mainJFrame.changeContentPane(gameController.getGamePanel());
	}
	
	/**
	 * @brief Handles action performed on the exit button
	 *
	 * Program exits successfully with code 0, by user's intention
	 * of leaving the game.
	 */
	public void pressExit() {
		System.exit(0);
	}
	
	/**
	 * @brief Returns menu's main panel
	 * @return Menu's main panel
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
