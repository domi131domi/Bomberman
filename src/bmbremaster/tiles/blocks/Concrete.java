package bmbremaster.tiles.blocks;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.server.Handler;
import bmbremaster.tiles.Tiles;

public class Concrete extends Tiles{

	public Concrete(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

	@Override
	public void tick() {
		//suppose there is nothing needed
	}

	@Override
	public void render(Graphics g) {
		g.drawImage( Assets.concrete, (int)x, (int)y, TILE_SIZE, TILE_SIZE, null );
	}

}
