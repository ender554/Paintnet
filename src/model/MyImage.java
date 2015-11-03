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
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class MyImage extends PaintObject implements Serializable {

	private double width;
	private double height;
	private String fileName;
	private transient Image image;

	/*-----------------
	 * Constructor
	 *----------------*/
	public MyImage(Point2D.Double locationStart, Point2D.Double locationEnd, String fileName, Image image) {
		super(locationStart, locationEnd, Color.BLACK);
		width = locationEnd.x - locationStart.x;
		height = locationEnd.y - locationStart.y;
		this.fileName = fileName;
		
		this.image = image;
		
		
	}
	
	/**
	 * method: bufferImage
	 * 
	 * adds the image
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	private void bufferImage() {
		try {
			image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
		//prevents lag on the network 
		if(image == null) {
			bufferImage();
		}
		
		
		g2.drawImage(image, (int) Math.round(locationStart.x), (int) Math.round(locationStart.y),
					(int) Math.round(width), (int) Math.round(height), null);
		

	}

	/**
	 * Method: drawGhost draws the unfilled shape
	 * 
	 * @param g2
	 *            Graphics2D
	 * @return none
	 * 
	 */
	public void drawGhost(Graphics2D g2) {
		Rectangle2D.Double rectangle = new Rectangle2D.Double();
		rectangle.setFrameFromDiagonal(locationStart, locationEnd);
		g2.setPaint(color);
		g2.draw(rectangle);

	}

}
