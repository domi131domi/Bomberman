package bmbremaster.graphics;

import java.awt.Dimension;
import java.util.ArrayList;

public class GameInfo {
	private ArrayList<Dimension> players = new ArrayList<Dimension>();
	
	public GameInfo() {
		players.add(new Dimension(0,0));
		players.add(new Dimension(0,0));
	}
	
	public void setPlayer(int number, Dimension coords) {
		players.set(number, coords);
	}
	
	public Dimension getPlayer(int number) {
		return players.get(number);
	}
}
