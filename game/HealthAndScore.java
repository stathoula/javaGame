package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.BufferedReader;

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
        //check if user has lost so as to increase the score by one
		if (!lost)
		     score++;
        //check if user has lost so as to
		if (end) {
			int highscore = read();
		    write(score,highscore);
		}
	}

	/**
	 * Compare the score with the highscore and store the highscore in an external file
	 *
	 * @param highscore , the number that is already stored in the external file
	 * @param score , player's score
     */
	public static void write(int highscore , int score) {
		try {
			FileOutputStream is = new FileOutputStream("game/highScore.txt");
			OutputStreamWriter out = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(out);
			if (score > highscore){
			   w.write(score + "");
		   } else {
			   w.write(highscore + "");
			}
			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file highScore.txt");
		}
	}

	/**
	 * Read the highscore from the external file
     */
    public static int read(){
		try{
			Scanner scanner = new Scanner(new File("game/highScore.txt"));
			int [] numbers = new int [100];
			int i = 0;
			while(scanner.hasNextInt())
			{
				 numbers[i++] = scanner.nextInt();
			}
			return numbers[0];

		} catch(Exception ex) {
			System.out.println("Problem reading the file highScore.txt");
			return -1;
		}

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
			g.drawString("Game Over", 280, 160);
		    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
			g.drawString("Your score is : " + score, 280, 190);
		    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
			g.drawString("Highscore : " + read(), 280, 220);

			lost = true;
			//we set end true in case user continues to play after game over
			end = true;
		}

	}

}