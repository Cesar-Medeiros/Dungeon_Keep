package dkeep.logic.level;

import java.util.Random;
import java.util.Vector;
import dkeep.cli.IOInterface.Direction;
import dkeep.logic.Board;
import dkeep.logic.Hero;
import dkeep.logic.MoveObj;
import dkeep.logic.Ogre;

public class Level2 extends Level{
	
	//Hero
	private Hero hero;
	
	//Ogre
	private Vector<Ogre> ogres;
	
	//Club
	private MoveObj club;
	
	
	private static final char keySymbol = 'k';
	private static final char doorSymbol = 'I';
	private static final char exitSymbol = 'S';
	
	private static final int numOgres = new Random().nextInt(6) + 1;
	
	private static char[][] boardMap = new char[][] {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'I', ' ', ' ', ' ', ' ', ' ',' ' , 'k' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', ' ', ' ', ' ', ' ', ' ',' ' , ' ' ,'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	


	@Override
	public String toString() {
		return "Level2";
	}
	
	
	@Override
	public void setup() {
		//Super class
		board = new Board(boardMap);
		
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
		
		//Level
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
	public void draw() {
		cleanScreen();
		board.draw(levelObjs);
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
			
			
			if(onKey(ogre))
				ogre.setSymbol(Ogre.overKeySymbol);
			else if(ogre.isStunned())
				ogre.setSymbol(Ogre.stunnedSymbol);
			else 
				ogre.setSymbol(Ogre.ogreSymbol);
			
			
			if(onKey(ogre.getClub()))
				ogre.getClub().setSymbol(Ogre.overKeySymbol);
			else
				ogre.getClub().setSymbol(Ogre.clubSymbol);
		}

		
		if(onKey(hero)) {
			pickKey();
		}		

		if(onClub(hero)) {
			hero.setSymbol(Hero.armedSymbol);
			pickClub();
		}		
		
		if(onExit(hero)) {
			completed = true;
			return;
		}
		
		hero.tryExit(heroDirection, this);
	}
	
	
	public boolean onKey(MoveObj moveObj) {
		
		boolean onKeyPos = (boardMap[moveObj.getPosY()][moveObj.getPosX()] == keySymbol);
		
		if (onKeyPos && moveObj instanceof Hero) {
			((Hero) moveObj).hasKey();
		}
		
		return onKeyPos;
	}
	
	public boolean onClub(MoveObj moveObj) {
		return moveObj.getPosX() == club.getPosX() && moveObj.getPosY() == club.getPosY();
	}
	
	public boolean onExit(MoveObj moveObj) {		
		return (boardMap[moveObj.getPosY()][moveObj.getPosX()] == exitSymbol);
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}


	public void setOgres(Vector<Ogre> ogres) {
		this.ogres = ogres;
	}


	public void setClub(MoveObj club) {
		this.club = club;
	}


	public static void setBoardMap(char[][] boardMap) {
		Level2.boardMap = boardMap;
	}


	public boolean onDoor(MoveObj moveObj) {
		return (boardMap[moveObj.getPosY()][moveObj.getPosX() - 1] == doorSymbol);
	}
	
	public void openDoor() {
		board.substChar(doorSymbol, exitSymbol);
	}
	

	public void pickKey() {
		board.substChar(keySymbol, ' ');
	}
	
	public void pickClub() {
		club.setSymbol(' ');
		hero.pickClub();
	}
}
