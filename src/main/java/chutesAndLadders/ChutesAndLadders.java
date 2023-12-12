package chutesAndLadders;

import java.applet.Applet;
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
	Board board;
	private PlayTheGame logic;
	private Player[] players;
	Player current;
	private JLabel playersTurn;
	private JLabel playersImg;
	private JPanel panel;
	private Image[] pieces;

	public ChutesAndLadders(String[] playerNames) throws IOException {
		initializeWindow();
		Font font = setupPanel();
		setupPlayersTurnLabel(font);
		setupPlayersImageLabel();
		setupSpinButton();
		piecesImage();
		initializePlayers(playerNames);
		currentPlayerInfo();

	}

	private void currentPlayerInfo() {
		current = players[0];

		playersTurn.setText("<html><div style=\"text-align: center;\">"
				+ current.getName() + "'s<br> turn</html>");

		playersImg.setIcon(new ImageIcon(current.getImage()));

		logic = new PlayTheGame(players);
	}

	private void initializePlayers(String[] playerNames) {
		players = new Player[playerNames.length];

		int x = 0;
		for (int i = 0; i < playerNames.length; i++) {
			players[i] = new Player(playerNames[i], pieces[i], x++);
		}
	}

	private void piecesImage() throws IOException {
		pieces = new Image[] { ImageIO.read(new File("red.png")),
				ImageIO.read(new File("blue.png")),
				ImageIO.read(new File("green.png")),
				ImageIO.read(new File("yellow.png")),
				ImageIO.read(new File("orange.png")),
				ImageIO.read(new File("purple.png")) };
	}

	private void setupSpinButton() {
		spinButton = new JButton();
		spinButton.setBorder(BorderFactory.createLineBorder(Color.black));
		spinButton.setBackground(Color.BLACK);
		spinButton.setIcon(new ImageIcon("ROLL.png"));
		spinButton.addActionListener(buttonListen);
		photos = new String[] { "#1.png", "#2.png", "#3.png", "#4.png",
				"#5.png", "#6.png" };

		panel.add(spinButton);

		add(panel, BorderLayout.LINE_START);
	}

	private void setupPlayersImageLabel() {
		playersImg = new JLabel();
		playersImg.setHorizontalAlignment(JLabel.CENTER);
		panel.add(playersImg);
	}

	private void setupPlayersTurnLabel(Font font) {
		playersTurn = new JLabel();
		playersTurn.setHorizontalAlignment(JLabel.CENTER);
		playersTurn.setFont(font);
		playersTurn.setForeground(Color.WHITE);
		panel.add(playersTurn);
	}

	private Font setupPanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 0, 7, 7));
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(230, 400));

		Font font = new Font("Arial", Font.BOLD, 30);
		board = new Board();
		add(board, BorderLayout.CENTER);
		return font;
	}

	private void initializeWindow() {
		setTitle("CHUTES AND LADDERS");
		setSize(1100, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);
	}

	ActionListener buttonListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {

			int value = rollDice();
			spinButton.setIcon(new ImageIcon(photos[value - 1]));
			if (current.getCol() != -1) {
				board.removeImage(current.getImage(), current.getRow(),
						current.getCol());
			}
			logic.current.turn(value);

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
		playSound("sound.wav");
		int again = JOptionPane.showConfirmDialog(this, "CONGRAGULATIONS! "
				+ current.getName() + " WINS!!!! \nDo you want to play again?",
				"Chutes and Ladders", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, new ImageIcon("smile.jpeg"));

		if (again == JOptionPane.YES_OPTION) {
			dispose();
			new ChooseNumPlayers().setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this,
					"HAVE A GOOD DAY! \nTHANK YOU FOR PLAYING",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("bye.png"));
			dispose(); // close the window
		}
	}

	private void checkBoard() {
		logic.checkboardMethod(this);
	}

	private int rollDice() {
		Random random = new Random();
		return random.nextInt(6) + 1;
	}

	public void playSound(final String file) {
		new Thread(new Runnable() {

			public void run() {
				Applet.newAudioClip(getClass().getResource(file)).play();
			}
		}).start();
	}

}
