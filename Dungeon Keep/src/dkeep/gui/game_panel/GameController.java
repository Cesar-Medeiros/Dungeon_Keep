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
	
	/**
	 * @brief Game's controller constructor
	 *
	 * Creates a GamePanel to be controlled by itself.
	 */
	public GameController() {
		gamePanel = new GamePanel(this);
		boardRenderer = new BoardRendererGUI(gameGraphics); 
	}
	
	/**
	 * @brief Creates a new game
	 *
	 * Creates a game based on the configuration chosen
	 * on the dialog box OptionsPanel.
	 */
	public void newGame() {
		GameConfig gameConfig = OptionsPanel.getGameConfig();
		dk = new DungeonKeep(gameConfig, boardRenderer);
		update();
	}
	
	/**
	 * @brief Initializes a new game
	 *
	 * Initializes a game, enabling buttons, loading game
	 * graphics and setting the graphical input interface.
	 */
	public void initializeGame() {
		gamePanel.enableButtons();
		loadGameGraphics();
		Input.setGraphicInput();
	}
			
	/**
	 * @brief Handles a direction input
	 * @param direction Input direction
	 *
	 * Appends a direction input via the interface in use,
	 * to a list of movements performed by the user.
	 */
	public void directionPressed(Direction direction) {
		Input.addDirection(direction);
		update();
	}
	
	/**
	 * @brief Updates game's current state
	 */
	public void update() {
		dk.update();
		dk.render();
		
		gamePanel.setGameStatus(dk.getState());
		if(dk.isEndGame()) gamePanel.disableButtons();
	}
		
	/**
	 * @brief Creates load/save dialog box
	 */
	public void loadSave() {
		dk = LoadSavePanel.createAndShowGUI(dk);
		dk.setBoardRenderer(boardRenderer);
	}
	
	/**
	 * @brief Updates game graphics
	 */
	public void loadGameGraphics() {
		boardRenderer.updateGameGraphics(new GameGraphics(
				gamePanel.getGameSize(),
				gamePanel.getGameGraphics()
				)
			);
		
		if(dk != null)
			dk.render();
	}
	
	/**
	 * @brief Handles keyboard's key pressed events
	 * @param e KeyEvent received from the keyboard
	 *
	 * Receives events in result of a key being pressed
	 * on the keyboard, handling those representing
	 * meaningful movement keys for the game (W,A,S,D).
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
	 * @brief Handles keyboard's key released events
	 *
	 * Not in use on the current version of the game.
	 */
	@Override
	public void keyReleased(KeyEvent e) {	

	}
	
	/**
	 * @brief Handles keyboard's key typed events
	 *
	 * Not in use on the current version of the game.
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	/**
	 * @brief Returns the game's panel
	 * @return Game's panel
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}

}
