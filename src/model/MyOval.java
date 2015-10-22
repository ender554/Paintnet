/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * MyOval creates the draw-able object Oval 
 * in the shape of an oval
 * 
 *-------------------------------------------*/

package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class MyOval extends PaintObject {
	private double height;
	private double width;

	/*-----------------
	 * Constructor
	 *----------------*/
	public MyOval(Point2D.Double locationStart, Point2D.Double locationEnd, Color color) {
		super(locationStart, locationEnd, color);
		width = Math.abs(locationEnd.x - locationStart.x);
		height = Math.abs(locationEnd.y - locationStart.y);
	}

	/**
	 * Method: draw
	 * 
	 * @param g2
	 *            Graphics2D
	 * @return none
	 * 
	 */
	public void draw(Graphics2D g2) {
		Ellipse2D.Double oval = new Ellipse2D.Double(locationStart.x, locationEnd.y, width, height);
		g2.setPaint(getColor());
		g2.fill(oval);

	}
}
