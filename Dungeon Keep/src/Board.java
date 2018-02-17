public class Board {

	private char[][] board;

	public Board() {
		board = new char[][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', ' ', 'I', ' ','X' ,' ' ,' ' , 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
	}

	public char getElement(int posX, int posY) {
		return board[posY][posX];
	}

	public void setElement(int posX, int posY, char element) {
		board[posY][posX] = element;
	}

	public void printBoard(Hero hero, Guard guard) {

		char elementHero = getElement(hero.getPosX(), hero.getPosY());
		char elementGuard = getElement(guard.getPosX(), guard.getPosY());

		setElement(hero.getPosX(), hero.getPosY(), 'H');
		setElement(guard.getPosX(), guard.getPosY(), 'G');

		for(char[] row : board) {
			for(char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.print("\n");
		}

		setElement(hero.getPosX(), hero.getPosY(), elementHero);
		setElement(guard.getPosX(), guard.getPosY(), elementGuard);
	}
	
	public <T extends Personage> void move(T type, char direction) {
		
		direction = Character.toUpperCase(direction);

		switch(direction) {

		case 'W': {
			char element = getElement(type.getPosX(), type.getPosY() - 1);

			if (element != 'X' && element != 'I') {
				type.setPosY(type.getPosY()-1);
			}
			break;
		}


		case 'S': {
			char element = getElement(type.getPosX(), type.getPosY() + 1);

			if (element != 'X' && element != 'I') {
				type.setPosY(type.getPosY()+1);
			}
			break;
		}

		case 'A': {
			char element = getElement(type.getPosX() - 1, type.getPosY());

			if (element != 'X' && element != 'I') {
				type.setPosX(type.getPosX()-1);
			}
			break;
		}

		case 'D': {
			char element = getElement(type.getPosX() + 1, type.getPosY());

			if (element != 'X' && element != 'I') {
				type.setPosX(type.getPosX()+1);
			}
			break;
		}

		default:
			break;
		}
}
	
	public void openDoors() {
		board[5][0] = 'S';
		board[6][0] = 'S';
	}
}
