package chutesAndLadders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Board extends JPanel {

	private JLabel[][] boardSlots;
	private BufferedImage image;

	public Board() {
		try {
			image = ImageIO.read(new File("board.jpg"));
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		GridLayout layout = new GridLayout(10, 10);
		setLayout(layout);

		boardSlots = new JLabel[10][10];

		Border border = BorderFactory.createLineBorder(Color.BLACK);

		for (int row = 0; row < boardSlots.length; row++) {
			for (int col = 0; col < boardSlots[row].length; col++) {
				boardSlots[row][col] = new JLabel();
				boardSlots[row][col].setBackground(new Color(0, 0, 0, 0));
				boardSlots[row][col].setBorder(BorderFactory
						.createLineBorder(Color.WHITE));
				add(boardSlots[row][col]);
			}
		}
		boardSlots[6][4].setIcon(new ImageIcon("#1.png"));
		boardSlots[6][4].setIcon(new ImageIcon("#2.png"));

		boardSlots[6][4].setHorizontalAlignment(JLabel.CENTER);

	}

	public void setIconPieces(int row, int column) {
		boardSlots[row][column].setIcon(new ImageIcon("redPiece.png"));
		boardSlots[row][column].setHorizontalAlignment(JLabel.CENTER);

		// boardSlots[row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE));
		add(boardSlots[row][column]);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}