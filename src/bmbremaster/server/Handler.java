package bmbremaster.server;

import java.awt.Graphics;
import java.util.ArrayList;

import bmbremaster.tiles.Tiles;

public class Handler {
	
	private ArrayList<Tiles> objects = new ArrayList<Tiles>();
	
	public void tick() {
		//ticks of all objects
		for( Tiles object: objects ) {
			object.tick();
		}
	}
	
	public void render(Graphics g) {
		for( Tiles object: objects) {
			object.render(g);
		}
	}
	
	public void addObject( Tiles object ) {
		objects.add(object);
	}
	
	public void removeObject( Tiles object ) {
		objects.remove(object);
	}
	
	public ArrayList<Tiles> getObjectsArray(){
		return objects;
	}
	
	
}
