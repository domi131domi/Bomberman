package bmbremaster.serverMain;

import bmbremaster.clientMain.Updater;
import bmbremaster.server.Server;
import bmbremaster.server.Msg;

public class ServerLauncher {

	public static void main(String[] args) {
		
		Server server = new Server();
		server.start(6666);
		
		server.listenForClients();
		while(server.getNumberOfClients() <= 1) {
			System.out.println("Czekankow");
		}
		server.stopListenForClients();
		System.out.println("Skonczyl sluchac");
		
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		Msg keys;
		while(true) {
			server.broadcast(new Msg(x1, y1, x2, y2), true);
			System.out.println("Wyslano wiadomosci");
			try {
				keys = server.getMessage(0, true);
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
			System.out.println("Odebrano wiadomosci");
			
		}
		
	}

}
