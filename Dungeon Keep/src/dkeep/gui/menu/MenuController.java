package dkeep.gui.menu;

import javax.swing.JPanel;

import dkeep.gui.frame.MainJFrame;
import dkeep.gui.game_panel.GameController;

public class MenuController {
	private MainJFrame mainJFrame;
	private MenuPanel mainPanel;
	
	
	public MenuController(MainJFrame mainJFrame) {
		mainPanel = new MenuPanel(this);
		this.mainJFrame = mainJFrame;
	}

	public void pressPlay() {
		GameController gameController = new GameController();
		mainJFrame.changeContentPane(gameController.getGamePanel());
	}
	
	public void pressExit() {
		System.exit(0);
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
}
