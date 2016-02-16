package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChutesAndLadders extends JFrame {

	private JButton spinButton;
	private String[] photos;
	private Board board;
	private PlayTheGame logic;
	private Player[] players;
	private Player current;
	private JLabel playersTurn;
	private JLabel playersImg;
	private JPanel panel;
	private Image[] pieces;

	public ChutesAndLadders(String[] playerNames) throws IOException {

		setTitle("CHUTES AND LADDERS");
		setSize(1100, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 0, 7, 7));
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(230, 400));

		Font font = new Font("Arial", Font.BOLD, 30);
		board = new Board();
		add(board, BorderLayout.CENTER);

		playersTurn = new JLabel();
		playersTurn.setHorizontalAlignment(JLabel.CENTER);
		playersTurn.setFont(font);
		playersTurn.setForeground(Color.WHITE);
		panel.add(playersTurn);

		playersImg = new JLabel();
		playersImg.setHorizontalAlignment(JLabel.CENTER);
		panel.add(playersImg);

		spinButton = new JButton();
		spinButton.setBorder(BorderFactory.createLineBorder(Color.black));
		spinButton.setBackground(Color.BLACK);
		spinButton.setIcon(new ImageIcon("ROLL.png"));
		spinButton.addActionListener(buttonListen);
		photos = new String[] { "#1.png", "#2.png", "#3.png", "#4.png",
				"#5.png", "#6.png" };

		panel.add(spinButton);

		add(panel, BorderLayout.LINE_START);

		pieces = new Image[] { ImageIO.read(new File("red.png")),
				ImageIO.read(new File("blue.png")),
				ImageIO.read(new File("green.png")),
				ImageIO.read(new File("yellow.png")),
				ImageIO.read(new File("orange.png")),
				ImageIO.read(new File("purple.png")) };

		players = new Player[playerNames.length];

		for (int i = 0; i < playerNames.length; i++) {
			players[i] = new Player(playerNames[i], pieces[i]);
		}

		current = players[0];

		playersTurn.setText("<html><div style=\"text-align: center;\">"
				+ current.getName() + "'s<br> turn</html>");
		playersImg.setIcon(new ImageIcon(current.getImage()));

		logic = new PlayTheGame(players);

	}

	ActionListener buttonListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {

			int value = rollDice();
			spinButton.setIcon(new ImageIcon(photos[value - 1]));
			if (current.getCol() != -1) {
				board.removeImage(current.getImage(), current.getRow(),
						current.getCol());
			}
			logic.turn(value);

			board.addImage(pieces[current.getNum()], current.getRow(),
					current.getCol());
			checkBoard();

			if (current.getRow() <= 0 && current.getCol() <= 0) {
				displayWinner();
			}

			current = logic.switchPlayer();
			playersImg.setIcon(new ImageIcon(current.getImage()));
			playersTurn.setText("<html><div style=\"text-align: center;\">"
					+ current.getName() + "'s<br> turn</html>");

		}
	};

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
		int row = current.getRow();
		int col = current.getCol();

		if (logic.checkSnake(current.getPosition())) {
			JOptionPane.showMessageDialog(this,
					"OH NO! YOU HIT A SNAKE - GOING DOWN...!");
			found = true;
		} else if (logic.checkLadder(current.getPosition())) {
			JOptionPane.showMessageDialog(this,
					"YAY! YOU HIT A LADDER - GOING UP...!");
			found = true;
		}

		// repaint piece

		if (found) {
			board.removeImage(current.getImage(), row, col);

			board.addImage(current.getImage(), current.getRow(),
					current.getCol());
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
