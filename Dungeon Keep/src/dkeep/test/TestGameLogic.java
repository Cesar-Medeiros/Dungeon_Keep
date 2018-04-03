package dkeep.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.Test;

import dkeep.logic.game.DungeonKeep;
import dkeep.logic.game.GameConfig;
import dkeep.logic.level.Level;

public class TestGameLogic {

	DungeonKeep game;

	@Test(timeout = 1000)
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		
		game = new DungeonKeep(new GameConfig(0, 0), null);
		
		assertEquals("Choose some options", game.getState());
		assertFalse(game.isEndGame());
		
		game.update();
		assertFalse(game.isEndGame());
		assertEquals("You can play now.", game.getState());
		
		Field completed = Level.class.getDeclaredField("completed");
		completed.setAccessible(true);
		completed.set(game.getLevel(), true);
		game.update();
		completed.set(game.getLevel(), true);
		game.update();
		assertEquals("You won!", game.getState());
		
		game = new DungeonKeep(new GameConfig(0, 0), null);
		
		Field gameOver = Level.class.getDeclaredField("gameOver");
		gameOver.setAccessible(true);
		game.update();
		gameOver.set(game.getLevel(), true);
		game.update();
		assertEquals("You were captured ... Game over!", game.getState());
		game.update();
		assertTrue(game.isEndGame());
	}
}
