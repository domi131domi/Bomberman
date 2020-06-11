package bmbremaster.tiles.blocks;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.tiles.Tiles;

public class Bricks extends Tiles{
	
	private boolean exists;

	public Bricks(float x, float y, int width, int height) {
		super(x, y, width, height);
		exists = true;
	}

	@Override
	public void tick() {
		//getting info about where the bomb exploded and setting exists variable to false, then need to be removed from handler
	}

	@Override
	public void render(Graphics g) {	
		g.drawImage( Assets.bricks, (int)x, (int)y, TILE_SIZE, TILE_SIZE, null );
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}
	

}
