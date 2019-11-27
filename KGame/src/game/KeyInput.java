package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import game.Game.STATE;



public class KeyInput extends KeyAdapter {
	
	public boolean p = false; //False when unpaused
	private Game game;
	private String s;
	private int timer;
	private int rel_time;
	private int score;
	private Random r;
	
	public KeyInput(Game game) {
		this.game = game;
		s = "";
		r = new Random();
		timer = 60*100;
		rel_time = 0;
		score = 0;
	}
	
	public String selStr() {
		String a = game.strArr.get(r.nextInt(game.strArr.size()));
		return a;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) {
			if (p) {
				p = false;
				//unpause (change game state, dont reset timer)
			} else {
				p = true;
				//pause (change game state, dont reset timer)
			}
		}  else if (key == KeyEvent.VK_ENTER) {
			//Comp string, add points
			if (game.userIn.getText().equals(game.userOut.getText())) {
				score += 100 - (rel_time/10);
			}
			game.userOut.setText(selStr());
			rel_time = 0;
			s = "";
			game.userIn.setText(s);//update text string
			
		}
		else if (key == KeyEvent.VK_BACK_SPACE) {
			//Backspace string
			s = s.substring(0, s.length()-1);
			game.userIn.setText(s);//update text string
			
		}
	}
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		char t = e.getKeyChar();
		//Only accepts alphabet, space, period and comma
		if (t == 32 || t == 33 || t == 44 || t == 46 || (t>64 && t<90) || (t>96 && t<123)) {
			s = s + t;
			game.userIn.setText(s);//update text string
		}
	}
	
	public void tick() {
		timer--;
		rel_time++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawString(game.userOut.getText(), 0, 50);
		g.drawString(game.userIn.getText(), 0, 100);
		g.drawString("Time: " + timer/100, 0, 200);
		g.drawString("RelTime: " + rel_time/100, 0, 300);
		g.drawString("Score: " + score, 0, 400);
		
		
	}
}
