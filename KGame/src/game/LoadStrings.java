package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import game.Game.DIFF;

public class LoadStrings {
	
	public static ArrayList<String> strArr = new ArrayList<String>();
	Game game;
	public LoadStrings(Game game) {
		this.game = game;
	}
	
	public void setDiff() throws IOException {
		if (game.diff == DIFF.easy) 
		{
			File f = new File("res/easyStr.txt");
			BufferedReader scane = new BufferedReader(new FileReader(f));
			
			String line = scane.readLine();
			
			while(line != null ) 
			{
				line = scane.readLine();
				strArr.add(line);
			}
			
			scane.close();
			
		} else if (game.diff == DIFF.medium) {
			File f = new File("res/normStr.txt");
			BufferedReader scane = new BufferedReader(new FileReader(f));
			
			String line = scane.readLine();
			
			while(line != null ) 
			{
				line = scane.readLine();
				strArr.add(line);
			}
			
			scane.close();
			
		} else if (game.diff == DIFF.hard) {
			File f = new File("res/hardStr.txt");
			BufferedReader scane = new BufferedReader(new FileReader(f));
			
			String line = scane.readLine();
			
			while(line != null ) 
			{
				line = scane.readLine();
				strArr.add(line);
			}
			
			scane.close();
	
		}
	}
	
	public ArrayList<String> getList() {
		try {
			setDiff();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strArr;
	}
	
}
