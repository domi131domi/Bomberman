package bmbremaster.graphics;

import java.util.ArrayList;

import bmbremaster.tiles.players.Player;

public class GameInfo {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	//private ArrayList<Dimension> bombs = new ArrayList<Dimension>();
	//private ArrayList<Dimension>  = new ArrayList<Dimension>();
	
	public Player getPlayer(int number) {
		return players.get(number);
	}
	/*
	public void setBomb(int number, Dimension coords) {
		bombs.set(number, coords);
	}
	
	public Dimension getBomb(int number) {
		return bombs.get(number);
	}
	
	public void addBomb( Dimension coords) {
		bombs.add(new Dimension (coords.width, coords.height));
	}
	*/
	
	public void addPlayer( Player player ) {
		players.add( player );
	}
	//-----------
}
