package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;

import model.PaintObject;

public class MyImage extends PaintObject{

	private double width;
	private double height;

	public MyImage(Double locationStart, Double locationEnd, Image myImage) {
		super(locationStart, locationEnd, myImage);
		width = Math.abs(locationEnd.x - locationStart.x);
		height = Math.abs(locationEnd.y - locationStart.y);
	}

	@Override
	public void draw(Graphics2D g2) {
		
		
		Rectangle2D.Double rectangle = new Rectangle2D.Double(locationStart.x, locationEnd.y, width, height);
		g2.setPaint(getColor());
		g2.fill(rectangle);
		
	}
	

}
