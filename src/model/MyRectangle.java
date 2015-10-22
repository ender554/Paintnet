package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MyRectangle extends PaintObject{
	private double height;
	private double width;

	public MyRectangle(Point2D.Double locationStart, Point2D.Double locationEnd, Color color) {
		super(locationStart, locationEnd, color);
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