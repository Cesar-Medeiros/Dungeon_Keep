package dkeep.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.Test;
import dkeep.cli.GameInit;
import dkeep.cli.GameInit.State;
import dkeep.logic.level.Level;

public class TestGameLogic {

	GameInit game;
	
	public void insertInput(char direction) {
	     String str =Character.toString(direction);
	     ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
	     System.setIn(bais);
	}
	
	@Test(timeout = 1000)
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		
		game = new GameInit();
		assertEquals(State.START, game.getState());
		assertEquals(0, game.getiLevel());
		assertFalse(game.isEndGame());
		
		
		game.StateMachine();
		assertEquals(0, game.getiLevel());
		
		
		
		
		insertInput('D');
		game.StateMachine();
		assertFalse(game.getLevel().gameOver());
		assertFalse(game.getLevel().completed());
		
		
		Field completed = Level.class.getDeclaredField("completed");
		completed.setAccessible(true);
		completed.set(game.getLevel(), true);
		insertInput('D');
		game.StateMachine();
		assertEquals(State.START,game.getState());
		
		
		Field gameOver = Level.class.getDeclaredField("gameOver");
		gameOver.setAccessible(true);
		insertInput('D');
		game.StateMachine();
		gameOver.set(game.getLevel(), true);
		insertInput('D');
		game.StateMachine();
		assertEquals(State.GAMEOVER, game.getState());
		insertInput('D');
		game.StateMachine();
		assertTrue(game.isEndGame());
	}

}
