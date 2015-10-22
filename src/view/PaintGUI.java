package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.MyLine;
import model.MyOval;
import model.MyRectangle;
import model.PaintObject;

public class PaintGUI extends JFrame{
	
	public static void main(String[] args){
		PaintGUI paintGUI = new PaintGUI();
		paintGUI.setVisible(true);
	}
	public PaintGUI(){
		MyLine theLine = new MyLine(new Point2D.Double(50, 50), new Point2D.Double(150, 150), Color.BLACK);
		PaintPanel canvas = new PaintPanel(theLine);
		
		this.setSize(700, 700);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		canvas.setSize(680, 400);
		canvas.setLocation(0, 0);
		canvas.setBackground(Color.BLACK);
		this.add(canvas);
		
		List<PaintObject> shapes = new ArrayList<PaintObject>();
		MyLine line = new MyLine(new Point2D.Double(24,42), new Point2D.Double(500, 500), Color.WHITE);
		shapes.add(line);
		MyOval oval = new MyOval(new Point2D.Double(300,300), new Point2D.Double(200,200), Color.BLUE);
		shapes.add(oval);
		MyRectangle rectangle = new MyRectangle(new Point2D.Double(150,150), new Point2D.Double(25, 25), Color.YELLOW);
		shapes.add(rectangle);
		
		canvas.drawShapes(shapes);
		
		
		
		
	}
	/*---------------------
	//@Override
	public void PaintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
	}
	------------------------*/

}
