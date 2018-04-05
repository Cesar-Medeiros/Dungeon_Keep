package dkeep.logic.level;

import java.io.Serializable;

import dkeep.logic.board.Board;
import dkeep.logic.board.BoardRenderer;
import dkeep.logic.characters.MoveObj;

public abstract class Level implements Serializable{

	private static final long serialVersionUID = 1L;
	protected boolean gameOver;
	protected boolean completed;
	protected Board board;
	protected MoveObj[] levelObjs;
	
	/**
	 * Sets game board
	 * @param board Game board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	/**
	 * Sets level moving objects
	 * @param objs Level moving objects
	 */
	public void setObjs(MoveObj[] objs) {
		this.levelObjs = objs;
	}
	
	public abstract void setup();
	public abstract void update();
	
	/**
	 * Indicates if game is over
	 * @return Returns true if game is over, false otherwise
	 */
	public boolean gameOver() {
		return gameOver;
	}
	
	/**
	 * Indicates if level is completed
	 * @return Returns true if level is completed, false otherwise
	 */
	public boolean completed() {
		return completed;
	}

	/**
	 * Renders game board through an interface
	 * @param boardRenderer Selected interface
	 */
	public void render(BoardRenderer boardRenderer){
		board.fillBoard(levelObjs);
		boardRenderer.render(board);
	}
	
}
