package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayMusic {
	public static void playMusic(String fp, boolean p) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File music = new File(fp);
		AudioInputStream aud = AudioSystem.getAudioInputStream(music);
		Clip clip = AudioSystem.getClip();
		if(music.exists() && p == true) {
			
			clip.open(aud);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		if(p==false) {
			clip.stop();
		}
		
	}
	
}
