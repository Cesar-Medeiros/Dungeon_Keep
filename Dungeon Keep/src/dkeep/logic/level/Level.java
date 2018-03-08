package dkeep.logic.level;

import dkeep.logic.Board;
import dkeep.logic.MoveObj;

public abstract class Level {

	protected boolean gameOver;
	protected boolean completed;
	protected Board board;
	MoveObj[] levelObjs;
	
	
	public abstract void setup();
	public abstract void draw();
	public abstract void update();
	
	public boolean gameOver() {return gameOver;}
	public boolean completed() {return completed;}
	
	@Override
	public abstract String toString();
	
	protected static void cleanScreen() {
		// Clear Screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
}
