package test;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameState extends BasicGameState{
	
	private ArrayList<Circle> balls;
	private Circle mouseball;
	private int timePassed;
	private Random random;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		balls = new ArrayList<Circle>();
		mouseball = new Circle(0, 0, 10);
		timePassed = 0;
		random = new Random();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setColor(Color.yellow);
		g.fill(mouseball);
		g.setColor(Color.red);
		for (Circle c: balls) {
			g.fill(c);
		}
		g.setColor(Color.white);
		g.drawString("Current Balls: " + balls.size(), 20, 50);
		g.drawString("Current Lives: " + SetupClass.lives, 20, 65);
		g.drawString("Current Score: " + SetupClass.gameScore, 20, 80);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		mouseball.setCenterX(container.getInput().getMouseX());
		mouseball.setCenterY(container.getInput().getMouseY());
		timePassed += delta;
		if (timePassed > 500) {
			timePassed = 0;
			balls.add(new Circle(200 + random.nextInt(400), 0, 10));
		}
		for (Circle c: balls) {
			c.setCenterY(c.getCenterY() + (delta/5f));
		}
		for (int i = balls.size() - 1; i >= 0; i--) {
			Circle c = balls.get(i);
			if (c.getCenterY() > 610) {
				balls.remove(i);
				SetupClass.lives--;
			} else if (c.intersects(mouseball)) {
				balls.remove(i);
				SetupClass.gameScore++;
			}
		}
		if (SetupClass.lives < 0) {
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
