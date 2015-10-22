/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * MyImage creates the draw-able object image 
 * in the shape of a rectangle 
 * 
 *-------------------------------------------*/

package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;

public class MyImage extends PaintObject {

	private double width;
	private double height;
	private Image image;

	/*-----------------
	 * Constructor
	 *----------------*/
	public MyImage(Point2D.Double locationStart, Point2D.Double locationEnd, Image image) {
		super(locationStart, locationEnd, Color.BLACK);
		width = Math.abs(locationEnd.x - locationStart.x);
		height = Math.abs(locationEnd.y - locationStart.y);
		this.image = image;
	}

	/**
	 * Method: draw
	 * @param g2
	 * 		Graphics2D
	 * @return none
	 * 
	 */
	public void draw(Graphics2D g2) {

		g2.drawImage(image, (int) Math.round(locationStart.x), (int) Math.round(locationEnd.y), (int) Math.round(width),
				(int) Math.round(height), null);

	}

}
