package screens;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage image;
	
	public BufferedImage loadImage(String s) throws IOException{
		image = ImageIO.read(getClass().getResource(s));
		return image;
	}
}
