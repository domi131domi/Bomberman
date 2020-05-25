package bomberman.objects.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import bomberman.objects.ID;

public class UndestroyableBlock extends GameObject {
	
	public static final int BLOCK_SIZE = 48;
	
	public UndestroyableBlock( int x, int y, ID id ) {
		super( x, y, id );
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor( Color.blue );
		g.fillRect( x, y, 32, 32 );
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
}
