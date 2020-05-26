package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Client {

	private JFrame frame;
	private JTextField messageField;
	private static JTextArea textArea = new JTextArea();
	
	private ClientClass client;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Client() {
		initialize();
		String name = JOptionPane.showInputDialog("Enter name:");
		client = new ClientClass(name, "localhost", 52864);
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Chat program");
		frame.setBounds(100, 100, 795, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setLocationRelativeTo(null);
	
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnButton = new JButton("Send");
		btnButton.addActionListener(e -> {
			client.send(messageField.getText());
			messageField.setText("");
		});
		
		messageField = new JTextField();
		panel.add(messageField);
		messageField.setColumns(50);
		panel.add(btnButton);


	}
	
	
	public static void printToConsole(String message) {
		textArea.setText(textArea.getText() + message + "\n");
	}
}
