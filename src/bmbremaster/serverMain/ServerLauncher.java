package bmbremaster.serverMain;

import java.awt.FlowLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerLauncher {
	static JTextField textfield1, textfield2;
	static Updater game = null;
	static boolean isStarted = false;
		
	public static void main(String[] args) {
		
			JFrame f = new JFrame("Text Field Examples");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setResizable(false);
			f.setSize(600,600);
			f.setLocationRelativeTo(null);
		    f.getContentPane().setLayout(new FlowLayout());
		    textfield1 = new JTextField("6666",10);
		    textfield2 = new JTextField("map",10);
		    f.getContentPane().add(textfield1);
		    f.getContentPane().add(textfield2);
		    
		    
		    JButton btn = new JButton("Start");
			btn.addActionListener(e -> {
				if(!isStarted) {
					game = new Updater(Integer.parseInt(textfield1.getText()), textfield2.getText());
					game.start();
					isStarted = true;
				}
			});
			
			 JButton btn2 = new JButton("Stop");
				btn2.addActionListener(e -> {
					if(isStarted) {
						game.stop();
						isStarted = false;
					}
				});
			
			f.getContentPane().add(btn);
			f.getContentPane().add(btn2);
			
			
			JTextArea textArea = new JTextArea(30, 50);
			JScrollPane scrollPane = new JScrollPane(textArea);
			f.getContentPane().add(scrollPane);
			PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
			System.setOut(printStream);
			System.setErr(printStream);

			f.setVisible(true);
		    		
		}
	
	static public class CustomOutputStream extends OutputStream {
	    private JTextArea textArea;
	     
	    public CustomOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
	    }
	     
	    @Override
	    public void write(int b) throws IOException {
	        textArea.append(String.valueOf((char)b));
	        textArea.setCaretPosition(textArea.getDocument().getLength());
	    }
	}
		
	}
