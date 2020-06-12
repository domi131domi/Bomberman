package bmbremaster.server;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;

import bmbremaster.tiles.blocks.Fire;

public class Msg implements Serializable{
	private static final long serialVersionUID = 1L;
	public int p1x, p1y, p2x, p2y, p1h, p2h;
	public boolean a,d,w,s,space, p1dir, p2dir;
	//
	private String text = "";
	private boolean isTextMsg = false;
	
	private ArrayList<Dimension> concreteCoords = new ArrayList<Dimension>(50);
	private ArrayList<Dimension> bombsCoords = new ArrayList<Dimension>(20);
	private ArrayList<Fire.FireCoords> fireCoords = new ArrayList<Fire.FireCoords>(80);
	private ArrayList<Dimension> bricksCoords = new ArrayList<Dimension>(30);
	
	public Msg() {
		
	}
	
	public Msg(int p1x, int p1y, int p2x, int p2y, int p1h, int p2h, boolean p1dir, boolean p2dir, String text) {
		this.p1x = p1x;
		this.p2x = p2x;
		this.p1y = p1y;
		this.p2y = p2y;
		
		this.p1h = p1h;
		this.p2h = p2h;
		
		this.p1dir = p1dir;
		this.p2dir = p2dir;
		
		this.text = text;
	}
	
	public Msg(boolean a, boolean d, boolean w, boolean s, boolean space) {
		this.a = a;
		this.d = d;
		this.w = w;
		this.s = s;
		this.space = space;
		this.isTextMsg = false;
	}
	
	public Msg(String text) {
		this.text = text;
		this.isTextMsg = true;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean isTextMsg() {
		return isTextMsg;
	}
	
	public void addConcreteCoords( int x, int y ) {
		concreteCoords.add( new Dimension(x,y));
	}
	
	public ArrayList<Dimension> getConcretes( ) {
		return concreteCoords;
	}
	
	public void addBombCoords( int x, int y ) {
		bombsCoords.add( new Dimension(x,y));
	}
	
	public ArrayList<Dimension> getBombs( ) {
		return bombsCoords;
	}
	
	public void addFireCoords( int x, int y, int width, int height, int direction ) {
		fireCoords.add( new Fire.FireCoords(x, y, width, height, direction));
	}
	
	public ArrayList<Fire.FireCoords> getFireCoords() {
		return fireCoords;
	}
	
	public void addBrickCoords( int x, int y ) {
		bricksCoords.add( new Dimension(x,y));
	}
	
	public ArrayList<Dimension> getBricks( ) {
		return bricksCoords;
	}
	
}
