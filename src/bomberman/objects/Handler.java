package bomberman.objects;

import java.util.LinkedList;

import bomberman.graphics.Rendering;
import bomberman.graphics.Screen;

public class Handler implements Rendering {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	@Override
	public void tick() {
		for( int i = 0; i < object.size(); i++ ) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	@Override
	public void render( Screen screen ) {
		for( int i = 0; i < object.size(); i++ ) {
			GameObject tempObject = object.get(i);
			tempObject.render(screen);
		}
	}
	
	public void addObject( GameObject object ) {
		this.object.add( object );
	}
	
	public void removeObject( GameObject object ) {
		this.object.remove( object );
	}
}