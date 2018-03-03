package dkeep.logic.guard;

import dkeep.cli.IOInterface.Direction;

public class RookieGuard extends Guard {

	public RookieGuard(int posX, int posY) 
	{
		super(posX, posY);
	}
	
	
	@Override
	protected Direction nextMove() {
		return getNextDir();
	}

}
