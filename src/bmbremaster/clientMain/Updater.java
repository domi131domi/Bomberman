package bmbremaster.clientMain;

import java.util.Set;

import bmbremaster.graphics.Assets;
import bmbremaster.server.Handler;
//import bmbremaster.server.Handler;
import bmbremaster.server.Msg;
import bmbremaster.server.Server;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.blocks.Concrete;
import bmbremaster.tiles.players.Player;

public class Updater implements Runnable {
	private boolean running = false;
	private Thread thread;
	
	private Handler handler = new Handler();
	private Player player1;
	private Player player2;
	
	private Server server = new Server();
	private Msg keys, msg;
	private boolean drawBomb1, drawBomb2;
	private String textMsg = "";
	private int port = 6666, numberOfPlayers = 2;
	private Set<Integer> IDs;
	private int player1_id = -1, player2_id = -1;
	

	
	public void run() {
		
		server.start(port);
		waitForPlayers(numberOfPlayers);
		setIDs();
		initMap();
		
		player1 = new Player( 10 + Tiles.TILE_SIZE, 10 + Tiles.TILE_SIZE, 0 ) ;
		player2 = new Player( Assets.WIDTH - 10 - Tiles.TILE_SIZE*2, Assets.HEIGHT - 10 - Tiles.TILE_SIZE*2, 1 ) ;
		
		while(true) {
			if(server.getNumberOfClients() == numberOfPlayers) {
				try {
				msg = new Msg( (int) player1.getX(), (int) player1.getY(), (int) player2.getX(), (int) player2.getY(), textMsg );
				prepareMessage( msg );
				server.broadcast( msg, true);
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
	
	private void prepareMessage( Msg msg ){
		for( Tiles object: handler.getObjectsArray() ) {
			msg.addConcreteCoords(object.getX(), object.getY());
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
		while(server.getNumberOfClients() != number) {
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
	
	private void tick() {
		setKeys(0);
		player1.tick( keys );
		setKeys(1);	
		player2.tick( keys );	
	}
	
	public void initMap() {
		
		int y = Tiles.TILE_SIZE;
		for( int i = 1; i < 12; i+=2 ) {
			for( int j = 1; j < 12; j += 2) {
				handler.addObject( new Concrete( i*y + 10, j*y + 10, Tiles.TILE_SIZE, Tiles.TILE_SIZE) );
				//if( (j == 0 || j == 12) && i != 12 ) {
				//	handler.addObject( new Concrete( (i+1)*y + 10, j*y + 10, Tiles.TILE_SIZE, Tiles.TILE_SIZE) );
				//}
				//if( i == 0 || i == 12 )
				//	j++;
				//else 
				//	j+=2;
			}
		}
	}
	
}
