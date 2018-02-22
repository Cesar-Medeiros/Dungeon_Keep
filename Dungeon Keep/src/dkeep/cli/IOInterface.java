package dkeep.cli;


public abstract class IOInterface {

	protected static enum Interface {
		CLI, GRAPHICS
	};
	
	public static enum Direction {
		UP, RIGHT, DOWN, LEFT, NONE
	};	
	
	private static Interface currentInterface = null;
		
	public static void setInterface(Interface currentInterface) {
		IOInterface.currentInterface = currentInterface;
	}
	
	public static Direction getDirection() {
		switch(currentInterface) {
		case CLI:
			return CLI.getDirection();
		default: 
			return Direction.NONE;
		}
	}
}
