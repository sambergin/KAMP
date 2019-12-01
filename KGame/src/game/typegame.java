package game;

import java.util.ArrayList;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;



import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class TypeGame extends javax.swing.JFrame
{
	public TypeGame() {
	}
	
	public static int score=0;;
	public static ArrayList<String> strArr = new ArrayList<String>();
	
	
	public static boolean easy = false;
	public static boolean normal = false;
	public static boolean hard = false;
	
	//private static void initComponents()
	//{
	private static JFrame base = new JFrame();
	private static JPanel baseP = new JPanel();
		
	private static JLabel showScore = new JLabel("Score: " + score);
	private static JLabel outMes = new JLabel("Type this... FAST!!!");
	
	
	private static JTextField stringOut = new JTextField("",200);
	private static JTextField inputArea = new JTextField("", 200);
	private static JButton subButton = new JButton("ENTER");
	private static Font myFont = new Font("SchoolHouse Printed A", Font.BOLD, 20);
    
	public static int time=0;
	private static JLabel showTime = new JLabel("Time: %d", time);
	public static int relative_time=0;
	private static JLabel showRelTime = new JLabel("Relative time: %d", relative_time);
	public static Timer timer = new Timer();
	public static TimerTask task = new TimerTask()
	{
		public void run()
		{
			
			
			showTime.setText(String.format("Time: %d", time));
			showRelTime.setText(String.format("Time Spent on This Phrase: %d", relative_time));
			
			if(time <= 60) {
				
				if(relative_time>=60)
					{
						relative_time=0;
						try {
							playGame(strArr);
						} catch (LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			
				time++;
				relative_time++;	
				
		
			}
			else {
				endGame();
				
				
			}
			
		}
	};
	

	
	private static void initComponents() 
	{
	
    base.getContentPane().setBackground(Color.MAGENTA);
        
    base.setDefaultCloseOperation(EXIT_ON_CLOSE);
    base.setSize(new Dimension(1000,400));
        
    base.setVisible(true);
    base.getContentPane().setLayout(new BorderLayout());
        
    Border borderInput = BorderFactory.createLineBorder(Color.BLUE, 5);
    inputArea.setBorder(borderInput);
    inputArea.setBackground(Color.ORANGE);    
    Border borderOutput = BorderFactory.createLineBorder(Color.MAGENTA, 5);
    stringOut.setBorder(borderOutput);
    stringOut.setBackground(Color.BLUE);    
    inputArea.setSize(200,20);
    inputArea.setBackground(Color.ORANGE);
    inputArea.setForeground(Color.BLUE);
    inputArea.setFont(myFont);
    inputArea.setHorizontalAlignment(JTextField.CENTER);
    
    stringOut.setSize(200,20);
    stringOut.setBackground(Color.GREEN);
    stringOut.setForeground(Color.MAGENTA);
    stringOut.setFont(myFont);
    stringOut.setHorizontalAlignment(JTextField.CENTER);
    stringOut.setEditable(false);
    
    outMes.setVerticalAlignment(JLabel.CENTER);
    outMes.setHorizontalAlignment(JLabel.CENTER);
    outMes.setSize(200,20);
    outMes.setBackground(Color.PINK);
    outMes.setVisible(true);
    
    
    
    
    baseP.setLayout(new BoxLayout(baseP, BoxLayout.Y_AXIS));
    baseP.add(outMes);
    baseP.add(stringOut);
    baseP.add(inputArea);
    
    baseP.add(showScore);
    baseP.add(showTime);
    baseP.add(showRelTime);
    baseP.setBackground(Color.YELLOW);
    
        
    base.getContentPane().add(baseP);
    
        
       
	}
	
	
	
	private static void registerListeners()
	{
		class subButtonListener implements KeyListener
		{
			

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(stringOut.getText().equals(inputArea.getText()))
					{
						score += 100 - relative_time;
						showScore.setText(String.format("Score: %d", score));
						relative_time=0;
						inputArea.setText("");
						try {
							playGame(strArr);
						} catch (LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						score -= 50;
						showScore.setText(String.format("Score: %d", score));
						relative_time=0;
						inputArea.setText("");
						try {
							playGame(strArr);
						} catch (LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		
		
		inputArea.addKeyListener(new subButtonListener());
	}
	
	private static void typeGame(ArrayList strs) throws LineUnavailableException
	{
		initComponents();
		
		Random random = new Random();
		int sent_index = random.nextInt(strs.size());
		
		stringOut.setText(strs.get(sent_index).toString());
		timer.scheduleAtFixedRate(task, 0, 1000);
		playMusic("res/typesong.wav");
	}

	private static void playGame(ArrayList strs) throws LineUnavailableException
	{
		
			
				Random random = new Random();
				int sent_index = random.nextInt(strs.size());
				
				stringOut.setText(strs.get(sent_index).toString());
				
		
	}
	
	private static void endGame() {
		if (score >= 2000) {
			stringOut.setText(String.format("Game Over! You Scored: %d, Fantastic!", score));
		}
		else if ((score >= 1500) && (score <= 1999)) {
			stringOut.setText(String.format("Game Over! You Scored: %d, Great Job!", score));
		}
		else if ((score >= 1000) && (score <= 1499)) {
			stringOut.setText(String.format("Game Over! You Scored: %d, Good Job!", score));
		}
		else if ((score >= 500) && (score <= 999)) {
			stringOut.setText(String.format("Game Over! You Scored: %d, Nice!", score));
		}
		else if ((score >= 0) && (score <= 199)) {
			stringOut.setText(String.format("Game Over! You Scored: %d, Keep Practicing!", score));
		}
	}
	
	static void playMusic(String songloc) throws LineUnavailableException {
		
		try {
			File song = new File(songloc);
			if(song.exists()) {
				AudioInputStream audio = AudioSystem.getAudioInputStream(song);
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
				
			}
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}
}