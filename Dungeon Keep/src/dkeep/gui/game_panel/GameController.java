package dkeep.gui.game_panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dkeep.gui.load_save.LoadSavePanel;
import dkeep.gui.options.OptionsPanel;
import dkeep.logic.game.DungeonKeep;
import dkeep.logic.game.GameConfig;
import dkeep.util.Direction;
import dkeep.util.Input;

public class GameController implements KeyListener {
	
	GamePanel gamePanel;
	DungeonKeep dk;
	GameGraphics gameGraphics;
	BoardRendererGUI boardRenderer;
	
	public GameController(){
		gamePanel = new GamePanel(this);
		boardRenderer = new BoardRendererGUI(gameGraphics); 
	}
	
	public void newGame() {
		GameConfig gameConfig = OptionsPanel.getGameConfig();
		dk = new DungeonKeep(gameConfig, boardRenderer);
		update();
	}
	
	public void initializeGame() {
		gamePanel.enableButtons();
		loadGameGraphics();
		Input.setGraphicInput();
	}
			
	public void directionPressed(Direction direction) {
		Input.addDirection(direction);
		update();
	}
	
	
	public void update() {
		dk.update();
		dk.render();
		
		gamePanel.setGameStatus(dk.getState());
		if(dk.isEndGame()) gamePanel.disableButtons();
	}
		
	

	public void loadSave() {
		dk = LoadSavePanel.createAndShowGUI(dk);
		dk.setBoardRenderer(boardRenderer);
	}
	
	
	public void loadGameGraphics() {
		boardRenderer.updateGameGraphics(new GameGraphics(
				gamePanel.getGameSize(),
				gamePanel.getGameGraphics()
				)
			);
		
		if(dk != null)
			dk.render();
	}	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			directionPressed(Direction.UP);
			break;
		case KeyEvent.VK_A:
			directionPressed(Direction.LEFT);
			break;
		case KeyEvent.VK_S:
			directionPressed(Direction.DOWN);
			break;
		case KeyEvent.VK_D:
			directionPressed(Direction.RIGHT);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	
		// not in use
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// not in use
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

}
