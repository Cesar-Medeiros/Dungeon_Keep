package dkeep.logic;

import dkeep.cli.IOInterface;

public class Board {
	
	private char[][] board;
	private char[][] boardBuffer;


	public void loadLevel() {
		char[][] level = boardBuffer;
		for(int i=0; i<level.length; i++)
			for(int j=0; j<level[i].length; j++)
				board[i][j]=level[i][j];
	}
	
	public Board(char[][] level) {
		int V_SIZE = level.length;
		int H_SIZE = level[0].length;
		board = new char[V_SIZE][H_SIZE];
		boardBuffer = level;
		loadLevel();
	}
	
	
	
	public char[][] getBoard() {
		return board;
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
		IOInterface.printBoard(this, characters);
	}
	
	
	

}
