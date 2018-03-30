package dkeep.logic.characters;

import java.io.Serializable;

import dkeep.logic.board.Board;
import dkeep.util.Direction;

public class MoveObj implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int posX;
	protected int posY;
	protected char currentSymbol;

	public MoveObj(int posX, int posY, char symbol) {
		this.posX = posX;
		this.posY = posY;
		this.currentSymbol = symbol;	
		
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public char getSymbol() {
		return currentSymbol;
	}
	
	public void setSymbol(char symbol) {
		this.currentSymbol = symbol;
	}
	
	public boolean nearPos(MoveObj obj) {
		int deltaPosX = Math.abs(this.posX - obj.posX);
		int deltaPosY = Math.abs(this.posY - obj.posY);
		return	(deltaPosX + deltaPosY) <= 1;
	}
	
	public boolean collision(MoveObj obj) {
		return (posX == obj.getPosX() && posY == obj.getPosY());
	}
	
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
