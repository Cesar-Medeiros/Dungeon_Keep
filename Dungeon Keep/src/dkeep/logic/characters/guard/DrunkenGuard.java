package dkeep.logic.characters.guard;

import java.util.Random;

import dkeep.logic.characters.util.Movement;
import dkeep.util.Direction;
import static dkeep.util.Direction.NONE;

public class DrunkenGuard extends Guard {

	private static final long serialVersionUID = 1L;
	private Random rand;	
	private boolean reversed;
	
	public DrunkenGuard(int posX, int posY, Movement movement)
	{
		super(posX, posY, movement);
		rand = new Random();
		reversed = false;
	}
	
	
	@Override
	protected Direction nextMove() {
		Direction direction = NONE;
		
		int chance = rand.nextInt(100);

		if (active) {
			
			/* sleep chance */
			if (chance < 20) {
				putToSleep();
			}
			
		} else {
			
			/* wake up chance */
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

	
	protected void putToSleep()
	{
		active = false;
		currentSymbol = 'g';
	}

	
	protected void wakeUp() 
	{
		active = true;
		currentSymbol = 'G';
	}
	

	protected boolean revertPath() {
		return (rand.nextInt(100) > 50);
	}

}
