package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.Font;

/**
 * A class that animates an Rectangle on the canvas which represent the player.
 *
 * @author Efstathia Chioteli
 * @opt nodefillcolor white
 */
public class Player extends GameObject {
    /** var type of Random*/
	Random r = new Random();
	/** var type of Handler*/
	Handler handler;
	/** keep the time that the game run*/
	private int time = 0;
    HealthAndScore healthAndScore = new HealthAndScore();
     /**
     * Creates a rectangle at a specific position
     *
     * @param x the position of the tree on axle x
     * @param y the position of the tree on axle y
     * @param id enumeration, what kinf of id this object is
     */
	public Player (int x , int y, ID id, Handler handler){
		super(x, y, id);
		this.handler = handler;
	}

    /** Gets the bound of the Rectangle */
	public Rectangle getBounds(){
		return new Rectangle(x, y, 27, 27);
	}

	/**
	 * Changes the bound of the object so as to move
	 *
     */
	public void tick() {
		x += velX;
		y += velY;
		time++;

        //it helps so as player not to go out of the bounds of the window
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);

        //check if the player crash with the snowflake
		collision();
	}

	/**
	 * it check if the player crash with the snowflakes
	 *
     */
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
            //if player crash with a snowflake-id the health will decrease
			if (tempObject.getId() == ID.SnowFast) {
				// we check if the player collide with the snowflake
				//we also want player to lose health after the first 80 milliseconds
				if (getBounds().intersects(tempObject.getBounds()) && time >= 80) {
					HealthAndScore.HEALTH -= 0.0001;
				}
			}
			//if player crash with a lifeup-id, health will increase
			if(tempObject.getId() == ID.LifeUp) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HealthAndScore.HEALTH += 12;
					handler.removeObject(tempObject);
				}
			}
		}
	}

	/**
	 * Draws the player
	 *
	 * @param g The Graphics object on which we will paint
     */
	public void render(Graphics g) {

		g.setColor(Color.red);
		g.fillRect(x, y, 27, 27);
		g.setColor(Color.white);
		g.drawRect(x, y, 27, 27);

        //start countdown
		if (time >= 0 && time <= 20) {
		    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
			g.drawString("3", 280, 180);
		}
		if (time >= 21 && time <= 40) {
		    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
			g.drawString("2", 280, 180);
		}
		if (time >= 41 && time <= 69) {
		    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
			g.drawString("1", 280, 180);
		}
        //indication that the game has started and player loses health after this indication
		if (time >= 70 && time <= 120) {
		    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
			g.drawString("Start", 280, 180);
		}

	}

}