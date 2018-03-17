package dkeep.cli;

import dkeep.logic.Board;
import static dkeep.cli.IOInterface.Direction.UP;
import static dkeep.cli.IOInterface.Direction.RIGHT;
import static dkeep.cli.IOInterface.Direction.DOWN;
import static dkeep.cli.IOInterface.Direction.LEFT;
import static dkeep.cli.IOInterface.Direction.NONE;

public abstract class IOInterface {

	public static enum Interface {
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
		case GRAPHICS:
			return Graphic.getDirection();
		default: 
			return NONE;
		}
	}
	
	public static Direction revertDirection(Direction dir) {
		switch(dir) {
		case UP:
			return DOWN;
		case RIGHT:
			return LEFT;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		default:
			return NONE;
		}
	}
	
	public static void printBoard(Board board) {
		switch(currentInterface) {
		case CLI:
			CLI.printBoard(board);
			break;
		case GRAPHICS:
			Graphic.printBoard(board);
			break;
		default: break;
		}
	}
}
