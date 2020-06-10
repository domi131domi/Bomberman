package bmbremaster.clientMain;

public class Game {
	
	private ClientWindow window;
	
	public int width, height;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		window = new ClientWindow(title, width, height);
	}
}
