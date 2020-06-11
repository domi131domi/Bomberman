package bmbremaster.tiles.blocks;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.players.Player;

public class Bomb extends Tiles {
	
	public static final float DEFAULT_TIME = 3.0f;
	
	private float timeLeft;

	public Bomb( int x, int y, int width, int height) {
		super( x, y, width, height);
		timeLeft = DEFAULT_TIME;
	}

	@Override
	public void tick() {
		//maybe decreasing time of existing?
	}

	@Override
	public void render(Graphics g) {
		g.drawImage( Assets.bomb, (int)x, (int)y, TILE_SIZE, TILE_SIZE, null);
	}

	public float getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(float timeLeft) {
		this.timeLeft = timeLeft;
	}
/*
	@Override
	public void onCollision(int x, int y, int sizeX, int sizeY) {
		// TODO Auto-generated method stub
		
	}
	*/

	@Override
	public void onCollision(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	
}
