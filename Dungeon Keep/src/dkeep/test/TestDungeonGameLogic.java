package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.guard.Guard;
import dkeep.logic.characters.guard.RookieGuard;
import dkeep.logic.characters.util.Movement;
import dkeep.util.Direction;


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
		Guard guard = new RookieGuard(3, 1, new Movement());
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
		Guard guard = new RookieGuard(3, 1, new Movement());
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
		Guard guard = new RookieGuard(3, 1, new Movement());
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
		Guard guard = new RookieGuard(3, 1, new Movement());
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
			Guard guard = new RookieGuard(3, 1, new Movement());
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
			Guard guard = new RookieGuard(3, 1, new Movement());
			Hero hero = new Hero(1, 2);
			MoveObj[] objs = new MoveObj[] { hero, guard };
			
			
			board.fillBoard(objs);
			assertEquals('H', board.getElement(1, 2));
			
			
			board.openDoors();
			board.fillBoard(objs);
			
			
			boolean sucessfulMove = hero.moveCharacter(board, Direction.LEFT);
			assertTrue(sucessfulMove);
			
			//assertTrue(board.onStairs(hero));
		}		
}
