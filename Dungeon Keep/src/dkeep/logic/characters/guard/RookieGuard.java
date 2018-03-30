package dkeep.logic.characters.guard;

import dkeep.logic.characters.util.Movement;
import dkeep.util.Direction;

public class RookieGuard extends Guard {

	private static final long serialVersionUID = 1L;


	public RookieGuard(int posX, int posY, Movement movement) 
	{
		super(posX, posY, movement);
	}
	
	
	@Override
	protected Direction nextMove() {
		return movement.getNext();
	}

}
