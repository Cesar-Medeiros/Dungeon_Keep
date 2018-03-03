package dkeep.logic.level;

import dkeep.logic.Board;
import dkeep.logic.Hero;
import dkeep.logic.MoveObj;
import dkeep.logic.guard.DrunkenGuard;
import dkeep.logic.guard.Guard;
import dkeep.logic.guard.RookieGuard;
import dkeep.logic.guard.SuspiciousGuard;

public class Level1 extends Level{

	private Hero hero;
	private Guard[] guards;
	private Guard guard;
	private MoveObj lever;
	private int guardIndex;
	
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

		guards = new Guard[3];
		guards[0] = new RookieGuard(8,1);
		guards[1] = new DrunkenGuard(8,1);
		guards[2] = new SuspiciousGuard(8,1);
		guardIndex = 0;
		guard = guards[guardIndex];
		
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
		System.out.println(guardIndex);
		hero.move(board);
		guard.move(board);

		if (guard.hasFinishedTurn()) {
			pickNextGuard();
		}

		if (hero.collision(lever)) {
			board.openDoors();
		}

		if (board.onStairs(hero)) {
			completed = true;
			return;

		} else if (hero.nearPos(guard) && guard.isActive()) {
			gameOver = true;
			return;
		}
	}

	public void pickNextGuard() {
		guard = guards[(++guardIndex) % 3];
		levelObjs[1]  = guard;
		guardIndex %= 3;
	}

}
