package bmbremaster.clientMain;

import java.util.Set;

import bmbremaster.server.Handler;
import bmbremaster.server.Msg;
import bmbremaster.server.Server;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.blocks.Concrete;
import bmbremaster.tiles.blocks.Fire;

public class Updater implements Runnable {
	private boolean running = false;
	private Thread thread;
	
	private Handler handler;
	
	private Server server = new Server();
	private Msg keys, msg;
	private String textMsg = "";
	private int port = 6666, numberOfPlayers = 2;
	private Set<Integer> IDs;
	private int player1_id = -1, player2_id = -1;
	

	
	public void run() {
		
		server.start(port);
		waitForPlayers(numberOfPlayers);
		setIDs();
		initMap();
		
		handler.initPlayers();

		while(true) {
			if(server.getNumberOfClients() == numberOfPlayers) {
				try {
				msg = new Msg( handler.getPlayer(0).getX(), handler.getPlayer(0).getY(), handler.getPlayer(1).getX(), handler.getPlayer(1).getY(), textMsg );
				prepareMessage( msg );
				server.broadcast( msg, true);
				textMsg = "";
				tick();
				} catch(Exception e) {			
				}
			} else {
				try {
				server.stop();
				player1_id = -1;
				player2_id = -1;
				server.start(port);
				waitForPlayers(numberOfPlayers);
				setIDs();
				initMap();
				
				handler.initPlayers();
				
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void prepareMessage( Msg msg ){
		for( Tiles object: handler.getBlocksArray() ) {
			msg.addConcreteCoords(object.getX(), object.getY());
		}
		for(Tiles bomb : handler.getBombs() )
			msg.addBombCoords(bomb.getX(), bomb.getY());
		for(Fire fire: handler.getFireArray())
			msg.addFireCoords(fire.getX(), fire.getY(), fire.getWidth(), fire.getHeight(), fire.getDirection());
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
		handler.getPlayer(0).tick( keys, handler );
		setKeys(1);	
		handler.getPlayer(1).tick( keys, handler );
		handler.tick();
	}
	
	public void initMap() {
		handler = new Handler();
		int y = Tiles.TILE_SIZE;
		for( int i = 2; i < 11; i+=2 ) {
			for( int j = 2; j < 11; j += 2) {
				handler.addObject( new Concrete( i*y + 10, j*y + 10, Tiles.TILE_SIZE, Tiles.TILE_SIZE) );
			}
		}
	}
	
}
