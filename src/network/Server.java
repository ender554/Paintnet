package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import model.PaintObject;

public class Server {

	public static final int SERVER_PORT = 8001;

	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
	// current list of shapes
	private static Vector<PaintObject> shapes;
	
	public static void main(String[] args) throws IOException {
		sock = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);
		
		
		while (true) {
			Socket s = sock.accept();
			

			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

			
			clients.add(os);
			new ClientHandler(is, clients).start();			
			os.writeObject(shapes);
			
			System.out.println("Accepted a new connection from " + s.getInetAddress());
		}
	}
	
	public static void setPaintObjects(Vector<PaintObject> shapes) {
		Server.shapes = shapes;
	}
	
}

class ClientHandler extends Thread {
	private List<ObjectOutputStream> clients;
	private ObjectInputStream is;
	Vector<PaintObject> paintObjects = null;
	
	public ClientHandler(ObjectInputStream is, List<ObjectOutputStream> clients) {
		this.clients = clients;
		this.is = is;
	}
	
	@Override
	public void run() {

		while(true) {			
			try {
				paintObjects = (Vector<PaintObject>) is.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Client closed connection");				
				break;
			}
			
			Server.setPaintObjects(paintObjects);
			writePaintObjectsToClients();
			
		}
	}
	
	private void writePaintObjectsToClients() {
		
		List<ObjectOutputStream> clientsToRemove = new ArrayList<ObjectOutputStream>();
		
		
		for(ObjectOutputStream os : clients) {						
			try {				
				os.reset();  // Avoid sending the same serialized object next time.
				os.writeObject(paintObjects);
			} catch (IOException e) {				
				clientsToRemove.add(os);														
			}
		}
		
		clients.removeAll(clientsToRemove);

	}
	
	
}
