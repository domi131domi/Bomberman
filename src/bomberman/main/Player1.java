package bomberman.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player1 extends GameObject {
	
	//czy zycie i pasek zycia maja byc elementem playera czy oddzielna klasa HUD?
	//public static int HEALTH = 100;

	//private int damage;
	Handler handler;
	
	public Player1( int x, int y, ID id, Handler handler ) {
		super( x, y ,id );
		this.handler = handler;
		currentVelocity = defaultVelocity;
	//	damage = defaultDamage;
		velocityX = 0;
		velocityY = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	private void collision() {
		for( int i = 0; i < handler.object.size(); i++ ) {
			GameObject tempObject = handler.object.get(i);
			
			if( tempObject.getID() == ID.Player2 ) {
				if( getBounds().intersects( tempObject.getBounds() ) ) {
					
					//trzeba napisac funkcje ktore nie pozwola przenikac przez drugiego gracza
					
					//x = Game.clamp(x, 0, tempObject.getX() - 64);
					//y = Game.clamp(y, 0, tempObject.getY() - 64);
					HUD.HEALTH1 -= 2;
					HUD.HEALTH2 += 1;
				}
			}	
		}
	}
	
	public void tick() {
		x += velocityX;
		y += velocityY;
		
		x = Game.clamp( x, 0, Game.WIDTH - 46 );
		y = Game.clamp( y, 0, Game.HEIGHT - 68 );
		
		collision();
	}
	
	public void render( Graphics g ) {
		
		g.setColor( Color.blue );
		g.fillRect( x, y, 32, 32 );
	}
}
