package mouseGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Target extends GameObject {

	private Handler handler;
	
	public Target(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds() {
		return (new Rectangle(x, y, 32, 32));
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

}
