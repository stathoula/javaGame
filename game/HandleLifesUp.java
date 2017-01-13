package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

/**
 * A class whose role is to show up the lifUp object
 *
 * @author Efstathia Chioteli
 */
public class HandleLifesUp {
    /** initialize the scorekeep */
	private int scoreKeep = 0;
    /** var type of Random*/
	Random r = new Random();
	/** var type of Handler*/
	private Handler handler;
	/** var type of HealthAndScore*/
	private HealthAndScore healthAndScore;

    /**
     * Create HandleLifesUp object
     *
     * @param handler , it handles the methods tick and render
     * @param  healthAndScoreit, object of the class HealthAndScore
     */
   public HandleLifesUp(Handler handler, HealthAndScore healthAndScore) {
	   this.handler = handler;
	   this.healthAndScore = healthAndScore;
   }

	/**
	 * it increase scorekeep and every 500 it create LifeUp objects
	 *
     */
	public void tick() {
		scoreKeep++;

		if (scoreKeep % 500 == 0) {
		    handler.addObject(new LifeUp(r.nextInt(Game.WIDTH), 0, ID.LifeUp, handler));
		    handler.addObject(new LifeUp(r.nextInt(Game.WIDTH), 0, ID.LifeUp, handler));

		}

	}

	public void render(Graphics g) {
	}

}