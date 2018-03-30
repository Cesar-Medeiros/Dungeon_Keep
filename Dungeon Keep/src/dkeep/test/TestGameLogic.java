package dkeep.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.Test;

import dkeep.logic.game.GameConfig;
import dkeep.logic.game.GameEngine;
import dkeep.logic.level.Level;

public class TestGameLogic {

	GameEngine game;
	
	public void insertInput(char direction) {
	     String str =Character.toString(direction);
	     ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
	     System.setIn(bais);
	}
	
	@Test(timeout = 1000)
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		
		game = new GameEngine(new GameConfig(null, null, 0, 0));
		
		
		assertEquals("Choose some options", game.getState());
		assertFalse(game.isEndGame());
		
		
		insertInput('D');		
		game.update();
		assertFalse(game.isEndGame());
		assertEquals("You can play now.", game.getState());
		
		
//		Field completed = Level.class.getDeclaredField("completed");
//		completed.setAccessible(true);
//		completed.set(game.getLevel(), true);
//		insertInput('D');
//		game.update();
//		assertEquals("You won!", game.getState());
//		
//		
//		Field gameOver = Level.class.getDeclaredField("gameOver");
//		gameOver.setAccessible(true);
//		
//		insertInput('D');
//		game.update();
//		gameOver.set(game.getLevel(), true);
//		insertInput('D');
//		game.update();
//		assertEquals("You were captured ... Game over!", game.getState());
//		insertInput('D');
//		game.update();
//		assertTrue(game.isEndGame());
	}

}
