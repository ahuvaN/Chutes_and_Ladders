package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ChutesAndLadders extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton spinButton;
	private ImageIcon[] photos;
	private Board board;
	private GameLogic logic;
	private Player[] players;
	private Player current;
	private JLabel playersTurn;
	private JLabel playersImg;
	private JPanel panel;
	private ImageIcon[] pieces;
	private Random random;

	public ChutesAndLadders() throws IOException {
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

		JPanel playerPanel = new JPanel(new GridLayout(2, 1));
		playerPanel.setBackground(Color.BLACK);
		playersTurn = new JLabel("", JLabel.CENTER);
		playersTurn.setFont(font);
		playersTurn.setForeground(Color.WHITE);
		playersTurn.setVerticalAlignment(JLabel.BOTTOM);
		playerPanel.add(playersTurn);

		JLabel turn = new JLabel("turn", JLabel.CENTER);
		turn.setFont(font);
		turn.setForeground(Color.WHITE);
		turn.setVerticalAlignment(JLabel.TOP);
		playerPanel.add(turn);

		panel.add(playerPanel);

		playersImg = new JLabel();
		playersImg.setHorizontalAlignment(JLabel.CENTER);
		panel.add(playersImg);

		spinButton = new JButton();
		spinButton.setBorder(BorderFactory.createLineBorder(Color.black));
		spinButton.setBackground(Color.BLACK);
		spinButton.setIcon(new ImageIcon(getClass().getResource("/ROLL.png")));
		spinButton.addActionListener(this);
		photos = new ImageIcon[] {
				new ImageIcon(getClass().getResource("/#1.png")),
				new ImageIcon(getClass().getResource("/#2.png")),
				new ImageIcon(getClass().getResource("/#3.png")),
				new ImageIcon(getClass().getResource("/#4.png")),
				new ImageIcon(getClass().getResource("/#5.png")),
				new ImageIcon(getClass().getResource("/#6.png")) };

		panel.add(spinButton);

		add(panel, BorderLayout.LINE_START);

		pieces = new ImageIcon[] {
				new ImageIcon(getClass().getResource("/red.png")),
				new ImageIcon(getClass().getResource("/blue.png")),
				new ImageIcon(getClass().getResource("/green.png")),
				new ImageIcon(getClass().getResource("/yellow.png")),
				new ImageIcon(getClass().getResource("/orange.png")),
				new ImageIcon(getClass().getResource("/purple.png")) };

	}

	public void setPlayers(String[] playerNames) {
		players = new Player[playerNames.length];

		int x = 0;
		for (int i = 0; i < playerNames.length; i++) {
			players[i] = new Player(playerNames[i], pieces[i].getImage(), x++);
		}

		current = players[0];

		playersTurn.setText(current.getName() + "'s");

		playersImg.setIcon(new ImageIcon(current.getImage()));

		logic = new GameLogic(players);
	}

	private void displayWinner() {
		// playSound("sound.wav");
		int again = JOptionPane.showConfirmDialog(this, "CONGRAGULATIONS! "
				+ current.getName() + " WINS!!!! \nDo you want to play again?",
				"Chutes and Ladders", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, new ImageIcon("smile.jpeg"));

		if (again == JOptionPane.YES_OPTION) {
			dispose();
			// new GameMenu().setVisible(true); -------------NEED TO FIX
			// THIS-------------

		} else {
			JOptionPane.showMessageDialog(this,
					"HAVE A GOOD DAY! \nTHANK YOU FOR PLAYING",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("bye.png"));
			dispose(); // close the window
		}
	}

	private void checkBoard() {
		boolean found = false;
		int row = current.getRow();
		int col = current.getCol();

		if (logic.checkSnake(current.getPosition())) {
			JOptionPane.showMessageDialog(this,
					"OH NO! YOU HIT A SNAKE - GOING DOWN...!",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("snake.gif"));
			found = true;
		} else if (logic.checkLadder(current.getPosition())) {
			JOptionPane.showMessageDialog(this,
					"YAY! YOU HIT A LADDER - GOING UP...!",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("ladder.jpe"));
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
		random = new Random();
		Timer timer = new Timer(3000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				spinButton.setIcon(photos[random.nextInt(7)]);
			}

		});

		return random.nextInt(6) + 1;

	}

	// public void playSound(final String file) {
	// new Thread(new Runnable() {
	//
	// public void run() {
	// Applet.newAudioClip(getClass().getResource(file)).play();
	// }
	// }).start();
	// }

	public void actionPerformed(ActionEvent e) {
		int value = rollDice();
		spinButton.setIcon(photos[value - 1]);
		if (current.getCol() != -1) {
			board.removeImage(current.getImage(), current.getRow(),
					current.getCol());
		}
		logic.turn(value);

		board.addImage(pieces[current.getNum()].getImage(), current.getRow(),
				current.getCol());
		checkBoard();

		if (current.getRow() <= 0 && current.getCol() <= 0) {
			displayWinner();
		}

		current = logic.switchPlayer();
		playersImg.setIcon(new ImageIcon(current.getImage()));
		playersTurn.setText(current.getName() + "'s");

	}

}
