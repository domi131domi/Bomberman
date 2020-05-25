package bomberman.objects;

import bomberman.graphics.Rendering;
import bomberman.graphics.Screen;

public abstract class GameObject implements Rendering {

	protected int x, y;
	protected ID id;
	protected boolean removed = false; 

	
	public GameObject( int x, int y, ID id ) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	@Override
	public abstract void tick();
	
	@Override
	public abstract void render( Screen screen );	
	
	public abstract boolean collide( GameObject object );		//collision with other objects
	
	
	
	//standard methods
	
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

}
