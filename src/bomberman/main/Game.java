package bomberman.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import bomberman.graphics.HUD;
import bomberman.gui.Window;
import bomberman.input.KeyInput;
import bomberman.objects.Handler;
import bomberman.objects.ID;
import bomberman.objects.players.Player1;
import bomberman.objects.players.Player2;


public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 12345678910L;

	public static final int WIDTH = 960;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		 
		new Window( WIDTH, HEIGHT, "The Bomberman", this );
		
		hud = new HUD();

		r = new Random();
		
		handler.addObject( new Player1( WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player1, handler ) );
		handler.addObject( new Player2( WIDTH/2 + 64, HEIGHT/2 + 64, ID.Player2 ) );
		
		//for( int i = 1; i < HEIGHT/48/2 - 1 ; i++ ) {
		//	for( int j = 1; j < WIDTH/48/2 - 1 ; j++ ) {
		//		handler.addObject( new Block( j*48*2, i*48*2, ID.Block ));
		//	}
		//}
	}

	public synchronized void start(){
		thread = new Thread( this );
		thread.start();
		running = true;		
	}
	
	public synchronized void stop() {
		try {
			running = false;
			thread.join();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}	
	}
	
	public void run() {
		this.requestFocus();
		//game loop - gotta check this better
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while( running ) {
			long now = System.nanoTime();
			delta += ( now - lastTime ) / ns;
			lastTime = now;
			while( delta >= 1 ) {
				tick();
				delta--;
			}
			if( running )
				render();
			frames++;
			
			if( System.currentTimeMillis() - timer > 1000 ) {
				timer += 1000;
				//System.out.println( "FPS: " + frames );
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {	
		handler.tick();
		hud.tick();
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if( bs == null ) {
			this.createBufferStrategy(3);
			return;
		}	
		Graphics g = bs.getDrawGraphics();
		
		g.setColor( Color.yellow );
		g.fillRect( 0, 0, WIDTH, HEIGHT );
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp( int var, int min, int max ) {
		if( var >= max )
			return var = max;
		else if ( var <= min )
			return var = min;
		else
			return var;
	}
	
	public static void main ( String[] args ) {
		new Game();
	}
}
