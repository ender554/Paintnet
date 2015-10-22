package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public abstract class PaintObject {
	protected Point2D.Double locationStart, locationEnd;
	private Color color;
	
	public PaintObject(Point2D.Double locationStart, Point2D.Double locationEnd, Color color){
		this.locationStart = locationStart;
		this.locationEnd = locationEnd;
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue());
		
	}
	

	public Point2D.Double getLocationStart(){
		return this.locationStart;
	}
	public Point2D.Double getLocationEnd(){
		return this.locationEnd;
	}
	public Color getColor(){
		return this.color;
	}
	public abstract void draw(Graphics2D g2);
	
	

}









/*

lines, rectangles, ovals, image

paint objects:
	paint objects
		color
		size
		locationStart(x,y)
		locationEnd(x,y)
		draw
		
	abstract draw
	


*/