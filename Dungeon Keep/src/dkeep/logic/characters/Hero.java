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
	 * @brief Hero character constructor
	 * @param posX Initial hero's x-position
	 * @param posY Initial hero's y-position
	 * 
	 * Creates an unarmed hero, and without the key,
	 * on the specified board cell.
	 */
	public Hero(int posX, int posY) {
		super(posX, posY, heroSymbol);
		hasKey = false;
		club = false;
		direction = NONE;
	}
	
	/**
	 * @brief Moves hero on the board
	 * @param board Game board
	 * @return Movement's direction
	 * 
	 * Waits for the user to input a valid direction
	 * and afterwards moves the hero on the board.
	 */
	public Direction move(Board board) {
		direction = Input.getDirection();
		moveCharacter(board, direction);
		return direction;
	}
	
	/**
	 * @brief Indicates if hero is armed
	 * @return Returns true if hero is armed, false otherwise
	 */
	public boolean isArmed(){
		return club;
	}
	
	/**
	 * @brief Hero picks up the club 
	 * 
	 * The hero gets armed and its symbol is now 'A'.
	 */
	public void pickClub() {
		setSymbol(armedSymbol);
		club = true;
	}

	/**
	 * @brief Hero picks up the key
	 * 
	 * Hero's symbol is now 'K'.
	 */
	public void pickKey() {
		if(getSymbol() != armedSymbol)
			setSymbol(withKeySymbol);
		hasKey = true;
	}

	/**
	 * @brief Indicates if hero has key
	 * 
	 * Returns true if hero has key, false otherwise.
	 */
	public boolean hasKey() {
		return hasKey;
	}
}
