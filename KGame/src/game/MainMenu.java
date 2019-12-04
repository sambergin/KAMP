package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.Game.DIFF;
import game.Game.STATE;

public class MainMenu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r;
	private LoadStrings temp;
	private BufferedImage image;
	
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
				AudioPlayer.getSound("menu_select").play();
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
				AudioPlayer.getSound("menu_select").play();
				game.strArr = temp.getList();
				game.userOut.setText(game.strArr.get(r.nextInt(game.strArr.size())));
				game.gameState = STATE.TypeGame;
			}
			//Options Button
			if (mouseOver(mx, my, Game.WIDTH/2-100, 400, 200, 64)) {
				AudioPlayer.getSound("menu_select").play();
				game.gameState = STATE.OptionsMenu;
			}
			//Quit Button
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) {
				AudioPlayer.getSound("menu_select").play();
				System.exit(1);
			}
		}
		else if(game.gameState == STATE.OptionsMenu) {
			//Difficulty
			if (mouseOver(mx, my, 150, 100, 200, 64)) {
				game.diff = DIFF.easy;
				AudioPlayer.getSound("menu_select").play();
			}
			if (mouseOver(mx, my, 400, 100, 200, 64)) {
				game.diff = DIFF.medium;
				AudioPlayer.getSound("menu_select").play();
			}
			if (mouseOver(mx, my, 650, 100, 200, 64)) {
				game.diff = DIFF.hard;
				AudioPlayer.getSound("menu_select").play();
			}
			//mouse hs
			if (mouseOver(mx, my, Game.WIDTH/2-100, 200, 200, 64)) {
				game.gameState = STATE.MouseHSMenu;
				AudioPlayer.getSound("menu_select").play();
			}
			//keyboard hs
			if (mouseOver(mx, my, Game.WIDTH/2-100, 300, 200, 64)) {
				game.gameState = STATE.TypeHSMenu;
				AudioPlayer.getSound("menu_select").play();
			}
			//mute
			if (mouseOver(mx, my, Game.WIDTH/2-100, 400, 200, 64)) {
				try {
					PlayMusic.playMusic("res/bensound-moose.wav", false);
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			//Back Button
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) {
				game.gameState = STATE.MainMenu;
				AudioPlayer.getSound("menu_select").play();
			}
		} else if (game.gameState == STATE.MouseHSMenu || game.gameState == STATE.TypeHSMenu ) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) {
				game.gameState = STATE.OptionsMenu;
				AudioPlayer.getSound("menu_select").play();
			}
		} else if (game.gameState == STATE.MPauseMenu) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 400, 200, 64)) { //Continue
				AudioPlayer.getSound("menu_select").play();
				game.gameState = STATE.MouseGame;
			}
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) { //Return to main / quit
				AudioPlayer.getSound("menu_select").play();
				game.gameState = STATE.MainMenu;
				game.hud.reset();
			}
		} else if (game.gameState == STATE.TPauseMenu) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 400, 200, 64)) { //Continue
				AudioPlayer.getSound("menu_select").play();
				game.gameState = STATE.TypeGame;
			}
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) { //Return to main / quit
				AudioPlayer.getSound("menu_select").play();
				game.gameState = STATE.MainMenu;
				game.ip.reset();
			}
		} else if (game.gameState == STATE.MouseEndMenu) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) { //Return to main / quit
				AudioPlayer.getSound("menu_select").play();
				game.gameState = STATE.MainMenu;
				game.hud.reset();
			}
		} else if (game.gameState == STATE.TypeEndMenu) {
			if (mouseOver(mx, my, Game.WIDTH/2-100, 500, 200, 64)) { //Return to main / quit
				AudioPlayer.getSound("menu_select").play();
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
	
	public void render(Graphics g) throws IOException {
		
		Font fnt = new Font("arial", 1, 30);
		g.setFont(fnt);
		if(game.gameState == STATE.MainMenu) {
			//Mouse Game
			
			g.setColor(Color.BLUE);
			
			g.fillRect(Game.WIDTH/2-100, 200, 200, 64);
			g.setColor(Color.YELLOW);
			g.drawString("Mouse Game", Game.WIDTH/2-95, 240);
			//Type Game
			g.setColor(Color.CYAN);
			g.fillRect(Game.WIDTH/2-100, 300, 200, 64);
			g.setColor(Color.PINK);
			g.drawString("Type Game", Game.WIDTH/2-85, 340);
			//Options Button
			g.setColor(Color.MAGENTA);
			g.fillRect(Game.WIDTH/2-100, 400, 200, 64);
			g.setColor(Color.YELLOW);
			g.drawString("Options", Game.WIDTH/2-55, 440);
			//Quit Button
			g.setColor(Color.RED);
			g.fillRect(Game.WIDTH/2-100, 500, 200, 64);
			g.setColor(Color.BLUE);
			g.drawString("Quit", Game.WIDTH/2-35, 540);
		}
		if(game.gameState == STATE.OptionsMenu) {
			
			g.setColor(Color.GREEN);
			//Difficulty
			g.fillRect(150, 100, 200, 64); //easy
			g.setColor(Color.WHITE);
			g.drawString("Easy", 210, 140);
			g.setColor(Color.ORANGE);
			g.fillRect(400, 100, 200, 64); //medium
			g.setColor(Color.WHITE);
			g.drawString("Medium", 445, 140);
			g.setColor(Color.RED);
			g.fillRect(650, 100, 200, 64); //hard
			g.setColor(Color.WHITE);
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
			g.setColor(Color.ORANGE);
			g.drawString("Game Paused", Game.WIDTH/2-100, 200);
			//Continue Button
			g.setColor(Color.BLUE);
			g.fillRect(Game.WIDTH/2-100, 400, 200, 64);
			g.setColor(Color.YELLOW);
			g.drawString("Continue", Game.WIDTH/2-70, 440);
			//Main Menu Button
			g.setColor(Color.MAGENTA);
			g.fillRect(Game.WIDTH/2-100, 500, 200, 64);
			g.setColor(Color.ORANGE);
			g.drawString("Main Menu", Game.WIDTH/2-80, 540);
		}
		if(game.gameState == STATE.MouseEndMenu) {
			
			g.setColor(Color.MAGENTA);
			//Score
			g.drawString("You Scored: "+game.hud.score, Game.WIDTH/2-70, 300);
			//Main Menu Button
			g.setColor(Color.MAGENTA);
			g.fillRect(Game.WIDTH/2-100, 500, 200, 64);
			g.setColor(Color.ORANGE);
			g.drawString("Main Menu", Game.WIDTH/2-80, 540);
		}
		if(game.gameState == STATE.TypeEndMenu) {
			
			g.setColor(Color.MAGENTA);
			//Score
			g.drawString("You Scored: "+game.ip.score, Game.WIDTH/2-70, 300);
			//Main Menu Button
			g.setColor(Color.MAGENTA);
			g.fillRect(Game.WIDTH/2-100, 500, 200, 64);
			g.setColor(Color.ORANGE);
			g.drawString("Main Menu", Game.WIDTH/2-80, 540);
		}
		
	}
}
