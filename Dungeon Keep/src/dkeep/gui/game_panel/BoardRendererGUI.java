package dkeep.gui.game_panel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dkeep.logic.board.Board;
import dkeep.logic.board.BoardRenderer;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.Ogre;

public class BoardRendererGUI implements BoardRenderer{

	private Image wall;
	private Image floor;
	private Image hero;
	private Image heroA;
	private Image guard;
	private Image lever;
	private Image closedDoor;
	private Image openDoor;
	private Image ogre;
	private Image club;
	
	GameGraphics gameGraphics;
	
	/**
	 * @brief GUI board renderer constructor
	 * @param gamegraphics Game's graphics
	 * 
	 * Loads all necessary images for the game.
	 */
	public BoardRendererGUI() {
		try {
			wall = ImageIO.read(new File("res/Board/Wall.png"));
			floor = ImageIO.read(new File("res/Board/Floor.png"));
			hero = ImageIO.read(new File("res/Board/Hero.png"));
			guard = ImageIO.read(new File("res/Board/Guard.png"));
			lever = ImageIO.read(new File("res/Board/Key.png"));
			closedDoor = ImageIO.read(new File("res/Board/ClosedDoor.png"));
			openDoor = ImageIO.read(new File("res/Board/OpenDoor.png"));
			ogre = ImageIO.read(new File("res/Board/Ogre.png"));
			club = ImageIO.read(new File("res/Board/Club.png"));
			heroA= ImageIO.read(new File("res/Board/HeroA.png"));
			
		} catch (IOException e) {
			System.err.println("Error: Could not load images");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * @brief Renders game graphics on the GUI
	 * @param board Game board
	 * 
	 * Scales game images depending on the game's board dimension,
	 * but maintaining its original ratio.
	 */
	@Override
	public void render(Board board) {
		int minDimension = Math.min(gameGraphics.getHeight(), gameGraphics.getWidth());
		int height = minDimension;
		int width = minDimension;
		
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
		Image ogreResized = ogre.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image clubResized = club.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image heroAResized = heroA.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Graphics arg0 = gameGraphics.getGraphics();
		
		
		for (int j = 0; j < nRow; j++) {
			for (int i = 0; i < nCol; i++) {
				arg0.drawImage(floorResized, sizeX * i, sizeY * j, null);
				switch(board.getElement(i, j)) {
				
				case Board.wallSymbol: 
					arg0.drawImage(wallResized, sizeX * i, sizeY * j, null);
					break;
					
				case Hero.heroSymbol: case 'K': 
					arg0.drawImage(heroResized, sizeX * i, sizeY * j, null);
					break;
	
				case Hero.armedSymbol:
					arg0.drawImage(heroAResized, sizeX * i, sizeY * j, null);
					break;
					
				case 'G': case 'g': 
					arg0.drawImage(guardResized, sizeX * i, sizeY * j, null);
					break;
					
				case Ogre.ogreSymbol:
					arg0.drawImage(ogreResized, sizeX * i, sizeY * j, null);
					break;
					
				case Ogre.clubSymbol:
					arg0.drawImage(clubResized, sizeX * i, sizeY * j, null);
					break;
					
				case Board.closeDoorSymbol: case Board.openableDoorSymbol:
					arg0.drawImage(closedDoorResized, sizeX * i, sizeY * j, null);
					break;
					
				case Board.openDoorSymbol:
					arg0.drawImage(openDoorResized, sizeX * i, sizeY * j, null);
					break;
					
				case Board.keySymbol:
					arg0.drawImage(leverResized, sizeX * i, sizeY * j, null);
					break;
					
				default: 
					arg0.drawImage(floorResized, sizeX * i, sizeY * j, null);
					break;
				}
			}
		}
	}
	
	/**
	 * @brief Sets game's graphics
	 * @param gameGraphics Game's graphics
	 * 
	 * Graphics are composed by the game's panel dimension 
	 * and graphical components.
	 */
	public void updateGameGraphics(GameGraphics gameGraphics) {
		this.gameGraphics = gameGraphics;
	}
}
