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
import javax.swing.JOptionPane;

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
		BufferedImage img = ImageIO.read(new File("red.png"));
		BufferedImage img2 = ImageIO.read(new File("blue.png"));
		current = new Player(playerOneName, 1, img);

		logic = new PlayTheGame(current, new Player(PlayerTwoName, 2, img2));
	}

	ActionListener buttonListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {

			int value = rollDice();
			spinButton.setIcon(new ImageIcon(photos[value - 1]));
			if (current.getPosition().getCol() != -1) {
				board.removeImage(current.getImage(), logic.getCurrent()
						.getPosition().getRow(), logic.getCurrent()
						.getPosition().getCol());
			}
			img = logic.turn(value);

			board.addImage(img, logic.getCurrent().getPosition().getRow(),
					logic.getCurrent().getPosition().getCol());
			checkBoard();

			if (logic.getCurrent().getPosition().getRow() <= 0
					&& logic.getCurrent().getPosition().getCol() <= 0) {
				displayWinner();
			}

			checkBoard();

			current = logic.switchPlayer();
		}
	};

	private void displaySnakeMessage() {
		JOptionPane.showMessageDialog(this,
				"OH NO! YOU HIT A SNAKE - GOING DOWN...!");
	}

	private void displayLadderMessage() {
		JOptionPane.showMessageDialog(this,
				"YAY! YOU HIT A LADDER - GOING UP...!");
	}

	private void displayWinner() {

		int again = JOptionPane.showConfirmDialog(this, "CONGRAGULATIONS! "
				+ current.getName() + " WINS!!!! \nDo you want to play again?",
				"Chutes and Ladders", JOptionPane.YES_NO_OPTION);

		if (again == JOptionPane.YES_OPTION) {
			dispose();
			new ChooseNumPlayers().setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this,
					"HAVE A GOOD DAY! \nTHANK YOU FOR PLAYING");
			dispose(); // close the window
		}
	}

	private void checkBoard() {
		boolean found = false;
		int row = current.getPosition().getRow();
		int col = current.getPosition().getCol();

		logic.checkSnakeLadder(current.getPosition());

		if (logic.isSnake() == true) {
			displaySnakeMessage();
			logic.setSnake(false);

			board.removeImage(current.getImage(), row, col);
			found = true;

		} else if (logic.isLadder() == true) {
			displayLadderMessage();
			logic.setLadder(false);

			board.removeImage(current.getImage(), row, col);
			found = true;
		}

		// repaint piece

		if (found) {
			board.addImage(img, logic.getCurrent().getPosition().getRow(),
					logic.getCurrent().getPosition().getCol());

		}
	}

	private int rollDice() {
		Random random = new Random();
		int val = random.nextInt(6) + 1;
		return val;

	}

	/*
	 * public void music() throws UnsupportedAudioFileException, IOException {
	 * 
	 * File file = new File("cheering.mp3"); AudioInputStream audioIn =
	 * AudioSystem.getAudioInputStream(file); Clip clip = null; try { clip =
	 * AudioSystem.getClip(); } catch (LineUnavailableException e) {
	 * 
	 * } try { clip.open(audioIn); } catch (LineUnavailableException e) {
	 * 
	 * } clip.start(); }*
	 */
}
