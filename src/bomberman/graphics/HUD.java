package bomberman.graphics;

import java.awt.Color;
import java.awt.Graphics;

import bomberman.main.Game;

public class HUD {
	
	public static int HEALTH1 = 100;
	public static int HEALTH2 = 0;
	
	private int greenValue1 = 255;
	private int greenValue2 = 255;
	
	public void tick() {
		HEALTH1 = Game.clamp(HEALTH1, 0, 100);
		HEALTH2 = Game.clamp(HEALTH2, 0, 100);
		greenValue1 = Game.clamp( greenValue1, 0, 255 );
		greenValue2 = Game.clamp( greenValue2, 0, 255 );
		greenValue1 = HEALTH1*2;
		greenValue2 = 200 - HEALTH2*2;
	}
	
	public void render( Graphics g ) {
		g.setColor( Color.gray );
		g.fillRect( 16, 16, 200, 16 );
		g.setColor( new Color(100, greenValue1, 0 ) );
		g.fillRect( 16, 16, HEALTH1 * 2, 16 );
		g.setColor( Color.white );
		g.drawRect( 16, 16, 200, 16 );
		
		g.setColor( Color.gray );
		g.fillRect( Game.WIDTH - 32 - 200, 16, 200, 16 );
		g.setColor( new Color(100, greenValue2, 0 ) );
		g.fillRect( Game.WIDTH - 32 - 200 + HEALTH2*2, 16, 200 - HEALTH2*2, 16 );
		g.setColor( Color.white );
		g.drawRect( Game.WIDTH - 32 - 200, 16, 200, 16 );
	}
	
	
}
