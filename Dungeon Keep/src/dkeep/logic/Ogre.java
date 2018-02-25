package dkeep.logic;

import java.util.Random;

import dkeep.cli.IOInterface.Direction;
import static dkeep.cli.IOInterface.Direction.NONE;
import static dkeep.cli.IOInterface.Direction.RIGHT;
import static dkeep.cli.IOInterface.Direction.LEFT;
import static dkeep.cli.IOInterface.Direction.UP;
import static dkeep.cli.IOInterface.Direction.DOWN;


public class Ogre extends MoveObj {

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
		
		
		if(isStunned()) {
			decreaseStun();
			return NONE;
		}
		
		Direction direction = move(board, this);
		
		club.setPosX(this.posX);
		club.setPosY(this.posY);
		
		
		move(board, club);
		return direction;
		
	}
	
public void decreaseStun(){
	if(stun == 0){
		currentSymbol = ogreSymbol;
	}
	else{
		stun--;
	}
}

	public boolean isStunned() {
		return stun != 0;
	}

	public void stun(int turns) {
		System.out.println("Old Symbol: " + currentSymbol);
		stun = turns;
		currentSymbol = stunnedSymbol;
		System.out.println("New Symbol: " + currentSymbol);
	}
}
