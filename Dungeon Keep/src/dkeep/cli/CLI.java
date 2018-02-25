package dkeep.cli;

import java.io.IOException;
import java.util.Scanner;

import dkeep.logic.Board;

public class CLI extends IOInterface {

	private static Scanner scan = new Scanner(System.in);

	public static Direction getDirection() {
		
//		try {
//			if(System.in.available() == 0) return Direction.NONE;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
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
	

	public static void printBoard(Board board) {
		
		for(char[] row : board.getBoard()) {
			for(char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.print("\n");
		}
		
		System.out.print("\nKey: ");
		
	}
	
	
}
