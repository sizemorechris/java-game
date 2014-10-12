package block;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState{
	
	private Rectangle block;
	private int dX;
	private int dY;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		block = new Rectangle(50, 50, 100, 100);
		dX = 0;
		dY = 0;
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		container.setVSync(true);
		g.setBackground(Color.white);
		g.setColor(Color.blue);
		g.drawString("Block Head", 50, 50);
		g.fill(block);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {	
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			if (400 + dX > 50) {
				dX -= 4;
			}
		} else if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			if (400 + dX < 750) {
				dX += 4;
			}
		}
		block.setCenterX(400 + dX);
		block.setCenterY(450 + dY);
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
