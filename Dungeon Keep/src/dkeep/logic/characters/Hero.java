package dkeep.logic.characters;

import dkeep.logic.board.Board;
import dkeep.util.Direction;
import dkeep.util.Input;
import static dkeep.util.Direction.NONE;

public class Hero extends MoveObj{

	private static final long serialVersionUID = 1L;
	private boolean hasKey;
	private boolean club;
	private Direction direction;
	
	public static final char heroSymbol = 'H';
	public static final char withKeySymbol = 'K';
	public static final char armedSymbol = 'A';
	
	/**
	 * Hero character constructor. Creates an unarmed hero,
	 * and without the key, on the specified board cell.
	 * @param posX Initial hero's x-position
	 * @param posY Initial hero's y-position
	 */
	public Hero(int posX, int posY) {
		super(posX, posY, heroSymbol);
		hasKey = false;
		club = false;
		direction = NONE;
	}
	
	/**
	 * Moves hero on the board after the user inputs
	 * a valid direction.
	 * @param board Game board
	 * @return Movement's direction
	 */
	public Direction move(Board board) {
		direction = Input.getDirection();
		moveCharacter(board, direction);
		return direction;
	}
	
	/**
	 * Indicates if hero is armed
	 * @return Returns true if hero is armed, false otherwise
	 */
	public boolean isArmed(){
		return club;
	}
	
	/**
	 * Hero gets armed and its symbol is now 'A'.
	 */
	public void pickClub() {
		setSymbol(armedSymbol);
		club = true;
	}

	/**
	 * Hero picks up the key and its symbol is now 'K'.
	 */
	public void pickKey() {
		if(getSymbol() != armedSymbol)
			setSymbol(withKeySymbol);
		hasKey = true;
	}

	/**
	 * Indicates if hero has key
	 * @return Returns true if hero has key, false otherwise.
	 */
	public boolean hasKey() {
		return hasKey;
	}
}
