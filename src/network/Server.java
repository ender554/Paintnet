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
	private static int numConnected;
	
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
	// current list of shapes
	private static Vector<PaintObject> shapes;
	
	public static void main(String[] args) throws IOException {
		sock = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);
		numConnected = 0;
		
		
		while (true) {
			List<ObjectOutputStream> oneClient = new ArrayList<ObjectOutputStream>();
			
			Socket s = sock.accept();
			numConnected ++;
			

			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

			oneClient.add(os);
			clients.add(os);
			ClientHandler cl = new ClientHandler(is, clients);
			cl.start();
			os.writeObject(shapes);
			
			System.out.println("Accepted a new connection from " + s.getInetAddress());
			
		}
	}
	
	public static void setPaintObjects(Vector<PaintObject> shapes) {
		Server.shapes = shapes;
	}
	public static int getNumClients(){
		return numConnected;
	}
	public static void setNumClients(int numClients) {
		Server.numConnected = numClients;
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
		//Server.getNumClients() <= numConnected
		
		System.out.println("Thread: " + this.getId() + " running");
		System.out.println("number of clients: " + clients.size());
		
		while(true) {			
			//System.out.println(Server.getNumClients() + " " + numConnected);
			
			System.out.println("Thread " + this.getId() + ": " + clients);
			
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
		
		try {
			is.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Thread stopping: " + this.getId());
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
