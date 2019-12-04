package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Target extends GameObject {

	private Random r;
	int a;
	
	public Target(int x, int y, ID id) {
		super(x, y, id);

		r = new Random();
		a = r.nextInt(3);
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
		if (a == 0) {
			g.setColor(Color.red);
		} else if (a == 1) {
			g.setColor(Color.orange);
		} else if (a == 2) {
			g.setColor(Color.green);
		} else {
			g.setColor(Color.blue);
		}
		
		
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
