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
	
	public Hero(int posX, int posY) {
		super(posX, posY, heroSymbol);
		hasKey = false;
		club = false;
		direction = NONE;
	}
	
	public Direction move(Board board) {
		direction = Input.getDirection();
		moveCharacter(board, direction);
		return direction;
	}
	
	public boolean isArmed(){
		return club;
	}
	
	public void pickClub() {
		setSymbol(armedSymbol);
		club = true;
	}

	public void pickKey() {
		hasKey = true;
		setSymbol(withKeySymbol);
	}

	public boolean hasKey() {
		return hasKey;
	}
}
