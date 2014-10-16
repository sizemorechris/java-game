package block;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class BlockHead extends StateBasedGame{

	public BlockHead(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new BlockHead("Block Head"));
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		app.setAlwaysRender(true);
		app.setDisplayMode(800, 600, false);
		app.start();	
		
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new GameState());
		this.addState(new GameOverState());
		this.addState(new GameWinState());
	}
	
}