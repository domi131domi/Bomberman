package bomberman.server;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server serv = new Server();
		serv.start(6666);
		serv.listenForClients();
		while(serv.getNumberOfClients() != 2) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		serv.stopListenForClients();
		
		Thread listenThread = new Thread("listenForClients") {
			public void run() {
				while(true) {
					Msg mess;
					try {
						mess = serv.getMessage(1, true);
						serv.broadcast(mess, true);		
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}}; listenThread.start();
		
		while(true) {
			Msg mess = serv.getMessage(0, true);
			serv.broadcast(mess, true);		
		}
		
		
	}

}
