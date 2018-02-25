package dkeep.logic;

import dkeep.cli.IOInterface;
import dkeep.cli.IOInterface.Direction;
import static dkeep.cli.IOInterface.Direction.NONE;

public class Hero extends MoveObj{

	
	private boolean hasClub;
	private Direction direction;
	
	
	public Hero(int posX, int posY) {
		super(posX, posY, 'H', 'K');
		hasClub = false;
		direction = NONE;
	}
	
	public Direction move(Board board) {
		direction = IOInterface.getDirection();
		moveCharacter(board, direction);
		return direction;
	}
	
}
