package dkeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import dkeep.cli.GameInit;
import dkeep.cli.IOInterface;
import dkeep.cli.GameInit.State;
import dkeep.cli.IOInterface.Direction;
import dkeep.logic.Board;
import dkeep.logic.Hero;
import dkeep.logic.MoveObj;
import dkeep.logic.Ogre;
import dkeep.logic.level.Level;
import dkeep.logic.level.Level2;

public class TestKeepGameLogic {

	char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X'},
		{'X', ' ', ' ', ' ', 'X'},
		{'I', ' ', ' ', ' ', 'X'},
		{'I', 'k', ' ', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X'}
	};
	
	@Test
	public void testHeroMovesNextToOgre() {
				
		Board board = new Board(boardMap);
		Hero hero = new Hero(1,1);
		Ogre ogre = new Ogre(3,1);
		MoveObj[] objs = new MoveObj[] { hero, ogre };
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 1));
		
		boolean sucessfulMove = hero.moveCharacter(board, Direction.RIGHT);
		assertTrue(sucessfulMove);
		assertTrue(hero.nearPos(ogre) || hero.collision(ogre));
	}
	
	@Test
	public void testHeroMovesToKeyCell() {
		
		Board board = new Board(boardMap);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] { hero };
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));
		
		boolean sucessfulMove = hero.moveCharacter(board, Direction.DOWN);
		assertTrue(sucessfulMove);
		
		hero.hasKey();
		assertSame(hero.getSymbol(), 'K');
	}
	
	@Test
	public void testHeroMovesToExitDoorWithoutKey() {
		
		Board board = new Board(boardMap);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] { hero };
		
		board.fillBoard(objs);
		assertEquals('H', board.getElement(1, 2));
		
		boolean sucessfulMove = hero.moveCharacter(board, Direction.LEFT);
		assertFalse(sucessfulMove);
	}
	
	@Test
	public void testHeroMovesToExitDoorWithKey() {
		
		Level2 lvl = new Level2();
		
		Board board = new Board(boardMap);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] { hero };
		
		Level2.setBoardMap(boardMap);
		lvl.setBoard(board);
		lvl.setObjs(objs);
		
		hero.hasKey();
		board.fillBoard(objs);
		assertEquals('K', board.getElement(1, 2));
		
		assertSame('I', board.getElement(0, 2));
		assertTrue(hero.tryExit(Direction.LEFT, lvl));
		board.fillBoard(objs);
		assertSame('S', board.getElement(0, 2));
	}
	
	@Test
	public void testHeroMovesToExitGettingVictory() {
		
		Level2 lvl = new Level2();
		
		Board board = new Board(boardMap);
		Hero hero = new Hero(1,2);
		MoveObj[] objs = new MoveObj[] { hero };
		
		Level2.setBoardMap(boardMap);
		lvl.setBoard(board);
		lvl.setObjs(objs);
		
		hero.hasKey();
		board.fillBoard(objs);
		
		assertTrue(hero.tryExit(Direction.LEFT, lvl));
		board.fillBoard(objs);
		
		assertTrue(hero.moveCharacter(board, Direction.LEFT));
		assertTrue(lvl.onExit(hero));
	}	
}