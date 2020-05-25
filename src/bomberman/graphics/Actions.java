package bomberman.graphics;

public class Actions {
		
	public final int SIZE;
	private int x, y;
	public int[] pixelsArray;
	protected int scaledWidth;
	protected int scaledHeight;
	private ActionsRead sheet;
	
	
	public Actions(int size, int color) {
		SIZE = size;
		pixelsArray = new int[SIZE * SIZE];
		setColor(color);
	}
	
	
	public Actions(int size, int x, int y, ActionsRead sheet, int rw, int rh) {
		SIZE = size;
		pixelsArray = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		scaledWidth = rw;
		scaledHeight = rh;
		loadAction();
	}
	
	private void loadAction() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixelsArray[x + y * SIZE] = sheet.pixelsArray[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
	
	private void setColor( int color ) {
		for( int i = 0; i < pixelsArray.length; i++ ) {
			pixelsArray[i] = color;
		}
	}
	
	public static Actions voidActions = new Actions( 16, 0xffffff );
	
	
	
	
	//standard methods
	
	public int getSize() {
		return SIZE;
	}
	
	public int[] getPixelsArray() {
		return pixelsArray;
	}
	
	public int getOnePixel( int i ) {
		return pixelsArray[i];
	}
	
	public int getScaledWidth() {
		return scaledWidth;
	}
	
	public int getScaledHeight() {
		return scaledHeight;
	}
}
