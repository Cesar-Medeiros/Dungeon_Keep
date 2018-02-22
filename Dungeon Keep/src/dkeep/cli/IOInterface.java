package dkeep.cli;

import dkeep.logic.Board;
import dkeep.logic.MoveObj;

public abstract class IOInterface {

	protected static enum Interface {
		CLI, GRAPHICS
	};
	
	public static enum Direction {
		UP, RIGHT, DOWN, LEFT, NONE
	};	
	
	private static Interface currentInterface = null;
		
	public static void setInterface(Interface currentInterface) {
		IOInterface.currentInterface = currentInterface;
	}
	
	public static Direction getDirection() {
		switch(currentInterface) {
		case CLI:
			return CLI.getDirection();
		default: 
			return Direction.NONE;
		}
	}
	
	
	public static void printBoard(Board board, MoveObj... characters) {
		switch(currentInterface) {
		case CLI:
			CLI.printBoard(board, characters);
			break;
		default: break;
		}
	}
}
