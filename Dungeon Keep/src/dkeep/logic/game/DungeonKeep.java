package dkeep.logic.game;

import java.io.Serializable;

public class DungeonKeep implements Serializable{

	private static final long serialVersionUID = 1L;
	private GameEngine engine;
	
	public DungeonKeep(GameConfig gameConfig) {
		 engine = new GameEngine(gameConfig);
	}
	
	public void render(GameGraphics gameGraphics) {
		engine.render(gameGraphics);
	}
	public void update() {
		engine.update();
	}
	
	public String getState() {
		return engine.getState();
	}
	
	//TODO: For real time play
	public static void runGame(GameConfig gameConfig) {
		DungeonKeep dk = new DungeonKeep(gameConfig);
		
		// Game loop.
		while (!dk.engine.isEndGame()) {
			dk.engine.update();
			//TODO: Better render
//			dk.engine.render();
		}
				
	}

}