package bmbremaster.tiles.blocks;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.server.Handler;
import bmbremaster.tiles.Tiles;

public class Bomb extends Tiles {
	
	public static final float DEFAULT_TIME = 3.0f;
	
	private float timeLeft;

	public Bomb(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
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
	
	
}
