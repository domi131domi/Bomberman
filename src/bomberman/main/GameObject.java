package bomberman.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	static final int defaultVelocity = 3;
	static final int defaultDamage = 25;
	
	protected int x, y;
	protected ID id;
	protected int currentVelocity;
	protected int velocityX, velocityY;
	
	public GameObject( int x, int y, ID id ) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render( Graphics g );
	public abstract Rectangle getBounds();
	
	public void setX( int x ) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	
	public void setY( int y ) {
		this.y = y;
	}
	public int getY( ) {
		return y;
	}
	
	public void setID( ID id ) {
		this.id = id;
	}
	public ID getID() {
		return id;
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
