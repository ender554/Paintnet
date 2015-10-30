/*-------------------------------------------
 * Author(s):  Daniel Spence, Joshua Adams
 * PaintGUI is the layout of Netpaint and
 * contains the main method 
 *-------------------------------------------*/

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
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

@SuppressWarnings("serial")
public class PaintGUI extends JFrame {

	@SuppressWarnings("unused")
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


		// read in image try catch
		try {
			image = ImageIO.read(new File("./images/doge.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// gui set up
		layoutGUI();

	}
	
	public void layoutGUI() {
		JScrollPane pane = new JScrollPane();
		PaintPanel canvas = new PaintPanel(this);
		
		this.setSize(1024, 768);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Closed");
				canvas.getClient().close();
				System.exit(0);
			}
		});
		this.setTitle("Netpaint");

		this.setLayout(new BorderLayout());

		canvas.setPreferredSize(new Dimension(2000, 2000));
		canvas.setLocation(0, 0);
		canvas.setBackground(Color.WHITE);
		pane.setViewportView(canvas);

		pane.getVerticalScrollBar().setUnitIncrement(16);

		this.add(pane, BorderLayout.CENTER);

		//added buttons
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

		//color chooser panel set up and function
		final JColorChooser tcc = new JColorChooser();
		tcc.getSelectionModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				color = tcc.getColor();

			}
		});

		this.add(tcc, BorderLayout.SOUTH);

		this.color = Color.BLACK;
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
