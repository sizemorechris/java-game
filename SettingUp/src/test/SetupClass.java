package test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SetupClass extends BasicGame{
	
	public SetupClass(String title) {
		super(title);
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawString("Hello World!", 50, 50);
		
	}
	
	public static void main(String[] args) throws SlickException {	
		AppGameContainer app = new AppGameContainer(new SetupClass("Setup Test"));
		app.setShowFPS(false);
		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
