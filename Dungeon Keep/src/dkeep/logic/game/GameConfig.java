package dkeep.logic.game;

import java.io.Serializable;

public class GameConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	private int typeGuard;
	private int numOgres;	
	private char[][] customLevel;
	
	public GameConfig(int typeGuard, int numOgres) {
		this.typeGuard = typeGuard;
		this.numOgres = numOgres;
	}

	public GameConfig(int typeGuard, int numOgres, char[][] customLevel) {
		this.typeGuard = typeGuard;
		this.numOgres = numOgres;
		this.customLevel = customLevel;
	}
	
	public char[][] getCustomLevel() {
		return customLevel;
	}

	public int getNumOgres() {
		return numOgres;
	}

	public int getTypeGuard() {
		return typeGuard;
	}
	
}
