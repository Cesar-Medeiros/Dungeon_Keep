package dkeep.logic.level;

import java.util.Vector;

import dkeep.logic.board.Board;
import dkeep.logic.characters.Hero;
import dkeep.logic.characters.MoveObj;
import dkeep.logic.characters.Ogre;
import dkeep.util.Direction;


public class Level2 extends Level{
	
	private static final long serialVersionUID = 1L;

	protected Hero hero;
	protected Vector<Ogre> ogres;
	protected int numOgres;
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
	
	/**
	 * Keep level constructor
	 * @param numOgres Level's number of ogres
	 */
	public Level2(int numOgres) {
		setBoard(new Board(boardMap));
		this.numOgres = numOgres;
	}

	/**
	 * Keep level constructor
	 */
	public Level2() {
		
	}
	
	/**
	 * Creates level moving objects (Hero, Ogres and Club)
	 */
	@Override
	public void setup() {
		hero = new Hero(1,7);
		
		club = new MoveObj(1,6, '*');
		
		ogres = new Vector<Ogre>();
		for(int i = 0; i < numOgres; i++) {
			Ogre ogre = new Ogre(4,1);
			ogres.addElement(ogre);
		}
		
		pushElements();
	}
	
	/**
	 * Stores objects of the Keep level
	 */
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
	
	/**
	 * Updates keep level iteration, verifying if the hero has
	 * reached its objectives or if somehow it got hit by an ogre's club. 
	 */
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
		
		if(board.onOpenableDoor(hero,heroDirection) && hero.hasKey()) {
			board.openDoors();
		}
	}
	
	/**
	 * Checks if moving object is hit by a club
	 * @param moveObj Moving object to be compared
	 * @return Returns true if object is over the club, false otherwise
	 */
	public boolean onClub(MoveObj moveObj) {
		return moveObj.getPosX() == club.getPosX() && moveObj.getPosY() == club.getPosY();
	}
}

