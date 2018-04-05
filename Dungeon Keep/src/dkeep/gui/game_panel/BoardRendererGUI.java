package dkeep.gui.game_panel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

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
	
	private boolean resize; 
	private Graphics graphics;
	private GameGraphics gameGraphics;
	private Hashtable<Character, Image> hashImage;
	
	
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
		hashImage = new Hashtable<>();
	}
	

	private void resizeImages(int sizeX, int sizeY) {
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
		
		hashImage.put(Board.wallSymbol, wallResized);
		hashImage.put(Board.floorSymbol,floorResized);
		hashImage.put(Hero.heroSymbol, heroResized);
		hashImage.put(Hero.withKeySymbol, heroResized);
		hashImage.put(Hero.armedSymbol, heroAResized);
		hashImage.put(Board.closeDoorSymbol, closedDoorResized);
		hashImage.put(Board.openableDoorSymbol, closedDoorResized);
		hashImage.put(Board.openDoorSymbol, openDoorResized);
		hashImage.put(Board.keySymbol, leverResized);
		hashImage.put('G', guardResized);
		hashImage.put('g', guardResized);
		hashImage.put(Ogre.ogreSymbol, ogreResized);
		hashImage.put(Ogre.stunnedSymbol, ogreResized);
		hashImage.put(Ogre.overKeySymbol, ogreResized);
		hashImage.put(Ogre.clubSymbol, clubResized);
		hashImage.put(Board.floorSymbol, floorResized);
		this.resize = false;
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
		int minDimension = (int) Math.min(gameGraphics.getSize().getHeight(), gameGraphics.getSize().getWidth());
		
		int nCol = board.getNumCol();
		int nRow = board.getNumRow();
		
		int sizeX = minDimension/nRow;
		int sizeY = minDimension/nCol;
		
		if(resize) resizeImages(sizeX, sizeY);
		
		for (int j = 0; j < nRow; j++) {
			for (int i = 0; i < nCol; i++) {
				graphics.drawImage(hashImage.get(Board.floorSymbol), sizeX * i, sizeY * j, null);
				Image image = hashImage.get(board.getElement(i, j));
				if( image != null) {
					graphics.drawImage(image, sizeX * i, sizeY * j, null);
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
		this.graphics = gameGraphics.getGraphics();
		
		if(this.gameGraphics != null && this.gameGraphics.getSize() == gameGraphics.getSize())
			return;
		
		this.resize = true;
		this.gameGraphics = gameGraphics;
	}


}
