package dkeep.logic;
import java.io.IOException;
import dkeep.cli.IOInterface.Direction;

public class Level2 extends Level{
	
	//Hero
	private Hero hero;
	private boolean pickedKey;
	
	//Ogre
	private Ogre ogre;
	
	//Util
	
	private long futureTime;
	
	
	private static final char keySymbol = 'k';
	private static final char doorSymbol = 'I';
	private static final char exitSymbol = 'S';
	
	private static char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'I', ' ', ' ', ' ', ' ', ' ',' ' , 'k' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	@Override
	public String toString() {
		return "Level2";
	}
	
	
	@Override
	public void setup() {
		//Super class
		board = new Board(boardMap);
		
		//Hero
		hero = new Hero(1,7, 'H', 'K');
		pickedKey = false;
		
		//Ogre
		ogre = new Ogre();
		
		//Util
		futureTime = System.currentTimeMillis() + 1000;
		
	}
	
	
	@Override
	public void draw() {
		cleanScreen();
		board.printBoard(hero, ogre);
	}
	
	
	@Override
	public void update() {
		
		Direction direction = Direction.NONE;
		boolean successMove = false;
		
		try {
			if(System.in.available() != 0) {
				hero.move(board);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		if(System.currentTimeMillis() > futureTime) {
			ogre.move(board);
			futureTime = System.currentTimeMillis() + 1000;
		}
		
		
		
		if(hero.nearPos(ogre) || hero.nearPos(ogre.getClub())) {
			gameOver = true;
		}
		
		
		if(onKey(hero)) {
			hero.toSpecialSymbol();
			pickKey();
		}
		
		
		if(onKey(ogre)) {
			ogre.toSpecialSymbol();
		}
		else {
			ogre.toNormalSymbol();
		}
		
		
		if(onKey(ogre.getClub())) {
			ogre.getClub().toSpecialSymbol();
		}
		else {
			ogre.getClub().toNormalSymbol();
		}
		
		
		if(direction == Direction.LEFT && 
				!successMove && pickedKey &&  onDoor(hero)) 
		{
			openDoor();		
		}
		
		if(onExit(hero)) {
			completed = true;
		}
		
	}
	
	
	public boolean onKey(MoveObj moveObj) {
		return (boardMap[moveObj.getPosY()][moveObj.getPosX()] == keySymbol);
	}
	
	public boolean onExit(MoveObj moveObj) {		
		return (boardMap[moveObj.getPosY()][moveObj.getPosX()] == exitSymbol);
	}
	
	public boolean onDoor(MoveObj moveObj) {
		return (boardMap[moveObj.getPosY()][moveObj.getPosX() - 1] == doorSymbol);
	}
	
	public void openDoor() {
		board.substChar(doorSymbol, exitSymbol);
	}
	

	public void pickKey() {
		pickedKey = true;
		board.substChar(keySymbol, ' ');
	}
	
	

}
