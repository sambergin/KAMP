package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JTextField;

public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4083421003724088077L;
	
	public static final int WIDTH = 1000, HEIGHT = WIDTH / 12*9; //16:9
	private Thread thread;
	private boolean running = false;
	
	private Random r;//To spawn in random locations
	private Handler handler;//List of all gameObjects
	private LoadStrings temp;
	private HighScores tempM;
	public HUD hud;
	private Spawn spawner;
	private MainMenu mainMenu;
	public KeyInput ip;
	public HighScores hs;
	public ArrayList<String> strArr;//List of loaded strings
	public ArrayList<String> mouseHS;
	public JTextField userIn;
	public JTextField userOut;
	public Font font;
	
	public enum STATE { //Set Game state from menu to either game
		MouseGame,
		TypeGame,
		MainMenu,
		OptionsMenu,
		MouseHSMenu,
		TypeHSMenu,
		MouseEndMenu, 
		TypeEndMenu,
		MPauseMenu,
		TPauseMenu;
	};
	
	public enum DIFF { //Difficulty on game
		easy,
		medium,
		hard;
	};
	

	//default states
	public STATE gameState = STATE.MainMenu;
	public DIFF diff = DIFF.easy;
	
	public Game() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		font = new Font("SchoolHouse Printed A", Font.BOLD, 20);
		temp = new LoadStrings(this);
		tempM = new HighScores(this);
		handler = new Handler();
		mainMenu = new MainMenu(this, handler, temp, tempM);
		hud = new HUD(this, handler);
		userIn = new JTextField("", 200);
		userIn.setFont(font);
		userOut = new JTextField("", 200);
		userOut.setFont(font);
		ip = new KeyInput(this);
		mouseHS = tempM.getList();
		this.addMouseListener(new MouseClick(handler, this));
		this.addMouseListener(mainMenu);
		this.addKeyListener(ip);
		
		AudioPlayer.init(); //Will work when audiofiles in res
		AudioPlayer.getMusic("music").loop(); //Loops music until exit

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
			strArr = temp.getList();
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
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick(); //Tick the program
				delta--;
			}
			if (running) { //Render if running
				try {
					render();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	private void tick() { //60 ticks per sec, updates game objects
		
		if (gameState == STATE.MouseGame) {
			handler.tick();
			hud.tick();
			spawner.tick();
			
		} else if(gameState == STATE.TypeGame) {
			handler.tick();
			ip.tick();
		} else{
			mainMenu.tick();
		} 
		
		
	}
	
	
	private void render() throws IOException { //60fps, renders game objects
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);//g.drawimage here for backgrounds, use states to set background
		
		
		if(gameState == STATE.MouseGame) {
			hud.render(g);
			handler.render(g);
		} else if(gameState == STATE.TypeGame) {
			
			ip.render(g);
			handler.render(g);
		} else{
			mainMenu.render(g);
		} 
		
		
		g.dispose();
		bs.show();
	}
	
	public static void main (String args[]) {
		try {
			new Game();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
