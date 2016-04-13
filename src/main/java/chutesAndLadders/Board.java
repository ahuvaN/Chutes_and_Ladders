package chutesAndLadders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private BoardSquare[][] boardSlots;
	private BufferedImage image;

	public Board() {
		try {
			image = ImageIO.read(getClass().getResource("/game_board.png"));
		} catch (IOException e) {

		}
		GridLayout layout = new GridLayout(10, 10);
		setLayout(layout);

		boardSlots = new BoardSquare[10][10];

		for (int row = 0; row < boardSlots.length; row++) {
			for (int col = 0; col < boardSlots[row].length; col++) {
				boardSlots[row][col] = new BoardSquare();
				boardSlots[row][col].setBackground(new Color(0, 0, 0, 0));
				add(boardSlots[row][col]);
			}
		}
	}

	public void addImage(Image img, int row, int col) {
		boardSlots[row][col].addPlayer(img);
		boardSlots[row][col].repaint();
	}

	public void removeImage(Image img, int row, int col) {
		boardSlots[row][col].removePlayer(img);
		boardSlots[row][col].repaint();
	}

	public BoardSquare getSquare(int row, int col) {
		return boardSlots[row][col];
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}