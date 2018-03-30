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
	
	public static void save(DungeonKeep dk) {
		try {
			OutputStream fileOut = new FileOutputStream("1.save");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(dk);
			out.close();
			fileOut.close();
			 System.out.printf("Serialized data is saved in 1.save");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static DungeonKeep load(DungeonKeep dk) {
		
		InputStream fileIn;
		try {
			fileIn = new FileInputStream("1.save");
			ObjectInputStream out = new ObjectInputStream(fileIn);
			dk = (DungeonKeep) out.readObject();
			out.close();
			fileIn.close();
			System.err.println("Deserialized data was load from 1.save");
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
