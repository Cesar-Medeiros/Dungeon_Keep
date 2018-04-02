package dkeep.gui.load_save;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.logic.game.DungeonKeep;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class LoadSavePanel extends JDialog {

	private static final long serialVersionUID = 1L;	
	public static final Dimension PREFERREDSIZE = new Dimension(300,185);

	private JButton btnSave;
	private JButton btnLoad;
	private final JPanel contentPane = new JPanel();
	private DungeonKeep dk;
	private JTextField txtSaveName;
	private JComboBox<String> comboBoxLoadName;
	
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
		searchSaveGame();
	}

	private void configureLayout() {
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {30, 70};
		gbl_contentPane.rowHeights = new int[] {50, 50};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
	}

	private void configure() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 5, 5);
		
		
		txtSaveName = new JTextField();
		txtSaveName.setColumns(10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(txtSaveName, gbc);
		
				
		btnSave = new JButton("Save");
		gbc.gridx = 1;
		gbc.gridy = 0;
		contentPane.add(btnSave, gbc);
		
		
		btnLoad = new JButton("Load");
		gbc.gridx = 1;
		gbc.gridy = 1;
		contentPane.add(btnLoad, gbc);
		
		
		comboBoxLoadName = new JComboBox<String>();
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPane.add(comboBoxLoadName, gbc);
	}

	private void registerListeners() {
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadSaveGame.save(dk, txtSaveName.getText());
				searchSaveGame();
			}
		});
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dk = LoadSaveGame.load(dk, (String) comboBoxLoadName.getSelectedItem());
			}
		});
		
	}

	private void searchSaveGame() {
		File f =  new File("./SaveGame");
		f.mkdir();
		File [] paths = f.listFiles();
		comboBoxLoadName.removeAllItems();
		 for(File path:paths) {
			 comboBoxLoadName.addItem(path.getName().substring(0, path.getName().length() - 5));
	     }
		 
		 if(comboBoxLoadName.getModel().getSize() == 0) 
			 btnLoad.setEnabled(false);
		 else btnLoad.setEnabled(true);
		
	}

}
