package dkeep.logic;

import java.util.Random;

import dkeep.cli.IOInterface.Direction;

public class Ogre extends MoveObj {

	
	private MoveObj club;
	private Direction clubDirection;
	private Random rand;
	
	public Ogre() {
		super(8,1,'O', '$');
		rand = new Random();
		club = new MoveObj(4,1, '*', '$');
		clubDirection = Direction.NONE;
	}
	
	private Direction nextMove() {
		switch(rand.nextInt(4)) {
		case 0: return Direction.RIGHT;
		case 1: return Direction.UP;
		case 2: return Direction.LEFT;
		case 3: return Direction.DOWN;
		default: return Direction.NONE;
		}
	}
	
	
	
	public MoveObj getClub() {
		return club;
	}

	public void move(Board board) {
		Direction direction;
		
		do {
			direction = nextMove();
		} while(! moveCharacter(board, direction));
		
		
		
		club.setPosX(this.posX);
		club.setPosY(this.posY);
		
		
		do {
			clubDirection = nextMove();
		} while(! club.moveCharacter(board, clubDirection));
		
	}
}
