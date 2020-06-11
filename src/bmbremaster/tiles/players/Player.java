package bmbremaster.tiles.players;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.server.Msg;
import bmbremaster.tiles.Tiles;

public class Player extends Tiles{
	
	public static final int PLAYER_SIZE_X = 58;
	public static final int PLAYER_SIZE_Y = 58;
	public static final int DEFAULT_HEALTH = 100;
	public static final int DEFAULT_SPEED = 4;
	
	private int id;
	private int health;
	private int speed;
	
	public Player(int x, int y, int id ) {
		super(x, y, PLAYER_SIZE_X, PLAYER_SIZE_Y);
		health = DEFAULT_HEALTH;
		this.id = id;
		speed = DEFAULT_SPEED;
	}

	public void tick( Msg keys ) {
		if(keys.a) {
			x -= this.speed;
			x = clamp(x, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
		}
		if(keys.d) {
			x += this.speed;
			x = clamp(x, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
		}
		if(keys.w) {
			y -= this.speed;
			y = clamp(y, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
		}
		if(keys.s) {
			y += this.speed;
			y = clamp(y, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
		}
	}

	@Override
	public void tick() {}
	
	@Override
	public void render(Graphics g) {	
		if( id == 0 )
			g.drawImage(Assets.player1, (int)x, (int)y, PLAYER_SIZE_X, PLAYER_SIZE_Y, null );
		else if ( id == 1 )
			g.drawImage(Assets.player2, (int)x, (int)y, PLAYER_SIZE_X, PLAYER_SIZE_Y, null );
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void onCollision(Player player) {
		
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
