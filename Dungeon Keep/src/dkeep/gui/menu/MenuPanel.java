package dkeep.gui.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton btnPlay;
	private JButton btnExit;
	private MenuController controller;
	
	/**
	 * Menu's panel constructor
	 * @param controller Menu's panel controller
	 */
	public MenuPanel(MenuController controller) {
		configureLayout();
		configure();
		registerListeners();
		this.controller = controller;
	}
	
	/**
	 * Menu's panel layout configuration
	 */
	private void configureLayout() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gbl_contentPane);
	}
	
	/**
	 * Creates and configures menu's panel inner control
	 * buttons (Play & Exit).
	 */
	private void configure(){
		
		btnPlay = new JButton("Play");
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 2;
		gbc_btnPlay.gridy = 2;
		add(btnPlay, gbc_btnPlay);
		
		
		btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 3;
		add(btnExit, gbc_btnExit);
	}
	
	/**
	 * Registers listeners for the interaction with
	 * the several buttons on the menu panel.
	 */
	private void registerListeners(){
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.pressPlay();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.pressExit();
			}
		});
	}
}
