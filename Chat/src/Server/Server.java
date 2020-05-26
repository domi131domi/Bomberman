package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {
	
	private static DatagramSocket socket;
	private static int ClientID = 0;
	private static boolean running;
	private static ArrayList<ClientInfo> clients = new ArrayList<ClientInfo>();
	
	public static void start(int port) {
		try {
			
			socket = new DatagramSocket(port);
			
			running = true;
			listen();
			System.out.println("Sever Started on Port, " + port);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void broadcast(String message) {
		for(ClientInfo Info : clients) {
			send(message, Info.getAdress(), Info.getPort());
		}
	}
	
	private static void send(String message, InetAddress adress, int port) {
		try {			
			message += "\\e";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, adress, port);
			socket.send(packet);
			System.out.println("Send Message To, "+adress.getHostAddress()+":"+port);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void listen() {
		Thread listenThread = new Thread("ChatProgram Listener") {
			public void run() {
				try {
					while(running) {
						
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);
						
						String message = new String(data);
						message = message.substring(0, message.indexOf("\\e"));
					
						//manage message
						if(!isCommand(message, packet)) {
						broadcast(message);
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}; listenThread.start();
	}
	
	//command list
	//\con:[name] -> connect
	//\dis:[id] -> disconnect
	//
	private static boolean isCommand(String message, DatagramPacket packet) {
		if(message.startsWith("\\con:")) {
			
			String name = message.substring(message.indexOf(":")+1);
			clients.add(new ClientInfo(name, ClientID++, packet.getAddress(), packet.getPort()));
			
			broadcast("User: "+name+" connected.");
			return true;
		}
		
		return false;
	}
	
	public static void stop() {
		running = false;
	}
}
