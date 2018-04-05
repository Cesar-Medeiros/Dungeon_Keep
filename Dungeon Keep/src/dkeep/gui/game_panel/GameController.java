package dkeep.gui.game_panel;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import dkeep.gui.load_save.LoadSavePanel;
import dkeep.gui.options.OptionsPanel;
import dkeep.logic.game.DungeonKeep;
import dkeep.logic.game.GameConfig;
import dkeep.util.Direction;
import dkeep.util.Input;

public class GameController extends KeyAdapter {
	
	GamePanel gamePanel;
	DungeonKeep dk;
	GameGraphics gameGraphics;
	BoardRendererGUI boardRenderer;
	
	
	/**
	 * Game's controller constructor
	 */
	public GameController(){
		gamePanel = new GamePanel(this);
		boardRenderer = new BoardRendererGUI(); 
	}
	
	/**
	 * Creates a new game based on the configuration 
	 * chosen on the dialog box OptionsPanel.
	 */
	public void newGame() {
		GameConfig gameConfig = OptionsPanel.getGameConfig();
		dk = new DungeonKeep(gameConfig, boardRenderer);
		update();
	}
	
	/**
	 * Initializes a new game, enabling buttons, loading game
	 * graphics and setting the graphical input interface.
	 */
	public void initializeGame() {
		gamePanel.enableButtons();
		Input.setGraphicInput();
		gamePanel.repaint();
	}
	
	/**
	 * Appends a direction input via the interface in use,
	 * to a list of movements performed by the user.
	 * @param direction Input direction
	 */
	public void directionPressed(Direction direction) {
		Input.addDirection(direction);
		update();
	}
	
	/**
	 * Updates game's current state
	 */
	public void update() {
		dk.update();
		gamePanel.repaint();
		gamePanel.setGameStatus(dk.getState());
		if(dk.isEndGame()) gamePanel.disableButtons();
	}
		
	
	/**
	 * Creates load/save dialog box
	 */
	public void loadSave() {
		dk = LoadSavePanel.createAndShowGUI(dk);
		dk.setBoardRenderer(boardRenderer);
	}
	
	
	/**
	 * Renders game graphics
	 * @param g Graphics received from paintComponent
	 */
	public void render(Graphics g) {
		if(dk != null) {
			boardRenderer.updateGameGraphics(new GameGraphics(gamePanel.getBoardSize(), g));
			dk.render();
		}
	}
	
	
	/**
	 * Receives events in result of a key being pressed
	 * on the keyboard, handling those representing
	 * meaningful movement keys for the game (W,A,S,D).
	 * @param e KeyEvent received from the keyboard
	 */
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
	
	/**
	 * Returns the game's panel
	 * @return Game's panel
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}

}
