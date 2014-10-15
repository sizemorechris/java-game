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
	private boolean isJumping;
	private boolean collision;
	private boolean dead;
	private boolean ready;
	private boolean win;
	private int lives;
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
		player = new Rectangle(400, 350, 30, 100);
		floors = new Rectangle[40];
		ready = false;
		background = new Image("res/background.png");
		gold = new Image("res/gold.png");
		isJumping = false;
		collision = false;
		dead = false;
		dX = 0;
		dY = 0;
		lives = 3;
		vY = 160;
		tY = 0.0;
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
			g.drawString("Press Right Arrow When Ready", 375, 300);
			g.drawString("Up Arrow/Spacebar to Jump", 375, 250);
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
			g.translate(0, 0);
			dX = 0;
			dY = 0;
			win = false;
			ready = false;
		} else {
			g.translate(-dX, 0);
		}
		g.setColor(Color.black);
		for (int i = 0; i < 40; i++) {
			if (i == 10 || i == 15 || i == 16 || i == 22 || i == 23 || i == 24 || i == 30 || i == 31 || i == 32 || i == 35 || i == 36 || i== 37) {
				floors[i] = new Rectangle(0 + 80 * i, 444, 80, 2);
				tilesSheet.getSprite(2, 0).draw(0 + 80 * i, 446);
				tilesSheet.getSprite(1, 0).draw(0 + 80 * i, 526);
			} else {
				if(i == 39) {
					g.drawImage(gold, 0 + 80 * i, 366);
				}
				floors[i] = new Rectangle(0 + 80 * i, 444, 80, 2);
				tilesSheet.getSprite(0, 0).draw(0 + 80 * i, 446);
				tilesSheet.getSprite(1, 0).draw(0 + 80 * i, 526);
			}
		}
		blockHead.draw(50 + dX, 50, 50, 50);
		player.setX(410 + dX);
		player.setY(345 + dY);
		//g.draw(player);
		g.drawString("x " + lives, 110 + dX, 68); 
		blockyAnimation.draw(400 + dX, 354 + dY);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		blockyAnimation.stop();
		collision = false;
		for (int i = 0; i < 40; i++) {
			if (player.intersects(floors[i])) {
				collision = true;
				if (i == 10 || i == 15 || i == 16 || i == 22 || i == 23 || i == 24 || i == 30 || i == 31 || i == 32 || i == 35 || i == 36 || i== 37) {
					dead = true;
				}
			}
		}
		if (dead && lives == 1) {
			lives = 4;
			ready = false;
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
		if (dX > 2820) {
			win = true;
			lives = 3;
			ready = false;
			dY = 0;
			dX = 0;
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
			ready = true;
		}
		if (400 + dX > -10 && ready) {
			blockyAnimation.start();
			dX += 11;
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
