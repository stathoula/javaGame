package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A class which handle the keys that user uses
 *
 * @author Efstathia Chioteli
 */
public class KeyInput extends KeyAdapter {
    /** var type of Handler*/
	private Handler handler;

    /**
     * Create KeyInput object
     *
     * @param handler , it handles the methods tick and render
     */
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

    /**
     * this method moves the player when the user press keys
     *
     * @param KeyEvent
     */
	public void keyPressed(KeyEvent e) {
		/** read user's key input*/
		int key = e.getKeyCode();

        //for each object that exist on the window we check which one of the is the player
        //and move him depending ont they key that he pressed
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_UP) tempObject.setVelY(- 5);
				if (key == KeyEvent.VK_DOWN) tempObject.setVelY(5);
				if (key== KeyEvent.VK_RIGHT) tempObject.setVelX(5);
				if (key== KeyEvent.VK_LEFT) tempObject.setVelX(- 5);


			}

		}
        //if user press escape the game is over
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);

	}

    /**
     * this method stop moving the player when the user leave keys
     *
     * @param KeyEvent
     */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {

				if (key == KeyEvent.VK_UP) tempObject.setVelY(0);
				if (key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
				if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				if (key == KeyEvent.VK_LEFT) tempObject.setVelX(0);


			}

		}
	}

}