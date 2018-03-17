package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import dkeep.cli.GameInit;
import dkeep.cli.IOInterface;

public class GameState extends JPanel {

	private GameInit game;
	//private Timer timer;
	private Image leftUp;
	private Image rightUp;
	private Image centerUp;
	
	private Image leftDown;
	private Image rightDown;
	private Image centerDown;
	
	private Image left;
	private Image right;
	
	private Image floor;
	
	private Image hero;
	
	
	public GameState() {
		try {
			leftUp = ImageIO.read(new File("res\\Board\\LeftUp.png"));
			rightUp = ImageIO.read(new File("res\\Board\\RightUp.png"));
			centerUp = ImageIO.read(new File("res\\Board\\CenterUp.png"));
			
			leftDown = ImageIO.read(new File("res\\Board\\LeftDown.png"));
			rightDown = ImageIO.read(new File("res\\Board\\RightDown.png"));
			centerDown = ImageIO.read(new File("res\\Board\\CenterDown.png"));
			
			floor = ImageIO.read(new File("res\\Board\\Floor.png"));
			
			left = ImageIO.read(new File("res\\Board\\Left.png"));
			right = ImageIO.read(new File("res\\Board\\Right.png"));
			
			hero = ImageIO.read(new File("res\\Board\\Hero.png"));
		} catch (IOException e) {
		}
		
		 
		game = new GameInit(IOInterface.Interface.GRAPHICS);
	}

	
	public void update() {
		game.StateMachine();
	}
	
	public void draw() {
		repaint();
	}
	
	public void start() {
		update();
		draw();
		//refreshScreen();
	}
	
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		
		int height = 500;//(int) this.getSize().getHeight();
		int width = 500; //(int) this.getSize().getWidth();
		int nCol = 10;
		int nRow = 10;
		
		int sizeX = height/nRow;
		int sizeY = width/nCol;
		
		Image leftDownResized = leftDown.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image rightDownResized = rightDown.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image centerDownResized = centerDown.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		
		Image leftUpResized = leftUp.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image rightUpResized = rightUp.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image centerUpResized = centerUp.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		
		Image leftResized =  left.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		Image rightResized=  right.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		
		Image floorResized = floor.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		
		Image heroResized = hero.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
		
		for (int j = 0; j < nRow; j++) {
			for (int i = 0; i < nCol; i++) {
				
				if(j == 0) {
					if(i == 0) arg0.drawImage(leftUpResized, 0, j * sizeY, null);
					else if(i + 1 == nCol)	arg0.drawImage(rightUpResized, sizeX * i, j * sizeY, null);
					else arg0.drawImage(centerUpResized, sizeX * i, j * sizeY, null);
				}
				else if(j + 1 == nRow) {
					if(i == 0) arg0.drawImage(leftDownResized, 0, j * sizeY, null);
					else if(i + 1 == nCol)	arg0.drawImage(rightDownResized, sizeX * i, j * sizeY, null);
					else arg0.drawImage(centerDownResized, sizeX * i, j * sizeY, null);
				}
				else {
					if(i == 0) arg0.drawImage(leftResized, 0, j * sizeY, null);
					else if(i + 1 == nCol)	arg0.drawImage(rightResized, sizeX * i, j * sizeY, null);
					else arg0.drawImage(floorResized, sizeX * i, j * sizeY, null);
				}
			}
		}
		arg0.drawImage(heroResized, sizeX * 1, 1 * sizeY, null);
	}
	
	
//	public void refreshScreen() {
//		timer = new Timer(0, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				game.StateMachine();
//				repaint();
//			}
//		});
//		timer.setRepeats(true);
//		timer.setDelay(17);
//		timer.start();
//	}

}
