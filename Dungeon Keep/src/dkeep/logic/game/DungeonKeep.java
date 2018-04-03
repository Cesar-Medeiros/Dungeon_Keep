package dkeep.logic.game;

import java.io.Serializable;

import dkeep.logic.board.BoardRenderer;
import dkeep.logic.level.CustomLevel2;
import dkeep.logic.level.Level;
import dkeep.logic.level.Level1;
import dkeep.logic.level.Level2;

public class DungeonKeep implements Serializable {


	private static final long serialVersionUID = 1L;

	public static enum State {
		START, GAME, GAMEOVER, WON
	};

	private State state;
	private Level[] levels;
	private int iLevel;
	private boolean endGame;
	private transient BoardRenderer boardRenderer;
	

	public DungeonKeep(GameConfig gameConfig, BoardRenderer boardRenderer) {
		endGame = false;
		state = State.START;
		this.boardRenderer = boardRenderer;
		levels = new Level[] {
			new Level1(gameConfig.getTypeGuard()),
			(gameConfig.getCustomLevel() == null)? new Level2(gameConfig.getNumOgres()) : new CustomLevel2(gameConfig.getCustomLevel()),
		};
	}

	public void start() {
		Level level = levels[iLevel];
		level.setup();
		state = State.GAME;
	}
	
	public void game() {
		Level level = levels[iLevel];
		level.update();

		if (level.gameOver()) {
			state = State.GAMEOVER;
			endGame = true;
		}

		if (level.completed()) {
			iLevel++;
			if (iLevel == levels.length) {
				state = State.WON;
			}
			else start();
		}
	}
	
	public void update() {

		if (endGame) {
			return;
		}

		switch (state) {

		case START: {
			start();
			break;
		}

		case GAME: {
			game();
			break;
		}

		case GAMEOVER: {
			endGame = true;
			break;
		}

		case WON: {
			endGame = true;
			break;
		}

		}
	}
	
	public void render() {
		if(state == State.WON) return;
		levels[iLevel].render(boardRenderer);
	}

	public String getState() {
		switch(state) {
		case START:
			return "Choose some options";
			
		case GAME:
			return "You can play now.";
			
		case GAMEOVER:
			return "You were captured ... Game over!";
			
		case WON:
			return "You won!";
			
		default: return "";
		}	
	}

	public boolean isEndGame() {
		return endGame;
	}
	
	public void setBoardRenderer(BoardRenderer boardRenderer) {
		this.boardRenderer = boardRenderer;
	}
	
	public Level getLevel() {
		return levels[iLevel];
	}
}
