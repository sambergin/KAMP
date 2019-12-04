package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import game.Game.DIFF;
import game.Game.STATE;

public class MouseClick extends MouseAdapter{

	private Handler handler;
	private Game game;
	private Random r;
	
	public MouseClick (Handler handler, Game game) {
		this.handler = handler;
		this.game = game;

		r = new Random();
	}
	
	public Rectangle getBounds(int x, int y) {
		if (game.diff == DIFF.easy) {
			return (new Rectangle(x, y, 64, 64));
		} else if (game.diff == DIFF.medium) {
			return (new Rectangle(x, y, 32, 32));
		} else {
			return (new Rectangle(x, y, 16, 16));
		}
		
	}
	public void mousePressed(MouseEvent e) {
		
		if (game.gameState == STATE.MouseGame) {
			Point loc = e.getPoint();
			
			int miss = game.hud.score;
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject temp = handler.object.get(i);
				
				if(getBounds(temp.getX(), temp.getY()).contains(loc)) {//If we clicked a target
					AudioPlayer.getSound("target_hit").play();
					GameObject a = handler.object.getFirst();
					handler.removeObject(a);
					if(temp.getId() == ID.easy) {//Score based on difficulty
						game.hud.score += 10;
						handler.addObject(new Target(r.nextInt(Game.WIDTH-128), r.nextInt(Game.HEIGHT-128), ID.easy));
					}
					if(temp.getId() == ID.medium) {
						game.hud.score += 20;
						handler.addObject(new Target(r.nextInt(Game.WIDTH-64), r.nextInt(Game.HEIGHT-64), ID.medium));
					}
					if(temp.getId() == ID.hard) {
						game.hud.score += 30;
						handler.addObject(new Target(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.hard));
					}
					Spawn.timer = 0;
				}
			}
			if (miss == game.hud.score) {
				AudioPlayer.getSound("target_miss").play();
				game.hud.score -= 20;
			}
		}
		
	}
	
}
