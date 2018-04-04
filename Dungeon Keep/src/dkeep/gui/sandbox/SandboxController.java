package dkeep.gui.sandbox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dkeep.gui.game_panel.BoardRendererGUI;
import dkeep.gui.game_panel.GameGraphics;
import dkeep.logic.board.Board;
import jdk.nashorn.internal.ir.GetSplitState;

public class SandboxController extends MouseAdapter {

	private SandboxPanel sandboxPanel;
	private char selectedElem;
	private char[][] boardMap;
	private int side;
	private BoardRendererGUI boardRenderer = new BoardRendererGUI(null);

	/**
	 * @brief Creates a sandbox controller and returns its board map
	 * @return Sandbox's controller board map
	 */
	public static char[][] getCustomLevel() {
		SandboxController sbc = new SandboxController();		
		return sbc.boardMap;
	}
	
	/**
	 * @brief Sandbox's controller constructor
	 * 
	 * Creates a Sandbox to be controlled by itself.
	 */
	public SandboxController() {
		new SandboxPanel(this);
	}
	
	/**
	 * @brief Creates a new custom board
	 * 
	 * Sets the board's default walls and floor. 
	 */
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
	
	/**
	 * @brief Adds the selected element to desired grid's position
	 * @param x Selected element x's position
	 * @param y Selected element y's position
	 */
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
	
	/**
	 * @brief Changes board's size
	 * @param value Board's new side's size
	 */
	public void changeBoardSize(int value) {
		side = value;
		createBoard();
		render();
	}

	/**
	 * @brief Selects an element
	 * @param element Element selected
	 */
	public void selectElement(char element) {	
		selectedElem = element;
	}
	
	/**
	 * @brief Updates game's graphics, rendering the new board
	 */
	public void render() {
		boardRenderer.updateGameGraphics(new GameGraphics(
				sandboxPanel.getBoardSize(),
				sandboxPanel.getBoardGrahics()
				)
			);
		boardRenderer.render(new Board(boardMap));
	}
	
	
	/**
	 * @brief Handles mouse's dragged events
	 * @param arg0 MouseEvent received from the mouse
	 *
	 * Receives events in result of dragging the mouse,
	 * adding the selected element to the desired position.
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		addElement(arg0.getX(), arg0.getY());
		render();
	}
	
	/**
	 * @brief Handles mouse's clicked events
	 * @param arg0 MouseEvent received from the mouse
	 *
	 * Receives events in result of clicking the mouse,
	 * adding an element to the desired position.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		addElement(arg0.getX(), arg0.getY());
		render();
	}
	
	/**
	 * @brief Sets sandbox's panel
	 * @param sandboxPanel Sandbox's panel
	 */
	public void setSandboxPanel(SandboxPanel sandboxPanel) {
		this.sandboxPanel = sandboxPanel;
	}
	
	/**
	 * @brief Gets sandbox's panel
	 * @return Sandbox's panel
	 */
	public SandboxPanel getSandboxPanel() {
		return sandboxPanel;
	}
}
