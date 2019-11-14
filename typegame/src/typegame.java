package typegame;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class typegame extends javax.swing.JFrame{
	
	public static int score;

	
	public static void typeGame() {
		initComponents();
		
		
	}
	
	private static void initComponents() {
		JFrame base = new JFrame();
		
        JLabel showScore = new JLabel("score");
        JTextArea stringOut = new JTextArea();
        JTextArea in = new JTextArea();
        
        base.getContentPane().setBackground(Color.MAGENTA);
        
        base.setDefaultCloseOperation(EXIT_ON_CLOSE);
        base.setSize(new Dimension(1920, 1080));
        base.setVisible(true);
        base.setLayout(null);
        
	}



	public static void main(String args[]) throws IOException {
		/*
		ArrayList<String> easyStr = new ArrayList<String>();
		ArrayList<String> normStr = new ArrayList<String>();
		ArrayList<String> hardStr = new ArrayList<String>();
		boolean easy = true;
		if (easy == true) {
			String str;
			File f = new File("/typegame/src/easyStr.txt");
			BufferedReader scane = new BufferedReader(new FileReader(f));
			
			String line = scane.readLine();
			
			while(line != null ) {
				line = scane.readLine();
				easyStr.add(line);
			}
			for (int i = 0; i < easyStr.size(); i++) {
				System.out.println(easyStr.get(i));
			}
			
			scane.close();
		}
		*/
		typeGame();
		
	}

}