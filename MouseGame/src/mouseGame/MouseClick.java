package mouseGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MouseClick extends MouseAdapter{

	private Handler handler;
	
	private Random r;
	
	public MouseClick (Handler handler) {
		this.handler = handler;

		r = new Random();
	}
	
	public Rectangle getBounds(int x, int y) {
		return (new Rectangle(x, y, 32, 32));
	}
	
	public void mouseReleased(MouseEvent e) {
		Point loc = e.getPoint();
		
		int miss = HUD.score;
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			if(getBounds(temp.getX(), temp.getY()).contains(loc)) {//If we clicked a target
				GameObject a = handler.object.getFirst();
				handler.removeObject(a);
				if(temp.getId() == ID.easy) {//Score based on difficulty
					HUD.score += 10;
					handler.addObject(new Target(r.nextInt(MouseGame.WIDTH-64), r.nextInt(MouseGame.HEIGHT-64), ID.easy, handler));
				}
				if(temp.getId() == ID.medium) {
					HUD.score += 20;
					handler.addObject(new Target(r.nextInt(MouseGame.WIDTH-64), r.nextInt(MouseGame.HEIGHT-64), ID.medium, handler));
				}
				if(temp.getId() == ID.hard) {
					HUD.score += 30;
					handler.addObject(new Target(r.nextInt(MouseGame.WIDTH-64), r.nextInt(MouseGame.HEIGHT-64), ID.hard, handler));
				}
				Spawn.timer = 0;
			}
		}
		if (miss == HUD.score) {
			HUD.score -= 20;
		}
		
	}
	
}
