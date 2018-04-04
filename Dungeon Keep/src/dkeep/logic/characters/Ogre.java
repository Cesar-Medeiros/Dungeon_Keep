package dkeep.logic.characters;

import java.util.Random;

import dkeep.logic.board.Board;
import dkeep.util.Direction;

import static dkeep.util.Direction.NONE;
import static dkeep.util.Direction.RIGHT;
import static dkeep.util.Direction.LEFT;
import static dkeep.util.Direction.UP;
import static dkeep.util.Direction.DOWN;

public class Ogre extends MoveObj {

	private static final long serialVersionUID = 1L;
	private int stun;
	private MoveObj club;
	private Random rand;
	
	public static final char ogreSymbol = 'O';
	public static final char overKeySymbol = '$';
	public static final char stunnedSymbol = '8';
	public static final char clubSymbol = '*';
	
	/**
	 * @brief Ogre character constructor
	 * @param posX Initial ogre's x-position
	 * @param posY Initial ogre's y-position
	 * 
	 * Creates an armed ogre on a specified board cell.
	 */
	public Ogre(int posX, int posY) {
		super(posX, posY, ogreSymbol);
		rand = new Random();
		club = new MoveObj(posX, posY, clubSymbol);
		stun = 0;
	}
	
	/**
	 * @brief Generates a random movement direction
	 * @return Generated random direction
	 */
	private Direction nextMove() {
		switch(rand.nextInt(4)) {
		case 0: return RIGHT;
		case 1: return UP;
		case 2: return LEFT;
		case 3: return DOWN;
		default: return NONE;
		}
	}
	
	/**
	 * @brief Returns ogre's club
	 * @return Ogre's club
	 */
	public MoveObj getClub() {
		return club;
	}

	/**
	 * @brief Moves randomly an object on the board
	 * @param board Game board
	 * @param obj Object to be moved
	 * @return Movement's direction
	 */
	private Direction move(Board board, MoveObj obj){
		Direction direction;
		
		do {
			direction = nextMove();
		} while(! obj.moveCharacter(board, direction));

		return direction;
	}

	/**
	 * @brief Moves an ogre on the board
	 * @param board Game board
	 * @return Ogre's movement direction
	 * 
	 * Ogre's movement is only enabled if it isn't stunned
	 * by the armed hero. In that case, it remains still for
	 * a set of the hero's movement turns.
	 */
	public Direction move(Board board) {
		
		Direction direction = NONE;
		
		if(!isStunned()) {
			direction = move(board, this);
		}
		else decreaseStun();
		
		club.setPosX(this.posX);
		club.setPosY(this.posY);
		
		move(board, club);	

		return direction;
		
	}

	/**
	 * @brief Decreases the ogre's stunned number of turns
	 */
	public void decreaseStun() {
		if (stun == 0) {
			currentSymbol = ogreSymbol;
		} else {
			stun--;
		}
	}

	/**
	 * @brief Indicates if ogre is stunned
	 * @return Returns true if ogre is stunned, false otherwise
	 */
	public boolean isStunned() {
		return stun != 0;
	}

	/**
	 * @brief Stuns ogre
	 * @param turns Number of turns for the ogre to be stunned
	 */
	public void stun(int turns) {
		stun = turns;
		currentSymbol = stunnedSymbol;
	}
}
