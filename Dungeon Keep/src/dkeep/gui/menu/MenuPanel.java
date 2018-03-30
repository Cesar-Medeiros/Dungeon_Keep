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
	private JButton btnSandbox;
	private JButton btnExit;
	private MenuController controller;
	
	public MenuPanel(MenuController controller) {
		configureLayout();
		configure();
		registerListeners();
		this.controller = controller;
	}
	
	
	private void configureLayout() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gbl_contentPane);
	}
	
	
	private void configure(){
		
		btnPlay = new JButton("Play");
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 2;
		gbc_btnPlay.gridy = 2;
		add(btnPlay, gbc_btnPlay);
		
		
		btnSandbox = new JButton("Sandbox");
		GridBagConstraints gbc_btnSandbox = new GridBagConstraints();
		gbc_btnSandbox.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSandbox.insets = new Insets(0, 0, 5, 5);
		gbc_btnSandbox.gridx = 2;
		gbc_btnSandbox.gridy = 3;
		add(btnSandbox, gbc_btnSandbox);
		
		
		btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 4;
		add(btnExit, gbc_btnExit);
	}
	
	
	private void registerListeners(){
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.pressPlay();
			}
		});
		
		btnSandbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.pressSandBox();
			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.pressExit();
			}
		});
			
	}
	
	
}
