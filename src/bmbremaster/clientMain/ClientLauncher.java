package bmbremaster.clientMain;

import bmbremaster.graphics.Assets;

public class ClientLauncher {	

	public static void main(String[] args) {
		Game game = new Game("Bomberman", Assets.WIDTH, Assets.HEIGHT);
		game.start();
	}

}