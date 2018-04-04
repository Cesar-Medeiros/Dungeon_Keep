package dkeep.gui.game_panel;

import java.awt.Dimension;
import java.awt.Graphics;

public class GameGraphics 
{
	private Dimension gamePanelDimension;
	private Graphics g;
	
	/**
	 * @brief GameGraphics constructor
	 * @param gamePanelDimension Game's panel dimension
	 * @param g Graphics context of the game
	 */
	public GameGraphics(Dimension gamePanelDimension, Graphics g) {
		this.gamePanelDimension = gamePanelDimension;
		this.g = g;
	}
	
	/**
	 * @brief Gets game's graphics context
	 * @return Game's graphics context
	 */
	public Graphics getGraphics() {
		return g;
	}
	
	/**
	 * @brief Gets game's panel dimension
	 * @return Game's panel dimension
	 */
	public Dimension getSize() {
		return gamePanelDimension;
	}
}
