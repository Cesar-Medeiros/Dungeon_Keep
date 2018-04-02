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
	
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void setObjs(MoveObj[] objs) {
		this.levelObjs = objs;
	}
	
	public abstract void setup();
	public abstract void update();
	
	public boolean gameOver() {return gameOver;}
	public boolean completed() {return completed;}

	
	public void render(BoardRenderer boardRenderer){
		board.fillBoard(levelObjs);
		boardRenderer.render(board);
	}
	
}
