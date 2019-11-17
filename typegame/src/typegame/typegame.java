package typegame;

import java.util.ArrayList;
import java.util.Scanner;
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
	//private static void initComponents()
	//{
	private static JFrame base = new JFrame();
	private static JPanel baseP = new JPanel();
		
	private static JLabel showScore = new JLabel("score");
	private static JTextArea stringOut = new JTextArea("YEE",1,200);
	private static JTextField inputArea = new JTextField("", 200);
	private static JButton subButton = new JButton("ENTER");
    
	private static void initComponents() 
	{
    base.getContentPane().setBackground(Color.MAGENTA);
        
    base.setDefaultCloseOperation(EXIT_ON_CLOSE);
    base.setSize(new Dimension(480, 360));
        
    base.setVisible(true);
    base.setLayout(new BorderLayout());
        
    Border borderInput = BorderFactory.createLineBorder(Color.BLUE, 5);
    inputArea.setBorder(borderInput);
        
    Border borderOutput = BorderFactory.createLineBorder(Color.GREEN, 5);
    stringOut.setBorder(borderOutput);
        
    inputArea.setSize(200,20);
    stringOut.setSize(200,20);
        
    baseP.setLayout(new BoxLayout(baseP, BoxLayout.Y_AXIS));
    baseP.add(stringOut);
    baseP.add(inputArea);
    baseP.add(subButton);
        
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
					System.out.println("\nyay\n");
					playGame(easyStr);
				}
				else
				{
					System.out.println("\nnop\n");
					inputArea.setForeground(Color.RED);
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
	}

	private static void playGame(ArrayList strs)
	{
		
	}

	public static void main(String args[]) throws IOException 
	{

		
		boolean easy = true;
		if (easy == true) 
		{
			File f = new File("C:/Users/Maddie/cp104-master/cp213/KAMP/src/easyStr.txt");
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