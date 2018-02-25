package dkeep.logic;

import java.util.Random;

import dkeep.cli.IOInterface.Direction;
import static dkeep.cli.IOInterface.Direction.NONE;
import static dkeep.cli.IOInterface.Direction.RIGHT;
import static dkeep.cli.IOInterface.Direction.LEFT;
import static dkeep.cli.IOInterface.Direction.UP;
import static dkeep.cli.IOInterface.Direction.DOWN;


public class Ogre extends MoveObj {

	
	private MoveObj club;
	private Random rand;
	
	
	public Ogre(int posX, int posY) {
		super(posX, posY, 'O', '$');
		rand = new Random();
		club = new MoveObj(posX, posY, '*', '$');
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
		
		Direction direction = move(board, this);
		
		club.setPosX(this.posX);
		club.setPosY(this.posY);
		
		
		move(board, club);
		return direction;
		
	}
}
