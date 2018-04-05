package dkeep.logic.characters.guard;

import java.util.Random;

import dkeep.logic.characters.util.Movement;
import dkeep.util.Direction;
import static dkeep.util.Direction.NONE;

public class DrunkenGuard extends Guard {

	private static final long serialVersionUID = 1L;
	private Random rand;	
	private boolean reversed;
	
	/**
	 * DrunkenGuard constructor. Initially the 
	 * DrunkenGuard's movement is not reversed.
	 * @param posX Initial DrunkenGuard's x-position
	 * @param posY Initial DrunkenGuard's y-position
	 * @param movement DrunkenGuard's list of movements
	 */
	public DrunkenGuard(int posX, int posY, Movement movement) {
		super(posX, posY, movement);
		rand = new Random();
		reversed = false;
	}
	
	/**
	 * Gets DrunkenGuard's next movement, which alternates 
	 * between sequential and reversed. It randomly falls asleep and
	 * when wakes up there's a chance that it'll revert his path.
	 * @return DrunkenGuard's next movement
	 */	
	@Override
	protected Direction nextMove() {
		Direction direction = NONE;
		
		int chance = rand.nextInt(100);

		if (active) {
	
			if (chance < 20) {
				putToSleep();
			}
			
		} else {
			
			if (chance < 80) {
				wakeUp();
				if (revertPath()) {
					reversed = true;
				} else {
					reversed = false;
				}
			}
		}
		
		if(!active)	return direction;
		
		if (reversed) {
			direction = movement.getNextR();
		} else {
			direction = movement.getNext();
		}
		
		return direction;
	}

	/**
	 * Puts DrunkenGuard to sleep setting its symbol
	 * to 'g', meaning it's not active.
	 */
	public void putToSleep() {
		active = false;
		currentSymbol = 'g';
	}

	/**
	 * Wakes up DrunkenGuard setting its symbol to 'G',
	 * meaning it's active.
	 */
	public void wakeUp() {
		active = true;
		currentSymbol = 'G';
	}
	
	/**
	 * Gives probability for the DrunkenGuard's path reversibility
	 * @return Returns true if path gets reverted, false otherwise
	 */
	protected boolean revertPath() {
		return (rand.nextInt(100) > 50);
	}
}
