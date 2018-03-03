package dkeep.logic.guard;

import java.util.Random;
import dkeep.cli.IOInterface.Direction;


public class SuspiciousGuard extends Guard {
	
	private Random rand;
	private int numRounds;
	private boolean reversed;
	
	public SuspiciousGuard(int posX, int posY)
	{
		super(posX, posY);
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
			direction = getNextDirR();
		} else {
			direction = getNextDir();
		}
		
		return direction;
	}

}
