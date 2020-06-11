package bmbremaster.server;

import java.io.Serializable;

public class Msg implements Serializable{
	private static final long serialVersionUID = 1L;
	public int p1x, p1y, p2x, p2y;
	//
	public boolean draw1, draw2;
	//
	public boolean a,d,w,s,space;
	
	public Msg() {
		
	}
	
	public Msg(int p1x, int p1y, int p2x, int p2y, boolean draw1, boolean draw2 ) {
		this.p1x = p1x;
		this.p2x = p2x;
		this.p1y = p1y;
		this.p2y = p2y;
		//
		this.draw1 = draw1;
		this.draw2 = draw2;
	}
	
	public Msg(boolean a, boolean d, boolean w, boolean s, boolean space) {
		this.a = a;
		this.d = d;
		this.w = w;
		this.s = s;
		//
		this.space = space;
	}
	
}
