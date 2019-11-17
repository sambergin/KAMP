package typegame;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class typegame {

	public static void main(String args[]) throws IOException {
		ArrayList<String> easyStr = new ArrayList<String>();
		ArrayList<String> normStr = new ArrayList<String>();
		ArrayList<String> hardStr = new ArrayList<String>();
		boolean easy = true;
		if (easy == true) {
			String str;
			File f = new File("typegame/src/easyStr.txt");
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
	}

}