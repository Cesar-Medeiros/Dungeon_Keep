package dkeep.util;

public enum Direction {
	UP, RIGHT, DOWN, LEFT, NONE;
	
	
	public Direction revertDirection() {
		switch(this) {
		case UP:
			return DOWN;
		case RIGHT:
			return LEFT;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		default:
			return NONE;
		}
	}
	
	public static Direction charToDirection(char charD) {
		charD = Character.toUpperCase(charD);
		
		switch(charD) {
		case 'W':
			return Direction.UP;
		case 'A':
			return Direction.LEFT;
		case 'S':
			return Direction.DOWN;
		case 'D':
			return Direction.RIGHT;
		default:
			return Direction.NONE;
		}
	}
	
};


