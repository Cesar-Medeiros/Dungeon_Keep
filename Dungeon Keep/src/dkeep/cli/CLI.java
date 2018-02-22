package dkeep.cli;

import java.util.Scanner;

public class CLI extends IOInterface {

	private static Scanner scan = new Scanner(System.in);

	public static Direction getDirection() {
		
		char direction = scan.next().charAt(0);
		direction = Character.toUpperCase(direction);
		
		switch(direction) {
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
	
	
}
