/*--------------------------------------------
 * Author(s): Daniel SPence, Joshua Adams
 * client side of network
 * 
 */

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
	private ServerListener sl;
	private volatile boolean running;

	public static void main(String[] args) {
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

	//constructor
	public Client(PaintPanel pp) {
		this.pp = pp;
		openConnection();

		running = true;
		sl = new ServerListener();
		sl.start();

	}

	//closes connection
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		running = false;
	}

	//opens connection to the server 
	private void openConnection() {
		/* Our server is on our computer, but make sure to use the same port. */
		try {
			socket = new Socket(ADDRESS, Server.SERVER_PORT);

			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("Connected to server at " + ADDRESS + ":" + Server.SERVER_PORT);
		} catch (IOException e) {
			System.out.println("Failed to connect to server at " + ADDRESS + ":" + Server.SERVER_PORT);
			System.out.println("Exiting...");
			System.exit(1);
		}

	}

	//sends shapes to the server
	public void sendShapes(Vector<PaintObject> shapes) {
		try {
			oos.reset();
			oos.writeObject(shapes);
		} catch (IOException e) {
			System.out.println("Server closed connection");
			System.exit(1);
		}
	}

	//receives the shapes
	@SuppressWarnings("unchecked")
	public Vector<PaintObject> receiveShapes() {
		try {
			return (Vector<PaintObject>) ois.readObject();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return new Vector<PaintObject>();
	}

	

	//listener for server
	private class ServerListener extends Thread {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {

			while (running) {
				Vector<PaintObject> paintObjects = null;
				try {
					paintObjects = (Vector<PaintObject>) ois.readObject();
				} catch (ClassNotFoundException | IOException e) {
					break;
				}
				if (paintObjects != null)
					pp.drawShapes(paintObjects);
			}
		}

	}
}
