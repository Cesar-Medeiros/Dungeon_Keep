package dkeep.cli;

import dkeep.logic.board.Board;
import dkeep.logic.board.BoardRenderer;

public class BoardRendererCLI implements BoardRenderer{
	
	/**
	 * Renders game graphics on the CLI, printing the
	 * game's board on the console interface.
	 */
	@Override
	public void render(Board board) {
		
		for(int j = 0; j < board.getNumRow(); j++) {
			for(int i = 0; i < board.getNumCol(); i++) {
				System.out.print(board.getElement(i, j) + " ");
			}
			System.out.print("\n");
		}
		
		System.out.print("\nKey: ");
	}
	
}
