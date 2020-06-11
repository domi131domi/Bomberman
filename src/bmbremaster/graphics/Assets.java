package bmbremaster.graphics;

import java.awt.image.BufferedImage;

import bmbremaster.tiles.Tiles;

public class Assets {
	
	public static final int WIDTH = 800, HEIGHT = 800;

	public static BufferedImage player1, player2, bomb, bricks, concrete, steelVer, steelHor;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/textures_vol2.png"));
		
		player1 = sheet.crop(0, 0, Tiles.TILE_SIZE, Tiles.TILE_SIZE);
		player2 = sheet.crop(Tiles.TILE_SIZE, 0, Tiles.TILE_SIZE, Tiles.TILE_SIZE);
		bomb = sheet.crop(Tiles.TILE_SIZE*2, 0, Tiles.TILE_SIZE, Tiles.TILE_SIZE);
		bricks = sheet.crop(0, Tiles.TILE_SIZE, Tiles.TILE_SIZE, Tiles.TILE_SIZE);
		concrete = sheet.crop(Tiles.TILE_SIZE, Tiles.TILE_SIZE, Tiles.TILE_SIZE, Tiles.TILE_SIZE);
		steelVer = sheet.crop(Tiles.TILE_SIZE*2, Tiles.TILE_SIZE, Tiles.TILE_SIZE, Tiles.TILE_SIZE);
		steelHor = sheet.crop(Tiles.TILE_SIZE*2, Tiles.TILE_SIZE*2, Tiles.TILE_SIZE, Tiles.TILE_SIZE);
	}
}
