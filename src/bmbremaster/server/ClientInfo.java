package bomberman.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientInfo {
	private Socket socket;
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
	
	public Msg getMessage() throws ClassNotFoundException, IOException {
		return (Msg) is.readObject();
	}

	public void sendMessage(Msg message) throws IOException {
		os.writeObject(message);
	}
	
	public void disconnect() throws IOException {
		socket.close();
		is.close();
		os.close();
	}
}
