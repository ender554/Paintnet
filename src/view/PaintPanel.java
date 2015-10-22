/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * PaintPanel acts as canvas for drawing on
 * 
 *-------------------------------------------*/

package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import model.PaintObject;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {

	@SuppressWarnings("unused")
	private PaintObject shape;
	private List<PaintObject> shapes;

	/*-----------------
	 * Constructor
	 *----------------*/
	public PaintPanel(PaintObject shape) {
		this.shape = shape;

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

}
