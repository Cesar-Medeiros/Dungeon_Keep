package dkeep.gui.game_panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dkeep.gui.load_save.LoadSavePanel;
import dkeep.gui.options.OptionsPanel;
import dkeep.logic.board.BoardRenderer;
import dkeep.logic.game.DungeonKeep;
import dkeep.logic.game.GameConfig;
import dkeep.logic.game.GameGraphics;
import dkeep.util.Direction;
import dkeep.util.Input;

public class GameController implements KeyListener {
	
	GamePanel gamePanel;
	DungeonKeep dk;
	BoardRenderer boardRenderer = new BoardRendererGUI(); 
	GameGraphics gameGraphics;
	
	
	public GameController(){
		gamePanel = new GamePanel(this);
	}
	
	
	public void initializeGame() {
		
		GameConfig gameConfig = OptionsPanel.getGameConfig();
		
		gamePanel.enableButtons();
		
		loadGameGraphics();
		Input.setGraphicInput();
		dk = new DungeonKeep(new GameConfig(
				gameConfig.getTypeGuard(),
				gameConfig.getNumOgres()
			)
		);
		update();
	}
			
	public void directionPressed(Direction direction) {
		Input.addDirection(direction);
		update();
	}
	
	public void update() {
		dk.update();
		dk.render(gameGraphics);
		gamePanel.setGameStatus(dk.getState());
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


	public void loadSave() {
		
		dk = LoadSavePanel.createAndShowGUI(dk);
		Input.setGraphicInput();
		dk.render(gameGraphics);
	}
	
	public void loadGameGraphics() {
		gameGraphics = new GameGraphics(
				gamePanel.getGameSize(),
				gamePanel.getGameGraphics(),
				boardRenderer
			);
		
		if(dk != null)
			dk.render(gameGraphics);
	}
	

}
