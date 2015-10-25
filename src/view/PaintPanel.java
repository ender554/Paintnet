/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * PaintPanel acts as canvas for drawing on
 * 
 *-------------------------------------------*/

package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JPanel;

import model.PaintObject;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {

	@SuppressWarnings("unused")
	private PaintObject shape;
	private List<PaintObject> shapes;
	private boolean draw = false;
	private PaintObject currentDrawingObject;

	/*-----------------
	 * Constructor
	 *----------------*/
	public PaintPanel(PaintObject shape) {
		this.shape = shape;
		this.addMouseMotionListener(new MouseActionListener());
		this.addMouseListener(new MouseActionListener());
	}

	/**
	 * Method: drawShapes constructs shape array and repaints
	 * 
	 * @param shapes
	 *            List of PaintObjects
	 */
	public void drawShapes(List<PaintObject> shapes) {
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
		for (PaintObject shape : shapes)
			shape.draw(g2);

	}
	
	private class MouseActionListener implements MouseMotionListener, MouseListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			if(draw) {
				System.out.println(arg0.getX() + " " + arg0.getY());
				
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			draw = !draw;
			
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
		
	}

}
