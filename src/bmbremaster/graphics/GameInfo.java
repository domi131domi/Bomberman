package bmbremaster.graphics;

import java.util.ArrayList;

import bmbremaster.tiles.blocks.Bomb;
import bmbremaster.tiles.blocks.Bricks;
import bmbremaster.tiles.blocks.Concrete;
import bmbremaster.tiles.blocks.Fire;
import bmbremaster.tiles.players.Player;

public class GameInfo {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>(20);
	private ArrayList<Concrete> concretes = new ArrayList<Concrete>(40);
	private ArrayList<Fire> fireArray = new ArrayList<Fire>(80);
	private ArrayList<Bricks> bricks = new ArrayList<Bricks>(30);

	public Player getPlayer(int number) {
		return players.get(number);
	}
	
	public void addPlayer( Player player ) {
		players.add( player );
	}
	
	public ArrayList<Bomb> getBombs( ) {
		return bombs;
	}
	
	public void addBomb( Bomb bomb) {
		bombs.add( bomb );
	}
	
	public Bomb getBomb(int number) {
		return bombs.get(number);
	}
	
	public int getBombsSize() {
		return bombs.size();
	}

	public void resetBombs() {
		bombs.clear();
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
	
	public int getConcreteSize() {
		return concretes.size();
	}

	public void resetConcretes() {
		concretes.clear();
	}
	
	public ArrayList<Fire> getFireArray( ) {
		return fireArray;
	}
	
	public void addFire( Fire fire) {
		fireArray.add( fire );
	}
	
	public Fire getFire(int number) {
		return fireArray.get(number);
	}
	
	public int getFireSize() {
		return fireArray.size();
	}

	public void resetFire() {
		fireArray.clear();
	}
	
	public ArrayList<Bricks> getBricks( ) {
		return bricks;
	}
	
	public void addBrick( Bricks brick) {
		bricks.add( brick );
	}
	
	public Bricks getBrick(int number) {
		return bricks.get(number);
	}
	
	public int getBricksSize() {
		return bricks.size();
	}

	public void resetBricks() {
		bricks.clear();
	}
	//-----------
}
