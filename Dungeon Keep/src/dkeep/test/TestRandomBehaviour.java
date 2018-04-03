package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Ogre;


public class TestRandomBehaviour {

	char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X'},
		{'X', ' ', ' ', ' ', 'X'},
		{'I', ' ', ' ', ' ', 'X'},
		{'I', 'k', ' ', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X'}
	};

	@Test(timeout = 1000)
	public void testSomeRandomMove() {
		Board board = new Board(boardMap);
		Ogre ogre = new Ogre(3, 1);		


		boolean top = false, left = false, bottom = false, right = false;


		while(!top || !left || !bottom || !right) {

			int posX = ogre.getPosX();
			int posY = ogre.getPosY();

			ogre.move(board);

			int newPosX = ogre.getPosX();
			int newPosY = ogre.getPosY();

			if(newPosX == posX && newPosY == posY + 1) {
				top = true;
			}
			else if(newPosX == posX - 1 && newPosY == posY) {
				left = true;
			}
			else if(newPosX == posX && newPosY == posY - 1) {
				bottom = true;
			}
			else if(newPosX == posX + 1 && newPosY == posY) {
				right = true;
			}
			else fail("Hit a Wall");
		}

	}

	@Test(timeout = 1000)
	public void testSomeRandomSwing() {
		Board board = new Board(boardMap);
		Ogre ogre = new Ogre(3, 1);

		boolean top = false, left = false, bottom = false, right = false;


		while(!top || !left || !bottom || !right) {

			ogre.move(board);

			int posX = ogre.getPosX();
			int posY = ogre.getPosY();

			int newClubPosX = ogre.getClub().getPosX();
			int newClubPosY = ogre.getClub().getPosY();

			if(newClubPosX == posX && newClubPosY == posY + 1) {
				top = true;
			}
			else if(newClubPosX == posX - 1 && newClubPosY == posY) {
				left = true;
			}
			else if(newClubPosX == posX && newClubPosY == posY - 1) {
				bottom = true;
			}
			else if(newClubPosX == posX + 1 && newClubPosY == posY) {
				right = true;
			}
			else fail("Hit a Wall");
		}

	}

}
