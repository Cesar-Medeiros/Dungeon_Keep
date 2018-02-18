import java.io.IOException;
import java.util.Scanner;

public class Level1 extends Level{

	private MoveObj hero;
	private Guard guard;
	private MoveObj lever;
	private Scanner scan;
	
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
		hero = new MoveObj(1,1, 'H');
		guard = new Guard();
		lever = new MoveObj(7, 8, 'k');
		scan = new Scanner(System.in);
	}

	@Override
	public void draw() {
		board.printBoard(lever, guard, hero);
	}

	@Override
	public void update() {
		
		if (onStairs()) {
			completed = true;
			return;
			
		} else if (hero.nearPos(guard)) {
			gameOver = true;
			return;
		}
		
		char direction = scan.next().charAt(0);		
		board.moveCharacter(hero, direction);
		board.moveCharacter(guard, guard.nextMove());
		
		if (hero.collision(lever)) {
			openDoors();
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
