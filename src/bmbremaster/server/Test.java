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
		
		
		Thread listenThread = new Thread("ChatProgram Listener") {
			public void run() {
				Msg mess1;
		while(true) {
			try {
				mess1 = serv.getMessage(1, true);
				serv.broadcast(mess1, true);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch(NullPointerException e2) {
				
			}
		}
			}}; listenThread.start();
		
			while(true) {
				Msg mess0;
				try {
					mess0 = serv.getMessage(0, true);
					serv.broadcast(mess0, true);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
					
			}	
			
			
	}

}
