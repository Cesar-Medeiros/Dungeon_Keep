package dkeep.logic.characters.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dkeep.util.Direction;

public class Movement implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private List<Direction> movement;
	private int moveIndex;
	
	private List<Boolean> passedBy;
	
	/**
	 * @brief Movement constructor
	 * @param list List of movement directions
	 * 
	 * Also creates another list to keep track of the past movements.
	 */
	public Movement(List<Direction> list){
		movement = new ArrayList<Direction>(list);
		passedBy = new ArrayList<Boolean>(Collections.nCopies(list.size(), false));
		moveIndex = 0;
	}
	
	/**
	 * @brief Movement constructor
	 */
	public Movement(){
		movement = null;
	}
	
	/**
	 * @brief Pops movement's direction out of the list
	 * @return Direction removed from the list
	 */
	public Direction pop() {
		return movement.remove(movement.size() - 1);
	}
	
	/**
	 * @brief Adds movement's direction to the list
	 * @param direction Direction to be inserted to the list
	 */
	public void push(Direction direction) {
		movement.add(direction);
	}
	
	/**
	 * @brief Gets next movement's direction
	 * @return Next direction present on the list
	 */
	public Direction getNext() {
		Direction direction = Direction.NONE;
		if(movement == null) {
			return direction;
		}
		
		if(moveIndex == movement.size()) {
			moveIndex = 0;
		}
		
		if(moveIndex == -1) {
			moveIndex = movement.size() - 1;
		}
		
		passedBy.set(moveIndex, true);
		direction = movement.get(moveIndex);
		moveIndex++;
		
		return direction;
	}
	
	/**
	 * @brief Gets next movement's inverted direction
	 * @return Next direction present on the list, but inverted
	 */
	public Direction getNextR() {
		Direction direction = Direction.NONE;
		if(movement == null) return direction;
		
		if(moveIndex == movement.size()) {
			moveIndex = 0;
		}
		
		passedBy.set(moveIndex, true);
		moveIndex--;
		
		if(moveIndex == -1) {
			moveIndex = movement.size() - 1;
		}
		
		direction = movement.get(moveIndex);
		return direction.revertDirection();
	}

	/**
	 * @brief Checks if guard is done with all its movements
	 * @return Returns true if guard has finished turn, false otherwise
	 */
	public boolean passedByAll() {

		for(Boolean b : passedBy) {
			if(b == false)
				return false;
		}
		
		Collections.fill(passedBy, false);
		
		return (true && (moveIndex %  movement.size() == 0));
	}	
}


