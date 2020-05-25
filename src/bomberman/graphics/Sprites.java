package bomberman.graphics;

public class Sprites {
	
	public final int SIZE;
	private int x, y;
	public int[] pixelsArray;
	protected int realWidth;
	protected int realHeight;
	private SpriteRead spriteSheet;
	
	public static Sprites voidSprite = new Sprites(16, 0xffffff); //black
	
	/*
	|--------------------------------------------------------------------------
	| Board sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprites grass = new Sprites(16, 6, 0, SpriteRead.tiles, 16, 16);
	public static Sprites brick = new Sprites(16, 7, 0, SpriteRead.tiles, 16, 16);
	public static Sprites wall = new Sprites(16, 5, 0, SpriteRead.tiles, 16, 16);
	public static Sprites portal = new Sprites(16, 4, 0, SpriteRead.tiles, 14, 14);
	
	/*
	|--------------------------------------------------------------------------
	| Player1 Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprites player1_up = new Sprites(16, 0, 0, SpriteRead.tiles, 12, 16);
	public static Sprites player1_down = new Sprites(16, 2, 0, SpriteRead.tiles, 12, 15);
	public static Sprites player1_left = new Sprites(16, 3, 0, SpriteRead.tiles, 10, 15);
	public static Sprites player1_right = new Sprites(16, 1, 0, SpriteRead.tiles, 10, 16);
	
	public static Sprites player1_up_1 = new Sprites(16, 0, 1, SpriteRead.tiles, 12, 16);
	public static Sprites player1_up_2 = new Sprites(16, 0, 2, SpriteRead.tiles, 12, 15);
	
	public static Sprites player1_down_1 = new Sprites(16, 2, 1, SpriteRead.tiles, 12, 15);
	public static Sprites player1_down_2 = new Sprites(16, 2, 2, SpriteRead.tiles, 12, 16);
	
	public static Sprites player1_left_1 = new Sprites(16, 3, 1, SpriteRead.tiles, 11, 16);
	public static Sprites player1_left_2 = new Sprites(16, 3, 2, SpriteRead.tiles, 12 ,16);
	
	public static Sprites player1_right_1 = new Sprites(16, 1, 1, SpriteRead.tiles, 11, 16);
	public static Sprites player1_right_2 = new Sprites(16, 1, 2, SpriteRead.tiles, 12, 16);
	
	public static Sprites player1_dead1 = new Sprites(16, 4, 2, SpriteRead.tiles, 14, 16);
	public static Sprites player1_dead2 = new Sprites(16, 5, 2, SpriteRead.tiles, 13, 15);
	public static Sprites player1_dead3 = new Sprites(16, 6, 2, SpriteRead.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Player2 Sprites
	|--------------------------------------------------------------------------
	 */
	
	public static Sprites player2_up = new Sprites(16, 8, 0, SpriteRead.tiles, 12, 16);
	public static Sprites player2_down = new Sprites(16, 10, 0, SpriteRead.tiles, 12, 15);
	public static Sprites player2_left = new Sprites(16, 11, 0, SpriteRead.tiles, 10, 15);
	public static Sprites player2_right = new Sprites(16, 9, 0, SpriteRead.tiles, 10, 16);
	
	public static Sprites player2_up_1 = new Sprites(16, 8, 1, SpriteRead.tiles, 12, 16);
	public static Sprites player2_up_2 = new Sprites(16, 8, 2, SpriteRead.tiles, 12, 15);
	
	public static Sprites player2_down_1 = new Sprites(16, 10, 1, SpriteRead.tiles, 12, 15);
	public static Sprites player2_down_2 = new Sprites(16, 10, 2, SpriteRead.tiles, 12, 16);
	
	public static Sprites player2_left_1 = new Sprites(16, 11, 1, SpriteRead.tiles, 11, 16);
	public static Sprites player2_left_2 = new Sprites(16, 11, 2, SpriteRead.tiles, 12 ,16);
	
	public static Sprites player2_right_1 = new Sprites(16, 9, 1, SpriteRead.tiles, 11, 16);
	public static Sprites player2_right_2 = new Sprites(16, 9, 2, SpriteRead.tiles, 12, 16);
	
