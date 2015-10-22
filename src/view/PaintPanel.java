package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import model.PaintObject;

public class PaintPanel extends JPanel{

	private PaintObject shape;
	private List<PaintObject> shapes;
	
	public PaintPanel(PaintObject shape){
		this.shape = shape;
		
	}
	
	public void drawShapes(List<PaintObject> shapes) {
		this.shapes = shapes;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g; 
		drawShapesOnMe(g2, shapes);
		
	}
	private void drawShapesOnMe(Graphics2D g2, List<PaintObject> shapes){
		for(PaintObject shape : shapes)
			shape.draw(g2);

	}
	
}

