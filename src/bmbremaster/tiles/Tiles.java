package bmbremaster.tiles;

import java.awt.Graphics;

import bmbremaster.tiles.players.Player;

public abstract class Tiles {

	public static final int TILE_SIZE = 60;
	
	protected int x, y;
	protected int width, height;
	protected boolean isDestructable;
	protected int kind = -1;
	
	public Tiles( int x, int y, int width, int height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick(Tiles tile);
	public abstract void render(Graphics g);
	public abstract void onCollision( Player player );
	
	public int[] isCollision(int x, int y , int sizeX, int sizeY) {
		int [] collisions = new int[4];
		if( x + sizeX <= this.x || x >= this.x + this.width || y + sizeY <= this.y || y >= this.y + this.height )
			return collisions;
		if ( x + sizeX > this.x && x + sizeX/2 <= this.x + this.width/2 )
			collisions[0] = 1; 	//left
		if ( x < this.x + this.width && x + sizeX/2 > this.x + this.width/2 )
			collisions[1] = 1;	//right
		if ( y + sizeY > this.y && y + sizeY/2 <= this.y + this.height/2 )
			collisions[2] = 1;	//up
		if ( y < this.y + this.height && y + sizeY/2 > this.y + this.height/2 )
			collisions[3] = 1;	//down
		return collisions;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static int clamp( int var, int min, int max ) {
		if( var >= max )
			return var = max;
		else if ( var <= min )
			return var = min;
		else
			return var;
	}
	
	public int getKind() {
		return this.kind;
	}

	public boolean isDestructable() {
		return isDestructable;
	}
	
}
