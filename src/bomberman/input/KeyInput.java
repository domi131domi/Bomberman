package bomberman.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import bomberman.objects.GameObject;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput( Handler handler ) {
		this.handler = handler;
	}
	
	public void keyPressed( KeyEvent e ) {
		int key = e.getKeyCode();
		
		for( int i = 0; i < handler.object.size() ; i++ ) {
			GameObject tempObject = handler.object.get(i);
			
			if( tempObject.getID() == ID.Player1 ) {
				if( key == KeyEvent.VK_B ) tempObject.boostVelocity(1);
				if( key == KeyEvent.VK_L ) tempObject.boostVelocity(-1);
				if( key == KeyEvent.VK_W ) tempObject.setVelocityY( (-1) * tempObject.getCurrentVelocity() );
				if( key == KeyEvent.VK_S ) tempObject.setVelocityY( tempObject.getCurrentVelocity() );
				if( key == KeyEvent.VK_A ) tempObject.setVelocityX( (-1) * tempObject.getCurrentVelocity() );
				if( key == KeyEvent.VK_D ) tempObject.setVelocityX( tempObject.getCurrentVelocity() );
			}
		}
	}
	
	public void keyReleased( KeyEvent e ) {
		int key = e.getKeyCode();
		
		for( int i = 0; i < handler.object.size() ; i++ ) {
			GameObject tempObject = handler.object.get(i);
		
			if( tempObject.getID() == ID.Player1 ) {
				if( key == KeyEvent.VK_W ) tempObject.setVelocityY(0);
				if( key == KeyEvent.VK_S ) tempObject.setVelocityY(0);
				if( key == KeyEvent.VK_A ) tempObject.setVelocityX(0);
				if( key == KeyEvent.VK_D ) tempObject.setVelocityX(0);
			}
		}
	}
}