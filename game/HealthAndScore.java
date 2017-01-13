package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

/**
 * A class that handles the user's life and keep the score when he looses
 *
 * @author Efstathia Chioteli
 */
public class HealthAndScore {

    /** initial life of the user */
	public static int HEALTH = 100;

    /** the quantity of the green which we use for the health's colour */
	private int greenValue = 255;
    /** initialize the score */
	private int score = 0;
	/** initiliaze the status of user (if hes has lost or not and if te game is over) */
	boolean lost, end = false;
    /** var type of Random*/
	Random r = new Random();
	/** var type of Handler*/
	 private Handler handler;

	/**
	 * Check if the health go out of the box and it changes the score adding +1 everytime
	 *
     */
	public void tick() {
        //it cares health to be between 0-100
		HEALTH = Game.clamp(HEALTH, 0, 100);
		//it cares greenValue to be between 0-255
		greenValue = Game.clamp(greenValue, 0, 255);

		greenValue = HEALTH * 2;
        //check if user has lost so as to upload the score
		if (!lost)
		     score++;

	}

	/**
	 * Draws the score and the health
	 *
	 * @param g The Graphics object on which we will paint
     */
	public void render(Graphics g) {
        //draw the box of life
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15,15,200,32);
        //draw the score
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 17));
		g.drawString("Score: " + score, 15, 64);

        //when user looses it display 'Game over' and the score
	     if (HEALTH == 0 || end) {

		    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
			g.drawString("Game Over", 280, 180);
		    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
			g.drawString("Your score is : " + score, 280, 210);

			lost = true;
			//we set end true in case user continues to play after game over
			end = true;
		}

	}

}