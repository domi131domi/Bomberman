package bmbremaster.tiles.blocks;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.players.Player;

public class Bomb extends Tiles {
	
	public static final int DEFAULT_TIME = 60;
	
	private int timeLeft;

	public Bomb( int x, int y, int width, int height) {
		super( x, y, width, height);
		timeLeft = DEFAULT_TIME;
	}

	@Override
	public void tick(Tiles tile) {
		timeLeft--;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage( Assets.bomb, (int)x, (int)y, TILE_SIZE, TILE_SIZE, null);
	}

	public int getTimeLeft() {
		return timeLeft;
	}

/*
	@Override
	public void onCollision(int x, int y, int sizeX, int sizeY) {
		// TODO Auto-generated method stub
		
	}
	*/

	@Override
	public void onCollision(Player player) {
		
		//dla bomb potrzebna jest wieksza tolerancja niz dla zwyklych plytek
		/*
		int [] collisions = isCollision( player.getX(), player.getY(), player.getWidth(), player.getHeight() );
		if( collisions[0] == 1 && collisions[2] == 1) { //left-up collision
			if(player.getSpeedX() > 0) {
				player.setX( player.getX() - player.getSpeedX());
				if(player.getY() + player.getHeight() > this.y && player.getY() + player.getHeight() < this.y + this.getHeight()/2)
					player.setY( player.getY() - player.getSpeed());
			}
			else if(player.getSpeedY() > 0) {
				player.setY( player.getY() - player.getSpeedY());
				if(player.getX() + player.getWidth() > this.x && player.getX() + player.getWidth() < this.x + this.getWidth()/2)
					player.setX( player.getX() - player.getSpeed() );
			}
		}
		if( collisions[1] == 1 && collisions[2] == 1) { //right-up collision
			if(player.getSpeedX() < 0) {
				player.setX( player.getX() - player.getSpeedX());
				if(player.getY() + player.getHeight() > this.y && player.getY() + player.getHeight() < this.y + this.getHeight()/2)
					player.setY( player.getY() - player.getSpeed());
			}
			else if(player.getSpeedY() > 0) {
				player.setY( player.getY() - player.getSpeedY());
				if(player.getX() > this.x + this.width/2 && player.getX() < this.x + this.width)
					player.setX( player.getX() + player.getSpeed() );
			}
		}
		if( collisions[1] == 1 && collisions[3] == 1) { //right-down collision
			if(player.getSpeedX() < 0) {
				player.setX( player.getX() - player.getSpeedX());
				if(player.getY() > this.y + this.height/2 && player.getY() < this.y + this.getHeight())
					player.setY( player.getY() + player.getSpeed());
			}
			else if(player.getSpeedY() < 0) {
				player.setY( player.getY() - player.getSpeedY());
				if(player.getX() > this.x + this.width/2 && player.getX() < this.x + this.width)
					player.setX( player.getX() + player.getSpeed());
			}
		}
		if( collisions[0] == 1 && collisions[3] == 1) { //left-down collision
			if(player.getSpeedX() > 0) {
				player.setX( player.getX() - player.getSpeedX() );
				if(player.getY() > this.y + this.height/2 && player.getY() < this.y + this.getHeight())
					player.setY( player.getY() + player.getSpeed());
			}
			else if(player.getSpeedY() < 0) {
				player.setY( player.getY() - player.getSpeedY());
				if(player.getX() + player.getWidth() > this.x && player.getX() + player.getWidth() < this.x + this.width/2)
					player.setX( player.getX() - player.getSpeed() );
			}
		}
		*/
	}
	
	
}
