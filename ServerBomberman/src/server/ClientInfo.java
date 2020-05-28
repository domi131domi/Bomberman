package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientInfo {
	private Socket socket;
	private Msg lastMessage;
	private ObjectOutputStream os;
	private ObjectInputStream is;
	
	public ClientInfo(Socket socket) throws IOException {
		this.socket = socket;
		os = new ObjectOutputStream(socket.getOutputStream());
		is = new ObjectInputStream(socket.getInputStream());
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void getMessage() throws ClassNotFoundException, IOException {
		lastMessage = (Msg) is.readObject();
	}

	public void sendMessage() {
		
	}
	
	public void disconnect() throws IOException {
		socket.close();
		is.close();
		os.close();
	}
}
