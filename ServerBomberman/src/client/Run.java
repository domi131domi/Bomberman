package client;

public class Run {

	public static void main(String[] args) {
		Client client = new Client();
		client.connect("localhost", 6666);
	}

}
