package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.MyLine;

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
		canvas.setBackground(Color.GREEN);
		this.add(canvas);
		
		
	}
	/*---------------------
	//@Override
	public void PaintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
	}
	------------------------*/

}
