package typegame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class typegame extends javax.swing.JFrame
{
	
	public static int score=0;;
	public static ArrayList<String> easyStr = new ArrayList<String>();
	public static ArrayList<String> normStr = new ArrayList<String>();
	public static ArrayList<String> hardStr = new ArrayList<String>();
	public static ArrayList<String> chosenStr = new ArrayList<String>();
	
	public static boolean easy = false;
	public static boolean normal = false;
	public static boolean hard = false;
	
	//private static void initComponents()
	//{
	private static JFrame base = new JFrame();
	private static JPanel baseP = new JPanel();
		
	private static JLabel showScore = new JLabel("Score: " + score);
	private static JTextField stringOut = new JTextField("",200);
	private static JTextField inputArea = new JTextField("", 200);
	private static JButton subButton = new JButton("ENTER");
	private static Font myFont = new Font("Party LET", Font.BOLD, 20);
    
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
						if(easy==true)
						{
							playGame(easyStr);
						}
						else if(normal == true)
						{
							playGame(normStr);
						}
						else if(hard == true)
						{
							playGame(hardStr);
						}
						else
						{
							//no difficulty selected, handle error case
						}
						
				}
				time++;
				relative_time++;	
				
		
			}
			else {
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
			
		}
	};
	
	private static void initComponents() 
	{
    base.getContentPane().setBackground(Color.MAGENTA);
        
    base.setDefaultCloseOperation(EXIT_ON_CLOSE);
    base.setSize(new Dimension(480, 360));
        
    base.setVisible(true);
    base.setLayout(new BorderLayout());
        
    Border borderInput = BorderFactory.createLineBorder(Color.BLUE, 5);
    inputArea.setBorder(borderInput);
        
    Border borderOutput = BorderFactory.createLineBorder(Color.MAGENTA, 5);
    stringOut.setBorder(borderOutput);
        
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
   
        
    baseP.setLayout(new BoxLayout(baseP, BoxLayout.Y_AXIS));
    baseP.add(stringOut);
    baseP.add(inputArea);
    baseP.add(subButton);
    baseP.add(showScore);
    baseP.add(showTime);
    baseP.add(showRelTime);
    baseP.setBackground(Color.CYAN);
        
    base.add(baseP);
    
        
        /*base.add(stringOut, BorderLayout.NORTH);
        base.add(inputArea, BorderLayout.CENTER);
        base.add(subButton, BorderLayout.EAST);*/
        
	}
	
	private static void registerListeners()
	{
		class subButtonListener implements ActionListener
		{
			public void actionPerformed(final ActionEvent e)
			{
				if(stringOut.getText().equals(inputArea.getText()))
				{
					score += 100 - relative_time;
					showScore.setText(String.format("Score: %d", score));
					relative_time=0;
					inputArea.setText("");
					playGame(easyStr);
				}
				else
				{
					score -= 50;
					showScore.setText(String.format("Score: %d", score));
					relative_time=0;
					inputArea.setText("");
					playGame(easyStr);
				}
			}
		}
		
		subButton.addActionListener(new subButtonListener());
	}
	
	private static void typeGame(ArrayList strs)
	{
		initComponents();
		
		Random random = new Random();
		int sent_index = random.nextInt(99);
		
		stringOut.setText(strs.get(sent_index).toString());
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

	private static void playGame(ArrayList strs)
	{
		
			
				Random random = new Random();
				int sent_index = random.nextInt(99);
				
				stringOut.setText(strs.get(sent_index).toString());
			
		
	}

	public static void main(String args[]) throws IOException 
	{	
		easy = true;
		if (easy == true) 
		{
			File f = new File("C:\\Users\\batman\\eclipse-workspace\\typegame\\src\\easyStr.txt");
			BufferedReader scane = new BufferedReader(new FileReader(f));
			
			String line = scane.readLine();
			
			while(line != null ) 
			{
				line = scane.readLine();
				easyStr.add(line);
			}
			for (int i = 0; i < easyStr.size(); i++) {
				System.out.println(easyStr.get(i));
			}
			
			scane.close();
		}
		registerListeners();
		typeGame(easyStr);
		
	}

}