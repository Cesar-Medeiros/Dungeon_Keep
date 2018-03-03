package dkeep.logic.guard;

import dkeep.logic.MoveObj;
import dkeep.logic.Board;
import dkeep.cli.IOInterface;
import dkeep.cli.IOInterface.Direction;

import static dkeep.cli.IOInterface.Direction.RIGHT;
import static dkeep.cli.IOInterface.Direction.LEFT;
import static dkeep.cli.IOInterface.Direction.UP;
import static dkeep.cli.IOInterface.Direction.DOWN;


public abstract class Guard extends MoveObj {
	protected int moveIndex;
	protected boolean active;
	
	protected Direction movements[] = {
			LEFT, 	DOWN,	DOWN,	DOWN, 
			DOWN, 	LEFT, 	LEFT, 	LEFT,
			LEFT, 	LEFT,	LEFT, 	DOWN,
			RIGHT, 	RIGHT, 	RIGHT, 	RIGHT,
			RIGHT,	RIGHT, 	RIGHT, 	UP,
			UP, 	UP, 	UP, 	UP
	};
	
	protected boolean passedBy[];
	protected int remainingPos;


	protected abstract Direction nextMove();
	
	public Guard(int posX, int posY) {
		super(posX, posY, 'G');
		moveIndex = 0;
		active = true;
		passedBy = new boolean[movements.length];
		remainingPos = movements.length;
	}
	
	public boolean hasFinishedTurn() {
		return ((moveIndex | remainingPos) == 0);
	}
	
	public boolean isActive() {
		return active;
	}

	public void move(Board board) 
	{
		if(passedBy[moveIndex] == false) {
			passedBy[moveIndex] = true;
			remainingPos--;
		}
		
		Direction direction = nextMove();
		moveCharacter(board, direction);
	}

	
	protected Direction getNextDir() {
		Direction direction = Direction.NONE;
		direction = movements[moveIndex];
		moveIndex++; moveIndex %= movements.length;
		return direction;
	}
	
	protected Direction getNextDirR() {
		Direction direction = Direction.NONE;
		moveIndex--;
		
		if(moveIndex < 0) moveIndex +=movements.length;
		else moveIndex %= movements.length;
		
		direction = movements[moveIndex];
		direction = IOInterface.revertDirection(direction);
		return direction;
	}
}
