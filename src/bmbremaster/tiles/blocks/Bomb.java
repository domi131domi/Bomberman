package bmbremaster.tiles.blocks;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.players.Player;

public class Bomb extends Tiles {
	
	public static final int DEFAULT_TIME = 120;
	private int timeLeft;
	private int ownerID = -1;
	private boolean isOwnerOut = false;
	private int throwDirection = -1;
	private int throwTime = 120;
	private int speed = Player.DEFAULT_SPEED;

	public Bomb( int x, int y, int width, int height) {
		super( x, y, width, height);
		timeLeft = DEFAULT_TIME;
	}

	@Override
	public void tick(Tiles tile) {
		timeLeft--;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage( Assets.bomb, (int)x, (int)y, TILE_SIZE, TILE_SIZE, null);
	}

	public int getTimeLeft() {
		return timeLeft;
	}
	
	public void setOwner(int id) {
		this.ownerID = id;
	}

	public void onCollision(Tiles tile) {
		int [] collisions = isCollision( tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
		if((collisions[0] != 0 || collisions[1] != 0 || collisions[2] != 0 || collisions[3] != 0) && this.speed != 0) {
			this.speed = 0;
			this.x = (int) ( Math.round((float) this.x / (float) Tiles.TILE_SIZE)) * Tiles.TILE_SIZE + 10;
			this.y = (int) ( Math.round((float) this.y / (float) Tiles.TILE_SIZE)) * Tiles.TILE_SIZE + 10;
		}
	}


	@Override
	public void onCollision(Player player) {
		int [] collisions = isCollision( player.getX(), player.getY(), player.getWidth(), player.getHeight());
	
		if(player.getId() == this.ownerID) {
			if(collisions[0] == 0 && collisions[1] == 0 && collisions[2] == 0 && collisions[3] == 0)
				this.isOwnerOut = true;
			
			if(this.isOwnerOut) {
				if(collisions[0] == 1 || (this.throwDirection == 0 && this.throwTime >= 0))
					if(player.getSpeedX() > 0 || (this.throwDirection == 0 && this.throwTime >= 0)) {
						this.x += this.speed;
						this.x = clamp(this.x, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
						this.throwDirection = 0;
						this.throwTime--;
					}
				if(collisions[1] == 1 || (this.throwDirection == 1 && this.throwTime >= 0))
					if(player.getSpeedX() < 0 || (this.throwDirection == 1 && this.throwTime >= 0)) {
						this.x -= this.speed;
						this.x = clamp(this.x, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
						this.throwDirection = 1;
						this.throwTime--;
					}
				if(collisions[2] == 1 || (this.throwDirection == 2 && this.throwTime >= 0))
					if(player.getSpeedY() > 0 || (this.throwDirection == 2 && this.throwTime >= 0)) {
						this.y += this.speed;
						this.y = clamp(this.y, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
						this.throwDirection = 2;
						this.throwTime--;
					}
				if(collisions[3] == 1 || (this.throwDirection == 3 && this.throwTime >= 0))
					if(player.getSpeedY() < 0 || (this.throwDirection == 3 && this.throwTime >= 0)) {
						this.y -= this.speed;
						this.y = clamp(this.y, 10 + Tiles.TILE_SIZE, 800 - Tiles.TILE_SIZE*2 - 10);
						this.throwDirection = 3;
						this.throwTime--;
					}
			
				}
			} else {
				if(!(collisions[0] == 0 && collisions[1] == 0 && collisions[2] == 0 && collisions[3] == 0)) {
					this.speed = 0;
					this.x = (int) ( Math.round((float) this.x / (float) Tiles.TILE_SIZE)) * Tiles.TILE_SIZE + 10;
					this.y = (int) ( Math.round((float) this.y / (float) Tiles.TILE_SIZE)) * Tiles.TILE_SIZE + 10;
				}
				
				if(collisions[0] == 1 && player.getSpeedX() > 0)
					player.setX(player.getX() - player.getSpeedX());
				if(collisions[1] == 1 && player.getSpeedX() < 0)
					player.setX(player.getX() - player.getSpeedX());
				if(collisions[2] == 1 && player.getSpeedY() > 0)
					player.setY(player.getY() - player.getSpeedY());
				if(collisions[3] == 1 && player.getSpeedY() < 0)
					player.setY(player.getY() - player.getSpeedY());
				
					
			}
		
		
	}
	
	
}
