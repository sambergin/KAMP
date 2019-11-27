package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	public boolean p = false; //False when unpaused
	
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
		}
	}
	public void keyReleased(KeyEvent e) {
		
	}
}
