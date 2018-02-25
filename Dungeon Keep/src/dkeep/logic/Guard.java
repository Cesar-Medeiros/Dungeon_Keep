package dkeep.logic;

import dkeep.cli.IOInterface.Direction;
import static dkeep.cli.IOInterface.Direction.RIGHT;
import static dkeep.cli.IOInterface.Direction.LEFT;
import static dkeep.cli.IOInterface.Direction.UP;
import static dkeep.cli.IOInterface.Direction.DOWN;


public class Guard extends MoveObj {

	private Direction movements[] = {
			LEFT, 	DOWN,	DOWN,	DOWN, 
			DOWN, 	LEFT, 	LEFT, 	LEFT,
			LEFT, 	LEFT,	LEFT, 	DOWN,
			RIGHT, 	RIGHT, 	RIGHT, 	RIGHT,
			RIGHT,	RIGHT, 	RIGHT, 	UP,
			UP, 	UP, 	UP, 	UP
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
