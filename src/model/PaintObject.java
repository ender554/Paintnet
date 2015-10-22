/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * PaintObject is the super class for all
 * objects that will be painted with this program
 * 
 *-------------------------------------------*/

package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;

public abstract class PaintObject {
	protected Point2D.Double locationStart, locationEnd;
	private Color color;
	protected Image myImage;

	/*-----------------
	 * Constructors
	 *----------------*/
	public PaintObject(Point2D.Double locationStart, Point2D.Double locationEnd, Color color) {
		this.locationStart = locationStart;
		this.locationEnd = locationEnd;
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue());

	}

	public PaintObject(Point2D.Double locationStart, Point2D.Double locationEnd, Image myImage) {
		this.locationStart = locationStart;
		this.locationEnd = locationEnd;
		this.myImage = myImage;
	}

	/*-----------------
	 * Getters
	 *----------------*/

	public Point2D.Double getLocationStart() {
		return this.locationStart;
	}

	public Point2D.Double getLocationEnd() {
		return this.locationEnd;
	}

	public Color getColor() {
		return this.color;
	}

	/**
	 * Method: draw
	 * 
	 * @param g2
	 *            Graphics2D
	 * @return abstract
	 * 
	 */
	public abstract void draw(Graphics2D g2);

}
