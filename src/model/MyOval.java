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
import java.io.Serializable;

@SuppressWarnings("serial")
public class MyOval extends PaintObject implements Serializable  {


	/*-----------------
	 * Constructor
	 *----------------*/
	public MyOval(Point2D.Double locationStart, Point2D.Double locationEnd, Color color) {
		super(locationStart, locationEnd, color);

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
		
		Ellipse2D.Double oval = new Ellipse2D.Double();
		oval.setFrameFromDiagonal(locationStart, locationEnd);
		
		g2.setPaint(getColor());
		g2.fill(oval);

	}

	/**
	 * Method: drawGhost
	 * 			draws the unfilled shape
	 * @param g2
	 * 		Graphics2D
	 * @return none
	 * 
	 */
	public void drawGhost(Graphics2D g2) {
		Ellipse2D.Double oval = new Ellipse2D.Double();
		oval.setFrameFromDiagonal(locationStart, locationEnd);
		g2.setPaint(color);
		g2.draw(oval);
		
	}
}
