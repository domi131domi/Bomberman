package bmbremaster.clientMain;

public class ClientLauncher {

	public static void main(String[] args) {
		Game game = new Game("Bomberman", 800, 800);
		game.start();
	}

}