package dkeep.util;

import java.util.Scanner;

public class CLI implements IOInterface{
	
	private Scanner scan;
	
	public CLI(){
		scan = new Scanner(System.in);
	}
	
	public Direction getDirection() {
		return Direction.charToDirection(scan.next().charAt(0));
	}

	@Override
	public void addDirection(Direction direction) {	
	}
}
