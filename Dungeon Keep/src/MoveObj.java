
public class MoveObj {

	protected int posX;
	protected int posY;
	protected char symbol;

	public MoveObj(int posX, int posY, char symbol) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.symbol = symbol;
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
	
	public boolean nearPos(MoveObj obj) {
		int deltaPosX = Math.abs(this.posX - obj.posX);
		int deltaPosY = Math.abs(this.posY - obj.posY);
		
		if(	(deltaPosX ^ deltaPosY) == 1  && 
			(deltaPosX | deltaPosY) == 1
			) 
			return true;
		else return false;
	}
	
	public boolean collision(MoveObj obj) {
		return (posX == obj.getPosX() && posY == obj.getPosY());
	}
	
}
