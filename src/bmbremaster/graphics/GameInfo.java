package bmbremaster.graphics;

import java.util.ArrayList;

import bmbremaster.tiles.blocks.Concrete;
import bmbremaster.tiles.players.Player;

public class GameInfo {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	//private ArrayList<Dimension> bombs = new ArrayList<Dimension>();
	private Concrete[] concretes = new Concrete[30];
	//private LinkedList<Concrete> concretes = new LinkedList<Concrete>();

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
	
	public Concrete[] getConcretes( ) {
		return concretes;
	}
	
	public void addConcrete( int number,  Concrete concrete ) {
		concretes[number] = concrete;
	}
	
	/*
	public void addConcrete( Concrete concrete ) {
		concretes.add( concrete );
	}
	*/

	//-----------
}
