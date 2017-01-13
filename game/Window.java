package game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 *  class which create the window of the game
 * The program's main window.
 * Extends Canvas to display the window where the
 * trees.player and snow are drawn, ans starth the thread via the Game.open() method
 *
 *
 * @author Efstathia Chioteli
 */
public class Window extends Canvas {

    /** Serial number of persistant  data.
     * Required, because JFrame implements serializable.
     */
	private static final long serialVersionUID = 1L;

    /**
     * Creates a window-frame at a spesific position with specific bounds
     *
     * @param width , the width of the window
     * @param height , the height of the window
     * @param title, the title of the window
     * @param game, the main class Game wgich will be added on the window
     */
	public Window(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);

        // Create our drawing canvas
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //το παράθυρο εμφανίζεται στην μέση, αλλιώς θα έβγαινε πάνω αριστερά
		frame.add(game);//add the game on the frame
		frame.setVisible(true);
		game.start();
	}
}