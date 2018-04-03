package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.guard.Guard;
import dkeep.logic.characters.guard.RookieGuard;
import dkeep.logic.characters.util.Movement;
import dkeep.logic.level.Level1;
import dkeep.util.Direction;


public class TestDungeonGameLogic {


	char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X'},
		{'X', ' ', ' ', ' ', 'X'},
		{'D', ' ', ' ', ' ', 'X'},
		{'D', 'k', ' ', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X'}
	};

	@Test
	public void testMoveHeroIntoFreeCell() {

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,1);
		MoveObj[] objs = new MoveObj[] { hero };

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));

		boolean successfulMove = hero.moveCharacter(board, Direction.DOWN);
		assertTrue(successfulMove);

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));
	}

	@Test
	public void testMoveHeroIntoWall() {

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,1);
		MoveObj[] objs = new MoveObj[] { hero };

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));

		boolean successfulMove = hero.moveCharacter(board, Direction.LEFT);
		assertFalse(successfulMove);

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));
		assertEquals('X', board.getElement(0, 1));
	}

	@Test
	public void testMoveHeroIntoGuard() {

		Board board = new Board(boardMap);
		Guard guard = new RookieGuard(3, 1, new Movement());
		Hero hero = new Hero(1,1);
		MoveObj[] objs = new MoveObj[] {hero, guard};

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));
		assertEquals('G', board.getElement(3, 1));

		boolean successfulMove = hero.moveCharacter(board, Direction.RIGHT);
		assertTrue(successfulMove);

		board.fillBoard(objs);
		assertEquals('H', board.getElement(2, 1));
		assertEquals('G', board.getElement(3, 1));

		assertTrue(hero.nearPos(guard));		
	}

	@Test
	public void testMoveHeroIntoClosedDoor() {

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] { hero };

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));
		
		boolean onOpenDoor = board.onOpenableDoor(hero, Direction.LEFT);
		assertTrue(onOpenDoor);
		
		boolean successfulMove = board.canMoveTo(0, 2);
		assertFalse(successfulMove);
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));
		assertEquals('D', board.getElement(0, 2));
	}

	@Test
	public void testMoveHeroIntoLever() {

		Level1 lvl1 = new Level1(0);

		Board board = new Board(boardMap);
		Hero hero = new Hero(1, 2);
		MoveObj[] objs = new MoveObj[] { hero };

		lvl1.setObjs(objs);
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));

		boolean successfulMove = hero.moveCharacter(board, Direction.DOWN);
		assertTrue(successfulMove);
		assertTrue(board.onKey(hero));

		board.openDoors();
		board.fillBoard(objs);

		assertEquals('H', board.getElement(1, 3));
		assertEquals('S', board.getElement(0, 2));
		assertEquals('S', board.getElement(0, 3));
	}

	@Test
	public void testMoveHeroIntoExitDoors() {

		Level1 lvl1 = new Level1(0);

		Board board = new Board(boardMap);
		Hero hero = new Hero(1, 2);
		MoveObj[] objs = new MoveObj[] { hero };

		lvl1.setObjs(objs);
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));
			
		board.openDoors();
		board.fillBoard(objs);
		
		boolean successfulMove = hero.moveCharacter(board, Direction.LEFT);
		assertTrue(successfulMove);
		
		assertTrue(board.onOpenDoor(hero));
	}		
}
