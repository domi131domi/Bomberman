package bmbremaster.serverMain;

import bmbremaster.server.Server;
import bmbremaster.server.Msg;

public class ServerLauncher {
	
	private static Server server = new Server();
	private static Msg keys;
	private static int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	

	public static void main(String[] args) {
		
		server.start(6666);
		waitForPlayers(2);
		
		
	
		
		
		while(true) {
			server.broadcast(new Msg(x1, y1, x2, y2), true);
			setKeys(0);
			try {
				keys = server.getMessage(1, true);
				if(keys.a)
					x2 -= 5;
				if(keys.d)
					x2 += 5;
				if(keys.w)
					y2 -= 5;
				if(keys.s)
					y2 += 5;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static void waitForPlayers(int number) {
		server.listenForClients();
		while(server.getNumberOfClients() != number) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		server.stopListenForClients();
	}
	
	static void setKeys(int number) {
		try {
			keys = server.getMessage(number, true);
			if(keys.a)
				x1 -= 5;
			if(keys.d)
				x1 += 5;
			if(keys.w)
				y1 -= 5;
			if(keys.s)
				y1 += 5;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
