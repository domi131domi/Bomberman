package bmbremaster.clientMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import bmbremaster.client.Client;
import bmbremaster.graphics.Assets;
import bmbremaster.graphics.GameInfo;
import bmbremaster.server.Msg;

public class Game implements Runnable {
	
	//temporary solution
	private boolean drawBombOne = false;
	private boolean drawBombTwo = false;
	//end of temp sol
	
	private ClientWindow window;
	private Thread thread;
	private boolean running = false;
	private Client client;
	private KeyInput keyboard;
	private GameInfo gameInfo;
	private boolean connected = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private int width, height;
	private String title;


	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		window = new ClientWindow(title, width, height);
		keyboard = new KeyInput();
		window.getCanvas().addKeyListener(keyboard);
		client = new Client();
		gameInfo = new GameInfo();
		
		try {
			client.connect("localhost", 6666);
			connected = true;
		} catch (IOException e) {
			e.printStackTrace();
			connected = false;
		}
		
		
	}
	
	private void tick() {
		//Load coordinates
		try {
			Msg coords = client.getMessage();
			gameInfo.setPlayer(0, new Dimension(coords.p1x, coords.p1y));
			gameInfo.setPlayer(1, new Dimension(coords.p2x, coords.p2y));
			if(coords.draw1) {
				gameInfo.setBomb(0, new Dimension(coords.p1x, coords.p1y));
				drawBombOne = true;
			}
			if(coords.draw2) {
				gameInfo.setBomb(1, new Dimension(coords.p2x, coords.p2y));
				drawBombTwo = true;
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null ) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		drawGame(g);
		
		bs.show();
		g.dispose();
	}
	
	private void send() { 
		try {
			client.sendMessage(new Msg(keyboard.left, keyboard.right, keyboard.up, keyboard.down, keyboard.space));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		Assets.init();
	}
	
	private void drawGame(Graphics g) {
		
		//draw grass background
		g.setColor(new Color(0x54DC35));
		g.fillRect(0, 0, 800, 800);
		
		//steel edges
		g.drawImage( Assets.steelVer, 0, 0, 10, Assets.HEIGHT, null );
		g.drawImage( Assets.steelVer, Assets.WIDTH - 10, 0, 10, Assets.HEIGHT, null );
		g.drawImage( Assets.steelHor, 10, 0, Assets.WIDTH - 20, 10, null );
		g.drawImage( Assets.steelHor, 10, Assets.HEIGHT - 10, Assets.WIDTH - 20, 10, null );
		
		
		//border concrete blocks - dont need to be objects checked by handler
		int y = 60;
		for( int i = 0; i < 13; i+=2 ) {
			int j = 0;
			while( j < 13 ) {
				g.drawImage( Assets.concrete, i * y + 10 , j * y + 10, null );
				if((j == 0 || j == 12) && i != 12 ) {
					g.drawImage( Assets.concrete, (i+1) * y + 10 , j * y + 10, null );
				}
				if( i == 0 || i == 12 )
					j++;
				else 
					j+=2;
			}
		}

		g.drawImage( Assets.player1, gameInfo.getPlayer(0).width, gameInfo.getPlayer(0).height, null );
		g.drawImage( Assets.player2, gameInfo.getPlayer(1).width, gameInfo.getPlayer(1).height, null );
		
		if(drawBombOne) {
			System.out.println("zara narysuje bombeee1");
			g.drawImage(Assets.bomb, gameInfo.getBomb(0).width, gameInfo.getBomb(0).height, null);
			drawBombOne = false;
		}
		if(drawBombTwo) {
			System.out.println("zara narysuje bombeee2");
			g.drawImage(Assets.bomb, gameInfo.getBomb(1).width, gameInfo.getBomb(1).height, null);
			drawBombTwo = false;
		}

	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;	//nanoseconds
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(connected && delta >= 1) {
				tick();
				render();
				keyboard.update();
				send();
				delta--;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
