package dkeep.gui.options;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dkeep.gui.sandbox.SandboxController;
import dkeep.logic.game.GameConfig;

//TODO: When close window - Error occur

public class OptionsPanel extends JDialog {

	JPanel contentPane;
	JTextField numOgresText;
	JComboBox<String> guardPersCombo;
	JButton btnNewButton;
	JButton btnCreateMap;
	JLabel lblGameStatus;
	int nOgres;
	int typeGuard;
	char[][] customLevel;
	
	
	public static final Dimension PREFERREDSIZE = new Dimension(300,185);
	private static final long serialVersionUID = 1L;
	
	/**
	 * Option's panel constructor
	 */
	public OptionsPanel() {
		super(null, "Options", ModalityType.APPLICATION_MODAL);
		setModal(true);
        setPreferredSize(PREFERREDSIZE);

        setLocationRelativeTo(null);
        
        pack();
        setResizable(false);
        
		configureLayout();
		configure();
		registerListeners();
		
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	numOgresText.setText(null);
            }
        });
        setVisible(true);
	}
	
	/**
	 * Returns the game's configuration chosen on the dialog box
	 * @return Game's configuration
	 */
	public static GameConfig getGameConfig() {
		OptionsPanel dialog = new OptionsPanel();
		
		if(dialog.getNumOgres() == -1) {
			return null;
		}
			
		else return new GameConfig(dialog.getGuard(), dialog.getNumOgres(), dialog.customLevel);
    }
	
	/**
	 * Creates and configures the option's dialog box layout.
	 */
	public void configureLayout() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE, 0.0};
		contentPane.setLayout(gbl_contentPane);
	}
	
	/**
	 * Configures the option's dialog box for the user
	 * to input the game configuration.
	 */
	public void configure() {		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel lblNumOgres = new JLabel("Number of Ogres");
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(lblNumOgres, gbc);
		
		numOgresText = new JTextField();
		gbc.insets = new Insets(0, 0, 5, 50);
		gbc.gridx = 1;
		gbc.gridy = 0;
		contentPane.add(numOgresText, gbc);
		numOgresText.setColumns(10);
		
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPane.add(lblGuardPersonality, gbc);
		

		guardPersCombo = new JComboBox<String>();
		guardPersCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.gridx = 1;
		gbc.gridy = 1;
		contentPane.add(guardPersCombo, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		btnNewButton = new JButton("Start !");
		gbc.gridx = 0;
		gbc.gridy = 2;
		contentPane.add(btnNewButton, gbc);
		
		
		btnCreateMap = new JButton("Create map");
		gbc.gridx = 1;
		gbc.gridy = 2;
		contentPane.add(btnCreateMap, gbc);
		
		gbc.gridwidth = 2;
		lblGameStatus = new JLabel("Choose some options.");
		gbc.insets = new Insets(5, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(lblGameStatus, gbc);
		

	}
	
	/**
	 * Registers listener for the buttons on the options panel
	 * (start & createMap).
	 */
	private void registerListeners() {
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(processOptions()) 
					dispose();
			}
		});
		
		btnCreateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customLevel = SandboxController.getCustomLevel();
			}
		});
	}

	/**
	 * Processes and tests options validity, requiring the user to input
	 * the number of ogres for the game which, by default, must fall in between 1-5.
	 * @return Returns true if configuration is valid, false otherwise
	 */
	private boolean processOptions() {
		try {
			nOgres = getNumOgres();

			if (nOgres > 5) {
				setGameStatus("Insert number of ogres 1-5.");
				return false;
			}
		} catch (NumberFormatException e) {
			setGameStatus("Invalid number of ogres.");
			return false;
		}
		return true;
	}

	/**
	 * Returns the number of ogres selected
	 * @return Number of ogres on the correspondent text field
	 * @throws NumberFormatException Throws NumberFormatException
	 * if it was not possible to parse the string into a valid integer.
	 */
	public int getNumOgres() throws NumberFormatException{
		String numOgresStr;
		int numOgres;
		
        try {
        	numOgresStr = numOgresText.getText();
        	numOgres = Integer.parseInt(numOgresStr);
        }
        catch(NullPointerException | NumberFormatException exception) {
        	numOgres = -1;
        }
		
		return numOgres;
	}
	
	/**
	 * Returns the type of guard selected
	 * @return Type of guard retrieved from the guard's ComboBox
	 */
	public int getGuard(){
		return guardPersCombo.getSelectedIndex();
	}
	
	/**
	 * Sets the game's current state
	 * @param text Game state
	 */
	public void setGameStatus(String text) {
		lblGameStatus.setText(text);
	}
}
