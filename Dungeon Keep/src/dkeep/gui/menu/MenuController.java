package dkeep.gui.menu;

import javax.swing.JPanel;

import dkeep.gui.frame.MainJFrame;
import dkeep.gui.game_panel.GameController;

public class MenuController {
	private MainJFrame mainJFrame;
	private MenuPanel mainPanel;
	
	/**
	 * Menu's controller constructor
	 * @param mainJFrame Menu's frame
	 */
	public MenuController(MainJFrame mainJFrame) {
		mainPanel = new MenuPanel(this);
		this.mainJFrame = mainJFrame;
	}

	/**
	 * Handles action performed on the play button, creating a
	 * game controller responsible for initializing a new game,
	 * changing the current panel of the frame to the game's panel.
	 */
	public void pressPlay() {
		GameController gameController = new GameController();
		mainJFrame.changeContentPane(gameController.getGamePanel());
	}
	
	/**
	 * Handles action performed on the exit button. Program
	 * exits successfully with code 0, by user's intention
	 * of leaving the game.
	 */
	public void pressExit() {
		System.exit(0);
	}
	
	/**
	 * Returns menu's main panel
	 * @return Menu's main panel
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
