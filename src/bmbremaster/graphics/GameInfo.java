package bmbremaster.graphics;

import java.util.ArrayList;

import bmbremaster.tiles.blocks.Concrete;
import bmbremaster.tiles.players.Player;

public class GameInfo {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	//private ArrayList<Dimension> bombs = new ArrayList<Dimension>();
	private ArrayList<Concrete> concretes = new ArrayList<Concrete>();

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
	public Player getPlayer(int number) {
		return players.get(number);
	}
	
	public void addPlayer( Player player ) {
		players.add( player );
	}
	
	public ArrayList<Concrete> getConcretes( ) {
		return concretes;
	}
	
	public void addConcrete( Concrete concrete ) {
		concretes.add( concrete );
	}
	
	public Concrete getConcrete(int number) {
		return concretes.get(number);
	}

	//-----------
}
