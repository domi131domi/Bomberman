package bmbremaster.clientMain;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;

import bmbremaster.chat.Chat;
import bmbremaster.client.Client;

public class ClientWindow {
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	private Client client;
	private final int chatWidth = 400;
	private Chat chat;
	
	public ClientWindow(String title, int width, int height, Client client) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.client = client;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame(title);
		frame.setSize(width + chatWidth,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(0,0));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setBackground(new Color(255, 174, 201, 0));
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas, BorderLayout.WEST);
		
		JPanel jchat = new JPanel();
		jchat.setLayout(new BorderLayout(0,0));
		jchat.setSize(new Dimension(400,800));
		jchat.setMinimumSize(new Dimension(50, 800));
		frame.add(jchat, BorderLayout.EAST);
		
		chat = new Chat(jchat, client, "Default");
		
		frame.pack();
	
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public Chat getChat() {
		return this.chat;
	}
	
}
