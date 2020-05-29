package bomberman.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import bomberman.server.Msg;

public class Client {
	private Socket clientSocket;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    
    public void connect(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        os = new ObjectOutputStream(clientSocket.getOutputStream());
        is = new ObjectInputStream(clientSocket.getInputStream());
    }
    
    public Msg getMessage() throws ClassNotFoundException, IOException {
		return (Msg) is.readObject();
    }
    
    public void sendMessage(Msg message) throws IOException {
    	os.writeObject(message);
    }
    
    public void disconnect() throws IOException {
		clientSocket.close();
    }
}
