package bomberman.chat;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import bomberman.client.Client;

import java.awt.BorderLayout;
import java.awt.Dimension;


public class Test {

	private JFrame frame;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Test() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Chat program");
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setLocationRelativeTo(null);
	
		JPanel lpanel = new JPanel();
		lpanel.setLayout(new BorderLayout(0,0));
		lpanel.setSize(new Dimension(50,800));
		frame.getContentPane().add(lpanel, BorderLayout.WEST);
		
		Client client = new Client();
		try {
			client.connect("localhost", 6666);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Chat chat = new Chat(lpanel, client, "Adam");
		
		Thread listenThread = new Thread("listenForClients") {
			public void run() {
		while(true) {
			
			
			try {
				chat.printToConsole(client.getMessage().getSystemMessage());
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
		}
		}}; listenThread.start();
		
	}
	
}
