package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1000, HEIGHT = WIDTH / 12*9; //16:9
	private Thread thread;
	private boolean running = false;
	
	private Random r;//To spawn in random locations
	private Handler handler;//List of all gameObjects
	private HUD hud;
	private Spawn spawner;
	private MainMenu mainMenu;
	
	public enum STATE { //Set Game state from menu to either game
		MouseGame,
		TypeGame,
		MainMenu,
		OptionsMenu,
		MouseHSMenu,
		TypeHSMenu,
		MouseEndMenu,
		TypeEndMenu,
		PauseMenu;
	};
	
	public enum DIFF { //Difficulty on game
		easy,
		medium,
		hard;
	};
	

	//default states
	public STATE gameState = STATE.MainMenu;
	public DIFF diff = DIFF.easy;
	
	public Game() {
		handler = new Handler();
		mainMenu = new MainMenu(this, handler);
		hud = new HUD();
		this.addMouseListener(new MouseClick(handler, this));
		this.addMouseListener(mainMenu);
		
		//AudioPlayer.init(); //Will work when audiofiles in res
		//AudioPlayer.getMusic("music").loop(); //Loops music until exit
		
		new Window(WIDTH, HEIGHT, "KAMP", this);
		
		spawner = new Spawn(handler, hud); //Handles spawning square every 3 seconds without a click
		r = new Random(); 
		
		if(gameState == STATE.MouseGame) {//Spawn different unit based on selected difficulty
			if (diff == DIFF.easy) {
				handler.addObject(new Target(r.nextInt(WIDTH-128), r.nextInt(HEIGHT-128), ID.easy, handler));
			} else if (diff == DIFF.medium) {
				handler.addObject(new Target(r.nextInt(WIDTH-64), r.nextInt(HEIGHT-64), ID.medium, handler));
			} else {
				handler.addObject(new Target(r.nextInt(WIDTH-32), r.nextInt(HEIGHT-32), ID.hard, handler));
			}
			
		}
		if(gameState == STATE.TypeGame) { //Typegame diff string
			if (diff == DIFF.easy) {
				handler.addObject(new Target(r.nextInt(WIDTH-128), r.nextInt(HEIGHT-128), ID.easy, handler));
			} else if (diff == DIFF.medium) {
				handler.addObject(new Target(r.nextInt(WIDTH-64), r.nextInt(HEIGHT-64), ID.medium, handler));
			} else {
				handler.addObject(new Target(r.nextInt(WIDTH-32), r.nextInt(HEIGHT-32), ID.hard, handler));
			}
			
		}
		
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() { //Game loop
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; //60 ticks per sec (60 FPS)
		double ns = 1000000000 / amountOfTicks; 
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick(); //Tick the program
				delta--;
			}
			if (running) { //Render if running
				render();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() { //60 ticks per sec, updates game objects
		handler.tick();
		if (gameState == STATE.MouseGame) {
			hud.tick();
			spawner.tick();
		} else if(gameState == STATE.TypeGame) {
			//tick typegame hud
		} else {
			mainMenu.tick();
		} 
		
		
	}
	
	
	private void render() { //60fps, renders game objects
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);//g.drawimage here for backgrounds, use states to set background
		
		
		if(gameState == STATE.MouseGame || gameState == STATE.TypeGame) {
			hud.render(g);
		} else if(gameState == STATE.TypeGame) {
			//Render typegame
		} else {
			mainMenu.render(g);
		} 
		
		
		handler.render(g);
		
		
		g.dispose();
		bs.show();
	}
	
	public static void main (String args[]) {
		new Game();
	}
}
