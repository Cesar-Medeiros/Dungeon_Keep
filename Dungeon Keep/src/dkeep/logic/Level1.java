package dkeep.logic;

public class Level1 extends Level{

	private Hero hero;
	private Guard guard;
	private MoveObj lever;
	
	private static char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'X', ' ', ' ', ' ', 'I', ' ','X' ,' ' , ' ', 'X'},
		{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
		{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
		{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
		{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
		{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	@Override
	public String toString() {
		return "Level1";
	}
	
	@Override
	public void setup() {
		board = new Board(boardMap);
		hero = new Hero(1,1);
		guard = new Guard();
		lever = new MoveObj(7, 8, 'k');
		
		int memory = 3; //Lever Guard Hero
		levelObjs = new MoveObj[memory];
		
		levelObjs[0] = lever;
		levelObjs[1] = guard;
		levelObjs[2] = hero;
	}

	@Override
	public void draw() {
		cleanScreen();		
		board.draw(levelObjs);
	}

	@Override
	public void update() {
		
		hero.move(board);
		
		guard.move(board);
	
		
		if (hero.collision(lever)) {
			openDoors();
		}
		
		if (onStairs()) {
			completed = true;
			return;
			
		} else if (hero.nearPos(guard)) {
			gameOver = true;
			return;
		}
		
	}

	public boolean onStairs() {
		return (hero.getPosX() == 0 && (hero.getPosY() == 5 || hero.getPosY() == 6));
	}
	
	public void openDoors() {
		boardMap[5][0] = 'S';
		boardMap[6][0] = 'S';
	}
}
