package dkeep.cli;

import dkeep.logic.board.Board;
import dkeep.logic.board.BoardRenderer;
import dkeep.logic.game.GameGraphics;

public class BoardRendererCLI implements BoardRenderer{
	
	
	public void render(Board board, GameGraphics gameGraphics) {
		
		for(int j = 0; j < board.getNumRow(); j++) {
			for(int i = 0; i < board.getNumCol(); i++) {
				System.out.print(board.getElement(i, j) + " ");
			}
			System.out.print("\n");
		}
		
		System.out.print("\nKey: ");
	}
	
}
