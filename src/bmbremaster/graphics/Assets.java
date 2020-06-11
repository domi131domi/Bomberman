package bmbremaster.graphics;

import java.awt.image.BufferedImage;

public class Assets {
	public static final int playerSizeX = 60;
	public static final int playerSizeY = 60;
	public static final int tileSize = 60;
	public static BufferedImage player1, player2, bomb, bricks, block;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/textures_vol2.png"));
		
		player1 = sheet.crop(0, 0, playerSizeX, playerSizeY);
		player2 = sheet.crop(playerSizeX, 0, playerSizeX, playerSizeY);
		bomb = sheet.crop(playerSizeX*2, 0, playerSizeX, playerSizeY);
		bricks = sheet.crop(0, playerSizeY, playerSizeX, playerSizeY);
		block = sheet.crop(playerSizeX, playerSizeY, playerSizeX, playerSizeY);
	}
}
