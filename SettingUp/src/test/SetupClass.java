package test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SetupClass extends StateBasedGame{
	
	public static int gameScore = 0;
	public static int lives = 3;
	
	public SetupClass(String title) {
		super(title);
	}
	
	public static void main(String[] args) throws SlickException {	
		AppGameContainer app = new AppGameContainer(new SetupClass("Setup Test"));
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		app.setDisplayMode(800, 600, false);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new GameState());
		this.addState(new GameOverState());
	}

}
