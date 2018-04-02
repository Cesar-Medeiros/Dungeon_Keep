package dkeep.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

import dkeep.logic.game.DungeonKeep;
import dkeep.logic.game.GameConfig;
import dkeep.util.Input;

public class CLI{
	
	public static void createAndShowCLI(){
		
		Scanner scan = new Scanner(System.in);
		boolean newGame = true;
		
		while(newGame) {
		
			newGame = false;
			
			int nOgres = 0;
			int typeGuard = 0;
			
			
			try{
				System.out.print("Number of Ogres: ");
				nOgres = scan.nextInt();
				
				System.out.println("");
				
				System.out.println("Type of guard: ");
				System.out.println("1 - Rookie ");
				System.out.println("2 - Drunken ");
				System.out.println("3 - Suspicious");
				System.out.print("Select: ");
				typeGuard = scan.nextInt() - 1;
				
				System.out.println("");
			}
			catch(InputMismatchException  e) {
				System.out.println("Invalid number inserted.");
				newGame = true;
				scan.nextLine();
				continue;
			}
		
			Input.setCliInput();
			
			DungeonKeep dk = new DungeonKeep(new GameConfig(typeGuard,nOgres), new BoardRendererCLI());
			
			while(! dk.isEndGame()) {
				dk.update();
				dk.render();
			}
			System.out.println(dk.getState());
						
			System.out.println("New Game (Y/N): ");
			newGame = (Character.toUpperCase(scan.next().charAt(0)) == 'Y');
		}
		scan.close();
	}	
}
