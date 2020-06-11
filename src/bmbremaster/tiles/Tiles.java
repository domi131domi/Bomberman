package bmbremaster.tiles;

import java.awt.Graphics;

public abstract class Tiles {

	public static final int TILE_SIZE = 60;
	
	protected int x, y;
	protected int width, height;
	
	public Tiles( int x, int y, int width, int height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

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

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
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
	
}
