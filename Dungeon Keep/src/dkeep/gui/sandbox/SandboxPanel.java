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

import dkeep.gui.load_save.LoadSaveGame;
import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.Ogre;

import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;


public class SandboxPanel extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel canvasPanel;
	private JButton wallBtn;
	private JButton heroBtn;
	private JButton ogreBtn;
	private JButton closedDoorBtn;
	private JButton openDoorBtn;
	private JButton keyBtn;
	private JButton saveBtn;
	private JSlider dimensionSlider;
	
    private Image wall;
    private Image floor;
    private Image hero;
    private Image ogre;
    private Image key;
    private Image closedDoor;
    private Image openDoor;

	private SandboxController controller;
	public static final Dimension PREFERREDSIZE = new Dimension(650,500);
	
	/**
	 * @brief Sandbox's panel constructor
	 * @param controller Sandbox's panel controller
	 */
	public SandboxPanel(SandboxController controller) {
		super(null, "Custom level creator", ModalityType.APPLICATION_MODAL);
		setModal(true);
        setPreferredSize(PREFERREDSIZE);

        setLocationRelativeTo(null);
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
	
	/**
	 * @brief Loads all the required images for the sandbox
	 */
    public void loadImages() {
        try {
            wall = ImageIO.read(new File("res/Board/Wall.png"));
            floor = ImageIO.read(new File("res/Board/Floor.png"));
            hero = ImageIO.read(new File("res/Board/Hero.png"));
            ogre = ImageIO.read(new File("res/Board/Ogre.png"));
            key = ImageIO.read(new File("res/Board/Key.png"));
            closedDoor = ImageIO.read(new File("res/Board/ClosedDoor.png"));
            openDoor = ImageIO.read(new File("res/Board/OpenDoor.png"));
        } catch (IOException e) {
            System.err.println("Error: Could not load images");
            e.printStackTrace();
        }
    }
	
    /**
	 * @brief Sandbox's panel layout configuration
	 */
	private void configureLayout() {
		((JPanel)getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0,  0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0};
		setLayout(gbl_contentPane);
	}
	
	/**
	 * @brief Sandbox's panel inner components configuration
	 *
	 * Creates and configures the sandbox's panel and its buttons.
	 */
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
		
		wallBtn = newIconButton(0, 3, wall, elementsPanel);
		heroBtn = newIconButton(0, 4, hero, elementsPanel);
		ogreBtn = newIconButton(1, 4, ogre, elementsPanel);
		closedDoorBtn = newIconButton(0, 5, closedDoor, elementsPanel);
		openDoorBtn = newIconButton(1, 5, openDoor, elementsPanel);
		keyBtn = newIconButton(1, 3, key, elementsPanel);
		
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
	
	/**
	 * @brief Sandbox's panel buttons listeners registration
	 *
	 * Registers listeners for the interaction with the several buttons on the
	 * sandbox's panel (Buttons of the game elements, Save & DimensionSlider).
	 */
	private void registerListeners(){
		
		canvasPanel.addMouseListener(controller);
		canvasPanel.addMouseMotionListener(controller);
		
		wallBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectElement(Board.wallSymbol);
			}
		});
		

		heroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectElement(Hero.heroSymbol);
			}
		});
		
		ogreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectElement(Ogre.ogreSymbol);
			}
		});

		
		closedDoorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectElement(Board.closeDoorSymbol);
			}
		});
		
		openDoorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectElement(Board.openableDoorSymbol);
			}
		});
		
		
		keyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.selectElement(Board.keySymbol);
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
	
	/**
	 * @brief Creates a new button with an icon
	 * @param x Button's x grid-position
	 * @param y Button's y grid-position
	 * @param image Button's icon
	 * @param elementsPanel Panel containing the buttons
	 * @return Button created
	 */
	public JButton newIconButton(int x, int y, Image image, JPanel elementsPanel) {
		JButton button = new JButton();
		button.setIcon(new ImageIcon(image));
		button.setMinimumSize(new Dimension(50,50));
		button.setPreferredSize(new Dimension(50,50));
		GridBagConstraints gbc_btn = new GridBagConstraints();
		gbc_btn.gridwidth = 2;
		gbc_btn.insets = new Insets(0, 0, 5, 5);
		gbc_btn.gridx = x;
		gbc_btn.gridy = y;
		elementsPanel.add(button, gbc_btn);
		return button;
	}
	
	/**
	 * @brief Returns the canvas's panel graphics
	 * @return Canvas's panel graphics
	 */
	public Graphics getBoardGrahics(){
		return canvasPanel.getGraphics();
	}
	
	/**
	 * @brief Returns the canvas's size
	 * @return Canvas size
	 */
	public Dimension getBoardSize(){
		return canvasPanel.getSize();
	}
}
