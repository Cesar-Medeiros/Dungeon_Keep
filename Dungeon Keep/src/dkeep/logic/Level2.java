package dkeep.logic;

import dkeep.cli.IOInterface;
import dkeep.cli.IOInterface.Direction;

public class Level2 extends Level{
	
	//Hero
	private Hero hero;
	private boolean pickedKey;
	
	//Ogre
	private Ogre ogre;
	
	
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
		hero = new Hero(1,7);
		pickedKey = false;
		
		//Ogre
		ogre = new Ogre(4,1);
		
	}
	
	
	@Override
	public void draw() {
		cleanScreen();
		IOInterface.printBoard(board, hero, ogre.getClub(), ogre);
	}
	
	
	@Override
	public void update() {
		
		Direction direction = hero.move(board);
			
		ogre.move(board);
		
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
		
		if(onExit(hero)) {
			completed = true;
			return;
		}
		
		
		if(direction == Direction.LEFT && pickedKey && onDoor(hero)) 
		{
			openDoor();		
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
