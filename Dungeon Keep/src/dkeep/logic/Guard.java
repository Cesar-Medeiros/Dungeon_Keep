package dkeep.logic;

import dkeep.cli.IOInterface.Direction;

public class Guard extends MoveObj {

	private Direction movements[] = {
			Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.DOWN, 
			Direction.DOWN, Direction.LEFT, Direction.LEFT, Direction.LEFT,
			Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.DOWN,
			Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,
			Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.UP,
			Direction.UP, Direction.UP, Direction.UP, Direction.UP
	};
	private static int moveIndex = 0;
	
	public Guard() {
		super(8,1,'G');
	}
	
	private Direction nextMove() {
		return movements[(moveIndex++) % 24];
	}
	
	public void move(Board board) {
		Direction direction = nextMove();
		moveCharacter(board, direction);
	}
}
