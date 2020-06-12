package bmbremaster.server;

import java.util.ArrayList;

import bmbremaster.graphics.Assets;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.blocks.Bomb;
import bmbremaster.tiles.blocks.Fire;
import bmbremaster.tiles.players.Player;

public class Handler {
	
	private Player player1;
	private Player player2;
	private ArrayList<Tiles> blocks = new ArrayList<Tiles>(50);
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>(20);
	private ArrayList<Fire> fireArray = new ArrayList<Fire>(30);
	
	public void tick() {
		//ticks of all objects
		for(Tiles object : blocks) {
			object.onCollision(player1);
			object.onCollision(player2);
		}
		for(Bomb bomb : bombs) {
			bomb.tick(null);
			if(bomb.getTimeLeft() <= 0) {
				bombs.remove(bomb);
				fireArray.add(new Fire(bomb.getX() - Tiles.TILE_SIZE*2, bomb.getY(), Tiles.TILE_SIZE*2, Tiles.TILE_SIZE, 0));
				fireArray.add(new Fire(bomb.getX() + Tiles.TILE_SIZE, bomb.getY(), Tiles.TILE_SIZE*2, Tiles.TILE_SIZE, 1));
				fireArray.add(new Fire(bomb.getX(), bomb.getY() - Tiles.TILE_SIZE * 2 , Tiles.TILE_SIZE, Tiles.TILE_SIZE * 2, 2));
				fireArray.add(new Fire(bomb.getX(), bomb.getY() + Tiles.TILE_SIZE, Tiles.TILE_SIZE, Tiles.TILE_SIZE * 2, 3));
			}
		}
		for(Fire fire : fireArray) {
			for(Tiles block : blocks) {
				fire.onCollision(block);
			}
			
			fire.tick(null);
			if(fire.getTimeLeft() <= 0)
				fireArray.remove(fire);
		}
	}
	
	public void addObject( Tiles object ) {
		blocks.add(object);
	}
	
	public void removeObject( Tiles object ) {
		blocks.remove(object);
	}
	
	public ArrayList<Tiles> getBlocksArray(){
		return blocks;
	}
	
	public void initPlayers() {
		player1 = new Player( 10 + Tiles.TILE_SIZE, 10 + Tiles.TILE_SIZE, 0 ) ;
		player2 = new Player( Assets.WIDTH - 10 - Tiles.TILE_SIZE*2, Assets.HEIGHT - 10 - Tiles.TILE_SIZE*2, 1 );
	}
	
	public Player getPlayer(int number) {
		if(number == player1.getId())
			return player1;
		else if(number == player2.getId())
			return player2;
		else
			return null;
	}

	public ArrayList<Bomb> getBombs() {
		return this.bombs;
	}

	public void addBomb(Bomb bomb) {
		this.bombs.add(bomb);
	}
	
	public ArrayList<Fire> getFireArray() {
		return this.fireArray;
	}

	public void addFire(Fire fire) {
		this.fireArray.add(fire);
	}
	
	
}
