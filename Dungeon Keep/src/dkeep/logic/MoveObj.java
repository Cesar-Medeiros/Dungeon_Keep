package dkeep.logic;

import dkeep.cli.IOInterface.Direction;

public class MoveObj {

	protected int posX;
	protected int posY;
	protected char currentSymbol;
	protected char symbol1;
	protected char symbol2;

	public MoveObj(int posX, int posY, char symbol) {
		this.posX = posX;
		this.posY = posY;
		this.currentSymbol = symbol;	
		this.symbol1 = symbol;
		this.symbol2 = ' ';
		
	}
	
	public MoveObj(int posX, int posY, char symbol, char symbol2) {
		this.posX = posX;
		this.posY = posY;
		this.currentSymbol = symbol;
		this.symbol1 = symbol;
		this.symbol2 = symbol2;
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
	
	public void toNormalSymbol() {
		this.currentSymbol = this.symbol1;
	}
	
	public void toSpecialSymbol() {
		this.currentSymbol = this.symbol2;
	}
	
	public boolean nearPos(MoveObj obj) {
		int deltaPosX = Math.abs(this.posX - obj.posX);
		int deltaPosY = Math.abs(this.posY - obj.posY);
		
		return	((deltaPosX ^ deltaPosY) == 1  && (deltaPosX | deltaPosY) == 1);
	}
	
	public boolean collision(MoveObj obj) {
		return (posX == obj.getPosX() && posY == obj.getPosY());
	}
	
	public boolean moveCharacter(Board board, Direction direction) {
		
		MoveObj character = this;
		
		switch(direction) {		
		case UP: {
			char element = board.getElement(character.getPosX(), character.getPosY() - 1);
			
			if( element != 'X' && element != 'I') {
				character.setPosY(character.getPosY()-1);
				return true;
			}
			return false;
		}
		
		
		case DOWN: {
			char element = board.getElement(character.getPosX(), character.getPosY() + 1);
			
			if( element != 'X' && element != 'I') {
				character.setPosY(character.getPosY()+1);
				return true;
			}
			return false;
		}
		
		
		case LEFT: {
			char element = board.getElement(character.getPosX() - 1, character.getPosY());
			
			if( element != 'X' && element != 'I') {
				character.setPosX(character.getPosX()-1);
				return true;
			}
			return false;
		}
		
		
		case RIGHT: {
			char element = board.getElement(character.getPosX() + 1, character.getPosY());
			
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
