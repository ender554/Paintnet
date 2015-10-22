package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class MyLine extends PaintObject{

	public MyLine(Point2D.Double locationStart, Point2D.Double locationEnd, Color color) {
		super(locationStart, locationEnd, color);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.draw(new Line2D.Double(locationStart.x, locationStart.y, locationEnd.x, locationEnd.y));
		
	}
	

}
