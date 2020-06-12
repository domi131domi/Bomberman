package bmbremaster.tiles.blocks;

import java.awt.Graphics;

import bmbremaster.graphics.Assets;
import bmbremaster.tiles.Tiles;
import bmbremaster.tiles.players.Player;

public class Bricks extends Tiles{
	

	public Bricks( int x, int y, int width, int height) {
		super(x, y, width, height);
		this.isDestructable = true;
		this.kind = 1;
	}

	@Override
	public void tick(Tiles tile) {
	}

	@Override
	public void render(Graphics g) {	
		g.drawImage( Assets.bricks, (int)x, (int)y, TILE_SIZE, TILE_SIZE, null );
	}


	/*
	@Override
	public void onCollision(int x, int y, int sizeX, int sizeY) {
		// TODO Auto-generated method stub
		
	}
	*/

	@Override
	public void onCollision(Player player) {
		int [] collisions = isCollision( player.getX(), player.getY(), player.getWidth(), player.getHeight() );
		if( collisions[0] == 1 ) { //left collision
			player.setX( player.getX() - player.getSpeed() );
		}
		/*else if( collisions[1] == 1 ) { //right collision
			player.setX( player.getX() + player.getSpeed() );
		}*/
		if( collisions[2] == 1 ) { //up collision
			player.setY( player.getY() - player.getSpeed() );
		}
		/*else if( collisions[3] == 1 ) { //down collision
			player.setY( player.getY() + player.getSpeed() );
		}*/
	}
	

}
