package dkeep.cli;
import dkeep.logic.Level;
import dkeep.logic.Level1;
import dkeep.logic.Level2;

public class GameInit {
	
	private static enum State {
		START, GAME, GAMEOVER
	};

	public static void main(String[] args) {
		State state = State.START;
		Level level = null;
		boolean endGame = false;
		
		IOInterface.setInterface(IOInterface.Interface.CLI);
	
		while (!endGame) {
			switch (state) {

			case START: {
				level = new Level1();
				level.setup();
				state = State.GAME;
				level.draw();
				break;
			}

			case GAME: {
				if (System.currentTimeMillis() % 100 == 0) {
					level.draw();
					
					if (level.gameOver()){
						state = State.GAMEOVER;
						break;
					}

					level.update();

					if (level.completed()) {
						if(level.toString() == "Level1") {
							level = new Level2();
							level.setup();
						}
						else if(level.toString() == "Level2") {
							level.draw();
							System.out.println("You won!");
							endGame = true;
						}
					}

				}
				break;
			}
			
			case GAMEOVER: {
				endGame = true;
				System.out.println("You were captured ... Game over!");
				break;
			}

			}
		}

	}



	
}