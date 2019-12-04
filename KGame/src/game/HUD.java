package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.Game.STATE;

public class HUD {

	public int score;
	private int timer;
	private Game game;
	private Handler handler;
	private Image im;
	
	public HUD(Game game, Handler handler) {
		timer = 60 * 100;
		this.game = game;
		this.handler = handler;
		score = 0;
	}
	
	public void reset() {
		if (!handler.object.isEmpty()) {
			handler.removeObject(handler.object.getFirst());
		}
		timer = 60*100;
		score = 0;
	}
	
	public void tick() {
		timer--;
		if (timer == 0) {
			handler.removeObject(handler.object.getFirst());
			game.gameState = STATE.MouseEndMenu;
		}
	}
	
	public void render(Graphics g) {
		ImageIcon i = new ImageIcon("res/KAMPcg.png");
		im = i.getImage();
		g.drawImage(im, 0, 0, null);
		g.setColor(Color.red);
		g.setFont(new Font("TimeNewRoman", Font.PLAIN, 32));
		g.drawString("Score: "+ score, 15, 32);
		g.drawString("Time: " + timer/100, 15, 64);
	}
	
}
