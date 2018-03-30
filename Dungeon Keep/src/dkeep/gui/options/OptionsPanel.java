package dkeep.gui.options;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dkeep.logic.game.GameConfig;

//TODO: When close window - Error occur

public class OptionsPanel extends JDialog {

	JTextField numOgresText;
	JComboBox<String> guardPersCombo;
	JButton btnNewButton;
	JLabel lblGameStatus;
	int nOgres;
	int typeGuard;
	
	
	public static final Dimension PREFERREDSIZE = new Dimension(300,185);
	private static final long serialVersionUID = 1L;
	
	public OptionsPanel() {
		super(null, "Options", ModalityType.APPLICATION_MODAL);
		setModal(true);

        setPreferredSize(PREFERREDSIZE);
        //Center the frame
        setLocationRelativeTo(null);

        //Display the window.
        pack();
        
        setResizable(false);
        test();
        registerListeners();
	}
	
	public static GameConfig getGameConfig() {
		OptionsPanel dialog = new OptionsPanel();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		return new GameConfig(dialog.getGuard(), dialog.getNumOgres());
    }
	
	
	public void test() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNumOgres = new JLabel("Number of Ogres");
		GridBagConstraints gbc_lblNumOgres = new GridBagConstraints();
		gbc_lblNumOgres.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOgres.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNumOgres.gridx = 0;
		gbc_lblNumOgres.gridy = 0;
		contentPane.add(lblNumOgres, gbc_lblNumOgres);
		
		numOgresText = new JTextField();
		GridBagConstraints gbc_numOgresText = new GridBagConstraints();
		gbc_numOgresText.insets = new Insets(0, 0, 5, 50);
		gbc_numOgresText.fill = GridBagConstraints.HORIZONTAL;
		gbc_numOgresText.gridx = 1;
		gbc_numOgresText.gridy = 0;
		contentPane.add(numOgresText, gbc_numOgresText);
		numOgresText.setColumns(10);
		
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		GridBagConstraints gbc_lblGuardPersonality = new GridBagConstraints();
		gbc_lblGuardPersonality.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuardPersonality.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGuardPersonality.gridx = 0;
		gbc_lblGuardPersonality.gridy = 1;
		contentPane.add(lblGuardPersonality, gbc_lblGuardPersonality);
		
		
		guardPersCombo = new JComboBox<String>();
		guardPersCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Rockie", "Drunken", "Suspicious"}));
		GridBagConstraints gbc_guardPersCombo = new GridBagConstraints();
		gbc_guardPersCombo.insets = new Insets(0, 0, 5, 0);
		gbc_guardPersCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardPersCombo.gridx = 1;
		gbc_guardPersCombo.gridy = 1;
		contentPane.add(guardPersCombo, gbc_guardPersCombo);
		
		
		btnNewButton = new JButton("Start !");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		lblGameStatus = new JLabel("Choose some options.");
		GridBagConstraints gbc_lblGameStatus = new GridBagConstraints();
		gbc_lblGameStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGameStatus.gridwidth = 2;
		gbc_lblGameStatus.insets = new Insets(5, 0, 0, 0);
		gbc_lblGameStatus.gridx = 0;
		gbc_lblGameStatus.gridy = 3;
		add(lblGameStatus, gbc_lblGameStatus);
		
	}
	
	
	private void registerListeners() {
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(processOptions()) 
					dispose();
			}
		});
	}

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

	public int getNumOgres() throws NumberFormatException{
		String numOgresStr = numOgresText.getText();
		int numOgres = Integer.parseInt(numOgresStr);
		return numOgres;
	}
	
	public int getGuard(){
		return guardPersCombo.getSelectedIndex();
	}
	
	public void setGameStatus(String text) {
		lblGameStatus.setText(text);
	}
}
