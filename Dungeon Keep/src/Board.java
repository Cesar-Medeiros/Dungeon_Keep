public class Board {
	
	private char[][] board;
	private char[][] boardBuffer;


	private void loadLevel(char [][] level) {
		for(int i=0; i<level.length; i++)
			for(int j=0; j<level[i].length; j++)
				board[i][j]=level[i][j];
	}
	
	
	
	public Board(char[][] level) {
		int V_SIZE = level.length;
		int H_SIZE = level[0].length;
		board = new char[V_SIZE][H_SIZE];
		boardBuffer = level;
		loadLevel(boardBuffer);
	}
	
	
	public char getElement(int posX, int posY) {
		return board[posY][posX];
	}
	
	
	public void setElement(int posX, int posY, char element) {
		board[posY][posX] = element;
	}
	
	
	public void printBoard(MoveObj... characters) {
		
		loadLevel(boardBuffer);
		
		for(MoveObj character : characters) {
			setElement(character.getPosX(), character.getPosY(), character.getSymbol());
		}
		
		for(char[] row : board) {
			for(char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.print("\n");
		}
		
		System.out.print("\nKey: ");
	}
	
	
	
	public boolean moveCharacter(MoveObj character, char direction) {
		
		direction = Character.toUpperCase(direction);
		
		switch(direction) {		
		case 'W': {
			char element = getElement(character.getPosX(), character.getPosY() - 1);
			
			if( element != 'X' && element != 'I') {
				character.setPosY(character.getPosY()-1);
				return true;
			}
			return false;
		}
		
		
		case 'S': {
			char element = getElement(character.getPosX(), character.getPosY() + 1);
			
			if( element != 'X' && element != 'I') {
				character.setPosY(character.getPosY()+1);
				return true;
			}
			return false;
		}
		
		
		case 'A': {
			char element = getElement(character.getPosX() - 1, character.getPosY());
			
			if( element != 'X' && element != 'I') {
				character.setPosX(character.getPosX()-1);
				return true;
			}
			return false;
		}
		
		
		case 'D': {
			char element = getElement(character.getPosX() + 1, character.getPosY());
			
			if( element != 'X' && element != 'I') {
				character.setPosX(character.getPosX()+1);
				return true;
			}
			return false;
		}
		
		default: return false;
		}
		
	}

}
