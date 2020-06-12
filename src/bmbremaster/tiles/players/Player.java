package bmbremaster.tiles.players;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.server.Handler;
import bmbremaster.server.Msg;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.blocks.Bomb;

public class Player extends Tiles{
	
	public static final int PLAYER_SIZE_X = 58;
	public static final int PLAYER_SIZE_Y = 58;
	public static final int DEFAULT_HEALTH = 100;
	public static final int DEFAULT_SPEED = 8;
	
	private int id;
	private int health;
	private int speed;
	private int bombTime;
	private int speedX = 0, speedY = 0;
	
	private boolean direction;	//left - false; right - true
	private boolean alive;

	
	public Player(int x, int y, int id ) {
		super(x, y, PLAYER_SIZE_X, PLAYER_SIZE_Y);
		health = DEFAULT_HEALTH;
		this.id = id;
		speed = DEFAULT_SPEED;
		this.direction = true;
		this.alive = true;
	}

	public void tick( Msg keys, Handler handler ) {
		if(keys.a) {
			speedX = -speed;
			if(keys.d)
				speedX = 0;
			if(speedY != 0)
				speedX = 0;
			x += speedX;
			x = clamp(x, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
		}
		if(keys.d) {
			speedX = speed;
			if(keys.a)
				speedX = 0;
			else if(speedY != 0)
				speedX = 0;
			x += speedX;
			x = clamp(x, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
		}
		if(keys.w) {
			speedY = -speed;
			if(keys.s)
				speedY = 0;
			if(speedX != 0)
				speedY = 0;
			y += speedY;
			y = clamp(y, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
		}
		if(keys.s) {
			speedY = speed;
			if(keys.w)
				speedY = 0;
			if(speedX != 0)
				speedY = 0;
			y += speedY;
			y = clamp(y, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
		}
		if(!keys.a && !keys.d)
			speedX = 0;
		if(!keys.w && !keys.s)
			speedY = 0;
		
		if(keys.space && bombTime <= 0) {
			bombTime = 120;
			handler.addBomb(new Bomb((int) ( Math.round((float) this.x / (float) Tiles.TILE_SIZE)) * Tiles.TILE_SIZE + 10, (int) ( Math.round((float) this.y / (float) Tiles.TILE_SIZE)) * Tiles.TILE_SIZE + 10, Tiles.TILE_SIZE, Tiles.TILE_SIZE));
		}
		--bombTime;
	}

	@Override
	public void tick(Tiles tile) {}
	
	@Override
	public void render(Graphics g) {

		if( id == 0 )//&& health > 0 ) {
				g.drawImage(Assets.player1, x, y, PLAYER_SIZE_X, PLAYER_SIZE_Y, null );
			//else
			//	g.drawImage(Assets.player1left, x, y, PLAYER_SIZE_X, PLAYER_SIZE_Y, null );
		else if ( id == 1 ) //&& health > 0) {
				g.drawImage(Assets.player2, x, y, PLAYER_SIZE_X, PLAYER_SIZE_Y, null );
			//else
			//	g.drawImage(Assets.player2left, x, y, PLAYER_SIZE_X, PLAYER_SIZE_Y, null );

	}
	
	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
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
	
	public int getSpeedX() {
		return speedX;
	}
	public int getSpeedY() {
		return speedY;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
