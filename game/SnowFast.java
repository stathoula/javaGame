package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

/**
 * A class that animates an oval Rectangle on the canvas which represent the snowflake.
 *
 * @author Efstathia Chioteli
 * @opt nodefillcolor white
 */
public class SnowFast extends GameObject {
    /** var type of Random*/
	Random r = new Random();

     /**
     * Creates a snowflake at a specific position
     *
     * @param x the position of the tree on axle x
     * @param y the position of the tree on axle y
     * @param id enumeration, what kinf of id this object is
     */
	public SnowFast (int x , int y, ID id){
		super(x, y, id);
        //we dont want the snow to be moved horizontally
		velX = 0;
		//we dont want the snow to be moved horizontally
		velY = 4;
	}
    /** Gets the bound of the Rectangle */
	public Rectangle getBounds() {
			return new Rectangle(x, y, 16, 16);
	}

	/**
	 * Changes the bound of the snowflake so as to move
	 *
     */
	public void tick() {
        x += velX;
		y += velY;

        //if the snowflake reach the bottom of the window it goes back to the top
	    if (y >= Game.HEIGHT ) {
            y = 0;
            x = (int) (Game.WIDTH * Math.random());
		}
	}

	/**
	 * Draws the snowflake.
	 *
	 * @param g The Graphics object on which we will paint
     */
	public void render(Graphics g) {
		g.setColor(Color.white);

		g.fillOval(x, y, 16, 16);
	}

}