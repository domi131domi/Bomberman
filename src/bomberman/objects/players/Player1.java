package bomberman.objects.players;

import bomberman.graphics.Screen;
import bomberman.main.Game;
import bomberman.objects.GameObject;
import bomberman.objects.Handler;
import bomberman.objects.ID;

public class Player1 extends Players {
	


	public Player1( int x, int y, ID id, Handler handler ) {
		super( x, y ,id, handler);
		currentVelocity = DEFAULT_VELOCITY;
		damage = DEFAULT_DAMAGE;
		velocityX = 0;
		velocityY = 0;
	}
	

	@Override
	public void tick() {
		x += velocityX;
		y += velocityY;
		
		x = Game.clamp( x, 0, Game.WIDTH - 46 );
		y = Game.clamp( y, 0, Game.HEIGHT - 68 );
		
	}
	
	@Override
	public boolean collide(GameObject object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void render ( Screen screen ) {	
		//g.setColor( Color.blue );
		//g.fillRect( x, y, 32, 32 );
	}
	
}
