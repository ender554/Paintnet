package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import model.PaintObject;

public class Server {

	public static final int SERVER_PORT = 9001;

	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
	
	public static void main(String[] args) throws IOException {
		sock = new ServerSocket(SERVER_PORT);
		System.out.println("Server started on port " + SERVER_PORT);

		while (true) {
			Socket s = sock.accept();
			

			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

			
			clients.add(os);
			
			ServerListener sl = new ServerListener(is,clients);
			new ServerListener(is, clients).start();

			
			System.out.println("Accepted a new connection from " + s.getInetAddress());
		}
	}
	public Server() {
		
	}
	
	
	
}

class ServerListener extends Thread {
	private List<ObjectOutputStream> clients;
	private ObjectInputStream is;
	Vector<PaintObject> paintObjects = null;
	
	public ServerListener(ObjectInputStream is, List<ObjectOutputStream> clients) {
		this.clients = clients;
		this.is = is;
	}
	
	@Override
	public void run() {
		
		
		
		while(true) {
			try {
				paintObjects = (Vector<PaintObject>) is.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writePaintObjectsToClients();
			
		}
	}
	
	private void writePaintObjectsToClients() {
		for(ObjectOutputStream os : clients) {
			try {
				os.writeObject(paintObjects);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


