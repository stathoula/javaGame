package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

/**
 * A class that animates an  Rectangle on the canvas which represent the lifeup.
 *
 * @author Efstathia Chioteli
 * @opt nodefillcolor white
 */
public class LifeUp extends GameObject {
    /** var type of Random*/
	Random r = new Random();
	/** var type of Handler*/
	Handler handler;
 /**
 * Creates a an object moving vertixally at a specific position which increase user's life
 *
 * @param x the position of the tree on axle x
 * @param y the position of the tree on axle y
 * @param id enumeration, what kinf of id this object is
 * @param handler, kind of Handler
 */
	public LifeUp (int x , int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		velX = 0;
		velY = 3;
	}

   /** Gets the bound of the Rectangle */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	/**
	 * Changes the bound of the object so as to move
	 *
     */
	public void tick() {
		x += velX;
		y += velY;

	}

	/**
	 * Draws the life-up.
	 *
	 * @param g The Graphics object on which we will paint
     */
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x,y,16,16);
	}

}