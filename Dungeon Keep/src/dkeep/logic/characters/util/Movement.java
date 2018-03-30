package dkeep.logic.characters.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dkeep.util.Direction;

public class Movement implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private List<Direction> movement;
	private int moveIndex;
	
	private List<Boolean> passedBy;
	
	
	public Movement(List<Direction> list){
		movement = new ArrayList<Direction>(list);
		passedBy = new ArrayList<Boolean>(list.size());
		moveIndex = 0;
	}
	
	public Movement(){
		movement = null;
	}
	
	public Direction pop() {
		return movement.remove(movement.size() - 1);
	}
	
	public void push(Direction direction) {
		movement.add(direction);
	}
	
	public Direction getNext() {
		Direction direction = Direction.NONE;
		if(movement == null) {
			return direction;
		}
		
		if(moveIndex == movement.size()) {
			moveIndex = 0;
		}
		
		direction = movement.get(moveIndex);
		moveIndex++;
		
		return direction;
	}
	
	public Direction getNextR() {
		Direction direction = Direction.NONE;
		if(movement == null) return direction;
		
		if(moveIndex == -1) {
			moveIndex = movement.size() - 1;
		}
		
		direction = movement.get(moveIndex);
		moveIndex--;
		
		return direction.revertDirection();
	}

	public boolean passedByAll() {
		
		for(Boolean b : passedBy) {
			if(b == false)
				return false;
		}
		return true;
	}	
	
}
