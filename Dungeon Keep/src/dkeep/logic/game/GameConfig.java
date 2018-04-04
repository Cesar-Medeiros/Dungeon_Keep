package dkeep.logic.game;

import java.io.Serializable;

public class GameConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	private int typeGuard;
	private int numOgres;	
	private char[][] customLevel;
	
	/**
	 * @brief GameConfig constructor
	 * @param typeGuard Index of the type of guard in-game
	 * @param numOgres Number of ogres in-game
	 */
	public GameConfig(int typeGuard, int numOgres) {
		this.typeGuard = typeGuard;
		this.numOgres = numOgres;
	}

	/**
	 * @brief GameConfig constructor
	 * @param typeGuard Index of the type of guard in-game
	 * @param numOgres Number of ogres in-game
	 * @param customLevel Custom board created
	 */
	public GameConfig(int typeGuard, int numOgres, char[][] customLevel) {
		this.typeGuard = typeGuard;
		this.numOgres = numOgres;
		this.customLevel = customLevel;
	}
	
	/**
	 * @brief Gets custom level board
	 * @return Custom level board
	 */
	public char[][] getCustomLevel() {
		return customLevel;
	}

	/**
	 * @brief Returns game's number of ogres
	 * @return Game's number of ogres
	 */
	public int getNumOgres() {
		return numOgres;
	}

	/**
	 * @brief Returns index of the game's type of guard
	 * @return Index of the game's type of guard
	 */
	public int getTypeGuard() {
		return typeGuard;
	}
}
