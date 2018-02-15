public class Board {
	
	private char[][] board;
	
	public Board() {
		board = new char[][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', ' ', 'I', ' ','X' ,' ' ,'G' , 'X'},
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
	
	public void printBoard(Hero hero) {
		
		char element = getElement(hero.getPosX(), hero.getPosY());
		
		setElement(hero.getPosX(), hero.getPosY(), 'H');
		
		
		for(char[] row : board) {
			for(char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.print("\n");
		}
		
		setElement(hero.getPosX(), hero.getPosY(), element);
		
	}
	
	public void moveHero(Hero hero, char direction) {
		
		direction = Character.toUpperCase(direction);
		
		switch(direction) {
			
		case 'W': {
			char element = getElement(hero.getPosX(), hero.getPosY() - 1);
			
			if( element != 'X' && element != 'I') {
				hero.setPosY(hero.getPosY()-1);
			}
			break;
		}
		
		
		case 'S': {
			char element = getElement(hero.getPosX(), hero.getPosY() + 1);
			
			if( element != 'X' && element != 'I') {
				hero.setPosY(hero.getPosY()+1);
			}
			break;
		}
		
		case 'A': {
			char element = getElement(hero.getPosX() - 1, hero.getPosY());
			
			if( element != 'X' && element != 'I') {
				hero.setPosX(hero.getPosX()-1);
			}
			break;
		}
		
		case 'D': {
			char element = getElement(hero.getPosX() + 1, hero.getPosY());
			
			if( element != 'X' && element != 'I') {
				hero.setPosX(hero.getPosX()+1);
			}
			break;
		}
		
		default: break;
		}
		
	}

}
