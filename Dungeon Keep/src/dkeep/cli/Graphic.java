package dkeep.cli;

import java.util.LinkedList;
import java.util.Queue;

import dkeep.logic.Board;

public class Graphic extends IOInterface {

	protected static Queue<Direction> directions = new LinkedList<Direction>();
	
	public static Direction getDirection() {
		Direction direction = directions.poll();
		
		if(direction == null) {
			return Direction.NONE;
		}
		else return direction;
	}
	
	public static void addDirection(Direction direction) {
		
		if(direction == null || direction == Direction.NONE || !(direction instanceof Direction))
			return;
	
		
		else directions.add(direction);
		
	}
	
	public static Direction convertToDirection(char direction) {
		
		switch(direction) {
		case 'W':
			return Direction.UP;
		case 'A':
			return Direction.LEFT;
		case 'S':
			return Direction.DOWN;
		case 'D':
			return Direction.RIGHT;
		default:
			return Direction.NONE;
		}
	}
	

	public static void printBoard(Board board) {
		
		for(char[] row : board.getBoard()) {
			for(char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.print("\n");
		}
		
		System.out.print("\nKey: ");
		
	}
	
	
}
