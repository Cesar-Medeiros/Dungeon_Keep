public abstract class Level {

	protected boolean gameOver;
	protected boolean completed;
	protected Board board;
	
	public abstract void setup();
	public abstract void draw();
	public abstract void update();
	
	public boolean gameOver() {return gameOver;}
	public boolean completed() {return completed;}
	
	@Override
	public abstract String toString();
}
