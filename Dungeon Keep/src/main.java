import java.io.IOException;
import java.util.*;

public class main {

	public static void main(String[] args) {
		
		Board board = new Board();
		Hero hero = new Hero();
		Lever lever = new Lever();
		Guard guard = new Guard();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			board.printBoard(hero, guard);
			
			if (hero.onStairs()) {
				System.out.println("You won!");
				scan.close();
				return;
			} else if (hero.capturedByGuard(guard)) {
				System.out.println("You were captured ... Game over!");
				scan.close();
				return;
			}
			
			char direction = scan.next().charAt(0);
			board.move(hero, direction);
			board.move(guard, guard.nextMove());
			
			if (hero.collisionWithLever(lever)) {
				board.openDoors();
			}
			
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}
}
