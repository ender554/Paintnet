/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * MyLine creates the draw-able object line
 *-------------------------------------------*/
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class MyLine extends PaintObject implements Serializable  {

	/*-----------------
	 * Constructor
	 *----------------*/
	public MyLine(Point2D.Double locationStart, Point2D.Double locationEnd, Color color) {
		super(locationStart, locationEnd, color);
	}

	/**
	 * Method: draw
	 * 
	 * @param g
	 *            Graphics2D
	 * @return none
	 * 
	 */
	public void draw(Graphics2D g) {
		g.setColor(getColor());
		g.draw(new Line2D.Double(locationStart.x, locationStart.y, locationEnd.x, locationEnd.y));

	}

}
