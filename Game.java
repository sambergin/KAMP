package screens;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Game extends Canvas implements Runnable {

	public final static String TITLE = "KAMP";
	public static final int WIDTH = 600;
	public static final int HEIGHT = 450;

	private boolean run = false;
	private Thread t;

	private BufferedImage image1 = null;

	public static void main(String args[]) {
		Game screen = new Game();

		screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		screen.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		screen.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame(screen.TITLE);
		JButton tGame = new JButton("Band KAMP (Typing Gane)");
		JButton cGame = new JButton("Space KAMP (Clicking Game)");
		JButton hScore = new JButton("High Scores");
		JButton options = new JButton("Options");

		tGame.setBounds(25, 325, 250, 100);
		cGame.setBounds(25, 175, 250, 100);
		hScore.setBounds(325, 175, 250, 100);
		options.setBounds(325, 325, 250, 100);

		frame.add(tGame);
		frame.add(cGame);
		frame.add(options);
		frame.add(hScore);
		frame.add(screen);
		frame.pack();
		frame.setBackground(Color.RED);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.black);
		frame.setVisible(true);

		screen.start();
	}


	private void render() {
		BufferStrategy b = this.getBufferStrategy();
		if(b==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = b.getDrawGraphics();
		/////////////////////////////////////////

		g.drawImage(image1, 0, 0, getWidth(), getHeight(), null);

		/////////////////////////////////////////
		g.dispose();
		b.show();
	}
	private void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			image1 = loader.loadImage("/KAMP.png");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	private synchronized void start() {
		if(run==true) {
			return;
		}
		run = true;
		t = new Thread(this);
		t.start();
	}

	private synchronized void stop() {
		if(run==false) {
			return;
		}

		run = false;
		try {
			t.join();
		}
		catch(InterruptedException e){ 
			e.printStackTrace();
		}
		System.exit(1);
	}
	public void run() {
		init();
		while(run == true) {
			System.out.println("WORKING");
			render();
		}
		stop();
	}
}
