package dkeep.logic.characters.guard;

import java.util.Random;

import dkeep.logic.characters.util.Movement;
import dkeep.util.Direction;

public class SuspiciousGuard extends Guard {
	
	private static final long serialVersionUID = 1L;
	private Random rand;
	private int numRounds;
	private boolean reversed;
	
	/**
	 * @brief SuspiciousGuard constructor
	 * @param posX Initial SuspiciousGuard's x-position
	 * @param posY Initial SuspiciousGuard's y-position
	 * @param movement SuspiciousGuard's list of movements
	 * 
	 * Initially the SuspiciousGuard's movement is not reversed.
	 */
	public SuspiciousGuard(int posX, int posY, Movement movement) {
		super(posX, posY, movement);
		rand = new Random();
		numRounds = rand.nextInt(10);
		reversed = false;
	}
	
	/**
	 * @brief Gets SuspiciousGuard's next movement
	 * @return SuspiciousGuard's next movement
	 * 
	 * SuspiciousGuard's movement alternates between
	 * sequential and reversed. The number of reversed
	 * rounds ranges between 0 and 2, while the number
	 * of sequential rounds ranges between 0 and 9.
	 */	
	@Override
	protected Direction nextMove() {
		Direction direction = Direction.NONE;
		
		if (numRounds-- == 0) {
			reversed = !reversed;
			if(reversed) numRounds = rand.nextInt(3);
			else numRounds = rand.nextInt(10);
		}
		
		if (reversed) {
			direction = movement.getNextR();
		} else {
			direction = movement.getNext();
		}
		
		return direction;
	}

}
