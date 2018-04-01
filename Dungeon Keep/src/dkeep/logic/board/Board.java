package dkeep.logic.board;

import java.io.Serializable;

import dkeep.logic.characters.MoveObj;

public class Board implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private char[][] board;
	private char[][] boardBuffer;
	
	public static final char wallSymbol = 'X';
	public static final char floorSymbol = ' ';
	public static final char closeDoorSymbol = 'I';
	public static final char openDoorSymbol = 'S';

	private void setElement(int posX, int posY, char element) {
		board[posY][posX] = element;
	}
	
	
	public void loadLevel() {
		char[][] level = boardBuffer;
		for(int i=0; i<level.length; i++)
			for(int j=0; j<level[i].length; j++)
				board[i][j]=level[i][j];
	}
	
	private char[][] clone(char[][] board) {
		char[][] cloneBoard = new char[board.length][];
		for(int i = 0; i < board.length; i++)
		    cloneBoard[i] = board[i].clone();
		
		return cloneBoard;
	}
	
	public Board(char[][] level) {
		int V_SIZE = level.length;
		int H_SIZE = level[0].length;
		board = new char[V_SIZE][H_SIZE];
		boardBuffer = clone(level);
		loadLevel();
	}
	
	
	public boolean substChar(char toSearch, char newChar) {
		
		for(int row = 0; row < boardBuffer.length; row++) {
			for(int col = 0; col < boardBuffer[row].length; col++) {
				if(board[row][col] == toSearch) {
					board[row][col] = newChar;
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

	public void openDoors() {
		for(char [] row : boardBuffer) {
			if(row[0] == closeDoorSymbol)
				row[0] = openDoorSymbol;
		}
	}
	
	public boolean onDoor(MoveObj obj) {
		return board[obj.getPosY()][obj.getPosX()] == openDoorSymbol;
	}
	
	public int getNumCol() {
		if(board == null) {
			return 0;
		}
		else {
			return board[0].length;
		}
		
	}
	
	public int getNumRow() {
		if(board == null) {
			return 0;
		}
		else {
			return board.length;
		}
	}


	public char getElement(int posX, int posY) {
		if(board == null) {
			return 0;
		}
		else {
			return board[posY][posX];
		}
	}
	
	public boolean canMoveTo(int posX, int posY) {
		char element = getElement(posX, posY);
		return (element != wallSymbol && element != closeDoorSymbol);
	}
}
