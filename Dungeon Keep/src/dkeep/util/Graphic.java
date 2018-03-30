package dkeep.util;

import java.util.LinkedList;
import java.util.Queue;

public class Graphic implements IOInterface {

	protected Queue<Direction> directions;
	
	public Graphic(){
		directions = new LinkedList<Direction>();
	}
	
	public Direction getDirection() {
		Direction direction = directions.poll();
		if(direction == null) {
			return Direction.NONE;
		}
		else return direction;
	}
	
	public void addDirection(Direction direction) {
		
		if(direction != null && direction != Direction.NONE)
			directions.add(direction);
		
	}
	
}
