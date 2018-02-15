import java.io.IOException;
import java.util.*;

public class main {

	public static void main(String[] args) {
		
		Board board = new Board();
		Hero hero = new Hero();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			board.printBoard(hero);
			char direction = scan.next().charAt(0);
			
			board.moveHero(hero, direction);
			
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
		
	}

}
