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
import java.awt.event.ComponentListener;

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
		
		JPanel controlPanel = new JPanel();
		GridBagConstraints gbc_controlPanel = new GridBagConstraints();
		gbc_controlPanel.insets = new Insets(0, 5, 5, 0);
		gbc_controlPanel.fill = GridBagConstraints.BOTH;
		gbc_controlPanel.gridx = 1;
		gbc_controlPanel.gridy = 1;
		add(controlPanel, gbc_controlPanel);
		GridBagLayout gbl_controlPanel = new GridBagLayout();
		gbl_controlPanel.columnWidths = new int[]{50, 50, 50, 50};
		gbl_controlPanel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_controlPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		controlPanel.setLayout(gbl_controlPanel);
		
		gamePanel = new JPanel();
		GridBagConstraints gbc_gamePanel = new GridBagConstraints();
		gbc_gamePanel.insets = new Insets(0, 0, 5, 5);
		gbc_gamePanel.fill = GridBagConstraints.BOTH;
		gbc_gamePanel.gridx = 0;
		gbc_gamePanel.gridy = 1;
		add(gamePanel, gbc_gamePanel);

		
		loadSaveButton = new JButton("Load|Save");
		GridBagConstraints gbc_loadSaveButton = new GridBagConstraints();
		gbc_loadSaveButton.fill = GridBagConstraints.BOTH;
		gbc_loadSaveButton.gridwidth = 2;
		gbc_loadSaveButton.insets = new Insets(0, 0, 5, 5);
		gbc_loadSaveButton.gridx = 1;
		gbc_loadSaveButton.gridy = 1;
		controlPanel.add(loadSaveButton, gbc_loadSaveButton);
		
		newGameButton = new JButton("New Game");
		GridBagConstraints gbc_newGameButton = new GridBagConstraints();
		gbc_newGameButton.fill = GridBagConstraints.BOTH;
		gbc_newGameButton.gridwidth = 2;
		gbc_newGameButton.insets = new Insets(0, 0, 5, 5);
		gbc_newGameButton.gridx = 1;
		gbc_newGameButton.gridy = 0;
		controlPanel.add(newGameButton, gbc_newGameButton);
		
		
		
		upButton = new JButton("Up");
		GridBagConstraints gbc_upButton = new GridBagConstraints();
		gbc_upButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_upButton.gridwidth = 2;
		gbc_upButton.insets = new Insets(0, 0, 5, 5);
		gbc_upButton.gridx = 1;
		gbc_upButton.gridy = 4;
		controlPanel.add(upButton, gbc_upButton);
		
		
		
		leftButton = new JButton("Left");
		GridBagConstraints gbc_leftButton = new GridBagConstraints();
		gbc_leftButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_leftButton.gridwidth = 2;
		gbc_leftButton.insets = new Insets(0, 0, 5, 5);
		gbc_leftButton.gridx = 0;
		gbc_leftButton.gridy = 5;
		controlPanel.add(leftButton, gbc_leftButton);
		
		
		
		rightButton = new JButton("Right");
		GridBagConstraints gbc_rightButton = new GridBagConstraints();
		gbc_rightButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rightButton.gridwidth = 2;
		gbc_rightButton.insets = new Insets(0, 0, 5, 0);
		gbc_rightButton.gridx = 2;
		gbc_rightButton.gridy = 5;
		controlPanel.add(rightButton, gbc_rightButton);
		
		
		
		downButton = new JButton("Down");
		GridBagConstraints gbc_downButton = new GridBagConstraints();
		gbc_downButton.gridwidth = 2;
		gbc_downButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_downButton.insets = new Insets(0, 0, 5, 5);
		gbc_downButton.gridx = 1;
		gbc_downButton.gridy = 6;
		controlPanel.add(downButton, gbc_downButton);
		
		
		
		exitButton = new JButton("Exit");
		GridBagConstraints gbc_exitButton = new GridBagConstraints();
		gbc_exitButton.insets = new Insets(5, 0, 0, 0);
		gbc_exitButton.gridwidth = 2;
		gbc_exitButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_exitButton.gridx = 1;
		gbc_exitButton.gridy = 10;
		controlPanel.add(exitButton, gbc_exitButton);
		
		
		
		lblGameStatus = new JLabel("You can start a new game.");
		GridBagConstraints gbc_lblGameStatus = new GridBagConstraints();
		gbc_lblGameStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGameStatus.insets = new Insets(5, 0, 0, 5);
		gbc_lblGameStatus.gridx = 0;
		gbc_lblGameStatus.gridy = 2;
		add(lblGameStatus, gbc_lblGameStatus);
	}
	
	
	private void registerListeners(){
	
		gamePanel.addKeyListener(controller);
		
		loadSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadSave();
				gamePanel.requestFocusInWindow();
			}
		});
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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