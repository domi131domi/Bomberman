package bmbremaster.server;

import java.io.Serializable;

public class Msg implements Serializable{
	private static final long serialVersionUID = 1L;
	public int p1x, p1y, p2x, p2y;
	public boolean a,d,w,s;
	
	public Msg() {
		
	}
	
	public Msg(int p1x, int p1y, int p2x, int p2y) {
		this.p1x = p1x;
		this.p2x = p2x;
		this.p1y = p1y;
		this.p2y = p2y;
	}
	
	public Msg(boolean a, boolean d, boolean w, boolean s) {
		this.a = a;
		this.d = d;
		this.w = w;
		this.s = s;
	}
	
}
