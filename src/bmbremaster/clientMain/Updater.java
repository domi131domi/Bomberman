package bmbremaster.clientMain;

import java.util.Set;

import bmbremaster.graphics.Assets;
import bmbremaster.server.Msg;
import bmbremaster.server.Server;

public class Updater implements Runnable {
	private boolean running = false;
	private Thread thread;
	
	private  Server server = new Server();
	private  Msg keys, msg;
	private  int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	private  boolean drawBomb1, drawBomb2;
	private String textMsg = "";
	private int port = 6666, numberOfPlayers = 2;
	private Set<Integer> IDs;
	private int player1_id = -1, player2_id = -1;
	
	public void run() {
		
		server.start(port);
		server.listenForClients();
		waitForPlayers(numberOfPlayers);
		setIDs();	
		
		while(true) {
			if(server.getNumberOfClients() == numberOfPlayers) {
				try {
				server.broadcast(new Msg(x1, y1, x2, y2, drawBomb1, drawBomb2, textMsg), true);
				textMsg = "";
				tick();
				} catch(Exception e) {			
				}
			} else {
				server.stop();
				server.start(port);
				waitForPlayers(numberOfPlayers);
				setIDs();
			}
		}
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e ) {
			e.printStackTrace();
		}
	}
	
	private void setIDs() {
		IDs = server.getIDs();
		for(int id : IDs) {
			if(player1_id == -1)
				player1_id = id;
			else
				player2_id = id;
		}
	}
	
	private void waitForPlayers(int number) {
		server.listenForClients();
		while(server.getNumberOfClients() < number) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		server.stopListenForClients();
	}
	
	private void setKeys(int number) {
		int id;
		if(number == 0)
			id = player1_id;
		else if(number == 1)
			id = player2_id;
		else 
			return;
		try {
			msg = server.getMessage(id, true);
			while(msg.isTextMsg()) {
				textMsg += msg.getText();
				msg = server.getMessage(id, true);
			}
			keys = msg;
		} catch (Exception e) {
		}
	}
	
	private int clamp( int var, int min, int max ) {
		if( var >= max )
			return var = max;
		else if ( var <= min )
			return var = min;
		else
			return var;
	}
	
	private void tick() {
		setKeys(0);
		if(keys.a) {
			x1 -= 5;
			x1 = clamp(x1, 10 + Assets.tileSize, 800 - Assets.tileSize*2 - 10);
		}
		if(keys.d) {
			x1 += 5;
			x1 = clamp(x1, 10 + Assets.tileSize, 800 - Assets.tileSize*2 - 10);
		}
		if(keys.w) {
			y1 -= 5;
			y1 = clamp(y1, 10 + Assets.tileSize, 800 - Assets.tileSize*2 - 10);
		}
		if(keys.s) {
			y1 += 5;
			y1 = clamp(y1, 10 + Assets.tileSize, 800 - Assets.tileSize*2 - 10);
		}
		if(keys.space) 
			drawBomb1 = true;
		else 
			drawBomb1 = false;
		
		setKeys(1);
		if(keys.a) {
			x2 -= 5;
			x2 = clamp(x2, 10 + Assets.tileSize, 800 - Assets.tileSize*2 - 10);
		}
		if(keys.d) {
			x2 += 5;
			x2 = clamp(x2, 10 + Assets.tileSize, 800 - Assets.tileSize*2 - 10);
		}
		if(keys.w) {
			y2 -= 5;
			y2 = clamp(y2, 10 + Assets.tileSize, 800 - Assets.tileSize*2 - 10);
		}
		if(keys.s) {
			y2 += 5;
			y2 = clamp(y2, 10 + Assets.tileSize, 800 - Assets.tileSize*2 - 10);
		}
		if(keys.space) 
			drawBomb2 = true;
		else 
			drawBomb2 = false;
		
		
		
	}
	
}
