package dkeep.logic.level;

import java.util.Vector;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.Ogre;
import dkeep.util.Direction;


public class Level2 extends Level{
	
	private static final long serialVersionUID = 1L;

	//Hero
	protected Hero hero;
	
	//Ogre
	protected Vector<Ogre> ogres;
	protected int numOgres;
	
	//Club
	protected MoveObj club;	
	
	
	protected static char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'D', ' ', ' ', ' ', ' ', ' ',' ' , 'k' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	

	
	public Level2(int numOgres) {
		setBoard(new Board(boardMap));
		this.numOgres = numOgres;
	}

	public Level2() {
	}
	
	@Override
	public void setup() {
		//Hero
		hero = new Hero(1,7);
		
		//Club
		club = new MoveObj(1,6, '*');
		
		//Ogre
		ogres = new Vector<Ogre>();
		for(int i = 0; i < numOgres; i++) {
			Ogre ogre = new Ogre(4,1);
			ogres.addElement(ogre);
		}
		
		pushElements();
	}
	
	public void pushElements() {
		int memory = 1 + 1 + 2*ogres.size();
		levelObjs = new MoveObj[memory];
		
		int offset = 0;
		
		levelObjs[offset] = club; offset++;
		levelObjs[offset] = hero; offset++;
		
		
		for(int i = 0; i < ogres.size(); i++) {
			levelObjs[i + offset] = ogres.elementAt(i).getClub();
		}
		offset +=ogres.size();
		
		for(int i = 0; i < ogres.size(); i++) {
			levelObjs[i + offset] = ogres.elementAt(i);
		}
	}
	
	@Override
	public void update() {
		
		Direction heroDirection = hero.move(board);
		
		for(Ogre ogre: ogres) {
			ogre.move(board);
			
			if(hero.nearPos(ogre)) {
				if(hero.isArmed()) ogre.stun(2);
				else if(!ogre.isStunned())
					gameOver = true;
			}
			
			if(hero.nearPos(ogre.getClub())) {
				gameOver = true;
			}
			

			if(board.onKey(ogre))
				ogre.setSymbol(Ogre.overKeySymbol);
			else if(ogre.isStunned())
				ogre.setSymbol(Ogre.stunnedSymbol);
			else 
				ogre.setSymbol(Ogre.ogreSymbol);
			
			
			if(board.onKey(ogre.getClub()))
				ogre.getClub().setSymbol(Ogre.overKeySymbol);
			else
				ogre.getClub().setSymbol(Ogre.clubSymbol);
		}

		
		if(board.onKey(hero)) {
			board.pickKey();
			hero.pickKey();
		}		

		if(onClub(hero)) {
			club.setSymbol(' ');
			hero.pickClub();
		}		
		
		if(board.onOpenDoor(hero)) {
			completed = true;
			return;
		}
		
		if(board.onOpenablenDoor(hero,heroDirection) && hero.hasKey()) {
			board.openDoors();
		}
	}
	
	
	public boolean onClub(MoveObj moveObj) {
		return moveObj.getPosX() == club.getPosX() && moveObj.getPosY() == club.getPosY();
	}
	
}
