import java.io.IOException;
import java.util.Random;

public class Level2 extends Level{
	
	private MoveObj hero;
	private MoveObj ogre;
	private Random rand;
	private long futureTime;
	private boolean pickedKey;
	
	
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
		board = new Board(boardMap);
		hero = new MoveObj(1,7, 'H');
		ogre = new MoveObj(4,1, 'O');
		rand = new Random();
		futureTime = System.currentTimeMillis() + 1000;
		pickedKey = false;
	}
	
	@Override
	public void draw() {
		board.printBoard(hero, ogre);
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
			char ogreDirection = randDir();
			board.moveCharacter(ogre, ogreDirection);
			futureTime = System.currentTimeMillis() + 1000;
		}
		
		if(hero.nearPos(ogre))
			gameOver = true;
	

		if(onKey(hero)) {
			hero.setSymbol('K');
			pickKey();
		}
		
		if(onKey(ogre)) {
			ogre.setSymbol('$');
		}
		else ogre.setSymbol('O');
		
		
		if(Character.toUpperCase(direction) == 'A' && !successMove && 
				pickedKey &&  onDoor())
			openDoor();
		
		if(onExit())
			completed = true;
		
	}
	
	
	public boolean onKey(MoveObj moveObj) {
		return (moveObj.getPosX() == 7 && moveObj.getPosY() == 1);
	}
	
	public boolean onExit() {		
		return (hero.getPosX() == 0 && hero.getPosY() == 1);
	}
	
	public boolean onDoor() {		
		return (hero.getPosX() == 1 && hero.getPosY() == 1);
	}
	
	public void openDoor() {
		boardMap[1][0] = 'S';
	}
	

	public void pickKey() {
		pickedKey = true;
		boardMap[1][7] = ' ';
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
