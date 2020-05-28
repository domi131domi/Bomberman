package client;

import java.io.IOException;
import java.net.UnknownHostException;

public class Run {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Client client = new Client();
		client.connect("localhost", 6666);
		//System.out.println(client.getMessage().text);
		client.disconnect();
	}

}
