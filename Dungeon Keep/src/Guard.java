
public class Guard extends MoveObj {

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
		super(8,1,'G');
	}
	
	public char nextMove() {
		return movements[(moveIndex++) % 24];
	}
}
