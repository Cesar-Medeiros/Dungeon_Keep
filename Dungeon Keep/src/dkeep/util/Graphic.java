package dkeep.util;

import java.util.LinkedList;
import java.util.Queue;

public class Graphic implements IOInterface {

	protected Queue<Direction> directions;
	
	/**
	 * Graphic interface constructor.
	 * Creates a list to store directions retrieved from the GUI.
	 */
	public Graphic(){
		directions = new LinkedList<Direction>();
	}
	
	/**
	 * Gets the head direction of the list
	 * @return Direction removed from the direction's list
	 */
	public Direction getDirection() {
		Direction direction = directions.poll();
		if(direction == null) {
			return Direction.NONE;
		}
		else return direction;
	}
	
	/**
	 * Adds direction to the list
	 * @param direction Direction to be inserted on the list
	 */
	public void addDirection(Direction direction) {
		if(direction != null && direction != Direction.NONE)
			directions.add(direction);
	}
}
