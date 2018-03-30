package dkeep.logic.level;

import static dkeep.util.Direction.DOWN;
import static dkeep.util.Direction.LEFT;
import static dkeep.util.Direction.RIGHT;
import static dkeep.util.Direction.UP;

import java.util.Arrays;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.guard.DrunkenGuard;
import dkeep.logic.characters.guard.Guard;
import dkeep.logic.characters.guard.RookieGuard;
import dkeep.logic.characters.guard.SuspiciousGuard;
import dkeep.logic.characters.util.Movement;

public class Level1 extends Level{

	private static final long serialVersionUID = 1L;
	private Hero hero;
	private Guard[] guards;
	private Guard guard;
	private MoveObj lever;
	private int guardIndex;	//TODO: Option GUI sequential guard
		
	
	private Movement movement =  new Movement(Arrays.asList(
			LEFT, 	DOWN,	DOWN,	DOWN, 
			DOWN, 	LEFT, 	LEFT, 	LEFT, 
			LEFT, 	LEFT,	LEFT, 	DOWN,
			RIGHT, 	RIGHT, 	RIGHT, 	RIGHT,
			RIGHT,	RIGHT, 	RIGHT, 	UP,
			UP, 	UP, 	UP, 	UP
	));
	
	private static char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'X', ' ', ' ', ' ', 'I', ' ', 'X' ,' ', ' ', 'X'},
		{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
		{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
		{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
		{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
		{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	
	public Level1(int guardIndex) {
		super(new Board(boardMap));
		this.guardIndex = guardIndex;		
	}


	@Override
	public void setup() {

		hero = new Hero(1,1);
		
		guards = new Guard[] {
				new RookieGuard(8,1, movement),
				new DrunkenGuard(8,1, movement),
				new SuspiciousGuard(8,1, movement)
			};
		
		guard = guards[guardIndex];
		lever = new MoveObj(7, 8, 'k');

		levelObjs = new MoveObj[] {
				lever,
				guard,
				hero
		};
		//TODO: Better relation to the super class
		
	}
	

	@Override
	public void update() {
		hero.move(board);
		guard.move(board);

		if (guard.hasFinishedTurn()) {
			pickNextGuard();
		}

		if (hero.collision(lever)) {
			board.openDoors();
		}

		if (board.onDoor(hero)) {
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
