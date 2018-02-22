package dkeep.logic;

public class MoveObj {

	protected int posX;
	protected int posY;
	protected char symbol;
	protected char symbol1;
	protected char symbol2;

	public MoveObj(int posX, int posY, char symbol) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.symbol = symbol;	
		this.symbol1 = symbol;
		this.symbol2 = ' ';
		
	}
	
	public MoveObj(int posX, int posY, char symbol, char symbol2) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.symbol = symbol;
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
		return symbol;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public void toNormalSymbol() {
		this.symbol = this.symbol1;
	}
	
	public void toSpecialSymbol() {
		this.symbol = this.symbol2;
	}
	
	public boolean nearPos(MoveObj obj) {
		int deltaPosX = Math.abs(this.posX - obj.posX);
		int deltaPosY = Math.abs(this.posY - obj.posY);
		
		return	((deltaPosX ^ deltaPosY) == 1  && (deltaPosX | deltaPosY) == 1);
	}
	
	public boolean collision(MoveObj obj) {
		return (posX == obj.getPosX() && posY == obj.getPosY());
	}
	
}
