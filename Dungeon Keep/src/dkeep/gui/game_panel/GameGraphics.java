package dkeep.gui.game_panel;

import java.awt.Dimension;
import java.awt.Graphics;

public class GameGraphics 
{
	private Dimension gamePanelDimension;
	private Graphics g;
	
	public GameGraphics(Dimension gamePanelDimension, Graphics g) {
		this.gamePanelDimension = gamePanelDimension;
		this.g = g;
	}
	

	public Graphics getGraphics() {
		return g;
	}

	public int getHeight() {
		return (int) gamePanelDimension.getHeight();
	}
	
	public int getWidth() {
		return (int) gamePanelDimension.getWidth();
	}
}
