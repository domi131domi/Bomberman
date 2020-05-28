package server;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		Server serv = new Server();
		serv.start(6666);
		serv.listenForClients();
		while(serv.getNumberOfClients() != 1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		serv.stopListenForClients();
		serv.sendMessage(0, new Msg("siema"), true);
		serv.stop();
		//serv.stop();
		
	}

}
