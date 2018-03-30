package dkeep.gui.menu;

import dkeep.gui.frame.MainJFrame;
import dkeep.gui.game_panel.GameController;

public class MenuController {
	private MainJFrame mainJFrame;

	
	public MenuController(MainJFrame mainJFrame) {
		this.mainJFrame = mainJFrame;
	}

	public void pressPlay() {
		GameController gameController = new GameController();
		mainJFrame.changeContentPane(gameController.getGamePanel());
	}
	
	public void pressSandBox() {
		
	}
	
	public void pressExit() {
		System.exit(0);
	}
	
}
