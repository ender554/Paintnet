/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * PaintGUI is the layout of Netpaint and
 * contains the main method 
 *-------------------------------------------*/

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.MyImage;
import model.MyLine;
import model.MyOval;
import model.MyRectangle;
import model.PaintObject;

@SuppressWarnings("serial")
public class PaintGUI extends JFrame {

	private Image image;
	private JRadioButton ovalButton;
	private JRadioButton lineButton;
	private JRadioButton rectangleButton;
	private JRadioButton imageButton;
	private ButtonGroup bg;
	private Color color;

	// main method creates new PaintGUI and sets visible
	public static void main(String[] args) {
		PaintGUI paintGUI = new PaintGUI();
		paintGUI.setVisible(true);
	}

	/*-----------------
	 * Constructor
	 *----------------*/
	public PaintGUI() {
		JScrollPane pane = new JScrollPane();
		PaintPanel canvas = new PaintPanel(this);

		// read in image try catch
		try {
			image = ImageIO.read(new File("./images/doge.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// gui set up
		this.setSize(1024, 768);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Netpaint");

		this.setLayout(new BorderLayout());

		canvas.setPreferredSize(new Dimension(2000, 2000));
		canvas.setLocation(0, 0);
		canvas.setBackground(Color.WHITE);
		pane.setViewportView(canvas);
		pane.getVerticalScrollBar().setUnitIncrement(16);
		this.add(pane, BorderLayout.CENTER);

		ovalButton = new JRadioButton("Oval");
		lineButton = new JRadioButton("Line");
		lineButton.setSelected(true);
		rectangleButton = new JRadioButton("Rectangle");
		imageButton = new JRadioButton("Image");

		bg = new ButtonGroup();
		bg.add(ovalButton);
		bg.add(lineButton);
		bg.add(rectangleButton);
		bg.add(imageButton);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(lineButton);
		buttonPanel.add(rectangleButton);
		buttonPanel.add(ovalButton);
		buttonPanel.add(imageButton);
		buttonPanel.setLocation(getWidth() / 2, (int) (getHeight() * .78));
		buttonPanel.setSize(400, 40);
		this.add(buttonPanel, BorderLayout.NORTH);

		final JColorChooser tcc = new JColorChooser();
		tcc.getSelectionModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				color = tcc.getColor();

			}
		});

		this.add(tcc, BorderLayout.SOUTH);

		this.color = tcc.getColor();

	}

	public Class<?> getSelectedShape() {
		Class<?> type;
		type = Object.class;

		if (ovalButton.isSelected())
			type = MyOval.class;
		else if (lineButton.isSelected())
			type = MyLine.class;
		else if (rectangleButton.isSelected())
			type = MyRectangle.class;
		else if (imageButton.isSelected())
			type = MyImage.class;

		return type;
	}
	public Color getColor(){
		return this.color;
	}

}
