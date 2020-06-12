package bmbremaster.tiles.blocks;

import java.awt.Graphics;
import java.io.Serializable;

import bmbremaster.graphics.Assets;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.players.Player;

public class Fire extends Tiles {
	
	private int timeLeft;
	private int direction;

	public Fire(int x, int y, int width, int height, int direction) {
		super(x, y, width, height);
		timeLeft = 60;
		this.direction = direction;
	}

	@Override
	public void tick(Tiles tile) {
		timeLeft--;
	}

	@Override
	public void render(Graphics g) {
		if(direction == 0) //left
			g.drawImage(Assets.explosionLeft, x, y, width, height, null);
		else if(direction == 1) //right
			g.drawImage(Assets.explosionRight, x, y, width, height, null);
		else if(direction == 2) //up
			g.drawImage(Assets.explosionUp, x, y, width, height, null);
		else if(direction == 3) //down
			g.drawImage(Assets.explosionDown, x, y, width, height, null);
		else if(direction == 4) //center
			g.drawImage(Assets.explosionCenter, x, y, width, height, null);
	}

	@Override
	public void onCollision(Player player) {
		int [] collisions = isCollision( player.getX(), player.getY(), player.getWidth(), player.getHeight() );
		if( collisions[0] == 1 || collisions[1] == 1 || collisions[2] == 1 || collisions[3] == 1 ) {
			int temp = player.getHealth();
			player.setHealth( --temp );	
		}
	}
	
	public void onCollision(Tiles tile) {
		boolean col = false;
		for(int i = 0; i < 4; i++)
			if(isCollision(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight())[i] == 1)
				col = true;
			if( col && (this.width != Tiles.TILE_SIZE || this.height != Tiles.TILE_SIZE)) {
				this.width = Tiles.TILE_SIZE;
				this.height = Tiles.TILE_SIZE;
				if(this.direction == 0)
					this.x += Tiles.TILE_SIZE;
				else if(this.direction == 2)
					this.y += Tiles.TILE_SIZE;
			}
	}
	
	public int getTimeLeft() {
		return timeLeft;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	static public class FireCoords implements Serializable {
		private static final long serialVersionUID = 4041889229282180846L;
		
		public int x;
		public int y;
		public int direction;
		public int width;
		public int height;
		public FireCoords(int x, int y, int width, int height, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.width = width;
			this.height = height;
		}
	}

}
