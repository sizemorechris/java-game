package block;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameMenuState extends BasicGameState{

	private Image enter;
	private Image background;
	private Image title;
	
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		enter = new Image("res/enter.png");
		background = new Image("res/background.png");
		title = new Image("res/title.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setBackground(Color.white);
		g.drawImage(background, 0, 0);
		g.drawImage(title, 200, 50);
		g.drawImage(enter, 225, 350);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {		
		if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {
		return 3;
	}

}
