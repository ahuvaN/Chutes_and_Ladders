package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ChutesAndLadders extends JFrame {

	private JButton spinButton;
	private String[] photos;
	private Board board;
	private PlayTheGame logic;
	private Player current;
	private Image img;

	public ChutesAndLadders(String playerOneName, String PlayerTwoName)
			throws IOException {

		setTitle("CHUTES AND LADDERS");
		setSize(1100, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		board = new Board();
		add(board, BorderLayout.CENTER);

		board = new Board();
		add(board, BorderLayout.CENTER);

		spinButton = new JButton();
		spinButton.setIcon(new ImageIcon("ROLL.png"));
		spinButton.addActionListener(buttonListen);
		photos = new String[] { "#1.png", "#2.png", "#3.png", "#4.png",
				"#5.png", "#6.png" };

		add(spinButton, BorderLayout.LINE_START);
		BufferedImage img = ImageIO.read(new File("redPiece.png"));
		BufferedImage img2 = ImageIO.read(new File("bluePiece.jpg"));
		current = new Player(playerOneName, 1, img);

		logic = new PlayTheGame(current, new Player(PlayerTwoName, 2, img2));

	}

	ActionListener buttonListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {

			int value = rollDice();
			spinButton.setIcon(new ImageIcon(photos[value - 1]));
			// int row = current.getPosition().getRow();
			// int col = current.getPosition().getCol();
			if (current.getPosition().getCol() != -1) {
				board.removeImage(current.getImage(), logic.getCurrent()
						.getPosition().getRow(), logic.getCurrent()
						.getPosition().getCol());
			}
			img = logic.turn(value);
			board.addImage(img, logic.getCurrent().getPosition().getRow(),
					logic.getCurrent().getPosition().getCol());
			current = logic.switchPlayer();

		}
	};

	public int rollDice() {
		Random random = new Random();
		int val = random.nextInt(6) + 1;
		return val;

	}

}
