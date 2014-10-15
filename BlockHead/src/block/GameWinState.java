package block;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameWinState extends BasicGameState{

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawString("You Win!", 400, 300);
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
		return 2;
	}

}

