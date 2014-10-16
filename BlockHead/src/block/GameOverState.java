package block;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState{
	
	private Image background;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		background = new Image("res/background.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		g.drawString("Game Over", 400, 300);
		g.drawString("Press enter to play again", 400, 350);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2)
			throws SlickException {
		if (container.getInput().isKeyDown(Input.KEY_ENTER)) {
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}
