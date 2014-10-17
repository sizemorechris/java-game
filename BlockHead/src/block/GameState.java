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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameState extends BasicGameState{
	
	private SpriteSheet blockySheet;
	private Animation blockyAnimation;
	private SpriteSheet tilesSheet;
	private Image blockHead;
	private Image background;
	private Image gold;
	private Rectangle player;
	private Rectangle[] floors;
	private boolean[] isSafe;
	private boolean isJumping;
	private boolean collision;
	private boolean dead;
	private boolean ready;
	private boolean win;
	private int lives;
	private int random;
	private int dX;
	private int dY;
	private double vY;
	private double tY;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		blockySheet = new SpriteSheet("res/avatarss.png", 50, 100);
		blockyAnimation = new Animation(blockySheet, 150);
		tilesSheet = new SpriteSheet("res/tiless.png", 80, 80);
		blockHead = new Image("res/blockHead.png");
		player = new Rectangle(400, 400, 1, 100);
		floors = new Rectangle[100];
		isSafe = new boolean[100];
		ready = false;
		background = new Image("res/background.png");
		gold = new Image("res/gold.png");
		isJumping = false;
		collision = false;
		dead = false;
		dX = 0;
		dY = 0;
		lives = 5;
		random = 0;
		vY = 160;
		tY = 0.0;
		updateWorld();
	}
	
	public void updateWorld() {
		for (int i = 0; i < 99; i++) {
			random = (int)(Math.random() * 3);
			if ((random == 0 || random == 2) && i > 9 && (isSafe[i - 3] && isSafe[i - 4])) {
				isSafe[i] = false;
			} else {
				isSafe[i] = true;
			}
		}
		isSafe[99] = true;
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		container.setVSync(true);
		g.setWorldClip(0, 0, 800, 600);
		g.setBackground(Color.white);
		g.drawImage(background, -2370 - (dX/5), 0);
		g.drawImage(background, 0 - (dX/5), 0);
		if (!ready) {
			g.setColor(Color.black);	
			g.drawString("Press Right Arrow When Ready", 375, 250);
			g.drawString("Up Arrow/Spacebar to Jump", 375, 200);
		}
		if (dead) {
			g.setColor(Color.red);
			g.fill(new Rectangle(0, 0, 800, 600));
			g.translate(0, 0);
			dX = 0;
			dY = 0;
			dead = false;
			ready = false;
			lives--;
		} else if (win){
			win = false;
			ready = false;
			g.translate(0, 0);
		} else {
			g.translate(-dX, 0);
		}
		g.setColor(Color.black);
		for (int i = 0; i < 100; i++) {
			random = (int)(Math.random() * 3);
			if (!isSafe[i]) {
				floors[i] = new Rectangle(0 + 80 * i, 444, 80, 2);
				tilesSheet.getSprite(2, 0).draw(0 + 80 * i, 446);
				tilesSheet.getSprite(1, 0).draw(0 + 80 * i, 526);
			} else {
				if(i == 99) {
					g.drawImage(gold, 0 + 80 * i, 366);
				}
				floors[i] = new Rectangle(0 + 80 * i, 444, 80, 2);
				tilesSheet.getSprite(0, 0).draw(0 + 80 * i, 446);
				tilesSheet.getSprite(1, 0).draw(0 + 80 * i, 526);
			}
		}
		blockHead.draw(50 + dX, 50, 50, 50);
		player.setX(420 + dX);
		player.setY(345 + dY);
		//g.draw(player); draw hit box
		g.drawString("x " + lives, 110 + dX, 68); 
		blockyAnimation.draw(400 + dX, 354 + dY);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		blockyAnimation.stop();
		collision = false;
		for (int i = 0; i < 100; i++) {
			if (player.intersects(floors[i])) {
				collision = true;
				if (!isSafe[i]) {
					dead = true;
				}
			}
		}
		if (dead && lives == 1) {
			lives = 6;
			ready = false;
			updateWorld();
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
		if (dX > 7550) {
			win = true;
			lives = 5;
			dX = 0;
			dY = 0;
			updateWorld();
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
			ready = true;
		}
		if (400 + dX > -10 && ready) {
			blockyAnimation.start();
			dX += 10;
		}	
		if ((container.getInput().isKeyDown(Input.KEY_SPACE) || container.getInput().isKeyDown(Input.KEY_UP)) && !isJumping) {
			isJumping = true;
		}
		if (isJumping) {
			tY += 1/60.0;
		}
		dY = -(int)Math.round((vY * tY - 200 * tY * tY));
		if (collision) {
			tY = 0;
			isJumping = false;
		}
		blockyAnimation.update(delta);		
	}
	
	@Override
	public int getID() {
		return 0;
	}
}
