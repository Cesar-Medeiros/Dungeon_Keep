public class GameInit {
	
	private static enum State {
		START, GAME, GAMEOVER
	};

	public static void main(String[] args) {
		State state = State.START;
		Level level = null;
		boolean endGame = false;
		
		while (!endGame) {
			switch (state) {

			case START: {
				level = new Level1();
				level.setup();
				state = State.GAME;
				break;
			}

			case GAME: {
				if (System.currentTimeMillis() % 100 == 0) {
					level.update();
					cleanScreen();
					level.draw();
					
					if (level.gameOver())
						state = State.GAMEOVER;

					if (level.completed()) {
						if(level.toString() == "Level1") {
							level = new Level2();
							level.setup();
						}
						else if(level.toString() == "Level2") {
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

	private static void cleanScreen() {
		// Clear Screen
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	

	
}