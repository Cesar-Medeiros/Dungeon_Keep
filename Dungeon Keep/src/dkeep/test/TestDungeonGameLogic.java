package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.IOInterface.Direction;
import dkeep.logic.Board;
import dkeep.logic.Hero;
import dkeep.logic.MoveObj;
import dkeep.logic.guard.Guard;
import dkeep.logic.guard.RookieGuard;


public class TestDungeonGameLogic {

	
	char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X'},
		{'X', ' ', ' ', ' ', 'X'},
		{'I', ' ', ' ', ' ', 'X'},
		{'I', 'k', ' ', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X'}
	};
	
	@Test
	public void testMoveHeroIntoFreeCell() {
		Board board = new Board(boardMap);
		Guard guard = new RookieGuard(3, 1);
		Hero hero = new Hero(1,1);
		
		board.fillBoard(new MoveObj[] {hero, guard});
		assertEquals('H', board.getElement(1, 1));
		
		boolean sucessfulMove = hero.moveCharacter(board, Direction.DOWN);
		
		assertTrue(sucessfulMove);
		board.fillBoard(new MoveObj[] {hero, guard});
		assertEquals('H', board.getElement(1, 2));
	}
	
	@Test
	public void testMoveHeroIntoWall() {
		Board board = new Board(boardMap);
		Guard guard = new RookieGuard(3, 1);
		Hero hero = new Hero(1,1);
		MoveObj[] objs = new MoveObj[] {hero, guard};
		
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));
		
		boolean sucessfulMove = hero.moveCharacter(board, Direction.LEFT);
		assertFalse(sucessfulMove);
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));
		
		assertEquals('X', board.getElement(0, 1));
	}
	
	@Test
	public void testMoveHeroIntoGuard() {
		Board board = new Board(boardMap);
		Guard guard = new RookieGuard(3, 1);
		Hero hero = new Hero(1,1);
		MoveObj[] objs = new MoveObj[] {hero, guard};
		
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));
		assertEquals('G', board.getElement(3, 1));
		
		boolean sucessfulMove = hero.moveCharacter(board, Direction.RIGHT);
		assertTrue(sucessfulMove);
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(2, 1));
		assertEquals('G', board.getElement(3, 1));
		
		assertTrue(hero.nearPos(guard));		
	}
	
	@Test
	public void testMoveHeroIntoClosedDoor() {
		Board board = new Board(boardMap);
		Guard guard = new RookieGuard(3, 1);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] {hero, guard};
		
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));
		
		boolean sucessfulMove = hero.moveCharacter(board, Direction.LEFT);
		assertFalse(sucessfulMove);
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));
		assertEquals('I', board.getElement(0, 2));
	}

		@Test
		public void testMoveHeroIntoLever() {
			Board board = new Board(boardMap);
			Guard guard = new RookieGuard(3, 1);
			Hero hero = new Hero(1, 2);
			MoveObj[] objs = new MoveObj[] { hero, guard };
	
			board.fillBoard(objs);
			assertEquals('H', board.getElement(1, 2));
			
			boolean sucessfulMove = hero.moveCharacter(board, Direction.DOWN);
			assertTrue(sucessfulMove);
			
			board.openDoors();
			board.fillBoard(objs);
			assertEquals('H', board.getElement(1, 3));
			assertEquals('S', board.getElement(0, 2));
			assertEquals('S', board.getElement(0, 3));
		}
		
		@Test
		public void testMoveHeroIntoExitDoors() {
			Board board = new Board(boardMap);
			Guard guard = new RookieGuard(3, 1);
			Hero hero = new Hero(1, 2);
			MoveObj[] objs = new MoveObj[] { hero, guard };
			
			
			board.fillBoard(objs);
			assertEquals('H', board.getElement(1, 2));
			
			
			board.openDoors();
			board.fillBoard(objs);
			
			
			boolean sucessfulMove = hero.moveCharacter(board, Direction.LEFT);
			assertTrue(sucessfulMove);
			
			assertTrue(board.onStairs(hero));
		}		
}
