package dkeep.logic.guard;

import java.util.Random;
import dkeep.cli.IOInterface.Direction;
import static dkeep.cli.IOInterface.Direction.NONE;


public class DrunkenGuard extends Guard {

	private Random rand;	
	private boolean reversed;
	
	public DrunkenGuard(int posX, int posY)
	{
		super(posX, posY);
		rand = new Random();
		reversed = false;
	}
	
	
	@Override
	protected Direction nextMove() {
		Direction direction = NONE;
		
		int chance = rand.nextInt(100);
		System.out.println(chance);

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
			direction = getNextDirR();
		} else {
			direction = getNextDir();
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
