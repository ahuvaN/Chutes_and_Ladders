package chutesAndLadders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {

	private BoardSquare[][] boardSlots;
	private BufferedImage image;

	public Board() {
		try {
			image = ImageIO.read(new File("board.jpg"));
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

	/**
	 * @param chutesAndLadders TODO
	 * 
	 */
	public void checkBoardMethod(ChutesAndLadders chutesAndLadders) {
		boolean found = false;
		int row = chutesAndLadders.current.getRow();
		int col = chutesAndLadders.current.getCol();
	
		if (chutesAndLadders.logic.checkSnake(chutesAndLadders.current.getPosition())) {
			JOptionPane.showMessageDialog(chutesAndLadders,
					"OH NO! YOU HIT A SNAKE - GOING DOWN...!",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("snake.gif"));
			found = true;
		} else if (chutesAndLadders.logic.checkLadder(chutesAndLadders.current.getPosition())) {
			JOptionPane.showMessageDialog(chutesAndLadders,
					"YAY! YOU HIT A LADDER - GOING UP...!",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("ladder.jpe"));
			found = true;
		}
	
		// repaint piece
	
		if (found) {
			removeImage(chutesAndLadders.current.getImage(), row, col);
	
			addImage(chutesAndLadders.current.getImage(), chutesAndLadders.current.getRow(),
					chutesAndLadders.current.getCol());
		}
	}
}