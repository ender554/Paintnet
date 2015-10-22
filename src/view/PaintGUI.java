/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * PaintGUI is the layout of Netpaint and
 * contains the main method 
 *-------------------------------------------*/

package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import javax.swing.JFrame;

import model.MyImage;
import model.MyLine;
import model.MyOval;
import model.MyRectangle;
import model.PaintObject;

@SuppressWarnings("serial")
public class PaintGUI extends JFrame {

	private Image image;

	// main method creates new PaintGUI and sets visible
	public static void main(String[] args) {
		PaintGUI paintGUI = new PaintGUI();
		paintGUI.setVisible(true);
	}

	/*-----------------
	 * Constructor
	 *----------------*/
	public PaintGUI() {
		MyLine theLine = new MyLine(new Point2D.Double(50, 50), new Point2D.Double(150, 150), Color.BLACK);
		PaintPanel canvas = new PaintPanel(theLine);

		// read in image try catch
		try {
			image = ImageIO.read(new File("./images/doge.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// gui set up
		this.setSize(700, 700);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		canvas.setSize(680, 400);
		canvas.setLocation(0, 0);
		canvas.setBackground(Color.BLACK);
		this.add(canvas);

		// add items to PaintObject list
		List<PaintObject> shapes = new ArrayList<PaintObject>();
		MyLine line = new MyLine(new Point2D.Double(24, 42), new Point2D.Double(500, 500), Color.WHITE);
		shapes.add(line);
		MyOval oval = new MyOval(new Point2D.Double(300, 300), new Point2D.Double(200, 200), Color.BLUE);
		shapes.add(oval);
		MyRectangle rectangle = new MyRectangle(new Point2D.Double(150, 150), new Point2D.Double(25, 25), Color.YELLOW);
		shapes.add(rectangle);
		MyLine line2 = new MyLine(new Point2D.Double(204, 4), new Point2D.Double(500, 500), Color.WHITE);
		shapes.add(line2);
		MyImage drawing = new MyImage(new Point2D.Double(100, 100), new Point2D.Double(10, 10), image);
		shapes.add(drawing);

		// draw images on canvas
		canvas.drawShapes(shapes);

	}

}
