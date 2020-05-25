package bomberman.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ActionsRead {
		
		private String path;
		public final int SIZE;
		public int[] pixelsArray;
		
		public static ActionsRead squares = new ActionsRead("/textures/textures.png", 256);
		
		public ActionsRead( String path, int size ) {
			this.path = path;
			SIZE = size;
			pixelsArray = new int[SIZE * SIZE];
			loadTextures();
		}
		
		private void loadTextures() {
			try {
				URL a = ActionsRead.class.getResource(path);
				BufferedImage image = ImageIO.read(a);
				int w = image.getWidth();
				int h = image.getHeight();
				image.getRGB( 0, 0, w, h, pixelsArray, 0, w );
			}
			catch (IOException e) {
				e.printStackTrace();
				e.getMessage();
				System.exit(0);
			}
		}
}
