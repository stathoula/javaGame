package game;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.awt.event.*;
import java.awt.Font;

/**
 * The Game program main class.
 *
 * @author Efstathia Chioteli
 */

public class Game extends Canvas implements Runnable {

    /** Serial number of persistant  data.
     * Required, because JFrame implements serializable.
     */
	private static final long serialVersionUID = 1L;
    /** The window's width , height. */
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	/** Number of trees */
	private static final int numTrees = 20;
	/** Number of snowflakes */
	private static final int numSnowFlakes = 35;
    /** var type of Thread*/
    private Thread thread;
    /** var type of boolean*/
    private boolean running = false;
    /** var type of Handler*/
    private Handler handler;
    /** var type of Random*/
    private Random r;
    /** var type of HealthAndScore*/
    private HealthAndScore healthAndScore;
    /** var type of HandleLifesUp*/
    private HandleLifesUp handleLifesUp;
    /** add all the object in the game*/
	public Game() {

		handler = new Handler();
		healthAndScore = new HealthAndScore();
		handleLifesUp = new HandleLifesUp(handler,healthAndScore);
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH,HEIGHT, "christmas game", this);
		r = new Random();

         // Create randomly-positioned trees.
		 for (int i = 1; i <= numTrees; i++)
			handler.addObject(new Tree(r.nextInt(WIDTH), HEIGHT/2, ID.Tree));
         // Create randomly-positioned snowflakes
		for (int i=1; i <= numSnowFlakes; i++) {
			handler.addObject(new SnowFast(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SnowFast));
	     }

         // Create the user
		handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT, ID.Player, handler));

	}
    /**Initialize the thread */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
    /**Stop the thread */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
   /**Run the game*/
	public void run() {
		this.requestFocus();// with this command , you dont need to click on the window so as to start moving the player
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
			  render();
			}

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
    /**handles all the tick methods of the package */
	public void tick() {
		handler.tick();
		healthAndScore.tick();
		handleLifesUp.tick();
	}
    /**handles all the render methods of the package and draw the moon and happy new year */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH,HEIGHT);

		g.setColor(Color.white);
		g.fillOval(550, 10, 50, 50);

		g.setColor(new Color(150, 0, 0));
	    g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 40));
		g.drawString("Happy New Year", 230, 50);

		handler.render(g);
		healthAndScore.render(g);


		g.dispose();
		bs.show();
	}

     /**
     * check if the var of the object go out of the window
     *
     * @param var, the number that we want to check
     * @param min, the min value that we want
     * @param max, the max value that we want
     */
	public static int clamp (int var, int min, int max) {

		if (var >= max)
		   return var = max;
		else if (var <= min)
		    return var = min;
		else
		    return var;
	}
    /**start the game */
	public static void main(String args[]) {
		new Game();
	}
}