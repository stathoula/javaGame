package game;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *  A class which handle all the objects on the window
 *
 * @author Efstathia Chioteli
 * @opt nodefillcolor white
 */
public class Handler {

    /** List with all the objects shown on the window */
	LinkedList<GameObject> object = new LinkedList<GameObject>();

    /** Change the bound of all the objects so as to been moved on the Window per frame*/
	public void tick() {
		for (int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);

			tempObject.tick();
		}
	}

    /** Draw all the objects on the window */
	public void render( Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.render(g);
		}
	}

    /** Add a component to be drawn. */
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	/** remove  a component from the window. */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}