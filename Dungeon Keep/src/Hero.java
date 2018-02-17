
public class Hero extends Personage {
	
	public Hero() {
		this.posX = 1;
		this.posY = 1;
	}
	
	public boolean onStairs() {
		return (posX == 0 && (posY == 5 || posY == 6));
	}
	
	public boolean collisionWithLever(Lever lever) {
		
		if (posX == lever.getPosX() &&
			posY == lever.getPosY()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean capturedByGuard(Guard guard) {
		
		if ((Math.abs(posX - guard.getPosX()) < 2) &&
			 posY == guard.getPosY()) {
				return true;
		} else if ((Math.abs(posY - guard.getPosY()) < 2) &&
			 posX == guard.getPosX()) {
			return true;
		} else {
			return false;
		}
	}
	
}
