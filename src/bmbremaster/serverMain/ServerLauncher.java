package bmbremaster.serverMain;

import bmbremaster.server.Msg;
import bmbremaster.server.Server;
import bmbremaster.tiles.Tiles;

public class ServerLauncher {
	
	private static Server server = new Server();
	private static Msg keys;
	private static int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	private static boolean drawBomb1, drawBomb2;
	

	public static void main(String[] args) {
		
		server.start(6666);
		waitForPlayers(2);
		
		
		while(true) {
			server.broadcast(new Msg(x1, y1, x2, y2, drawBomb1, drawBomb2), true);
			setKeys(0);
			try {
				keys = server.getMessage(1, true);
				if(keys.a) {
					x2 -= 5;
					x2 = clamp(x2, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
				}
				if(keys.d) {
					x2 += 5;
					x2 = clamp(x2, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
				}
				if(keys.w) {
					y2 -= 5;
					y2 = clamp(y2, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
				}
				if(keys.s) {
					y2 += 5;
					y2 = clamp(y2, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
				}
				if(keys.space) 
					drawBomb2 = true;
				else 
					drawBomb2 = false;
					
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
			if(keys.a) {
				x1 -= 5;
				x1 = clamp(x1, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
			}
			if(keys.d) {
				x1 += 5;
				x1 = clamp(x1, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
			}
			if(keys.w) {
				y1 -= 5;
				y1 = clamp(y1, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
			}
			if(keys.s) {
				y1 += 5;
				y1 = clamp(y1, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
			}
			if(keys.space) 
				drawBomb1 = true;
			else 
				drawBomb1 = false;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static int clamp( int var, int min, int max ) {
		if( var >= max )
			return var = max;
		else if ( var <= min )
			return var = min;
		else
			return var;
	}

}
