package game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void init() {
		
		try {
			soundMap.put("target_hit", new Sound("res/Target-Hit.ogg"));
			soundMap.put("target_miss", new Sound("res/miss_click.ogg"));
			soundMap.put("menu_select", new Sound("res/button-pressed.ogg"));
			musicMap.put("music", new Music("res/bensound-moose.ogg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
	
}
