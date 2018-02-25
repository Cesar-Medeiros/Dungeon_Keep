package dkeep.logic;

import dkeep.cli.IOInterface;
import dkeep.cli.IOInterface.Direction;
import static dkeep.cli.IOInterface.Direction.NONE;

public class Hero extends MoveObj{

	private boolean club;
	private Direction direction;
	
	public static final char heroSymbol = 'H';
	public static final char withKeySymbol = 'K';
	public static final char armedSymbol = 'A';
	
	public Hero(int posX, int posY) {
		super(posX, posY, heroSymbol);
		club = false;
		direction = NONE;
	}
	
	public Direction move(Board board) {
		direction = IOInterface.getDirection();
		moveCharacter(board, direction);
		return direction;
	}

	public boolean isArmed(){
		return club;
	}

	public void pickClub(){
		club = true;
	}
	
}
