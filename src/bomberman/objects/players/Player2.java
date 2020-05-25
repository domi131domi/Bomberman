package bomberman.objects.players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import bomberman.main.Game;
import bomberman.objects.GameObject;
import bomberman.objects.ID;

public class Player2 extends GameObject {
	
	static final int defaultVelocity = 3;
	static final int defaultDamage = 25;
	
	protected int currentVelocity;
	protected int velocityX, velocityY;
	
	
	public Player2( int x, int y, ID id ) {
		super( x, y ,id );
		currentVelocity = defaultVelocity;
		velocityX = 0;
		velocityY = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
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
	
	public void setVelocityX( int x ) {
		velocityX = x;
	}
	public int getVelocityX() {
		return velocityX;
	}
	
	public void setVelocityY( int y ) {
		velocityY = y;
	}
	public int getVelocityY() {
		return velocityY;
	}
	
	public int getCurrentVelocity() {
		return currentVelocity;
	}
	public void boostVelocity( int vel ) {
		if( vel > 0 && currentVelocity + vel <= 10 || vel < 0 && currentVelocity + vel >= defaultVelocity )
			currentVelocity += vel;
	}
	
}
