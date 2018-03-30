package dkeep.logic.game;

import java.io.Serializable;

public class GameConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	private int typeGuard;
	private int numOgres;	
	
	public GameConfig(int typeGuard, int numOgres) {
		this.typeGuard = typeGuard;
		this.numOgres = numOgres;
	}

	public int getNumOgres() {
		return numOgres;
	}

	public int getTypeGuard() {
		return typeGuard;
	}
	
}
