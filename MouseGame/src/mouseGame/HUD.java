package mouseGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static int score = 0;
	private int timer = 0;
	
	public HUD() {
		score = 0;
	}
	
	public void tick() {
		timer++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("TimeNewRoman", Font.PLAIN, 32));
		g.drawString("Score: "+ score, 15, 32);
		g.drawString("Time: " + timer, 15, 64);
	}
	
}
