package bomberman.objects.players;

import bomberman.graphics.Screen;
import bomberman.objects.GameObject;
import bomberman.objects.Handler;
import bomberman.objects.ID;

public abstract class Players extends GameObject {
	
	static final int DEFAULT_VELOCITY = 3;
	static final int DEFAULT_DAMAGE = 25;
	
	//private LinkedList<Bomb> bombs;
	//protected Keyboard input;
	//protected int timeBetweenBombs = 0;
	//public static LinkedList<PowerUp> powerUps = new LinkedList<PowerUp>();
	
	protected int currentVelocity;
	protected int velocityX, velocityY;
	public static int damage;
	
	Handler handler;
	
	public Players( int x, int y, ID id, Handler handler) {
		super( x, y, id );
		this.handler = handler;
	}
	
	@Override
	public abstract void tick();

	@Override
	public abstract void render(Screen screen);

	@Override
	public boolean collide(GameObject object) {
		return false;
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
		if( vel > 0 && currentVelocity + vel <= 10 || vel < 0 && currentVelocity + vel >= DEFAULT_VELOCITY )
			currentVelocity += vel;
	}


	
}
