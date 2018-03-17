package dkeep.cli;
import dkeep.logic.level.*;

public class GameInit {
	
	public static enum State {
		START, GAME, GAMEOVER, WON
	};

	private State state = State.START;
	private Level levels[] = null;
	private int iLevel = 0;
	private Level level = null;
	private boolean endGame = false;
	
	public GameInit(IOInterface.Interface ioInterface){
		levels = new Level[] {new Level1(), new Level2()};
		IOInterface.setInterface(ioInterface);
	}
	
	public void GameCycle() {		
		while (!endGame) {
			StateMachine();
		}
	}
	
	public void StateMachine() {
		if(endGame) return;
		level = levels[iLevel];
		switch (state) {

		case START: {
			level.setup();
			state = State.GAME;
			level.draw();
			break;
		}

		case GAME: {
				level.update();
				
				if (level.gameOver()){
					state = State.GAMEOVER;
				}


				if (level.completed()) {
					iLevel++;
					state = State.START;
					if(iLevel == levels.length) {
						state = State.WON;
					}
				}
				level.draw();
				
			break;
		}
		
		case GAMEOVER: {
			endGame = true;
			System.out.println("You were captured ... Game over!");
			break;
		}
		
		case WON:{
			endGame = true;
			System.out.println("You won!");
			break;
		}

		}
	}

	
	public State getState() {
		return state;
	}

	public int getiLevel() {
		return iLevel;
	}

	public boolean isEndGame() {
		return endGame;
	}
	
	public Level getLevel() {
		return level;
	}
}