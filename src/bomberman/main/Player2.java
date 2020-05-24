package bomberman.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player2 extends GameObject {
	
	public Player2( int x, int y, ID id ) {
		super( x, y ,id );
		currentVelocity = defaultVelocity;
		velocityX = 0;
		velocityY = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	
	public void tick() {
		x += velocityX;
		y += velocityY;
		
		x = Game.clamp( x, 0, Game.WIDTH - 46 );
		y = Game.clamp( y, 0, Game.HEIGHT - 68 );
	}
	
	public void render( Graphics g ) {
		g.setColor( Color.red );
		g.fillRect( x, y, 32, 32 );
	}
}
