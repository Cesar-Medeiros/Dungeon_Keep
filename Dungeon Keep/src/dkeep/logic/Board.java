package dkeep.logic;

import dkeep.cli.IOInterface;

public class Board {
	
	private char[][] board;
	private char[][] boardBuffer;


	
	public char[][] getBoard() {
		return board;
	}

	
	public char getElement(int posX, int posY) {
		return board[posY][posX];
	}
	

	public void setElement(int posX, int posY, char element) {
		board[posY][posX] = element;
	}
	
	
	
	
	
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
	
	public void fillBoard(MoveObj[] objs) {
		loadLevel();
		for(MoveObj obj : objs) {
			setElement(obj.getPosX(), obj.getPosY(), obj.getSymbol());
		}
	}
	
	public void draw(MoveObj[] objs) {
		fillBoard(objs);
		IOInterface.printBoard(this);
	}
	
	public void openDoors() {
		for(char [] row : boardBuffer) {
			if(row[0] == 'I')
				row[0] = 'S';
		}
	}
	
	public boolean onStairs(MoveObj obj) {
		return board[obj.getPosY()][obj.getPosX()] == 'S';
	}
}
