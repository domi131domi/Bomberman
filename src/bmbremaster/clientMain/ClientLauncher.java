package bmbremaster.clientMain;

public class ClientLauncher {
	
	public static final int WIDTH = 800, HEIGHT = 800;

	public static void main(String[] args) {
		Game game = new Game("Bomberman", WIDTH, HEIGHT);
		game.start();
	}

}