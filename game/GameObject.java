package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *  An abstract representation of a self-drawable object.
 *
 * @author Efstathia Chioteli
 */
public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;

     /**
     * Creates an object at a specific position depend on the ENUM ID
     *
     * @param x the position of the object on axle x
     * @param y the position of the object on axle y
     * @param id enumeration, what kind of id this object is
     */
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * Changes the bound of the object so as to move
	 *
     */
	public abstract void tick();

	/**
	 * Draws the object onto the window
	 *
     */
	public abstract void render(Graphics g);
	/** Gets the bound of the Rectangle */
	public abstract Rectangle getBounds();

	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelX() {
		return velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getVelY() {
		return velY;
	}


}