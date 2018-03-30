package dkeep.gui.load_save;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.logic.game.DungeonKeep;

public class LoadSavePanel extends JDialog {

	private static final long serialVersionUID = 1L;	
	public static final Dimension PREFERREDSIZE = new Dimension(300,185);

	private JButton btnSave;
	private JButton btnLoad;
	private final JPanel contentPane = new JPanel();
	private DungeonKeep dk;
	
	
	
	public static void main(String[] args) {
		LoadSavePanel.createAndShowGUI(null);
	}
	
	public static DungeonKeep createAndShowGUI(DungeonKeep dk) {
		LoadSavePanel dialog = new LoadSavePanel(dk);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		return dialog.dk;
		
	}


	public LoadSavePanel(DungeonKeep dk) {
		super(null, "Load and Save", ModalityType.APPLICATION_MODAL);
		setModal(true);
	    setPreferredSize(PREFERREDSIZE);
	    setResizable(false);
	    
	    //Center the frame
	    setLocationRelativeTo(null);
	
	    //Display the window.
	    pack();
	    
	    
	    this.dk = dk;
		configureLayout();
		configure();
		registerListeners();
	}

	private void configureLayout() {
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE, 0.0};
		contentPane.setLayout(gbl_contentPane);
	}

	private void configure() {
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 1;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		
		
		btnSave = new JButton("Save");
		contentPane.add(btnSave, gbc_btnNewButton);
		
		gbc_btnNewButton.gridy = 1;
		
		btnLoad = new JButton("Load");
		contentPane.add(btnLoad, gbc_btnNewButton);
		
	}

	private void registerListeners() {
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadSaveGame.save(dk);
			}
		});
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dk = LoadSaveGame.load(dk);
			}
		});
		
	}


}
