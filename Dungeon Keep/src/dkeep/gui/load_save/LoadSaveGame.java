package dkeep.gui.load_save;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import dkeep.logic.game.DungeonKeep;

public class LoadSaveGame {
	
	/**
	 * @brief Saves the current game state of the DungeonKeep game
	 * @param dk DungeonKeep's current game
	 * @param savename Save's filename
	 */
	public static void save(DungeonKeep dk, String savename) {
		try {
			OutputStream fileOut = new FileOutputStream("SaveGame/" + savename + ".save");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(dk);
			out.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Loads a saved DungeonKeep's game
	 * @param dk DungeonKeep's saved game
	 * @param loadname Save's filename
	 * @return DungeonKeep's game loaded from file
	 */
	public static DungeonKeep load(DungeonKeep dk, String loadname) {
		
		InputStream fileIn;
		try {
			fileIn = new FileInputStream("SaveGame/" + loadname + ".save");
			ObjectInputStream out = new ObjectInputStream(fileIn);
			dk = (DungeonKeep) out.readObject();
			out.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dk;
	}
}
