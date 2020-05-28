package server;

public class Test {

	public static void main(String[] args) {
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
		//serv.disconnect(0);
		serv.stopListenForClients();
		serv.stop();
		//serv.stop();
		
	}

}
