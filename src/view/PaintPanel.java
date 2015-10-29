/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * PaintPanel acts as canvas for drawing on
 * 
 *-------------------------------------------*/

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.MyImage;
import model.MyLine;
import model.MyOval;
import model.MyRectangle;
import model.PaintObject;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {

	@SuppressWarnings("unused")
	private PaintObject shape;
	private Vector<PaintObject> shapes;
	private boolean draw = false;
	private boolean drag = false;
	private PaintObject currentDrawingObject;
	private Point2D.Double currentDrawingStartPoint;
	private PaintGUI parent;
	private Image image;
	

	/*-----------------
	 * Constructor
	 *----------------*/
	public PaintPanel(PaintGUI parent) {
		try {
			image = ImageIO.read(new File("./images/doge.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.parent = parent;
		this.shapes = new Vector<PaintObject>();
		this.addMouseMotionListener(new MouseActionListener());
		this.addMouseListener(new MouseActionListener());
		this.currentDrawingObject = null;
	}

	/**
	 * Method: drawShapes constructs shape array and repaints
	 * 
	 * @param shapes
	 *            List of PaintObjects
	 */
	public void drawShapes(Vector<PaintObject> shapes) {
		this.shapes = shapes;
		repaint();
	}

	/**
	 * Method: paintComponent
	 * 
	 * @param g
	 *            Graphics
	 * @return none
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;	
		drawShapesOnMe(g2, shapes);
		

	}

	/**
	 * Method: drawShapeOnMe a helper method for paint component goes through
	 * the list of PaintObject shapes and draws them
	 * 
	 * @param g2
	 *            Graphics 2D
	 * @param shapes
	 *            List of PaintObject
	 */
	private void drawShapesOnMe(Graphics2D g2, List<PaintObject> shapes) {
		
		if(shapes != null) {
			for (PaintObject shape : shapes)
				if(shape != null) {
					shape.draw(g2);
				}
		}
			
		if(currentDrawingObject != null) {
			currentDrawingObject.drawGhost(g2);
		}

	}
	
	private PaintObject getPaintObject(Point2D.Double start, Point2D.Double end, Color color) {
		Class<?> type = parent.getSelectedShape();
		PaintObject retval = null;
		
		if(type == MyOval.class)
			retval = new MyOval(start, end, color);
		else if(type == MyLine.class)
			retval = new MyLine(start, end, color);
		else if(type == MyImage.class)
			retval = new MyImage(start, end, image);
		else if(type == MyRectangle.class)
			retval = new MyRectangle(start, end, color);
		
		return retval;
	}
	
	private class MouseActionListener implements MouseMotionListener, MouseListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			drag = true;
			if(draw) {							
				currentDrawingObject = getPaintObject(currentDrawingStartPoint, new Point2D.Double(e.getX(), e.getY()), parent.getColor());
				repaint();
			}
		
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(draw) {				
				currentDrawingObject = getPaintObject(currentDrawingStartPoint, new Point2D.Double(e.getX(), e.getY()), parent.getColor());
				repaint();
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			draw = !draw;
			currentDrawingStartPoint = new Point2D.Double(e.getX(), e.getY());
			
			if(!draw) {
				addShapeToList();
			}
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(drag) {
				draw = false;
				addShapeToList();
			}
			drag = false;
		}

	
		private void addShapeToList() {
			if(currentDrawingObject != null) {
				shapes.add(currentDrawingObject);
			}
			currentDrawingObject = null;
			repaint();
		}
	}

}
