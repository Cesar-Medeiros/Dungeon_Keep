package dkeep.gui.sandbox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dkeep.gui.game_panel.BoardRendererGUI;
import dkeep.gui.game_panel.GameGraphics;
import dkeep.logic.board.Board;

public class SandboxController extends MouseAdapter{

	private SandboxPanel sandboxPanel;
	private char selectedElem;
	private char[][] boardMap;
	private int side;
	private BoardRendererGUI boardRenderer = new BoardRendererGUI(null);


	public static char[][] getCustomLevel() {
		SandboxController sbc = new SandboxController();		
		return sbc.boardMap;
	}
	
	public SandboxController() {
		new SandboxPanel(this);
	}
	
	public void createBoard() {
		boardMap = new char[side][side];
		
		for(int i = 0; i < side; i++) {
			for(int j = 0; j < side; j++) {
				
				if(i == 0 || i == side - 1 || j == 0 || j == side - 1) {
					boardMap[i][j] = Board.wallSymbol;
				}
				else {
					boardMap[i][j] = Board.floorSymbol;
				}
			}
		}		
	}
	
	public void addElement(int x, int y) {
		int minDimension = (int) Math.min(sandboxPanel.getBoardSize().getWidth(), sandboxPanel.getBoardSize().getHeight());
		
		int nCol = side;
		int nRow = side;
		
		int sizeX = minDimension/nRow;
		int sizeY = minDimension/nCol;
		
		int posX = x/sizeX;
		int posY = y/sizeY;
		
		boardMap[posY][posX] = selectedElem;
	}
	

	
	public void changeBoardSize(int value) {
		side = value;
		createBoard();
		render();
	}

	
	public void selectElement(char element) {	
		selectedElem = element;
	}
	
	public void render() {
		boardRenderer.updateGameGraphics(new GameGraphics(
				sandboxPanel.getBoardSize(),
				sandboxPanel.getBoardGrahics()
				)
			);
		boardRenderer.render(new Board(boardMap));
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		addElement(arg0.getX(), arg0.getY());
		render();
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		addElement(arg0.getX(), arg0.getY());
		render();
	}
	
	
	public void setSandboxPanel(SandboxPanel sandboxPanel) {
		this.sandboxPanel = sandboxPanel;
	}
	
	
	public SandboxPanel getSandboxPanel() {
		return sandboxPanel;
	}
}
