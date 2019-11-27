package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import game.Game.DIFF;
import game.Game.STATE;

public class MainMenu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r;
	private LoadStrings temp;
	
	public MainMenu(Game game, Handler handler, LoadStrings temp) {
		this.temp = temp;
		this.game = game;
		this.handler = handler;
		r = new Random();
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.MainMenu) {
			//MG Button
			if (mouseOver(mx, my, Game.WIDTH/2-100, 200, 200, 64)) { //Launch Mousegame
				game.gameState = STATE.MouseGame;
				if (game.diff == DIFF.easy) {
					handler.addObject(new Target(r.nextInt(Game.WIDTH-128), r.nextInt(Game.HEIGHT-128), ID.easy, handler));
				} else if (game.diff == DIFF.medium) {
					handler.addObject(new Target(r.nextInt(Game.WIDTH-64), r.nextInt(Game.HEIGHT-64), ID.medium, handler));
				} else {
					handler.addObject(new Target(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.hard, handler));
				}
				
			}
			
			//TG Button
			if (mouseOver(mx, my, Game.WIDTH/2-100, 300, 200, 64)) {
				game.strArr = temp.getList();
				game.userOut.setText(game.strArr.get(r.nextInt(game.strArr.size())));
				game.gameState = STATE.TypeGame;
			}
			//Options Button
			if (mouseOver(mx, my, Game.WIDTH/2-100, 400, 200, 64)) {
				game.gameState = STATE.OptionsMenu;
			}
			//Quit Button
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) {
				System.exit(1);
			}
		}
		else if(game.gameState == STATE.OptionsMenu) {
			//Difficulty
			if (mouseOver(mx, my, 150, 100, 200, 64)) {
				game.diff = DIFF.easy;
			}
			if (mouseOver(mx, my, 400, 100, 200, 64)) {
				game.diff = DIFF.medium;
			}
			if (mouseOver(mx, my, 650, 100, 200, 64)) {
				game.diff = DIFF.hard;
			}
			//mouse hs
			if (mouseOver(mx, my, Game.WIDTH/2-100, 200, 200, 64)) {
				game.gameState = STATE.MouseHSMenu;
			}
			//keyboard hs
			if (mouseOver(mx, my, Game.WIDTH/2-100, 300, 200, 64)) {
				game.gameState = STATE.TypeHSMenu;
			}
			//mute
			if (mouseOver(mx, my, Game.WIDTH/2-100, 400, 200, 64)) {
				//Mute music
				
			}
			//Back Button
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) {
				game.gameState = STATE.MainMenu;
			}
		} else if (game.gameState == STATE.MouseHSMenu || game.gameState == STATE.TypeHSMenu ) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) {
				game.gameState = STATE.OptionsMenu;
			}
		} else if (game.gameState == STATE.MPauseMenu) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 400, 200, 64)) { //Continue
				game.gameState = STATE.MouseGame;
			}
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) { //Return to main / quit
				game.gameState = STATE.MainMenu;
				game.hud.reset();
			}
		} else if (game.gameState == STATE.TPauseMenu) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 400, 200, 64)) { //Continue
				game.gameState = STATE.TypeGame;
			}
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) { //Return to main / quit
				game.gameState = STATE.MainMenu;
				game.ip.reset();
			}
		} else if (game.gameState == STATE.MouseEndMenu) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) { //Return to main / quit
				game.gameState = STATE.MainMenu;
				game.hud.reset();
			}
		} else if (game.gameState == STATE.TypeEndMenu) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) { //Return to main / quit
				game.gameState = STATE.MainMenu;
				game.ip.reset();
			}
		}

	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
		if(mx > x && mx < x + w) {
			if(my  > y && my < y + h) {
				return true;
			}
		}
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		Font fnt = new Font("arial", 1, 30);
		g.setFont(fnt);
		if(game.gameState == STATE.MainMenu) {
			//Mouse Game
			g.setColor(Color.red);
			g.drawRect(Game.WIDTH/2-100, 200, 200, 64);
			g.drawString("Mouse Game", Game.WIDTH/2-95, 240);
			//Type Game
			g.drawRect(Game.WIDTH/2-100, 300, 200, 64);
			g.drawString("Type Game", Game.WIDTH/2-85, 340);
			//Options Button
			g.drawRect(Game.WIDTH/2-100, 400, 200, 64);
			g.drawString("Options", Game.WIDTH/2-55, 440);
			//Quit Button
			g.drawRect(Game.WIDTH/2-100, 500, 200, 64);
			g.drawString("Quit", Game.WIDTH/2-35, 540);
		}
		if(game.gameState == STATE.OptionsMenu) {
			
			g.setColor(Color.red);
			//Difficulty
			g.drawRect(150, 100, 200, 64); //easy
			g.drawString("Easy", 210, 140);
			g.drawRect(400, 100, 200, 64); //medium
			g.drawString("Medium", 445, 140);
			g.drawRect(650, 100, 200, 64); //hard
			g.drawString("Hard", 710, 140);
			//Mouse HS
			g.drawRect(Game.WIDTH/2-100, 200, 200, 64);
			g.drawString("Mouse HS", Game.WIDTH/2-75, 240);
			//Keyboard HS
			g.drawRect(Game.WIDTH/2-100, 300, 200, 64);
			g.drawString("Typing HS", Game.WIDTH/2-75, 340);
			//Vol Mute
			g.drawRect(Game.WIDTH/2-100, 400, 200, 64);
			g.drawString("Mute", Game.WIDTH/2-40, 440);
			//Back Button
			g.drawRect(Game.WIDTH/2-100, 500, 200, 64);
			g.drawString("Return", Game.WIDTH/2-50, 540);
		}
		if(game.gameState == STATE.MouseHSMenu || game.gameState == STATE.TypeHSMenu) {
			g.setColor(Color.red);
			//Back Button
			g.drawRect(Game.WIDTH/2-100, 500, 200, 64);
			g.drawString("Return", Game.WIDTH/2-50, 540);
		}
		if(game.gameState == STATE.MPauseMenu || game.gameState == STATE.TPauseMenu) {
			g.setColor(Color.red);
			//Continue Button
			g.drawRect(Game.WIDTH/2-100, 400, 200, 64);
			g.drawString("Continue", Game.WIDTH/2-50, 440);
			//Main Menu Button
			g.drawRect(Game.WIDTH/2-100, 500, 200, 64);
			g.drawString("Main Menu (Quit)", Game.WIDTH/2-50, 540);
		}
		if(game.gameState == STATE.MouseEndMenu) {
			
			g.setColor(Color.red);
			//Score
			g.drawString("Score: "+game.hud.score, Game.WIDTH/2-70, 440);
			//Main Menu Button
			g.drawRect(Game.WIDTH/2-100, 500, 200, 64);
			g.drawString("Main Menu", Game.WIDTH/2-70, 540);
		}
		if(game.gameState == STATE.TypeEndMenu) {
			
			g.setColor(Color.red);
			//Score
			g.drawString("Score: "+game.ip.score, Game.WIDTH/2-70, 440);
			//Main Menu Button
			g.drawRect(Game.WIDTH/2-100, 500, 200, 64);
			g.drawString("Main Menu", Game.WIDTH/2-70, 540);
		}
		
	}
}
