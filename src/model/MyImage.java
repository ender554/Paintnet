package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class MyImage extends PaintObject{

	private double width;
	private double height;
	private Image image;

	public MyImage(Point2D.Double locationStart, Point2D.Double locationEnd, Image image) {
		super(locationStart, locationEnd, Color.BLACK);
		width = Math.abs(locationEnd.x - locationStart.x);
		height = Math.abs(locationEnd.y - locationStart.y);
		this.image = image;
	}

	@Override
	public void draw(Graphics2D g2) {
		
		
        
         
        g2.drawImage(image, (int)Math.round(locationStart.x), (int)Math.round(locationEnd.y), (int)Math.round(width), (int)Math.round(height), null);
		
		//Rectangle2D.Double rectangle = new Rectangle2D.Double(locationStart.x, locationEnd.y, width, height);
		//g2.setPaint(getColor());
		//g2.fill(rectangle);
		
	}
	

}
