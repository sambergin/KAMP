package game;

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
		//easy
		if (this.id == ID.easy) {
			return (new Rectangle(x, y, 64, 64));
		}
		
		//medium
		else if (this.id == ID.medium) {
			return (new Rectangle(x, y, 32, 32));
		}
		//hard
		else {
			return (new Rectangle(x, y, 16, 16));
		}
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		
		//easy
		if (this.id == ID.easy) {
			g.fillRect(x, y, 64, 64);
		}
		
		//medium
		if (this.id == ID.medium) {
			g.fillRect(x, y, 32, 32);
		}
		//hard
		if (this.id == ID.hard) {
			g.fillRect(x, y, 16, 16);
		}
	}

}
