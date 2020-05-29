package bomberman.chat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bomberman.client.Client;
import bomberman.server.Msg;

public class Chat {
	private JTextField messageField;
	private static JTextArea textArea = new JTextArea();
	private Client client;
	
	public Chat(JPanel lpanel, Client client, String nick) {
		this.client = client;
		
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		lpanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		lpanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnButton = new JButton("Send");
		btnButton.addActionListener(e -> {
			try {
				client.sendMessage(new Msg(nick + ": " + messageField.getText()));
			} catch (IOException e1) {
				e1.printStackTrace();
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
	}

	
	public void printToConsole(String message) {
		textArea.setText(textArea.getText() + message + "\n");
	}
}
