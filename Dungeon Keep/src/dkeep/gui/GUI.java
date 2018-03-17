package dkeep.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dkeep.cli.Graphic;
import dkeep.cli.IOInterface;
import dkeep.cli.IOInterface.Direction;

public class GUI extends JFrame {


	
	private JPanel contentPane;
	private JTextField numOgresText;
	private JComboBox guardPersCombo;
	private JLabel lblGameStatus;
	private GameState gamePanel;
	private JButton upButton;
	private JButton leftButton;
	private JButton downButton;
	private JButton rightButton;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void initializeGame() {

		String numOgresStr = numOgresText.getText();
		int numOgres;
		try {
			numOgres = Integer.parseInt(numOgresStr);
		} catch (NumberFormatException e) {
			lblGameStatus.setText("Invalid number of ogres.");
			return;
		}
		
		int guard = guardPersCombo.getSelectedIndex();
		
		lblGameStatus.setText("You can play now.");
		
		upButton.setEnabled(true);
		leftButton.setEnabled(true);
		downButton.setEnabled(true);
		rightButton.setEnabled(true);
		gamePanel.start();
	}
	
	
	protected void directionButtonPressed(Direction direction) {
		Graphic.addDirection(direction);
		gamePanel.update();
		gamePanel.draw();
		
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0,  0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel optionsPanel = new JPanel();
		GridBagConstraints gbc_optionsPanel = new GridBagConstraints();
		gbc_optionsPanel.insets = new Insets(0, 0, 10, 5);
		gbc_optionsPanel.fill = GridBagConstraints.BOTH;
		gbc_optionsPanel.gridx = 0;
		gbc_optionsPanel.gridy = 0;
		contentPane.add(optionsPanel, gbc_optionsPanel);
		GridBagLayout gbl_optionsPanel = new GridBagLayout();
		gbl_optionsPanel.columnWidths = new int[] {0, 0, 0};
		gbl_optionsPanel.rowHeights = new int[] {0, 0};
		gbl_optionsPanel.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_optionsPanel.rowWeights = new double[]{1.0, 0.0};
		optionsPanel.setLayout(gbl_optionsPanel);
		
		JLabel lblNumOgres = new JLabel("Number of Ogres");
		GridBagConstraints gbc_lblNumOgres = new GridBagConstraints();
		gbc_lblNumOgres.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNumOgres.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOgres.gridx = 0;
		gbc_lblNumOgres.gridy = 0;
		optionsPanel.add(lblNumOgres, gbc_lblNumOgres);
		
		numOgresText = new JTextField();
		GridBagConstraints gbc_numOgresText = new GridBagConstraints();
		gbc_numOgresText.insets = new Insets(0, 0, 5, 50);
		gbc_numOgresText.fill = GridBagConstraints.HORIZONTAL;
		gbc_numOgresText.gridx = 1;
		gbc_numOgresText.gridy = 0;
		optionsPanel.add(numOgresText, gbc_numOgresText);
		numOgresText.setColumns(10);
		
		JPanel spacer = new JPanel();
		GridBagConstraints gbc_spacer = new GridBagConstraints();
		gbc_spacer.insets = new Insets(0, 0, 5, 0);
		gbc_spacer.fill = GridBagConstraints.BOTH;
		gbc_spacer.gridx = 2;
		gbc_spacer.gridy = 0;
		optionsPanel.add(spacer, gbc_spacer);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		GridBagConstraints gbc_lblGuardPersonality = new GridBagConstraints();
		gbc_lblGuardPersonality.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGuardPersonality.insets = new Insets(0, 0, 0, 5);
		gbc_lblGuardPersonality.gridx = 0;
		gbc_lblGuardPersonality.gridy = 1;
		optionsPanel.add(lblGuardPersonality, gbc_lblGuardPersonality);
		
		guardPersCombo = new JComboBox();
		guardPersCombo.setModel(new DefaultComboBoxModel(new String[] {"Rockie", "Drunken", "Suspicious"}));
		GridBagConstraints gbc_guardPersCombo = new GridBagConstraints();
		gbc_guardPersCombo.insets = new Insets(0, 0, 0, 5);
		gbc_guardPersCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardPersCombo.gridx = 1;
		gbc_guardPersCombo.gridy = 1;
		optionsPanel.add(guardPersCombo, gbc_guardPersCombo);
		
		gamePanel = new GameState();
		GridBagConstraints gbc_gamePanel = new GridBagConstraints();
		gbc_gamePanel.insets = new Insets(0, 0, 5, 5);
		gbc_gamePanel.fill = GridBagConstraints.BOTH;
		gbc_gamePanel.gridx = 0;
		gbc_gamePanel.gridy = 1;
		contentPane.add(gamePanel, gbc_gamePanel);
		GridBagLayout gbl_gamePanel = new GridBagLayout();
		gbl_gamePanel.columnWidths = new int[]{0, 0};
		gbl_gamePanel.rowHeights = new int[]{0, 0};
		gbl_gamePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_gamePanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		gamePanel.setLayout(gbl_gamePanel);
		
		JPanel controlPanel = new JPanel();
		GridBagConstraints gbc_controlPanel = new GridBagConstraints();
		gbc_controlPanel.insets = new Insets(0, 5, 5, 0);
		gbc_controlPanel.fill = GridBagConstraints.BOTH;
		gbc_controlPanel.gridx = 1;
		gbc_controlPanel.gridy = 1;
		contentPane.add(controlPanel, gbc_controlPanel);
		GridBagLayout gbl_controlPanel = new GridBagLayout();
		gbl_controlPanel.columnWidths = new int[]{50, 50, 50, 50};
		gbl_controlPanel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_controlPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		controlPanel.setLayout(gbl_controlPanel);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeGame();
			}
		});
		GridBagConstraints gbc_newGameButton = new GridBagConstraints();
		gbc_newGameButton.fill = GridBagConstraints.BOTH;
		gbc_newGameButton.gridwidth = 2;
		gbc_newGameButton.insets = new Insets(0, 0, 5, 5);
		gbc_newGameButton.gridx = 1;
		gbc_newGameButton.gridy = 0;
		controlPanel.add(newGameButton, gbc_newGameButton);
		
		upButton = new JButton("Up");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				directionButtonPressed(Direction.UP);
			}
		});
		upButton.setEnabled(false);
		GridBagConstraints gbc_upButton = new GridBagConstraints();
		gbc_upButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_upButton.gridwidth = 2;
		gbc_upButton.insets = new Insets(0, 0, 5, 5);
		gbc_upButton.gridx = 1;
		gbc_upButton.gridy = 4;
		controlPanel.add(upButton, gbc_upButton);
		
		leftButton = new JButton("Left");
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directionButtonPressed(Direction.LEFT);
			}
		});
		leftButton.setEnabled(false);
		GridBagConstraints gbc_leftButton = new GridBagConstraints();
		gbc_leftButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_leftButton.gridwidth = 2;
		gbc_leftButton.insets = new Insets(0, 0, 5, 5);
		gbc_leftButton.gridx = 0;
		gbc_leftButton.gridy = 5;
		controlPanel.add(leftButton, gbc_leftButton);
		
		rightButton = new JButton("Right");
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directionButtonPressed(Direction.RIGHT);
			}
		});
		rightButton.setEnabled(false);
		GridBagConstraints gbc_rightButton = new GridBagConstraints();
		gbc_rightButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rightButton.gridwidth = 2;
		gbc_rightButton.insets = new Insets(0, 0, 5, 0);
		gbc_rightButton.gridx = 2;
		gbc_rightButton.gridy = 5;
		controlPanel.add(rightButton, gbc_rightButton);
		
		downButton = new JButton("Down");
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				directionButtonPressed(Direction.DOWN);
			}
		});
		downButton.setEnabled(false);
		GridBagConstraints gbc_downButton = new GridBagConstraints();
		gbc_downButton.gridwidth = 2;
		gbc_downButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_downButton.insets = new Insets(0, 0, 5, 5);
		gbc_downButton.gridx = 1;
		gbc_downButton.gridy = 6;
		controlPanel.add(downButton, gbc_downButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
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
		contentPane.add(lblGameStatus, gbc_lblGameStatus);
	}


}
