package dkeep.logic.level;

import java.util.Vector;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.Ogre;

public class CustomLevel2 extends Level2 {

	private static final long serialVersionUID = 1L;

	
	private char[][] gameMap;
	
	public char[][] setupElements(char[][] gameMap){
		
		ogres = new Vector<Ogre>();
		club = new MoveObj(1,1, '*');
		char[][] boardMap = new char[gameMap.length][gameMap[0].length];
		
		for(int row = 0; row < gameMap.length; row++) {
			for(int col = 0; col < gameMap[row].length; col++) {
				switch(gameMap[row][col]) {
				case Hero.heroSymbol: 
					hero = new Hero(col, row); 
					break;
					
				case Ogre.ogreSymbol:
					ogres.add(new Ogre(col, row));
					break;
					
				case Board.closeDoorSymbol:
					boardMap[row][col] =  Board.closeDoorSymbol;
					break;
					
				case Board.floorSymbol:
					boardMap[row][col] =  Board.floorSymbol;
					break;
					
				case Board.wallSymbol:
					boardMap[row][col] =  Board.wallSymbol;
					break;
			}
		}
		}
		return boardMap;
		
	}
	
	public CustomLevel2(char[][] gameMap) {
		this.setBoard(new Board(setupElements(gameMap)));
		this.gameMap = gameMap;
	}

	@Override
	public void setup() {
		setupElements(gameMap);
		super.pushElements();
	}

	
}
