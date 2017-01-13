package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.Polygon;

/**
 * A self-drawable tree. Uses a box to specify the tree's bounds (the dimensions
 * constructor parameter). The trunk is placed in the middle of the bottom side
 * of the box, having a width equal to the 8% of the total width of the tree and
 * a height equal to the 20% of the total height of the bounding box. The main
 * body is represented as an isosceles triangle with a height of 80% of the
 * height of the bounding box.
 *
 * @author Efstathia Chioteli
 */

public class Tree extends GameObject {

	/** Tree trunk width as % of the bounding rectangle width */
	private final double trunkWidthFactor = 0.08;
	/** Tree trunk height as % of the bounding rectangle height */
	private final double trunkHeightFactor = 0.2;
	/** Tree body height as % of the bounding rectangle height */
	private final double bodyHeightFactor = 0.8;
	/** Trunk's color (RGB) */
	private final Color brown = new Color(204, 102, 0);
    /** Body's color (RGB) */
    private final Color green = new Color(0, 254, 0);
    /** tree width. */
	private static final int treeWidth = 100;
	/** Minimum tree height. */
    private static final int treeHeight = 200;
    /** var type of Random*/
    Random r = new Random();

    /**
     * Creates a tree at a spesific position
     *
     * @param x the position of the tree on axle x
     * @param y the position of the tree on axle y
     * @param id the id of this object
     */
	public Tree (int x, int y, ID id) {
		super(x, y, id);
	}

    /** Gets the bound of the Rectangle */
	public Rectangle getBounds() {
		return null;
	}

	/**
	 * This methdod is used when we want to move our object
	 * In this case we down want tree to move
     */
	public void tick() {}

	/**
	 * Draws the tree.
	 *
	 * @param g The Graphics object on which we will paint
     */
	public void render(Graphics g) {
		drawTrunk(g);
		drawBody(g);
	}

    /**
     * Draws the trunk. For details on how the lengths are calculated
     *
     * @param g The Graphics object on which we will paint
     * @see gr.aueb.Tree the class description.
     */
	private void drawTrunk(Graphics g) {
        /* Calculate the trunk rectangle first */
		Rectangle r = new Rectangle();

		r.x = (int) (x + (treeWidth - treeWidth * trunkWidthFactor) / 2);
		r.y = (int) (y + treeHeight * bodyHeightFactor);
		r.width = (int) (treeWidth * trunkWidthFactor);
		r.height = (int) (treeHeight * trunkHeightFactor);

        /* Fill it with brown color */
		Color c = g.getColor();
	    g.setColor(brown);
	    /* Draw it! */
	    g.drawRect(r.x, r.y, r.width, r.height);
		g.fillRect(r.x, r.y, r.width, r.height);
		g.setColor(c); //Revert paint color to default
	}


    /**
     * Draws the body. For details on how the lengths are calculated
     *
     * @param g The Graphics object on which we will paint
     * @see gr.aueb.Tree the class description.
     */
	 private void drawBody(Graphics g) {
		/* Create the polygon (triangle) to draw */
		Polygon p = new Polygon();
		p.addPoint(x + treeWidth / 2, y);

		p.addPoint(x,
			(int) (y + treeHeight * bodyHeightFactor));

		p.addPoint(x + treeWidth,
			(int) (y + treeHeight * bodyHeightFactor));

		/* Draw the body */
		g.setColor(Color.black);
		g.drawPolygon(p);

		/* Fill it with green color */
		Color c = g.getColor();
		g.setColor(green);
		g.fillPolygon(p);
		g.setColor(c); //Revert paint color to default
    }

}