	public static Sprites player2_dead1 = new Sprites(16, 12, 2, SpriteRead.tiles, 14, 16);
	public static Sprites player2_dead2 = new Sprites(16, 13, 2, SpriteRead.tiles, 13, 15);
	public static Sprites player2_dead3 = new Sprites(16, 14, 2, SpriteRead.tiles, 16, 16);
	
	
	/*
	|--------------------------------------------------------------------------
	| Bomb Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprites bomb = new Sprites(16, 0, 3, SpriteRead.tiles, 15, 15);
	public static Sprites bomb_1 = new Sprites(16, 1, 3, SpriteRead.tiles, 13, 15);
	public static Sprites bomb_2 = new Sprites(16, 2, 3, SpriteRead.tiles, 12, 14);
	
	/*
	|--------------------------------------------------------------------------
	| Explosion Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprites bomb_exploded = new Sprites(16, 0, 4, SpriteRead.tiles, 16, 16);
	public static Sprites bomb_exploded1 = new Sprites(16, 0, 5, SpriteRead.tiles, 16, 16);
	public static Sprites bomb_exploded2 = new Sprites(16, 0, 6, SpriteRead.tiles, 16, 16);
	
	public static Sprites explosion_vertical = new Sprites(16, 1, 5, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_vertical1 = new Sprites(16, 2, 5, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_vertical2 = new Sprites(16, 3, 5, SpriteRead.tiles, 16, 16);
	
	public static Sprites explosion_horizontal = new Sprites(16, 1, 7, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_horizontal1 = new Sprites(16, 1, 8, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_horizontal2 = new Sprites(16, 1, 9, SpriteRead.tiles, 16, 16);
	
	public static Sprites explosion_horizontal_left_last = new Sprites(16, 0, 7, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_horizontal_left_last1 = new Sprites(16, 0, 8, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_horizontal_left_last2 = new Sprites(16, 0, 9, SpriteRead.tiles, 16, 16);
	
	public static Sprites explosion_horizontal_right_last = new Sprites(16, 2, 7, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_horizontal_right_last1 = new Sprites(16, 2, 8, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_horizontal_right_last2 = new Sprites(16, 2, 9, SpriteRead.tiles, 16, 16);
	
	public static Sprites explosion_vertical_top_last = new Sprites(16, 1, 4, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_vertical_top_last1 = new Sprites(16, 2, 4, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_vertical_top_last2 = new Sprites(16, 3, 4, SpriteRead.tiles, 16, 16);
	
	public static Sprites explosion_vertical_down_last = new Sprites(16, 1, 6, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_vertical_down_last1 = new Sprites(16, 2, 6, SpriteRead.tiles, 16, 16);
	public static Sprites explosion_vertical_down_last2 = new Sprites(16, 3, 6, SpriteRead.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Brick Explosion
	|--------------------------------------------------------------------------
	 */
	public static Sprites brick_exploded = new Sprites(16, 7, 1, SpriteRead.tiles, 16, 16);
	public static Sprites brick_exploded1 = new Sprites(16, 7, 2, SpriteRead.tiles, 16, 16);
	public static Sprites brick_exploded2 = new Sprites(16, 7, 3, SpriteRead.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Powerups
	|--------------------------------------------------------------------------
	 */
	public static Sprites powerup_bombs = new Sprites(16, 0, 10, SpriteRead.tiles, 16, 16);
	public static Sprites powerup_flames = new Sprites(16, 1, 10, SpriteRead.tiles, 16, 16);
	public static Sprites powerup_speed = new Sprites(16, 2, 10, SpriteRead.tiles, 16, 16);
	public static Sprites powerup_wallpass = new Sprites(16, 3, 10, SpriteRead.tiles, 16, 16);
	public static Sprites powerup_detonator = new Sprites(16, 4, 10, SpriteRead.tiles, 16, 16);
	public static Sprites powerup_bombpass = new Sprites(16, 5, 10, SpriteRead.tiles, 16, 16);
	public static Sprites powerup_flamepass = new Sprites(16, 6, 10, SpriteRead.tiles, 16, 16);
	
	public Sprites(int size, int x, int y, SpriteRead sheet, int rw, int rh) {
		SIZE = size;
		pixelsArray = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		spriteSheet = sheet;
		realWidth = rw;
		realHeight = rh;
		load();
	}
	
	public Sprites(int size, int color) {
		SIZE = size;
		pixelsArray = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < pixelsArray.length; i++) {
			pixelsArray[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixelsArray[x + y * SIZE] = spriteSheet.pixelsArray[(x + this.x) + (y + this.y) * spriteSheet.SIZE];
			}
		}
	}
	
	/*
	|--------------------------------------------------------------------------
	| Moving Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprites movingSprite(Sprites normal, Sprites x1, Sprites x2, int animate, int time) {
		int calc = animate % time;
		int diff = time / 3;
		
		if(calc < diff) {
			return normal;
		}
			
		if(calc < diff * 2) {
			return x1;
		}
			
		return x2;
	}
	
	public static Sprites movingSprite(Sprites x1, Sprites x2, int animate, int time) {
		int diff = time / 2;
		return (animate % time > diff) ? x1 : x2; 
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public int[] getPixels() {
		return pixelsArray;
	}
	
	public int getPixel(int i) {
		return pixelsArray[i];
	}
	
	public int getRealWidth() {
		return realWidth;
	}
	
	public int getRealHeight() {
		return realHeight;
	}

}
