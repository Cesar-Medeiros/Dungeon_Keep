package dkeep.logic.game;

import java.awt.Dimension;
import java.awt.Graphics;

import dkeep.logic.board.BoardRenderer;

public class GameGraphics 
{
	private Dimension gamePanelDimension;
	private Graphics g;
	private BoardRenderer boardRenderer;
	
	public GameGraphics(Dimension gamePanelDimension, Graphics g, BoardRenderer boardRenderer) {
		this.gamePanelDimension = gamePanelDimension;
		this.g = g;
		this.boardRenderer = boardRenderer;
	}
	

	public Graphics getGraphics() {
		return g;
	}
	
	public BoardRenderer getBoardRenderer() {
		return boardRenderer;
	}

	public int getHeight() {
		return (int) gamePanelDimension.getHeight();
	}
	
	public int getWidth() {
		return (int) gamePanelDimension.getWidth();
	}
}
