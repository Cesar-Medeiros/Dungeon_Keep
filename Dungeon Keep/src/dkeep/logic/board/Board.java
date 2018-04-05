package dkeep.logic.board;

import java.io.Serializable;
import dkeep.logic.characters.MoveObj;
import dkeep.util.Direction;

public class Board implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private char[][] board;
	private char[][] boardBuffer;
	
	public static final char wallSymbol = 'X';
	public static final char floorSymbol = ' ';
	public static final char closeDoorSymbol = 'I';
	public static final char openDoorSymbol = 'S';
	public static final char openableDoorSymbol = 'D';
	public static final char keySymbol = 'k';

	/**
	 * Board constructor
	 * @param level Level's board
	 */
	public Board(char[][] level) {
		int V_SIZE = level.length;
		int H_SIZE = level[0].length;
		board = new char[V_SIZE][H_SIZE];
		boardBuffer = clone(level);
		loadLevel();
	}
	
	/**
	 * Returns board's number of columns
	 * @return Returns board's number of columns, 0 if the
	 * board does not yet exist
	 */
	public int getNumCol() {
		if(board == null) {
			return 0;
		}
		else {
			return board[0].length;
		}
	}
	
	/**
	 * Returns board's number of rows
	 * @return Returns board's number of rows, 0 if the
	 * board does not yet exist
	 */
	public int getNumRow() {
		if(board == null) {
			return 0;
		}
		else {
			return board.length;
		}
	}

	/**
	 * Returns board's element
	 * @param posX Element's x-position on the board
	 * @param posY Element's y-position on the board
	 * @return Returns board's element with coordinates
	 * passed as parameters
	 */
	public char getElement(int posX, int posY) {
		if(board == null) {
			return 0;
		}
		else {
			return board[posY][posX];
		}
	}
	
	/**
	 * Replaces element on the game board
	 * @param toSearch Element's char to be replaced
	 * @param newChar Element's new char to be inserted
	 */
	private void substChar(char toSearch, char newChar) {
		for(int row = 0; row < boardBuffer.length; row++) {
			for(int col = 0; col < boardBuffer[row].length; col++) {
				if(boardBuffer[row][col] == toSearch) {
					boardBuffer[row][col] = newChar;
				}
			}	
		}
	}
	
	/**
	 * Inserts element on the game's board
	 * @param posX Element's x-position
	 * @param posY Element's y-position
	 * @param element Element's character
	 */
	private void setElement(int posX, int posY, char element) {
		board[posY][posX] = element;
	}
	
	/**
	 * Loads level to the game's board
	 */
	public void loadLevel() {
		char[][] level = boardBuffer;
		for(int i=0; i<level.length; i++)
			for(int j=0; j<level[i].length; j++)
				board[i][j]=level[i][j];
	}
	
	/**
	 * Clones current game's board
	 * @param board Current game's board
	 * @return Cloned game board
	 */
	private char[][] clone(char[][] board) {
		char[][] cloneBoard = new char[board.length][];
		for(int i = 0; i < board.length; i++)
		    cloneBoard[i] = board[i].clone();
		
		return cloneBoard;
	}
	
	/**
	 * Fills game board with its moving objects
	 * @param objs Moving objects to be inserted on the board
	 */
	public void fillBoard(MoveObj[] objs) {
		loadLevel();
		for(MoveObj obj : objs) {
			setElement(obj.getPosX(), obj.getPosY(), obj.getSymbol());
		}
	}

	/**
	 * Opens dungeon's exit doors
	 */
	public void openDoors() {
		substChar(openableDoorSymbol, openDoorSymbol);
	}
	
	/**
	 * Erases key from the map and opens exit doors
	 */
	public void pickKey() {
		substChar(keySymbol, ' ');
		openDoors();
	}
	
	/**
	 * Checks if moving object is on an open door
	 * @param moveObj Moving object to be tested
	 * @return Returns true if object is on an open door, false otherwise
	 */
	public boolean onOpenDoor(MoveObj moveObj) {
		return(getElement(moveObj.getPosX(), moveObj.getPosY()) == openDoorSymbol);
	}

	/**
	 * Checks if there's an openable door near a moving object
	 * @param moveObj Moving object to be tested
	 * @param direction Direction where there's supposedly the door
	 * @return Returns true if there's an openable door on that direction, false otherwise
	 */
	public boolean onOpenableDoor(MoveObj moveObj, Direction direction) {
		int x = moveObj.getPosX();
		int y = moveObj.getPosY();
		
		switch(direction) {
		case LEFT: x -= 1; break;
		case RIGHT: x += 1; break;
		case UP: y-=1; break;
		case DOWN: y+=1; break;
		default: break;
		}
		return(getElement(x, y) == openableDoorSymbol);
	}
	
	/**
	 * Checks if moving object is on a key
	 * @param moveObj Moving object to be tested
	 * @return Returns true if object's over a key, false otherwise
	 */
	public boolean onKey(MoveObj moveObj) {
		boolean onKeyPos = (getElement(moveObj.getPosX(), moveObj.getPosY()) == keySymbol);
		return onKeyPos;
	}

	/**
	 * Checks if board's movement to a cell is possible
	 * @param posX Cell's x-position on the board
	 * @param posY Cell's y-position on the board
	 * @return Returns true if movement is possible, false otherwise
	 */
	public boolean canMoveTo(int posX, int posY) {
		if(posX < 0 || posX >= getNumCol() || posY < 0 || posY >= getNumRow()) return false;
		
		char element = getElement(posX, posY);
		return (element != wallSymbol && element != closeDoorSymbol && element != openableDoorSymbol);
	}
}
