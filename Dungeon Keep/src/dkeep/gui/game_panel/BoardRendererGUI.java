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
	
	
	private Image wallResized;
	private Image floorResized;
	private Image heroResized;
	private Image closedDoorResized;
	private Image openDoorResized;
	private Image leverResized;
	private Image guardResized;
	private Image ogreResized;
	private Image clubResized;
	private Image heroAResized;
	
	private boolean resize; 
	private Graphics graphics;
	private GameGraphics gameGraphics;
	
	/**
	 * Constructs the GUI board renderer, 
	 * loading all necessary images for the game.
	 */
	public BoardRendererGUI() {
		try {
			wall = ImageIO.read(new File("Dungeon Keep/res/Board/Wall.png"));
			floor = ImageIO.read(new File("Dungeon Keep/res/Board/Floor.png"));
			hero = ImageIO.read(new File("Dungeon Keep/res/Board/Hero.png"));
			guard = ImageIO.read(new File("Dungeon Keep/res/Board/Guard.png"));
			lever = ImageIO.read(new File("Dungeon Keep/res/Board/Key.png"));
			closedDoor = ImageIO.read(new File("Dungeon Keep/res/Board/ClosedDoor.png"));
			openDoor = ImageIO.read(new File("Dungeon Keep/res/Board/OpenDoor.png"));
			ogre = ImageIO.read(new File("Dungeon Keep/res/Board/Ogre.png"));
			club = ImageIO.read(new File("Dungeon Keep/res/Board/Club.png"));
			heroA= ImageIO.read(new File("Dungeon Keep/res/Board/HeroA.png"));
			
		} catch (IOException e) {
			System.err.println("Error: Could not load images");
			e.printStackTrace();
		}
	}
	
	/**
	 * Resizes images to a certain dimension
	 * @param board Game board
	 * @param sizeX Image's x-dimension
	 * @param sizeY Image's y-dimension
	 */
	private void resizeImages(Board board, int sizeX, int sizeY) {
		
		wallResized = wall.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		floorResized = floor.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		heroResized = hero.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		closedDoorResized = closedDoor.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		openDoorResized = openDoor.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		leverResized = lever.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		guardResized = guard.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		ogreResized = ogre.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		clubResized = club.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		heroAResized = heroA.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		this.resize = false;
	}

	/**
	 * Scales game images depending on the game's board dimension,
	 * but maintaining its original ratio.
	 * @param board Game board
	 */
	@Override
	public void render(Board board) {
		int minDimension = (int) Math.min(gameGraphics.getSize().getHeight(), gameGraphics.getSize().getWidth());
		int height = minDimension;
		int width = minDimension;
		
		int nCol = board.getNumCol();
		int nRow = board.getNumRow();
		
		int sizeX = height/nRow;
		int sizeY = width/nCol;
		
		if(resize) resizeImages(board, sizeX, sizeY);
		
		for (int j = 0; j < nRow; j++) {
			for (int i = 0; i < nCol; i++) {
				graphics.drawImage(floorResized, sizeX * i, sizeY * j, null);
				switch(board.getElement(i, j)) {
				
				case Board.wallSymbol: 
					graphics.drawImage(wallResized, sizeX * i, sizeY * j, null);
					break;
					
				case Hero.heroSymbol: case Hero.withKeySymbol: 
					graphics.drawImage(heroResized, sizeX * i, sizeY * j, null);
					break;
	
				case Hero.armedSymbol:
					graphics.drawImage(heroAResized, sizeX * i, sizeY * j, null);
					break;
					
				case 'G': case 'g': 
					graphics.drawImage(guardResized, sizeX * i, sizeY * j, null);
					break;
					
				case Ogre.ogreSymbol: case Ogre.stunnedSymbol:
					graphics.drawImage(ogreResized, sizeX * i, sizeY * j, null);
					break;
					
				case Ogre.overKeySymbol:
					graphics.drawImage(leverResized, sizeX * i, sizeY * j, null);
					graphics.drawImage(ogreResized, sizeX * i, sizeY * j, null);
					break;
					
				case Ogre.clubSymbol:
					graphics.drawImage(clubResized, sizeX * i, sizeY * j, null);
					break;
					
				case Board.closeDoorSymbol: case Board.openableDoorSymbol:
					graphics.drawImage(closedDoorResized, sizeX * i, sizeY * j, null);
					break;
					
				case Board.openDoorSymbol:
					graphics.drawImage(openDoorResized, sizeX * i, sizeY * j, null);
					break;
					
				case Board.keySymbol:
					graphics.drawImage(leverResized, sizeX * i, sizeY * j, null);
					break;
					
				default: 
					graphics.drawImage(floorResized, sizeX * i, sizeY * j, null);
					break;
				}
			}
		}
	}
	
	/**
	 * Sets game's graphics, composed by the game's panel
	 * dimension and its components.
	 * @param gameGraphics Game's graphics
	 */
	public void updateGameGraphics(GameGraphics gameGraphics) {
		this.graphics = gameGraphics.getGraphics();
		
		if(this.gameGraphics != null && this.gameGraphics.getSize() == gameGraphics.getSize())
			return;
		
		this.resize = true;
		this.gameGraphics = gameGraphics;
	}
}
