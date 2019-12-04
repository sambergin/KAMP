package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class HighScores{
	
	public static ArrayList<String> mouseHS = new ArrayList<String>();
	Game game;
	public HighScores(Game game) {
		this.game = game;
	}
	
	public static void newMHS(int score) throws IOException {
		readMHS();
		PrintWriter pw = new PrintWriter(new FileWriter("res/mouse_HS.txt"));
		int c = 0;
		for(int i = 0; i < 5 && c < mouseHS.size(); i++) {
			if(score > Integer.parseInt(mouseHS.get(i))) {
				pw.write(score);
			}
			else {
				pw.write(mouseHS.get(c));
				c++;
			}
		}
		pw.close();
	}
	
	public static void readMHS() throws IOException {
		mouseHS.clear();
		File f = new File("res/mouse_HS.txt");
		BufferedReader scan = new BufferedReader(new FileReader(f));
		String line = scan.readLine();
		while(line != null) {	
			line = scan.readLine();
			mouseHS.add(line);
		}
		scan.close();		
	}
	
	public ArrayList<String> getList() {	
		try {
			readMHS();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mouseHS;
	}

}
