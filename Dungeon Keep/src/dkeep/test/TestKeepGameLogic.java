package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.Ogre;
import dkeep.logic.level.Level2;
import dkeep.util.Direction;

public class TestKeepGameLogic {

	char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X'},
		{'X', ' ', ' ', ' ', 'X'},
		{'D', ' ', ' ', ' ', 'X'},
		{'D', 'k', ' ', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X'}
	};

	@Test
	public void testHeroMovesNextToOgre() {

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,1);
		Ogre ogre = new Ogre(3,1), ogre2 = new Ogre(4,1);
		MoveObj[] objs = new MoveObj[] { hero, ogre, ogre2 };

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));

		boolean successfulMove = hero.moveCharacter(board, Direction.RIGHT);
		assertTrue(successfulMove);
		assertTrue(hero.nearPos(ogre) || hero.collision(ogre));
	}

	@Test
	public void testHeroMovesToKeyCell() {

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] { hero };

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));

		boolean successfulMove = hero.moveCharacter(board, Direction.DOWN);
		assertTrue(successfulMove);

		hero.pickKey();
		assertTrue(hero.hasKey());
		assertSame(hero.getSymbol(), 'K');
	}

	@Test
	public void testHeroMovesToExitDoorWithoutKey() {

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] { hero };

		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));

		boolean successfulMove = board.canMoveTo(0, 2);
		assertFalse(successfulMove);
	}

	@Test
	public void testHeroMovesToExitDoorWithKey() {

		Level2 lvl2 = new Level2(1);

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,3);
		MoveObj[] objs = new MoveObj[] { hero };

		lvl2.setBoard(board);
		lvl2.setObjs(objs);
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 3));

		hero.pickKey();
		board.pickKey();
		board.fillBoard(objs);
		assertEquals('K', board.getElement(1, 3));

		assertSame('S', board.getElement(0, 3));
		assertSame('S', board.getElement(0, 2));
		assertTrue(board.canMoveTo(0, 3) && hero.hasKey());
		assertTrue(board.canMoveTo(0, 2) && hero.hasKey());
	}

	@Test
	public void testHeroMovesToExitGettingVictory() {

		Level2 lvl = new Level2(1);

		Board board = new Board(boardMap);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] { hero };

		lvl.setBoard(board);
		lvl.setObjs(objs);
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));

		hero.pickKey();
		board.pickKey();
		board.fillBoard(objs);
		assertEquals('K', board.getElement(1, 2));

		boolean successfulMove = hero.moveCharacter(board, Direction.LEFT);
		assertTrue(successfulMove); 
	}
}
