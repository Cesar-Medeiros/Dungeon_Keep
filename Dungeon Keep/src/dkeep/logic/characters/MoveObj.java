package dkeep.logic.characters;

import java.io.Serializable;

import dkeep.logic.board.Board;
import dkeep.util.Direction;

public class MoveObj implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int posX;
	protected int posY;
	protected char currentSymbol;

	/**
	 * Moving object constructor
	 * @param posX Initial object's x-position
	 * @param posY Initial object's y-position
	 * @param symbol Object's main character
	 */
	public MoveObj(int posX, int posY, char symbol) {
		this.posX = posX;
		this.posY = posY;
		this.currentSymbol = symbol;	
		
	}
	
	/**
	 * Returns the moving object's x-position
	 * @return Object's x-position on the board
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Sets moving object's x-position
	 * @param posX Object's x-position
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Returns the moving object's y-position
	 * @return Object's y-position on the board
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Sets moving object's y-position
	 * @param posY Object's y-position
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Returns object's current symbol
	 * @return Object's current symbol
	 */
	public char getSymbol() {
		return currentSymbol;
	}
	
	/**
	 * Sets moving object's symbol
	 * @param symbol Object's symbol
	 */
	public void setSymbol(char symbol) {
		this.currentSymbol = symbol;
	}
	
	/**
	 * Checks if moving objects are near in horizontally
	 * or vertically adjacent cells
	 * @param obj Moving object to be compared
	 * @return Returns true if objects are near, false otherwise
	 */
	public boolean nearPos(MoveObj obj) {
		int deltaPosX = Math.abs(this.posX - obj.posX);
		int deltaPosY = Math.abs(this.posY - obj.posY);
		return (deltaPosX + deltaPosY) <= 1;
	}
	
	/**
	 * Checks if there's collision between 2 moving objects
	 * @param obj Moving object to be compared
	 * @return Returns true if there is collision, false otherwise
	 */
	public boolean collision(MoveObj obj) {
		return (posX == obj.getPosX() && posY == obj.getPosY());
	}
	
	/**
	 * Moves an object on the board
	 * @param board	Game board
	 * @param direction Direction of movement
	 * @return Returns true if movement was successful, false otherwise
	 */
	public boolean moveCharacter(Board board, Direction direction) {
		
		MoveObj character = this;
		
		switch(direction) {	
		
		case UP: {
			boolean canMove = board.canMoveTo(character.getPosX(), character.getPosY() - 1);
			if(canMove) {
				character.setPosY(character.getPosY()-1);
			}
			return canMove;
		}
		
		case DOWN: {
			boolean canMove = board.canMoveTo(character.getPosX(), character.getPosY() + 1);
			if(canMove) {
				character.setPosY(character.getPosY()+1);
			}
			return canMove;
		}
		
		case LEFT: {
			boolean canMove = board.canMoveTo(character.getPosX() - 1, character.getPosY());
			if(canMove) {
				character.setPosX(character.getPosX()-1);
			}
			return canMove;
		}
				
		case RIGHT: {
			boolean canMove = board.canMoveTo(character.getPosX() + 1, character.getPosY());
			if(canMove) {
				character.setPosX(character.getPosX()+1);
			}
			return canMove;
		}
		
		default: return false;
		}	
	}
}
