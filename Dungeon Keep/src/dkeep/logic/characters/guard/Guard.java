package dkeep.logic.characters.guard;

import dkeep.util.Direction;
import dkeep.logic.board.Board;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.util.Movement;

public abstract class Guard extends MoveObj {
	
	private static final long serialVersionUID = 1L;

	private static char symbol = 'G';
	
	protected boolean active;
	protected Movement movement;
	

	protected abstract Direction nextMove();
	
	public Guard(int posX, int posY, Movement movement) {
		super(posX, posY, symbol);
		this.active = true;
		this.movement = movement;
	}
	
	public boolean hasFinishedTurn() {
		return movement.passedByAll();
	}
	
	public boolean isActive() {
		return active;
	}

	public void move(Board board) 
	{		
		Direction direction = nextMove();
		moveCharacter(board, direction);
	}
}
