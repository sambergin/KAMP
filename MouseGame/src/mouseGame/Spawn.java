package mouseGame;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	
	public static int timer = 0;
	private Random r;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		
		r = new Random();
	}
	
	public void tick() {
		timer++;
		
		if (timer >= 300) {
			timer = 0;
			GameObject a = handler.object.getFirst();
			handler.removeObject(a);
			handler.addObject(new Target(r.nextInt(MouseGame.WIDTH -64), r.nextInt(MouseGame.HEIGHT -64), ID.easy, handler));
		}
	}
}
