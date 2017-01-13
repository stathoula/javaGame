package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

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

        //it helps so as to dont loose the player from the window
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);

        //see if the player crash with the snowflake
		collision();
	}

	/**
	 * it check if the player crash with the snowflakes
	 *
     */
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
            //if it crash with a snowflake-id it looses life
			if (tempObject.getId() == ID.SnowFast) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HealthAndScore.HEALTH -= 0.0001;
				}
			}
			//if it crash with a lifeup-id it get life
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
	}

}