package dkeep.logic;
import dkeep.cli.IOInterface;
import dkeep.cli.IOInterface.Direction;

public class Hero extends MoveObj{

	
	private boolean hasClub;
	private Direction direction;
	
	public Hero(int posX, int posY, char symbol) {
		super(posX, posY, symbol);
		hasClub = false;
		direction = Direction.NONE;
	}
	
	
	public Hero(int posX, int posY, char symbol, char symbol2) {
		super(posX, posY, symbol, symbol2);
		hasClub = false;
		direction = Direction.NONE;
	}
	
	public void move(Board board) {
		direction = IOInterface.getDirection();
		moveCharacter(board, direction);
	}
	
}
