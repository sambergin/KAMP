package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyInput extends KeyAdapter {
	
	public boolean p = false; //False when unpaused
	private Game game;
	private String s;
	private int timer;
	
	public KeyInput(Game game) {
		this.game = game;
		s = "";
		timer = 60*100;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) {
			if (p) {
				p = false;
				//unpause
			} else {
				p = true;
				//pause
			}
		}  else if (key == KeyEvent.VK_ENTER) {
			//Comp string, add points
			s = "";
			game.userIn.setText(s);//update text string
			
		}
		else if (key == KeyEvent.VK_BACK_SPACE) {
			//Comp string, add points
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
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawString(game.userIn.getText(), 0, 200);
		g.drawString("Time: " + timer/100, 0, 200);
		
		
	}
}
