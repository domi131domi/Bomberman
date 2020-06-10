package bmbremaster.clientMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import bmbremaster.server.Msg;
import bmbremaster.client.Client;
import bmbremaster.graphics.GameInfo;

public class Game implements Runnable {
	
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
	private final int playerSizeX = 50;
	private final int playerSizeY = 50;

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
			client.sendMessage(new Msg(keyboard.left, keyboard.right, keyboard.up, keyboard.down));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		
	}
	
	private void drawGame(Graphics g) {
		//draw players
		g.setColor(Color.BLUE);
		g.fillRect(gameInfo.getPlayer(0).width,gameInfo.getPlayer(0).height, playerSizeX, playerSizeY);
		
		g.setColor(Color.RED);
		g.fillRect(gameInfo.getPlayer(1).width,gameInfo.getPlayer(1).height, playerSizeX, playerSizeY);
	}
	
	public void run() {
		init();
		
		while(running) {
			if(connected) {
			tick();
			render();
			keyboard.update();
			send();
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
