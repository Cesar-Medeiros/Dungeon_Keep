package dkeep.logic.characters.guard;

import java.util.Random;

import dkeep.logic.characters.util.Movement;
import dkeep.util.Direction;

public class SuspiciousGuard extends Guard {
	
	private static final long serialVersionUID = 1L;
	private Random rand;
	private int numRounds;
	private boolean reversed;
	
	public SuspiciousGuard(int posX, int posY, Movement movement)
	{
		super(posX, posY, movement);
		rand = new Random();
		numRounds = rand.nextInt(10);
		reversed = false;
	}
	
			
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
