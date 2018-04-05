package dkeep.util;


import dkeep.util.IOInterface;

public class Input {
	
	private static IOInterface ioInterface;
	
	/**
	 * Sets the GUI as the IOinterface
	 */
	public static void setGraphicInput() {
		ioInterface = new Graphic();
	}

	/**
	 * Sets the CLI as the IOinterface
	 */
	public static void setCliInput() {
		ioInterface = new CLI();
	}
	
	/**
	 * Returns a direction from the available IOinterface
	 * @return Returns the direction if there's an interface, NONE otherwise
	 */
	public static Direction getDirection() {
		Direction direction = Direction.NONE;
		
		if(ioInterface != null) {
			direction = ioInterface.getDirection();
		}
		return direction;
	}
	
	/**
	 * Adds a direction from the available IOinterface
	 * @param direction Direction retrieved
	 */
	public static void addDirection(Direction direction) {
		if(ioInterface != null) {
			ioInterface.addDirection(direction);
		}
	}
}
