package dkeep.logic.characters.guard;

import dkeep.logic.characters.util.Movement;
import dkeep.util.Direction;

public class RookieGuard extends Guard {

	private static final long serialVersionUID = 1L;

	/**
	 * @brief RookieGuard constructor
	 * @param posX Initial RookieGuard's x-position
	 * @param posY Initial RookieGuard's y-position
	 * @param movement RookieGuard's set of movements
	 */
	public RookieGuard(int posX, int posY, Movement movement) {
		super(posX, posY, movement);
	}
	
	/**
	 * @brief Gets RookieGuard's next movement
	 * @return RookieGuard's next movement
	 * 
	 * RookieGuard's movement is sequential.
	 */
	@Override
	protected Direction nextMove() {
		return movement.getNext();
	}

}
