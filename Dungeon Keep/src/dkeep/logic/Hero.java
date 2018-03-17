package dkeep.logic;

import dkeep.cli.IOInterface;
import dkeep.cli.IOInterface.Direction;
import dkeep.logic.level.Level2;

import static dkeep.cli.IOInterface.Direction.NONE;

public class Hero extends MoveObj{

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
		direction = IOInterface.getDirection();
		moveCharacter(board, direction);
		return direction;
	}
	
	public boolean isArmed(){
		return club;
	}

	public void hasKey() {
		hasKey = true;
		setSymbol(Hero.withKeySymbol);
	}
	
	public void pickClub() {
		club = true;
	}
	
	public boolean tryExit(Direction dir, Level2 lvl) {
		if (dir == Direction.LEFT && (currentSymbol == 'K' || currentSymbol == 'A') && lvl.onDoor(this)) {
			lvl.openDoor();
			return true;
		}
		else {
			return false;
		}
	}
}
