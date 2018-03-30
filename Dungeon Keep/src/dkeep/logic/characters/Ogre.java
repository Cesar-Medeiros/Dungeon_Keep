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
	
	
	public Ogre(int posX, int posY) {
		super(posX, posY, ogreSymbol);
		rand = new Random();
		club = new MoveObj(posX, posY, clubSymbol);
		stun = 0;
	}
	
	private Direction nextMove() {
		switch(rand.nextInt(4)) {
		case 0: return RIGHT;
		case 1: return UP;
		case 2: return LEFT;
		case 3: return DOWN;
		default: return NONE;
		}
	}
	
	
	
	public MoveObj getClub() {
		return club;
	}

	private Direction move(Board board, MoveObj obj){
		Direction direction;
		
		do {
			direction = nextMove();
		} while(! obj.moveCharacter(board, direction));

		return direction;
	}

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

	public void decreaseStun() {
		if (stun == 0) {
			currentSymbol = ogreSymbol;
		} else {
			stun--;
		}
	}

	public boolean isStunned() {
		return stun != 0;
	}

	public void stun(int turns) {
		stun = turns;
		currentSymbol = stunnedSymbol;
	}
}
