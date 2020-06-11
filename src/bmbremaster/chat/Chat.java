package bmbremaster.chat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bmbremaster.client.Client;
import bmbremaster.server.Msg;

public class Chat {
	private JTextField messageField;
	private static JTextArea textArea = new JTextArea();
	private Client client;
	private String nickname;
	
	public Chat(JPanel lpanel, Client client, String nickname) {
		this.client = client;
		this.nickname = nickname;

		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		lpanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		lpanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnButton = new JButton("Send");
		btnButton.addActionListener(e -> {
			if(!messageField.getText().isEmpty()) {
				if(isCommand(messageField.getText())) {
					handleCommand(messageField.getText());
				} else {
					try {
					client.sendMessage(new Msg(this.nickname + ": " + messageField.getText()));
					} catch(IOException e1) {
						printToConsole("Couldnt send message");
					}
				}
			}
			messageField.setText("");
		});
		
		messageField = new JTextField();
		panel.add(messageField);
		messageField.setColumns(20);
		panel.add(btnButton);
		
		Font font = textArea.getFont();
		float size = font.getSize() + 7.f;
		textArea.setFont( font.deriveFont(size) );
		
		printToConsole("Type: /help for info.");
		lpanel.getRootPane().setDefaultButton(btnButton);
}

	
	public void printToConsole(String message) {
		if(!message.isEmpty())
			textArea.setText(textArea.getText() + message + "\n");
	}
	
	private boolean isCommand(String message) {
		return message.startsWith("/");
	}
	
	private void handleCommand(String message) {
		printToConsole(message);
		
		if(message.startsWith("/connect ")) {
			try {
				message = message.substring(message.indexOf(" ") + 1);
				String ip = message.substring(0, message.indexOf(" "));
				int port = Integer.parseInt(message.substring(message.indexOf(" ") + 1));
				client.connect(ip, port);
				printToConsole("Connected to: \n" + ip + ":" + port);
			} catch(IOException e) {
				printToConsole("Server not found.");
			} catch(NumberFormatException | StringIndexOutOfBoundsException e1) {
				printToConsole("Unknown command. Check /help.");
			}
		} else if(message.startsWith("/disconnect")) {
			try {
				client.disconnect();
				printToConsole("Disconnected");
			} catch(IOException | NullPointerException e) {
				printToConsole("Couldnt disconnect.");
			}
		} else if(message.startsWith("/set name ")) {
			message = message.substring(10);
			this.nickname = message;
			printToConsole("Set name to: "+ message);
		} else if(message.startsWith("/help")) {
			printToConsole("Avaible commands:\n/help\n/connect [IPadress] [port]\n/disconnect\n/set name [nickname]");
		} else {
			printToConsole("Unknown command. Check /help.");
		}
	}
	
}
