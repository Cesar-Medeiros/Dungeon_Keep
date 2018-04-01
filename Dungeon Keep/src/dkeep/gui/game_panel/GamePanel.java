package dkeep.gui.game_panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.util.Direction;

public class GamePanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	private JLabel lblGameStatus;
	private JPanel gamePanel;
	private JButton upButton;
	private JButton leftButton;
	private JButton downButton;
	private JButton rightButton;
	private JButton loadSaveButton;
	private JButton newGameButton;
	private JButton exitButton;
	private GameController controller;
	
	public GamePanel(GameController controller) {
		this.controller = controller;
		configureLayout();
		configure();
		disableButtons();
		registerListeners();
	}
	
	
	private void configureLayout() {
		setBorder(new EmptyBorder(20, 20, 20, 20));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0,  0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0};
		setLayout(gbl_contentPane);
	}
	
	
	private void configure(){
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		gamePanel = new JPanel();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(gamePanel, gbc);
		
		JPanel controlPanel = new JPanel();
		gbc.insets = new Insets(0, 5, 5, 0);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(controlPanel, gbc);
		
		GridBagLayout gbl_controlPanel = new GridBagLayout();
		gbl_controlPanel.columnWidths = new int[]{50, 50, 50, 50};
		gbl_controlPanel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_controlPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		controlPanel.setLayout(gbl_controlPanel);
		

		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		
		createButtons(controlPanel, gbc);
		
		lblGameStatus = new JLabel("You can start a new game.");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblGameStatus, gbc);
	}
	
	private void createButtons(JPanel controlPanel, GridBagConstraints gbc) {
		newGameButton = new JButton("New Game");
		gbc.gridx = 1;
		gbc.gridy = 0;
		controlPanel.add(newGameButton, gbc);
		
		loadSaveButton = new JButton("Load|Save");
		gbc.gridx = 1;
		gbc.gridy = 1;
		controlPanel.add(loadSaveButton, gbc);
		
		upButton = new JButton("Up");
		gbc.gridx = 1;
		gbc.gridy = 4;
		controlPanel.add(upButton, gbc);
		
		leftButton = new JButton("Left");
		gbc.gridx = 0;
		gbc.gridy = 5;
		controlPanel.add(leftButton, gbc);
		
		downButton = new JButton("Down");
		gbc.gridx = 1;
		gbc.gridy = 6;
		controlPanel.add(downButton, gbc);
		
		rightButton = new JButton("Right");
		gbc.gridx = 2;
		gbc.gridy = 5;
		controlPanel.add(rightButton, gbc);
	
		exitButton = new JButton("Exit");
		gbc.gridx = 1;
		gbc.gridy = 10;
		controlPanel.add(exitButton, gbc);
	}
	
	private void registerListeners(){
	
		gamePanel.addKeyListener(controller);
		
		loadSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadSave();
				controller.initializeGame();
				gamePanel.requestFocusInWindow();
			}
		});
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.newGame();
				controller.initializeGame();
				gamePanel.requestFocusInWindow();
			}
		});
		
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.directionPressed(Direction.UP);
				gamePanel.requestFocusInWindow();
			}
		});
		
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.directionPressed(Direction.LEFT);
				gamePanel.requestFocusInWindow();
			}
		});
		
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.directionPressed(Direction.RIGHT);
				gamePanel.requestFocusInWindow();
			}
		});
		
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.directionPressed(Direction.DOWN);
				gamePanel.requestFocusInWindow();
			}
		});
		
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    	controller.loadGameGraphics();
		    }
		});
	}
	
	public void disableButtons() {
		upButton.setEnabled(false);
		leftButton.setEnabled(false);
		downButton.setEnabled(false);
		rightButton.setEnabled(false);
	}
	
	public void enableButtons() {
		upButton.setEnabled(true);
		leftButton.setEnabled(true);
		downButton.setEnabled(true);
		rightButton.setEnabled(true);
	}
	
	public void setGameStatus(String text) {
		lblGameStatus.setText(text);
	}
	
	
	public Graphics getGameGraphics() {
		return gamePanel.getGraphics();
	}
	
	public Dimension getGameSize() {
		return gamePanel.getSize();
	}
	
}