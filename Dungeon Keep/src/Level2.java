import java.io.IOException;
import java.util.Random;

public class Level2 extends Level{
	
	//Hero
	private MoveObj hero;
	private boolean pickedKey;
	
	//Ogre
	private MoveObj ogre;
	private MoveObj club;
	
	//Util
	private Random rand;
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
		hero = new MoveObj(1,7, 'H', 'K');
		pickedKey = false;
		
		//Ogre
		ogre = new MoveObj(4,1, 'O', '$');
		club = new MoveObj(4,1, '*', '$');
		
		//Util
		rand = new Random();
		futureTime = System.currentTimeMillis() + 1000;
		
	}
	
	
	@Override
	public void draw() {
		cleanScreen();
		board.printBoard(hero, club, ogre);
	}
	
	
	@Override
	public void update() {
		
		char direction = 0;
		boolean successMove = false;
		
		try {
			if(System.in.available() != 0) {
				direction = (char) System.in.read();
				successMove = board.moveCharacter(hero, direction);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		if(System.currentTimeMillis() > futureTime) {
			char ogreDirection;
			do{
				ogreDirection = randDir();
			} while(! board.moveCharacter(ogre, ogreDirection));
			
			
			club.setPosX(ogre.getPosX());
			club.setPosY(ogre.getPosY());
			
			char clubDirection;
			do{
				clubDirection = randDir();
			} while(! board.moveCharacter(club, clubDirection));
			
			
			
			futureTime = System.currentTimeMillis() + 1000;
		}
		
		
		
		if(hero.nearPos(ogre) | hero.nearPos(club)) {
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
		
		
		if(onKey(club)) {
			club.toSpecialSymbol();
		}
		else {
			club.toNormalSymbol();
		}
		
		
		if(Character.toUpperCase(direction) == 'A' && 
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
	
	
	private char randDir() {
		switch(rand.nextInt(4)) {
		case 0: return 'D';
		case 1: return 'W';
		case 2: return 'A';
		case 3: return 'S';
		default: return 0;
		}
	}

}
