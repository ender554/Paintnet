package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.PaintObject;

public class PaintPanel extends JPanel{

	private PaintObject shape;
	
	public PaintPanel(PaintObject shape){
		this.shape = shape;
		
	}
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g; 
		drawShapesOnMe(g2, shape);
		
	}
	private void drawShapesOnMe(Graphics2D g2, PaintObject shape){
		shape.draw(g2);
	}
	
}

