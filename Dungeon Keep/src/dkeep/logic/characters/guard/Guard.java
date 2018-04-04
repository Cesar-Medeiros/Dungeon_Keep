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
	
	/**
	 * @brief Guard constructor
	 * @param posX Initial guard's x-position
	 * @param posY Initial guard's y-position
	 * @param movement Guard's list of movements
	 */
	public Guard(int posX, int posY, Movement movement) {
		super(posX, posY, symbol);
		this.active = true;
		this.movement = movement;
	}
	
	/**
	 * @brief Indicates guard's turn state
	 * @return Returns true if guard's turn has finished, false otherwise
	 */
	public boolean hasFinishedTurn() {
		return movement.passedByAll();
	}
	
	/**
	 * @brief Returns guard's patrol state
	 * @return Returns true if guard is active, false otherwise
	 */
	public boolean isActive() {
		return active;
	}

	/*
	 * @brief Moves guard on the board
	 * @param board Game board
	 */
	public void move(Board board) {		
		Direction direction = nextMove();
		moveCharacter(board, direction);
	}
}
