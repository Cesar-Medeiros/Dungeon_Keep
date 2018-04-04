package dkeep.util;

import java.util.Scanner;

public class CLI implements IOInterface{
	
	private Scanner scan;
	
	/**
	 * @brief Console interface constructor
	 * 
	 * Creates a scanner to input directions.
	 */
	public CLI(){
		scan = new Scanner(System.in);
	}
	
	/**
	 * @brief Gets the next direction
	 * @return Direction required from the user
	 */
	public Direction getDirection() {
		return Direction.charToDirection(scan.next().charAt(0));
	}

	@Override
	public void addDirection(Direction direction) {	
		
	}
}
