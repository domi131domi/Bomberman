package bmbremaster.clientMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

import bmbremaster.server.Handler;
import bmbremaster.server.Msg;
import bmbremaster.server.Server;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.blocks.Bricks;
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
	private boolean keepPlaying = true;
	private int newGameTime = 120;
	private String nick1 = "";
	private String nick2 = "";
	private int wins1 = 0;
	private int wins2 = 0;
	private final int mapSize = 11;
	private int [][] map = new int[mapSize][mapSize];
	private String mapName;
	
	public Updater(int port, String mapName) {
		this.port = port;
		this.mapName = mapName;
	}

	
	public void run() {
		
		server.start(port);
		waitForPlayers(numberOfPlayers);
		setIDs();
		readMap();
		initMap();
		

		while(running) {
			if(server.getNumberOfClients() == numberOfPlayers) {
				try {
				msg = new Msg( handler.getPlayer(0).getX(), handler.getPlayer(0).getY(), handler.getPlayer(1).getX(), handler.getPlayer(1).getY(), 
							   handler.getPlayer(0).getHealth(), handler.getPlayer(1).getHealth(), handler.getPlayer(0).getDirection(), handler.getPlayer(1).getDirection(), textMsg );
				prepareMessage( msg );
				server.broadcast( msg, true);
				textMsg = "";
				tick();
				if(!keepPlaying) {
					if(newGameTime < 0) {
						initMap();
						newGameTime = 120;
						keepPlaying = true;
					}
					newGameTime--;
				}
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
				
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void prepareMessage( Msg msg ){
		for( Tiles object : handler.getBlocksArray() ) {
			if(object.getKind() == 2)
				msg.addConcreteCoords(object.getX(), object.getY());
			else if(object.getKind() == 1)
				msg.addBrickCoords(object.getX(), object.getY());
		}
		for(Tiles bomb : handler.getBombs() )
			msg.addBombCoords(bomb.getX(), bomb.getY());
		for(Fire fire: handler.getFireArray())
			msg.addFireCoords(fire.getX(), fire.getY(), fire.getWidth(), fire.getHeight(), fire.getDirection());
		msg.setNick1(nick1);
		msg.setNick2(nick2);
		msg.setWins1(wins1);
		msg.setWins2(wins2);
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
		server.stop();
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
				if(msg.getText().startsWith("//setnick")) {
					if(id == player1_id) {
						nick1 = msg.getText().substring(10);
						if(nick1.equals("Default"))
							nick1 = "Player1";
					} else if(id == player2_id) {
						nick2 = msg.getText().substring(10);
						if(nick2.equals("Default"))
							nick2 = "Player2";
					}
				} else {
					textMsg += msg.getText();
				}
				msg = server.getMessage(id, true);					
			}
			keys = msg;
		} catch (Exception e) {
		}
	}
	
	private void tick() {
		setKeys(0);
		if(keepPlaying)
			handler.getPlayer(0).tick( keys, handler );
		setKeys(1);	
		if(keepPlaying) {
			handler.getPlayer(1).tick( keys, handler );
			handler.tick();			
			if(handler.getPlayer(1).getHealth() <= 0) {
				textMsg += new String("\nPlayer 1 Won!\n");
				wins1++;
				keepPlaying = false;
			}
			if(handler.getPlayer(0).getHealth() <= 0) {
				textMsg += new String("\nPlayer 2 Won!\n");
				wins2++;
				keepPlaying = false;
			}
		}
	}
	
	public void readMap() {
		handler = new Handler();
		File myObj = new File(mapName + ".txt");
		try {
			Scanner myReader = new Scanner(myObj);
			int y = 0;
			while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        for(int x = 0; x < data.length(); x++) {
		        	if(x < mapSize)
		        		map[y][x] = data.charAt(x);
		        }
		        y++;
		      }
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono mapy");
		}
	}
	
	public void initMap() {
		int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
		for(int j = 0; j < mapSize; j++)
			for(int i = 0; i < mapSize; i++) {
				if(map[i][j] == '1')
					handler.addObject( new Concrete(10 + Tiles.TILE_SIZE + Tiles.TILE_SIZE * j, 10 + Tiles.TILE_SIZE + Tiles.TILE_SIZE * i,Tiles.TILE_SIZE, Tiles.TILE_SIZE));
				if(map[i][j] == '2')
					handler.addObject( new Bricks(10 + Tiles.TILE_SIZE + Tiles.TILE_SIZE * j, 10 + Tiles.TILE_SIZE + Tiles.TILE_SIZE * i,Tiles.TILE_SIZE, Tiles.TILE_SIZE));
				if(map[i][j] == '3') {
					if(x1 == -1) {
						x1 = i;
						y1 = j;
					} else if(x2 == -1) { 
						x2 = i;
						y2 = j;
					}
				}	
			}
		handler.initPlayers(10 + Tiles.TILE_SIZE + Tiles.TILE_SIZE * x1, 10 + Tiles.TILE_SIZE + Tiles.TILE_SIZE * y1, 10 + Tiles.TILE_SIZE + Tiles.TILE_SIZE * x2, 10 + Tiles.TILE_SIZE + Tiles.TILE_SIZE * y2);
		
	}
	
}
