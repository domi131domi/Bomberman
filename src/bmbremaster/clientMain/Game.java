package bmbremaster.clientMain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import bmbremaster.server.Msg;
import bmbremaster.client.Client;

public class Game implements Runnable {
	
	private ClientWindow window;
	private Thread thread;
	private boolean running = false;
	private Client client;
	private KeyInput keyboard;
	
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
		
		try {
			client.connect("localhost", 6666);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void tick() {
		
	}
	
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null ) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		int p1x = 0, p1y = 0, p2x = 0, p2y = 0;
		
		//Load coordinates
		try {
			Msg coords = client.getMessage();
			p1x = coords.p1x;
			p2x = coords.p2x;
			p1y = coords.p1y;
			p2y = coords.p2y;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		
		g.setColor(Color.red);
		g.fillRect(p1x, p1y, 50, 70);
		
		g.setColor(Color.green);
		g.fillRect(p2x, p2y, 50, 50);
		
		bs.show();
		g.dispose();
		
		keyboard.update();
		
		try {
			client.sendMessage(new Msg(keyboard.left, keyboard.right, keyboard.up, keyboard.down));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void init() {
		
	}
	
	public void run() {
		init();
		
		while(running) {
			tick();
			render();
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
