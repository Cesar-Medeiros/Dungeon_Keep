
public class Guard extends Personage {

	private char movements[] = {
			'A', 'S', 'S', 'S', 
			'S', 'A', 'A', 'A',
			'A', 'A', 'A', 'S',
			'D', 'D', 'D', 'D',
			'D', 'D', 'D', 'W',
			'W', 'W', 'W', 'W'
	};
	private static int moveIndex = 0;
	
	public Guard() {
		this.posX = 8;
		this.posY = 1;
	}
	
	public char[] getMovements() {
		return movements;
	}
	
	public void setMovements(char movements[]) {
		this.movements = movements;
	}
	
	public char nextMove() {
		
		moveIndex++;
		if (moveIndex == 24) {
			moveIndex = 1;
		}
		return movements[moveIndex - 1];
	}
}
