package dkeep.logic.board;

import dkeep.logic.board.Board;
import dkeep.logic.game.GameGraphics;

public interface BoardRenderer {
	public void render(Board board, GameGraphics gameGraphics);
}
