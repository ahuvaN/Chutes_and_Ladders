package chutesAndLadders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {

	public static void main(String[] args) throws IOException {
		ChutesAndLadders c = new ChutesAndLadders("Ahuva", "Rena");
		BufferedImage img = ImageIO.read(new File("red.png"));
		BufferedImage img2 = ImageIO.read(new File("blue.png"));
		
		Player current = new Player("Ahuva", 1, img);

		PlayTheGame logic = new PlayTheGame(current, new Player("Rena", 2, img2));
		current.changePosition(0, 2);
		logic.turn(5);
	}

}
