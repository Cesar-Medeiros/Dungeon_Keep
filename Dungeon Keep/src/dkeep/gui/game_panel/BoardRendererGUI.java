package dkeep.gui.game_panel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dkeep.logic.board.Board;
import dkeep.logic.board.BoardRenderer;

public class BoardRendererGUI implements BoardRenderer{

	private Image wall;
	private Image floor;
	private Image hero;
	private Image guard;
	private Image lever;
	private Image closedDoor;
	private Image openDoor;
	
	GameGraphics gameGraphics;
	
	public BoardRendererGUI(GameGraphics gameGraphics) {
		this.gameGraphics=gameGraphics;
		
		try {
			wall = ImageIO.read(new File("Dungeon Keep/res/Board/Wall.png"));
			floor = ImageIO.read(new File("Dungeon Keep/res/Board/Floor.png"));
			hero = ImageIO.read(new File("Dungeon Keep/res/Board/Hero.png"));
			guard = ImageIO.read(new File("Dungeon Keep/res/Board/Guard.png"));
			lever = ImageIO.read(new File("Dungeon Keep/res/Board/Lever.png"));
			closedDoor = ImageIO.read(new File("Dungeon Keep/res/Board/ClosedDoor.png"));
			openDoor = ImageIO.read(new File("Dungeon Keep/res/Board/OpenDoor.png"));
		} catch (IOException e) {
			System.err.println("Error: Could not load images");
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void render(Board board) {
		int minDimension = Math.min(gameGraphics.getHeight(), gameGraphics.getWidth());
		int height = minDimension;
		int width = minDimension;
		
		//TODO: Se a board nao for quadrada a imagem fica deformada
		int nCol = board.getNumCol();
		int nRow = board.getNumRow();
		
		int sizeX = height/nRow;
		int sizeY = width/nCol;
		
		Image wallResized = wall.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image floorResized = floor.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image heroResized = hero.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image closedDoorResized = closedDoor.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image openDoorResized = openDoor.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image leverResized = lever.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image guardResized = guard.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		
		Graphics arg0 = gameGraphics.getGraphics();
		
		
		for (int j = 0; j < nRow; j++) {
			for (int i = 0; i < nCol; i++) {
				arg0.drawImage(floorResized, sizeX * j, sizeY * i, null);
				switch(board.getElement(j, i)) {
				case 'X': 
					arg0.drawImage(wallResized, sizeX * j, sizeY * i, null);
					break;
					
				case 'H': case 'K': case 'A':
					arg0.drawImage(heroResized, sizeX * j, sizeY * i, null);
					break;
	
				case 'G': case 'g': case 'O':
					arg0.drawImage(guardResized, sizeX * j, sizeY * i, null);
					break;
					
				case 'I': case 'D':
					arg0.drawImage(closedDoorResized, sizeX * j, sizeY * i, null);
					break;
					
				case 'S':
					arg0.drawImage(openDoorResized, sizeX * j, sizeY * i, null);
					break;
					
				case 'k':
					arg0.drawImage(leverResized, sizeX * j, sizeY * i, null);
					break;
					
				default: 
					arg0.drawImage(floorResized, sizeX * j, sizeY * i, null);
					break;
				}
			}
		}
	}
	
	public void updateGameGraphics(GameGraphics gameGraphics) {
		this.gameGraphics = gameGraphics;
	}
}
