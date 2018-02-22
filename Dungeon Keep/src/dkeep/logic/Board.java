package dkeep.logic;

import dkeep.cli.IOInterface.Direction;

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
	
	public boolean substChar(char toSearch, char newChar) {
		
		for(int row = 0; row < boardBuffer.length; row++) {
			for(int col = 0; col < boardBuffer[row].length; col++) {
				if(boardBuffer[row][col] == toSearch) {
					boardBuffer[row][col] = newChar;
					return true;
				}
			}	
		}
		
		return false;
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
	
	
	
	public boolean moveCharacter(MoveObj character, Direction direction) {
				
		switch(direction) {		
		case UP: {
			char element = getElement(character.getPosX(), character.getPosY() - 1);
			
			if( element != 'X' && element != 'I') {
				character.setPosY(character.getPosY()-1);
				return true;
			}
			return false;
		}
		
		
		case DOWN: {
			char element = getElement(character.getPosX(), character.getPosY() + 1);
			
			if( element != 'X' && element != 'I') {
				character.setPosY(character.getPosY()+1);
				return true;
			}
			return false;
		}
		
		
		case LEFT: {
			char element = getElement(character.getPosX() - 1, character.getPosY());
			
			if( element != 'X' && element != 'I') {
				character.setPosX(character.getPosX()-1);
				return true;
			}
			return false;
		}
		
		
		case RIGHT: {
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
