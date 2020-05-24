package bomberman.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	
	private static final long serialVersionUID = 10987654321L;
	
	public Window( int width, int height, String title, Game game ) {
		 
		JFrame frame = new JFrame( title );
		
		frame.setPreferredSize( new Dimension( width, height ) );
		frame.setMaximumSize( new Dimension( width, height ) );
		frame.setMinimumSize( new Dimension( width, height ) );
		
		//stopping the thread of the application
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		//false due to i do not want changing the size (for now, at least)
		frame.setResizable( false );
		
		//initializing a new window with null places the window in the middle of the screen  
		frame.setLocationRelativeTo(null);
		
		frame.add( game );
		frame.setVisible( true );
		
		game.start();
	}

}
