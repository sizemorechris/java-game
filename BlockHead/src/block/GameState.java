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
	
	private SpriteSheet blockySheet;
	private Animation blockyAnimation;
	private boolean isJumping;
	private int dX;
	private int dY;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		blockySheet = new SpriteSheet("res/avatarss.png", 50, 100);
		blockyAnimation = new Animation(blockySheet, 150);
		isJumping = false;
		dX = 0;
		dY = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		container.setVSync(true);
		g.setBackground(Color.white);
		
		blockyAnimation.draw(400 + dX, 450 + dY);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		blockyAnimation.stop();
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			if (!isJumping) {
				if (400 + dX > 50) {
					blockyAnimation.start();
					dX -= 4;
				}
			} else {
				if (400 + dX > 50) {
					blockyAnimation.stop();
					dX -= 3;
				}
			}
		} 
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			if (!isJumping) {
				if (400 + dX < 750) {
					blockyAnimation.start();
					dX += 4;
				}
			} else {
				if (400 + dX < 750) {
					blockyAnimation.stop();
					dX += 3;
				}
			}
		}
		if (container.getInput().isKeyDown(Input.KEY_SPACE) || container.getInput().isKeyDown(Input.KEY_UP) && !isJumping) {
			
		}
	
		blockyAnimation.update(delta);
	}

	@Override
	public int getID() {
		return 0;
	}

}
