package bomberman.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteRead {

	private String path;
	public final int SIZE;
	public int[] pixelsArray;
	
	public static SpriteRead tiles = new SpriteRead("/textures/classic.png", 256);
	
	public SpriteRead(String path, int size) {
		this.path = path;
		SIZE = size;
		pixelsArray = new int[SIZE * SIZE];
		loadTextures();
	}
	
	private void loadTextures() {
		try {
			URL a = SpriteRead.class.getResource(path);
			BufferedImage image = ImageIO.read(a);
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixelsArray, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
			System.exit(0);
		}
	}
}
