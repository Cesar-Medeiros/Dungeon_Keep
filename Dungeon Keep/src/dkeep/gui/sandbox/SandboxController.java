package dkeep.gui.sandbox;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.Ogre;

public class SandboxController implements MouseListener, MouseMotionListener{

	private SandboxPanel sandboxPanel;
	private Image selectedImg;
	private char selectedElem;
	private char[][] boardMap;
	private int side = 6;
//	private Timer createTimer;
	


	public static char[][] getCustomLevel() {
		SandboxController sbc = new SandboxController();
		for(int j = 0; j < sbc.boardMap.length; j++) {
			for(int i = 0; i < sbc.boardMap[j].length; i++) {
				System.err.print(sbc.boardMap[j][i] + " ");
			}
		System.err.print("\n");
		}
		
		return sbc.boardMap;
	}
	
	public SandboxController() {
//		createTimer = new Timer(10, new ActionListener() {
//		    public void actionPerformed(ActionEvent evt) {
//		    	createBoard();
//		    }
//		});
//		createTimer.setRepeats(false);
//		createTimer.start();
		new SandboxPanel(this);
	}
	
	void createBoard() {
		
		boardMap = new char[side][side];
		Graphics g = sandboxPanel.getBoardGrahics();
	
		int minDimension = Math.min(sandboxPanel.getBoardWidth(), sandboxPanel.getBoardHeight());
		
		int sizeImage = minDimension/side;
		
		
		Image wallResized = sandboxPanel.getWall().getScaledInstance(sizeImage, sizeImage, Image.SCALE_DEFAULT);
		Image floorResized = sandboxPanel.getFloor().getScaledInstance(sizeImage, sizeImage, Image.SCALE_DEFAULT);
		
		for(int i = 0; i < side; i++) {
			for(int j = 0; j < side; j++) {
				
				if(i == 0 || i == side - 1 || j == 0 || j == side - 1) {
					g.drawImage(wallResized, i*sizeImage, j*sizeImage, null);
					boardMap[i][j] = Board.wallSymbol;
				}
				else {
					g.drawImage(floorResized, i*sizeImage, j*sizeImage, null);
					boardMap[i][j] = Board.floorSymbol;
				}
			}
		}
		
	}
	
	
	public void drawElement(int x, int y) {
		Graphics g = sandboxPanel.getBoardGrahics();
		
		int minDimension = Math.min(sandboxPanel.getBoardWidth(), sandboxPanel.getBoardHeight());
		
		int nCol = side;
		int nRow = side;
		
		int sizeX = minDimension/nRow;
		int sizeY = minDimension/nCol;
		
		int posX = x/sizeX;
		int posY = y/sizeY;
		
		Image selectedImgR = selectedImg.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		
		g.drawImage(selectedImgR, posX*sizeX, posY*sizeY, null);
		boardMap[posY][posX] = selectedElem;
		
	}
	
	
	public SandboxPanel getSandboxPanel() {
		return sandboxPanel;
	}
	
	public void changeBoardSize(int value) {
		side = value;
		createBoard();
	}

	public void selectWall(Image wall) {
		this.selectedImg = wall;	
		selectedElem = Board.wallSymbol;
	}


	public void selectHero(Image hero) {
		this.selectedImg = hero;
		selectedElem = Hero.heroSymbol;
		
	}

	public void selectGuard(Image guard) {
		this.selectedImg = guard;
		selectedElem = Ogre.ogreSymbol;
		
	}
	

	public void selectDoor(Image closedDoor) {
		this.selectedImg = closedDoor;
		selectedElem = Board.closeDoorSymbol;
	}


	public void selectKey(Image key) {
		this.selectedImg = key;
		selectedElem = 'k';
	}
	
	
	public void setSandboxPanel(SandboxPanel sandboxPanel) {
		this.sandboxPanel = sandboxPanel;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		drawElement(arg0.getX(), arg0.getY());
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		drawElement(arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// not used
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// not used
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// not used
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// not used
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// not used
	}
	
}
