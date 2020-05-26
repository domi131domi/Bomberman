package Server;

import java.net.InetAddress;

public class ClientInfo {
	
	private InetAddress adress;
	private int port;
	private String name;
	private int id;
	
	public ClientInfo(String name, int id, InetAddress adress, int port) {
		this.name = name;
		this.id = id;
		this.adress = adress;
		this.port = port;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPort() {
		return port;
	}
	
	public InetAddress getAdress() {
		return adress;
	}
	
	public int getId() {
		return id;
	}
}
