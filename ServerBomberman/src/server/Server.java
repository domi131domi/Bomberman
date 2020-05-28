package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import server.ClientInfo;

public class Server {
	private ServerSocket serverSocket;
	private HashMap<Integer, ClientInfo> clients = new HashMap<Integer, ClientInfo>();
	Set<Integer> IDs = new HashSet<Integer>();
	private boolean running = false;
	private boolean openForConnections;
    private int indexID;
    
    public void start(int port) {
    	try {
    	serverSocket = new ServerSocket(port);
    	indexID = 0;
    	running = true;
    	openForConnections = false;
    	System.out.println("Server Started on port: " + port);
    	} catch(IOException e) {
    		System.out.println("Couldnt start server");
    		e.printStackTrace();
    	}
    }
    
    public void listenForClients() {
    	if(!openForConnections && running) {
    		openForConnections = true;
    		System.out.println("Waiting for connections");
    		Thread listenThread = new Thread("listenForClients") {
    			public void run() {
    				while(openForConnections) {
    					try {
    						clients.put(indexID, new ClientInfo(serverSocket.accept()));
    						IDs.add(indexID);
    						System.out.println("Client connected. ID: " + indexID);
    						++indexID;
    					} catch(IOException e) {
    						if(openForConnections)
    							System.out.println("Couldnt connect client.");
    					}
    			
    				}
    	    		System.out.println("Server doesnt wait for connetions anymore.");
    			}
    		}; listenThread.start();
    	}
    }
    
    public int getNumberOfClients() {
    	return clients.size();
    }
    
    public void stopListenForClients() {
    	openForConnections = false;
    }
    
    public void stop() {
    	stopListenForClients();
    	try {
    		for(int id : IDs) {
    			disconnect(id);
    		}
    		System.out.println("All Clients disconnected");
    		serverSocket.close();
    		running = false;
    		System.out.println("Server stopped.");
    	} catch(IOException e) {
    		System.out.println("Couldnt close server.");
    		e.printStackTrace();
    	}
    }
    
    public void disconnect(int ID) {
    	try {
			clients.get(ID).disconnect();
	    	clients.remove(ID);
	    	IDs.remove(ID);
	    	System.out.println("Client removed from the server. ID: " + ID);
		} catch (IOException e) {
			System.out.println("Cannot remove client. ID: " + ID);
		}

    }
    
    
    /*
    public boolean isRunning() {
    	return running;
    }
    */
}
