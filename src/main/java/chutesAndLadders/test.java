package chutesAndLadders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {

	public static void main(String[] args) {
		
		try {
			BufferedImage img = ImageIO.read(new File("redPiece.png"));
			BufferedImage img2 = ImageIO.read(new File("bluePiece.jpg"));

			Player one = new Player("A", 1, img);
			Player two = new Player("B", 2, img2);
			one.changePosition(8, 2);
			PlayTheGame logic = new PlayTheGame(one, two);
			logic.turn(3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
