package block;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState{
	
	//private Rectangle block;
	private SpriteSheet blockySheet;
	private Animation blockyAnimation;
	private int dX;
	private int dY;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		blockySheet = new SpriteSheet("res/avatarss.png", 50, 100);
		blockyAnimation = new Animation(blockySheet, 150);
		dX = 0;
		dY = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		container.setVSync(true);
		g.setBackground(Color.white);
		blockyAnimation.draw(100, 100);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {	
		/*if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			if (400 + dX > 50) {
				dX -= 4;
			}
		} 
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			if (400 + dX < 750) {
				dX += 4;
			}
		}*/
		blockyAnimation.update(delta);
		
		//block.setCenterX(400 + dX);
		//block.setCenterY(450 + dY);
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
