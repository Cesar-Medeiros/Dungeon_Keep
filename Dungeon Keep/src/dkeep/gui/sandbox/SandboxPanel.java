package dkeep.gui.sandbox;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JSlider;


public class SandboxPanel extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel canvasPanel;
	private JButton wallBtn;
	private JButton heroBtn;
	private JButton guardBtn;
	private JButton closedDoorBtn;
	private JButton openDoorBtn;
	private JButton keyBtn;
	private JButton saveBtn;
	private JSlider dimensionSlider;
	
	
    private Image wall;
    private Image floor;
    private Image hero;
    private Image guard;
    private Image key;
    private Image closedDoor;
    private Image openDoor;
	
	
	private SandboxController controller;
	public static final Dimension PREFERREDSIZE = new Dimension(650,500);
	
	
	public SandboxPanel(SandboxController controller) {
		super(null, "Custom level creator", ModalityType.APPLICATION_MODAL);
		setModal(true);
        setPreferredSize(PREFERREDSIZE);

        //Center the frame
        setLocationRelativeTo(null);
        
        //Display the window.
        pack();
        
        this.controller = controller;
        this.controller.setSandboxPanel(this);
		loadImages();
		configureLayout();
		configure();
		registerListeners();
		
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
	}
	
	
    public void loadImages() {
        try {
            wall = ImageIO.read(new File("res/Board/Wall.png"));
            floor = ImageIO.read(new File("res/Board/Floor.png"));
            hero = ImageIO.read(new File("res/Board/Hero.png"));
            guard = ImageIO.read(new File("res/Board/Guard.png"));
            key = ImageIO.read(new File("res/Board/Lever.png"));
            closedDoor = ImageIO.read(new File("res/Board/ClosedDoor.png"));
            openDoor = ImageIO.read(new File("res/Board/OpenDoor.png"));
        } catch (IOException e) {
            System.err.println("Error: Could not load images");
            e.printStackTrace();
        }
    }
	

	private void configureLayout() {
		//setBorder(new EmptyBorder(20, 20, 20, 20));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0,  0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0};
		setLayout(gbl_contentPane);
	}
	
	
	private void configure(){
		
		JPanel elementsPanel = new JPanel();
		GridBagConstraints gbc_elementsPanel = new GridBagConstraints();
		gbc_elementsPanel.insets = new Insets(0, 5, 5, 0);
		gbc_elementsPanel.fill = GridBagConstraints.BOTH;
		gbc_elementsPanel.gridx = 1;
		gbc_elementsPanel.gridy = 1;
		add(elementsPanel, gbc_elementsPanel);
		GridBagLayout gbl_elementsPanel = new GridBagLayout();
		gbl_elementsPanel.columnWidths = new int[]{50, 50, 50, 50};
		gbl_elementsPanel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_elementsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_elementsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		elementsPanel.setLayout(gbl_elementsPanel);
		
		canvasPanel = new JPanel();
		GridBagConstraints gbc_canvasPanel = new GridBagConstraints();
		gbc_canvasPanel.insets = new Insets(0, 0, 5, 5);
		gbc_canvasPanel.fill = GridBagConstraints.BOTH;
		gbc_canvasPanel.gridx = 0;
		gbc_canvasPanel.gridy = 1;
		add(canvasPanel, gbc_canvasPanel);

		wallBtn = new JButton();
		wallBtn.setIcon(new ImageIcon(wall));
		wallBtn.setMinimumSize(new Dimension(50,50));
		wallBtn.setPreferredSize(new Dimension(50,50));
		GridBagConstraints gbc_wallBtn = new GridBagConstraints();
		gbc_wallBtn.gridwidth = 2;
		gbc_wallBtn.insets = new Insets(0, 0, 5, 5);
		gbc_wallBtn.gridx = 0;
		gbc_wallBtn.gridy = 3;
		elementsPanel.add(wallBtn, gbc_wallBtn);
		
		heroBtn = new JButton();
		heroBtn.setIcon(new ImageIcon(hero));
		heroBtn.setMinimumSize(new Dimension(50,50));
		heroBtn.setPreferredSize(new Dimension(50,50));
		GridBagConstraints gbc_heroBtn = new GridBagConstraints();
		gbc_heroBtn.gridwidth = 2;
		gbc_heroBtn.insets = new Insets(0, 0, 5, 5);
		gbc_heroBtn.gridx = 0;
		gbc_heroBtn.gridy = 4;
		elementsPanel.add(heroBtn, gbc_heroBtn);
		
		guardBtn = new JButton();
		guardBtn.setIcon(new ImageIcon(guard));
		guardBtn.setMinimumSize(new Dimension(50,50));
		guardBtn.setPreferredSize(new Dimension(50,50));
		GridBagConstraints gbc_guardBtn = new GridBagConstraints();
		gbc_guardBtn.gridwidth = 2;
		gbc_guardBtn.insets = new Insets(0, 0, 5, 0);
		gbc_guardBtn.gridx = 1;
		gbc_guardBtn.gridy = 4;
		elementsPanel.add(guardBtn, gbc_guardBtn);
		
		closedDoorBtn = new JButton();
		closedDoorBtn.setIcon(new ImageIcon(closedDoor));
		closedDoorBtn.setMinimumSize(new Dimension(50,50));
		closedDoorBtn.setPreferredSize(new Dimension(50,50));
		GridBagConstraints gbc_closedDoorBtn = new GridBagConstraints();
		gbc_closedDoorBtn.gridwidth = 2;
		gbc_closedDoorBtn.insets = new Insets(0, 0, 5, 5);
		gbc_closedDoorBtn.gridx = 0;
		gbc_closedDoorBtn.gridy = 5;
		elementsPanel.add(closedDoorBtn, gbc_closedDoorBtn);
		
		openDoorBtn = new JButton();
		openDoorBtn.setIcon(new ImageIcon(openDoor));
		openDoorBtn.setMinimumSize(new Dimension(50,50));
		openDoorBtn.setPreferredSize(new Dimension(50,50));
		GridBagConstraints gbc_openDoorBtn = new GridBagConstraints();
		gbc_openDoorBtn.gridwidth = 2;
		gbc_openDoorBtn.insets = new Insets(0, 0, 5, 0);
		gbc_openDoorBtn.gridx = 1;
		gbc_openDoorBtn.gridy = 5;
		elementsPanel.add(openDoorBtn, gbc_openDoorBtn);
		
		keyBtn = new JButton();
		keyBtn.setIcon(new ImageIcon(key));
		keyBtn.setMinimumSize(new Dimension(50,50));
		keyBtn.setPreferredSize(new Dimension(50,50));
		GridBagConstraints gbc_keyBtn = new GridBagConstraints();
		gbc_keyBtn.gridwidth = 2;
		gbc_keyBtn.insets = new Insets(0, 0, 5, 5);
		gbc_keyBtn.gridx = 1;
		gbc_keyBtn.gridy = 3;
		elementsPanel.add(keyBtn, gbc_keyBtn);
		
		dimensionSlider = new JSlider();
		GridBagConstraints gbc_dimensionSlider = new GridBagConstraints();
		gbc_dimensionSlider.fill = GridBagConstraints.HORIZONTAL;
		gbc_dimensionSlider.insets = new Insets(0, 0, 0, 5);
		gbc_dimensionSlider.gridx = 0;
		gbc_dimensionSlider.gridy = 2;
		add(dimensionSlider, gbc_dimensionSlider);
		dimensionSlider.setMinimum(6);
		dimensionSlider.setMaximum(20);
		dimensionSlider.setValue(dimensionSlider.getMinimum());
		
		
		saveBtn = new JButton("Save map");
		GridBagConstraints gbc_saveBtn = new GridBagConstraints();
		gbc_saveBtn.gridx = 1;
		gbc_saveBtn.gridy = 2;
		add(saveBtn, gbc_saveBtn);
	}
	
	private void registerListeners(){
		
		canvasPanel.addMouseListener(controller);
		canvasPanel.addMouseMotionListener(controller);
		
		wallBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectWall(wall);
			}
		});
		

		heroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectHero(hero);
			}
		});
		
		guardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectGuard(guard);
			}
		});
		
		closedDoorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectDoor(closedDoor);
			}
		});
		
		
		keyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectKey(key);
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		dimensionSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				controller.changeBoardSize(dimensionSlider.getValue());
			}
			
		});
	}
	
	public int getBoardWidth() {
		return canvasPanel.getWidth();
	}
	
	public int getBoardHeight() {
		return canvasPanel.getHeight();
	}
	
	public Graphics getBoardGrahics(){
		return canvasPanel.getGraphics();
	}

	public Image getWall() {
		return wall;
	}

	public Image getFloor() {
		return floor;
	}
	
}