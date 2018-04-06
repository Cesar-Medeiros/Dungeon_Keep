package dkeep.test;

import static dkeep.util.Direction.DOWN;
import static dkeep.util.Direction.LEFT;
import static dkeep.util.Direction.RIGHT;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;

import dkeep.cli.BoardRendererCLI;
import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.guard.DrunkenGuard;
import dkeep.logic.characters.guard.Guard;
import dkeep.logic.characters.guard.RookieGuard;
import dkeep.logic.characters.guard.SuspiciousGuard;
import dkeep.logic.characters.util.Movement;
import dkeep.logic.game.DungeonKeep;
import dkeep.logic.game.GameConfig;
import dkeep.logic.level.Level1;
import dkeep.util.Direction;
import dkeep.util.Input;

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
		assertEquals(boardMap[0][0], board.getElement(0, 0));
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

	
	@Test
	public void testMoveDrunkenGuard() {

		Level1 lvl1 = new Level1(1);

		Board board = new Board(boardMap);
		DrunkenGuard guard = new DrunkenGuard(1, 1, new Movement(Arrays.asList(DOWN)));
		MoveObj[] objs = new MoveObj[] { guard };

		lvl1.setObjs(objs);
		board.fillBoard(objs);

		assertTrue(guard.isActive());
		assertSame('G', board.getElement(1, 1));

		guard.move(board);
		board.fillBoard(objs);
		assertSame('G', board.getElement(1, 2));
	}
	
	@Test
	public void testPutGuardToSleep() {

		Level1 lvl1 = new Level1(1);

		Board board = new Board(boardMap);
		DrunkenGuard guard = new DrunkenGuard(1, 1, new Movement(Arrays.asList(DOWN)));
		MoveObj[] objs = new MoveObj[] { guard };

		lvl1.setObjs(objs);
		board.fillBoard(objs);

		assertTrue(guard.isActive());
		assertSame('G', board.getElement(1, 1));

		guard.putToSleep();
		board.fillBoard(objs);

		assertFalse(guard.isActive());
		assertSame('g', board.getElement(1, 1));
		
		guard.move(board);
		
		assertSame('g', board.getElement(1, 1));

	}

	@Test
	public void testWakeUpGuard() {

		Level1 lvl1 = new Level1(1);

		Board board = new Board(boardMap);
		DrunkenGuard guard = new DrunkenGuard(1, 1, new Movement());
		MoveObj[] objs = new MoveObj[] { guard };

		guard.putToSleep();
		lvl1.setObjs(objs);
		board.fillBoard(objs);

		assertFalse(guard.isActive());
		assertSame('g', board.getElement(1, 1));

		guard.wakeUp();
		board.fillBoard(objs);

		assertTrue(guard.isActive());
		assertSame('G', board.getElement(1, 1));
	}

	@Test
	public void testMoveNearGuardWhileAsleep() {

		DungeonKeep game = new DungeonKeep(new GameConfig(0, 0), null);
		Level1 lvl1 = (Level1) game.getLevel();

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,1);
		DrunkenGuard guard = new DrunkenGuard(3, 1, new Movement());
		MoveObj[] objs = new MoveObj[] { hero, guard };

		guard.putToSleep();
		lvl1.setObjs(objs);
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));
		assertEquals('g', board.getElement(3, 1));

		game.update();
		assertFalse(game.isEndGame());

		hero.moveCharacter(board, Direction.RIGHT);
		board.fillBoard(objs);
		game.update();
		assertFalse(lvl1.gameOver());
	}

	@Test
	public void testGuardRevertPath() throws Exception {

		Movement movements =  new Movement(Arrays.asList(LEFT));

		Level1 lvl1 = new Level1(1);

		Board board = new Board(boardMap);
		SuspiciousGuard guard = new SuspiciousGuard(1, 1, movements);
		MoveObj[] objs = new MoveObj[] { guard };

		lvl1.setObjs(objs);
		board.fillBoard(objs);
		assertEquals('G', board.getElement(1, 1));

		Field reversed = SuspiciousGuard.class.getDeclaredField("reversed");
		reversed.setAccessible(true);
		reversed.setBoolean(guard, true);
		Field numRounds = SuspiciousGuard.class.getDeclaredField("numRounds");
		numRounds.setAccessible(true);
		numRounds.setInt(guard, 3);

		guard.move(board);
		board.fillBoard(objs);
		assertEquals('G', board.getElement(2, 1));
	}
	
	@Test
	public void testBoard() throws Exception {
		
		Board board = new Board(boardMap);
		
		assertFalse(board.canMoveTo(-1, 0));
		assertFalse(board.canMoveTo(0, -1));
		assertFalse(board.canMoveTo(board.getNumCol(), 0));
		assertFalse(board.canMoveTo(0, board.getNumRow()));
		
		assertTrue(board.canMoveTo(1, 1));
	}
	
	@Test
	public void testMovement() throws Exception {
		
		Movement movement = new Movement(Arrays.asList(LEFT,RIGHT));
		
		assertEquals(LEFT, movement.getNext());
		assertEquals(RIGHT, movement.getNextR());
		
	}
	
	@Test
	public void testLevel1() throws Exception {
		Level1 lvl1 = new Level1(0);
		lvl1.setup();
		lvl1.update();
		assertFalse(lvl1.completed());
		assertFalse(lvl1.gameOver());
		
		Input.setGraphicInput();
		Input.addDirection(RIGHT);
		Input.addDirection(RIGHT);
		Input.addDirection(RIGHT);
		Input.addDirection(DOWN);
		Input.addDirection(DOWN);
		Input.addDirection(DOWN);
		Input.addDirection(DOWN);
		lvl1.update();
		lvl1.update();
		lvl1.update();
		lvl1.update();
		lvl1.update();
		lvl1.update();
		lvl1.update();
		assertFalse(lvl1.completed());
		assertTrue(lvl1.gameOver());
	}
}
