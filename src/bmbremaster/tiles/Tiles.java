package bmbremaster.tiles;

import java.awt.Graphics;

import bmbremaster.server.Handler;

public abstract class Tiles {

	public static final int TILE_SIZE = 60;
	
	protected Handler handler;
	
	protected float x, y;
	protected int width, height;
	
	public Tiles( Handler handler, float x, float y, int width, int height ) {
		this.handler = handler;
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

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
