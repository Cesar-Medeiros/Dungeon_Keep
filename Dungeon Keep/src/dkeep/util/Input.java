package dkeep.util;


import dkeep.util.IOInterface;

public class Input {
	
	private static IOInterface ioInterface;
	
	
	public static void setGraphicInput() {
		ioInterface = new Graphic();
	}

	
	public static void setCliInput() {
		ioInterface = new CLI();
	}
	
	public static Direction getDirection() {
		Direction direction = Direction.NONE;
		
		if(ioInterface != null) {
			direction = ioInterface.getDirection();
		}
		return direction;
	}
	
	public static void addDirection(Direction direction) {
		if(ioInterface != null) {
			ioInterface.addDirection(direction);
		}
	}
	
	
}
