package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.SwingUtilities;

import model.PaintObject;
import view.PaintGUI;
import view.PaintPanel;

public class Client {
	
	private static final String ADDRESS = "localhost";
	Socket socket;	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	PaintPanel pp;
	
	static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {					
					new PaintGUI().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	public Client(PaintPanel pp) {
		this.pp = pp;
	}
	
	private void openConnection() {
		/* Our server is on our computer, but make sure to use the same port. */
		try {
			// TODO 6: Connect to the Server
			socket = new Socket(ADDRESS, Server.SERVER_PORT);
			
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("Connected to server at " + ADDRESS + ":" + Server.SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendShapes(Vector<PaintObject> shapes) {
		try {
			oos.writeObject(shapes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class ServerListener extends Thread {

		@Override
		public void run() {
			// TODO 9: Repeatedly accept String objects from the server and add
			// them to our model.
			while(true) {
				Vector<PaintObject> paintObjects = null;
				try {
					paintObjects = (Vector<PaintObject>) ois.readObject();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(paintObjects != null)
					pp.drawShapes(paintObjects);
			}
		}
	}
}
